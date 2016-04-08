package com.mourato.aquarium.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.view.View;

import com.mourato.aquarium.controller.PlaySoundsControl;
import com.mourato.aquarium.helpers.ViewHelper;

public class Bubble extends View {

    protected static final long INTERVAL_TIMER = 400;
    private int sprites[];
    private int sprite;
    private Bitmap bubble;
    private Matrix matrix;
    private Handler handlerUpdateSprite = new Handler();
    private long interval;
    
    public Bubble(Context context) {
        super(context);
        setY(480);
        matrix = new Matrix();
        sprites = new int[4];
        setInterval(INTERVAL_TIMER);
        handlerUpdateSprite.postDelayed(runnable, interval);
        PlaySoundsControl.getInstance(context).bubbles();
    }
    
    public void drawBubble(Canvas canvas) {
        matrix.reset();
        matrix.setScale(0.25f, 0.25f);
        matrix.postTranslate(getX(), getY());
        canvas.drawBitmap(getBubble(), matrix, null);
    }
    
    public int[] getSprites() {
        return sprites;
    }

    public void setSprites(int[] sprites) {
        this.sprites = sprites;
    }
    
    public Bitmap getBubble() {
        return bubble;
    }

    public void setBubble(int res) {
        this.bubble = ViewHelper.getBitmapFromFactory(getContext(), res);
    }    
    
    /**
     * Class Runnable that has the run() method to call method invalidate after
     * an interval
     */
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            handlerUpdateSprite.postDelayed(runnable, interval);
            switch (sprite) {
                case 0:
                    setBubble(sprites[sprite]);
                    break;
                case 1:
                    setBubble(sprites[sprite]);
                    break;
                case 2:
                    setBubble(sprites[sprite]);
                    break;
                case 3:
                    setBubble(sprites[sprite]);
                    sprite = -1;
                    break;                    
                default:
                    break;
            }
            sprite++;
        }
    };

    public void setInterval(long interval) {
        this.interval = interval;
        
    }

}
