package com.mourato.aquarium.state;

import android.graphics.Canvas;

import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.PathFish;

public interface FishState {
    public void swim(Fish fish, Canvas canvas);
    public PathFish getPath();
}
