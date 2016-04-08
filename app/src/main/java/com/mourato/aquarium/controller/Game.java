
package com.mourato.aquarium.controller;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Debug;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mourato.aquarium.model.BigBubble;
import com.mourato.aquarium.model.BigFish;
import com.mourato.aquarium.model.Bubble;
import com.mourato.aquarium.model.Crab;
import com.mourato.aquarium.model.Fish;
import com.mourato.aquarium.model.Food;
import com.mourato.aquarium.model.FoodType;
import com.mourato.aquarium.model.PathFish;
import com.mourato.aquarium.model.RepositoryAquarium;
import com.mourato.aquarium.model.RepositoryFoods;
import com.mourato.aquarium.model.SmallBubble;

public class Game extends View {

    public static final int INTERVAL_TIMER = 170;

    private Path foodPath;

    private PathFish path;

    private List<Fish> listFishes;

    private Food food;

    private PathMeasure measure;

    private FoodType foodType;

    private Handler handlerUpdateCanvas = new Handler();

    private Crab crab;
    
    private Bubble bubble, bigBubble;
    
    public void updateFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        listFishes = new RepositoryAquarium(getContext()).listAquariumPopulation();
        foodPath = new Path();
        measure = new PathMeasure(foodPath, false);
        path = new PathFish(new BigFish(getContext(), 0, 0));

        updateFoodType(FoodType.BIG_FISH_FOOD);
        crab = new Crab(getContext(), null);
        bubble = new SmallBubble(getContext());
        bigBubble = new BigBubble(getContext());
        handlerUpdateCanvas.postDelayed(runnable, INTERVAL_TIMER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        List<Food> foods = RepositoryFoods.getInstance().getFoodList();
        bubble.drawBubble(canvas);
        bigBubble.drawBubble(canvas);
        crab.drawCrab(canvas);
        for (Food food : foods) {
            food.setScaleX(0.2f);
            food.setScaleY(0.2f);
            food.drawFood(canvas);
        }
        for (Fish fish : listFishes) {
            fish.fishThink(canvas);
        }
        logHeap();
    }

    public void createNewFood(float x, float y) {
        int size = RepositoryFoods.getInstance().getListSize();
        if (size < RepositoryFoods.SIZE) {
            food = new Food(getContext());
            food.setxFoodPos((int) x);
            food.setyFoodPos((int) y);
            food.setFoodType(foodType);

            this.foodPath.reset();
            this.foodPath.moveTo(x, y);

            this.path = new PathFish(new BigFish(getContext(), 0, 0));
            this.path.moveTo(x, y);

        }
        addFoodToRepository();
    }

    private void addFoodToRepository() {
        measure.setPath(foodPath, false);
        if (food != null) {
            this.food.setPathFish(path);
            RepositoryFoods.getInstance().addFood(food);
            PlaySoundsControl.getInstance().play("food_on_water.mp3");
            food = null;
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (Fish fish : listFishes) {
            fish.swimFast();
        }
        RepositoryFoods.getInstance().changeFoodAvailability();
        PlaySoundsControl.getInstance(getContext()).play("fish_tap.mp3");
        return false;
    }

    /**
     * Class Runnable that has the run() method to call method invalidate after
     * an interval
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handlerUpdateCanvas.postDelayed(runnable, INTERVAL_TIMER);
            invalidate();
        }
    };
    
    public void logHeap() {
        Double allocated = new Double(Debug.getNativeHeapAllocatedSize())/new Double((1048576));
        Double available = new Double(Debug.getNativeHeapSize())/1048576.0;
        Double free = new Double(Debug.getNativeHeapFreeSize())/1048576.0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        Log.d("AQUARIUM HEAP", "debug. =================================");
        Log.d("AQUARIUM HEAP", "debug.heap native: allocated " + df.format(allocated) + "MB of " + df.format(available) + "MB (" + df.format(free) + "MB free)");
        Log.d("AQUARIUM HEAP", "debug.memory: allocated: " + df.format(new Double(Runtime.getRuntime().totalMemory()/1048576)) + "MB of " + df.format(new Double(Runtime.getRuntime().maxMemory()/1048576))+ "MB (" + df.format(new Double(Runtime.getRuntime().freeMemory()/1048576)) +"MB free)");
    }

}
