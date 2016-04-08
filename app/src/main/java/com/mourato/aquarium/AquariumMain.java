package com.mourato.aquarium;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.mourato.aquarium.controller.Game;
import com.mourato.aquarium.controller.PlaySoundsControl;
import com.mourato.aquarium.helpers.DragShadowBuilderView;
import com.mourato.aquarium.model.FoodType;
import com.mourato.aquarium.model.RepositoryFoods;
import com.mourato.aquarium.model.RepositoryFoods.OnFoodRepositoryUpdated;

public class AquariumMain extends Activity implements View.OnTouchListener, View.OnDragListener,
    OnFoodRepositoryUpdated {

    private FoodType foodType;
    private Game game;
    private int potId;
    private ImageView bigPot, smallPot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlaySoundsControl.getInstance(this);
        game = (Game) findViewById(R.id.game);
        
        bigPot = (ImageView)findViewById(R.id.btn_big_fish_food);
        bigPot.setOnTouchListener(this);
        
        smallPot = (ImageView)findViewById(R.id.btn_small_fish_food);
        smallPot.setOnTouchListener(this);
        
        game.setOnDragListener(this);
        RepositoryFoods.getInstance().setOnFoodRepositoryUpdated(this);
        
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //this condition avoid food pot goes away 
        if(view instanceof Game) return super.onTouchEvent(event);
        
        if(view.getId() == R.id.btn_big_fish_food){
            foodType = FoodType.BIG_FISH_FOOD;
        } else if(view.getId() == R.id.btn_small_fish_food){
            foodType = FoodType.SMALL_FISH_FOOD;
        }
        game.updateFoodType(foodType);
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilderView shadowBuilder = new DragShadowBuilderView(view);
            view.startDrag(data, shadowBuilder, view, 0);
            potId = view.getId();
            PlaySoundsControl.getInstance().play("taking_pot.mp3");
            return true;
        } 
        return false;
    }

    @Override
    public void updateFoodPots() {
        int amountBigPot = RepositoryFoods.getInstance().getAmountFoodByType(FoodType.BIG_FISH_FOOD);
        int amountSmallPot = RepositoryFoods.getInstance().getAmountFoodByType(FoodType.SMALL_FISH_FOOD);
        if(amountBigPot > 2) {
            bigPot.setBackground(getResources().getDrawable(R.drawable.empty_food_pot));
        } else {
            bigPot.setBackground(getResources().getDrawable(R.drawable.full_pot_food));
        }
        if(amountSmallPot > 2) {
            smallPot.setBackground(getResources().getDrawable(R.drawable.empty_food_pot));
        } else {
            smallPot.setBackground(getResources().getDrawable(R.drawable.full_pot_food));
        }
        
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                //-150 +20 image diff to drop food on pot border
                game.createNewFood(event.getX()-150, event.getY()+20);
                findViewById(potId).setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                findViewById(potId).setVisibility(View.INVISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                findViewById(potId).setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }
    
    @Override
    protected void onPause() {
        PlaySoundsControl.getInstance().stopSounds();
        super.onPause();
    }
    
    @Override
    protected void onResume() {
        PlaySoundsControl.getInstance(this).startSounds();
        super.onResume();
    }

}
