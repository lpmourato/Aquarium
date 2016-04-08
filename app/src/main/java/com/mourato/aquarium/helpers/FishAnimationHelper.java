
package com.mourato.aquarium.helpers;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class FishAnimationHelper {

    public static AnimationSet grow(View view, float minZoom, float maxZoom) {
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animation);
//        set.setDuration(1000);
//        set.start();
        return set;
    }
    public static void zoomInZoomOut(View view, int duration) {
        AnimatorSet aninSet = new AnimatorSet();
        Animator zoonInX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 2f);
        Animator zoonInY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 2f);
        
        Animator zoonOutX = ObjectAnimator.ofFloat(view, "scaleX", 2f, 1f);
        Animator zoonOutY = ObjectAnimator.ofFloat(view, "scaleY", 2f, 1f);

        List<Animator> zoomInList = new ArrayList<Animator>();
        zoomInList.add(zoonInX);
        zoomInList.add(zoonInY);
        
        List<Animator> zoomOutList = new ArrayList<Animator>();
        zoomOutList.add(zoonOutX);
        zoomOutList.add(zoonOutY);
        
        aninSet.playTogether(zoomInList);
        aninSet.playTogether(zoomOutList);
        aninSet.setDuration(duration);
        aninSet.start();
    }
}
