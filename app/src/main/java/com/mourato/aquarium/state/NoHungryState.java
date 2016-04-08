package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.controller.PlaySoundsControl;
import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.PathFish;

public class NoHungryState implements FishState {
    
    private static final long TIME_WITHOUT_HUNGRY = 10000;
    private PathFish pathFish;
    private long time;
    public NoHungryState(PathFish pathFish) {
        this.pathFish = pathFish;
        time = System.currentTimeMillis();
        PlaySoundsControl.getInstance().play("eating.mp3");
    }
    @Override
    public void swim(Fish fish, Canvas canvas) {
        long pastTime = System.currentTimeMillis() - time;
        if(pastTime > TIME_WITHOUT_HUNGRY){
            fish.setIsFat(false);
            fish.setState(new SwimmingRandomicallyState(pathFish));
        } else {
            fish.setIsFat(true);
            pathFish.updateFishReference(fish);
            pathFish.generateNewPath(null);
        }
        pathFish.moveFish(canvas, PathFish.FISH_STEP);
    }
    @Override
    public PathFish getPath() {
        return pathFish;
    }

}
