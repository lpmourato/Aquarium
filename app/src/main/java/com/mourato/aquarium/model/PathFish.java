package com.mourato.aquarium.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;

import com.mourato.aquarium.helpers.ScreenHelper;

public class PathFish extends Path {
    public static float FISH_STEP = 5.3f;
    public static float FISH_STEP_QUICKLY = 18.3f;
    private static final int DIFF = 150;
    private float lengthPath;
    private PathMeasure measure;
    private float foodDistance;
    private float[] fishPosition;
    private float[] tan;
    private Matrix matrix;
    private Context context;
    private float value = 1f;
    private Fish fish;
    private int screenW, screenH;
    private long lastDirectionChanged;

    public PathFish(Fish fish) {
        super();
        this.fish = fish;
        this.context = fish.getContext();
        measure = new PathMeasure(this, false);
        fishPosition = new float[2];
        tan = new float[2];
        matrix = new Matrix();
        screenW = ScreenHelper.getScreenSize(context).x;
        screenH = ScreenHelper.getScreenSize(context).y;
    }
    
    public void moveFish(Canvas canvas, float step) {
        measure.setPath(this, false);
        lengthPath = measure.getLength();
        if(foodDistance < lengthPath/value){
            float offsetX = fish.getBitmap().getWidth() / 2;
            float offsetY = fish.getBitmap().getHeight() / 2;
            
            measure.getPosTan(foodDistance, fishPosition, tan);
            matrix.reset();
            
            //change fish size
            float scale = fish.getScale();
            matrix.setScale(scale, scale);

            //change fish direction
            int xPos = (int)(fishPosition[0]);
            boolean isXequal = Math.floor(xPos - fish.getLeft()) > 0 ? false : true;
            fish.setDirection(FishDirection.LEFT);
            if(fish.getLeft() < xPos && !isXequal){
                boolean isChangedDirectionFast = System.currentTimeMillis() - lastDirectionChanged 
                        > 7 ? true : false;
                if(isChangedDirectionFast){
                    fish.setDirection(FishDirection.RIGHT);
                    matrix.postScale(-1, 1);
                    lastDirectionChanged = System.currentTimeMillis();
                }
            }
            //defines the center of view
            matrix.preTranslate(-offsetX, -offsetY);
            //moves fish to new position
            matrix.postTranslate(fishPosition[0], fishPosition[1]);

            foodDistance += step;
            fish.setLeft(xPos);
            fish.setTop((int)fishPosition[1]);
        } else {
            reset();
        }
        canvas.drawBitmap(fish.getBitmap(), matrix, null);
    }
    
    public PathFish generateNewPath(Food food) {
        this.moveTo(fish.getLeft(), fish.getTop());
        if(food != null){
            value = 1f;
            this.lineTo(food.getXFoodPos(), food.getYFoodPos());
        } else {
            //variable to swin half of the path 
            value = 2;
            int fishWidth = fish.getBitmap().getWidth() / 2;
            int fishHeight= fish.getBitmap().getHeight();
            int x = RepositoryAquarium.randInt(DIFF, screenW - DIFF - fishWidth);
            int y = RepositoryAquarium.randInt(DIFF, screenH - DIFF - fishHeight);
            this.lineTo(x, y);
        }
        this.close();
        return this;
    }
    
    public PathFish generateLongPath() {
        this.moveTo(fish.getLeft(), fish.getTop());
        int fishWidth = fish.getBitmap().getWidth();
        int fishHeight= fish.getBitmap().getHeight();
        int x = screenW - DIFF - fishWidth;
        int y = screenH - DIFF - fishHeight;
        this.lineTo(x, y);
        this.close();
        return this;
    }
    
    public boolean hasPath() {
        return foodDistance == 0.0f ? false : true;
    }
    
    public Point getActualPosition(){
        return new Point((int)fishPosition[0], (int)fishPosition[1]);
    }
    
    @Override
    public void reset() {
        super.reset();
        foodDistance = 0.0f;
    }
    
    public void updateFishReference(Fish fish2) {
        this.fish = fish2;
    }
    
}
