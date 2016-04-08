package com.mourato.aquarium.model;

import android.content.Context;

public class SmallFish extends Fish {

    public SmallFish(Context context, int x, int y) {
        super(context, x, y);
        super.setFood(new Food(context));
        super.getFood().setFoodType(FoodType.SMALL_FISH_FOOD);
        setScale(0.4f);
    }
    
    @Override
    public String toString() {
        return "Small Fish";
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        super.scale = scale;
    }
    
}
