package com.mourato.aquarium.model;

import android.content.Context;

import com.mourato.aquarium.R;

public class SmallBubble extends Bubble {

    private int sprites[];
    public SmallBubble(Context context) {
        super(context);
        setX(450);
        sprites = new int[4];
        sprites[0] = R.drawable.bubble_1;
        sprites[1] = R.drawable.bubble_2;
        sprites[2] = R.drawable.bubble_3;
        sprites[3] = R.drawable.bubble_4;
        setBubble(R.drawable.bubble_1);
        super.setSprites(sprites);
    }

}
