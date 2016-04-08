package com.mourato.aquarium.model;

import android.content.Context;

import com.mourato.aquarium.R;

public class BigBubble extends Bubble {

    private int sprites[];

    public BigBubble(Context context) {
        super(context);
        sprites = new int[4];
        sprites[0] = R.drawable.bubble_5;
        sprites[1] = R.drawable.bubble_6;
        sprites[2] = R.drawable.bubble_7;
        sprites[3] = R.drawable.bubble_8;
        setBubble(R.drawable.bubble_5);
        setX(1300);
        super.setSprites(sprites);
        super.setInterval(350);
    }

}