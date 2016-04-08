
package com.mourato.aquarium.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.View;

import com.mourato.aquarium.R;
import com.mourato.aquarium.helpers.ViewHelper;
import com.mourato.aquarium.state.FishState;
import com.mourato.aquarium.state.SwimmingQuicklyState;
import com.mourato.aquarium.state.SwimmingRandomicallyState;

public abstract class Fish extends View {

    protected static final long INTERVAL_TIMER = 240;

    protected float scale = 0;

    Matrix matrix;

    private Food food;

    Food foodToFollow = null;

    private Bitmap bmpFish;

    private FishState fishState;

    private int sprites[];
    private int fat_sprites[];
    private boolean isFat = false;
    
    private int sprite = 0;
    
    FishDirection direction;
    
    public Fish(Context context, int x, int y) {
        super(context);
        sprites = new int[3];
        sprites[0] = R.drawable.fish_1;
        sprites[1] = R.drawable.fish_2;
        sprites[2] = R.drawable.fish_3;
        
        fat_sprites = new int[3];
        fat_sprites[0] = R.drawable.fish_4;
        fat_sprites[1] = R.drawable.fish_5;
        fat_sprites[2] = R.drawable.fish_6;
        
        setBitmap(R.drawable.fish_1);
        this.setLeft(x);
        this.setTop(y);
        matrix = new Matrix();
        setState(new SwimmingRandomicallyState(new PathFish(this)));
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FishDirection getDirection() {
        return direction;
    }

    public void setDirection(FishDirection direction) {
        this.direction = direction;
    }

    public Bitmap getBitmap() {
        return bmpFish;
    }

    public void setBitmap(Bitmap bitmap) {
        bmpFish = bitmap;
    }

    protected void setBitmap(int id) {
        bmpFish = ViewHelper.getBitmapFromFactory(getContext(), id);
    }

    public void setState(FishState state) {
        this.fishState = state;
    }

    public FishState getState() {
        return this.fishState;
    }
    
    public void setIsFat(boolean isFat) {
       this.isFat = isFat; 
    }
    
    public boolean isFat() {
        return isFat;
    }

    public void fishThink(Canvas canvas) {
        changeSprite();
        getState().swim(this, canvas);
    }

    public void swimFast() {
        this.setState(new SwimmingQuicklyState(getState().getPath()));
    }
    
    public Rect getBounds() {
        Rect rect = new Rect(getLeft(), getTop(),
                getLeft() + getBitmap().getWidth()/6, getTop() + 100 );
//        if(FishDirection.LEFT.equals(getDirection())){
//            rect = new Rect(0, 0,
//                    100, 100 );
//        }
        return rect;
    }

    private void changeSprite() {
        switch (sprite) {
            case 0:
                setBitmap(getSprites()[sprite]);
                break;
            case 1:
                setBitmap(getSprites()[sprite]);
                break;
            case 2:
                setBitmap(getSprites()[sprite]);
                sprite = -1;
                break;
            default:
                break;
        }
        sprite++;
    }

    public abstract float getScale();

    public abstract void setScale(float scale);

    public int[] getSprites() {
        return isFat ? fat_sprites : sprites;
    }

}
