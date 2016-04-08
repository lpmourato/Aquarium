package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.Food;
import com.mourato.aquarium.model.PathFish;
import com.mourato.aquarium.model.RepositoryFoods;

public class SwimmingRandomicallyState implements FishState {
    private Food food;
    private PathFish pathFish;
    
    public SwimmingRandomicallyState(PathFish pathFish2) {
        this.pathFish = pathFish2;
    }
    @Override
    public void swim(Fish fish, Canvas canvas) {
        food = RepositoryFoods.getInstance().getFood(fish.getFood().getFoodType());
        fish.setIsFat(false);
        pathFish.updateFishReference(fish);
        if(food != null){
            RepositoryFoods.getInstance().setFoodUnavailable(food);
            fish.setState(new SwimmingToFoodState(pathFish));
            fish.setFood(food);
        } else if(!pathFish.hasPath()){
            pathFish.generateNewPath(null);
        }
        pathFish.moveFish(canvas, PathFish.FISH_STEP);
    }
    @Override
    public PathFish getPath() {
        return pathFish;
    }

}
