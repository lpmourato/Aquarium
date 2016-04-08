package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.PathFish;

public class EatingFoodState implements FishState {

    private PathFish pathFish;
    public EatingFoodState(PathFish pathFish2) {
        this.pathFish = pathFish2;
    }
    @Override
    public void swim(Fish fish, Canvas canvas) {
        fish.setState(new NoHungryState(pathFish));
//        pathFish.moveFish(canvas, PathFish.FISH_STEP);
    }
    @Override
    public PathFish getPath() {
        return pathFish;
    }

}
