
package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.Food;
import com.mourato.aquarium.model.PathFish;
import com.mourato.aquarium.model.RepositoryFoods;

public class SwimmingToFoodState implements FishState {

    Food foodToFollow;
    PathFish pathFish;

    long hashFood = 0l;

    public SwimmingToFoodState(PathFish pathFish2) {
        this.pathFish = pathFish2;
        this.pathFish.reset();
    }

    @Override
    public void swim(Fish fish, Canvas canvas) {
        this.foodToFollow = fish.getFood();
        if(foodToFollow != null){
            if(foodToFollow.hashCode() == hashFood && foodToFollow.getBounds().intersect(fish.getBounds())){
                RepositoryFoods.getInstance().removeFood(foodToFollow);
                fish.setState(new EatingFoodState(pathFish));
            } else if(foodToFollow.hashCode() != hashFood && hashFood > 0 ) {
                pathFish.reset();
            }else {
                pathFish.generateNewPath(foodToFollow);
            }
            hashFood = foodToFollow.hashCode();
        } else {
            pathFish.reset();
            fish.setState(new SwimmingRandomicallyState(pathFish));
        }
        pathFish.moveFish(canvas, PathFish.FISH_STEP);
    }

    @Override
    public PathFish getPath() {
        return pathFish;
    }

}
