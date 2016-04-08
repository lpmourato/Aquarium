package com.mourato.aquarium.model;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.mourato.aquarium.R;

public class Food extends View{

    public static final float TOUCH_TOLERANCE = 0.8f;
    private static final long INTERVAL_TIMER = 250;
    private int xFoodPos;
    private int yFoodPos;
    private FoodType foodType;
    private PathFish pathFish;
    private int sprites[];
    private int sprite = 0;
    private Bitmap bitmap;
    private boolean available;
    private Timer timer;
    
    public Food(Context context) {
        super(context);
        this.foodType = FoodType.BIG_FISH_FOOD;
        setBitmap(R.drawable.food_1);
        sprites = new int[3];
        sprites[0] = R.drawable.food_1;
        sprites[1] = R.drawable.food_2;
        sprites[2] = R.drawable.food_3;
        timer = new Timer();
        timer.schedule(task, INTERVAL_TIMER, INTERVAL_TIMER);
        task.run();
        setAvailable(true);
    }

    private void setBitmap(int foodId) {
        bitmap = BitmapFactory.decodeResource(getResources(), foodId);        
    }

    public PathFish getPathFish() {
        return pathFish;
    }
    public void setPathFish(PathFish pathFish) {
        this.pathFish = pathFish;
    }
    public FoodType getFoodType() {
        return foodType;
    }
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
    
    public int getXFoodPos() {
        return xFoodPos;
    }
    public void setxFoodPos(int xFoodPos) {
        this.xFoodPos = xFoodPos + 50;
    }
    public int getYFoodPos() {
        return yFoodPos;
    }
    public void setyFoodPos(int yFoodPos) {
        this.yFoodPos = yFoodPos;
    }
    
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Rect getBounds(){
        return new Rect(getXFoodPos(), getYFoodPos(), getXFoodPos() + 20, getYFoodPos() + 20);
    }
    
    @Override
    public String toString() {
        return "Food "+ this.foodType;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
    }
    
    public void drawFood(Canvas canvas) {
        canvas.drawBitmap(bitmap, xFoodPos, yFoodPos, null);
    }
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            switch (sprite) {
                case 0:
                    setBitmap(sprites[sprite]);
                    break;
                case 1:
                    setBitmap(sprites[sprite]);
                    break;
                case 2:
                  setBitmap(sprites[sprite]);
                  sprite = -1;
                  break;
                default:
                    break;
            }
            sprite++;
        }
    };
    
}
