package com.mourato.aquarium.model;

import android.content.Context;

public class BigFish extends Fish {

    public BigFish(Context context, int x, int y) {
        super(context, x, y);
        super.setFood(new Food(context));
        super.getFood().setFoodType(FoodType.BIG_FISH_FOOD);
        setScale(0.6f);
    }

    @Override
    public String toString() {
        return "Big Fish";
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
