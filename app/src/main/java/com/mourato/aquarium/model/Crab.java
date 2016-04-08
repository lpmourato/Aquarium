package com.mourato.aquarium.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.mourato.aquarium.R;
import com.mourato.aquarium.helpers.ViewHelper;

public class Crab extends View {

    protected static final long INTERVAL_TIMER = 240;
    private int sprites[];
    private int sprite;
    private Bitmap crab;
    private Matrix matrix;
    private float x, y = 650;
    private CrabDirection direction;
    
    public Crab(Context context, AttributeSet attrs) {
        super(context, attrs);
        sprites = new int[3];
        sprites[0] = R.drawable.crab_1;
        sprites[1] = R.drawable.crab_2;
        sprites[2] = R.drawable.crab_3;
        setCrab(R.drawable.crab_1);
        matrix = new Matrix();
        direction = CrabDirection.RIGHT;
    }
    
    public Bitmap getCrab() {
        return crab;
    }
    
    public void setCrab(int id) {
        this.crab = ViewHelper.getBitmapFromFactory(getContext(), id);
    }

    public void drawCrab(Canvas canvas) {
        changeSprite();
        boolean isRightDirection = CrabDirection.RIGHT.equals(direction);
        boolean isLeftDirection = CrabDirection.LEFT.equals(direction);
        
        if(x < 0.0f && isLeftDirection){
            direction = CrabDirection.RIGHT;
        } else if(x + getCrab().getWidth() / 4.3 > canvas.getWidth() && isRightDirection){
            direction = CrabDirection.LEFT;
        }
        if(isLeftDirection){
            x -= 6;
        } else {
            x += 5;
        }
        matrix.reset();
        matrix.setScale(0.2f, 0.2f);
        matrix.postTranslate(x, y);
        x++;
        canvas.drawBitmap(getCrab(), matrix, null);
    }
    
    private void changeSprite() {
        switch (sprite) {
            case 0:
                setCrab(sprites[sprite]);
                break;
            case 1:
                setCrab(sprites[sprite]);
                break;
            case 2:
                setCrab(sprites[sprite]);
                sprite = -1;
                break;
            default:
                break;
        }
        sprite++;
    }
    
}
