package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.PathFish;

public class SwimmingQuicklyState implements FishState {

    private PathFish pathFish;
    public SwimmingQuicklyState(PathFish pathFish) {
        this.pathFish = pathFish;
        this.pathFish.generateLongPath();
    }
    
    @Override
    public void swim(Fish fish, Canvas canvas) {
        if(!pathFish.hasPath()){
            fish.setState(new SwimmingRandomicallyState(pathFish));
        }
        pathFish.moveFish(canvas, PathFish.FISH_STEP_QUICKLY);
    }

    @Override
    public PathFish getPath() {
        return pathFish;
    }

}
