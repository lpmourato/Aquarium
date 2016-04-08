package com.mourato.aquarium.controller;

import android.content.Context;

import com.mourato.aquarium.helpers.PlaySounds;


public class PlaySoundsControl {
    private static PlaySoundsControl instance;
    private PlaySounds playSounds;
    private PlaySoundsControl(Context context) {
        playSounds = new PlaySounds(context);
    }

    public static PlaySoundsControl getInstance(Context context) {
        if(instance == null){
            instance = new PlaySoundsControl(context);
        }
        return instance;
    }
    
    public static PlaySoundsControl getInstance() {
        return instance;
    }
    
    public void startSounds() {
        playSounds.start();
    }
    
    public void play(String fileName) {
        playSounds.play(fileName);
    }

    public void bubbles() {
        playSounds.bubbles();
        
    }

    public void stopSounds() {
        playSounds.stop();
    }
    
    
}
