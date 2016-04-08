package com.mourato.aquarium.model;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FishMenu extends LinearLayout {

    public FishMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    
    public Rect getBounds(){
        Rect rect = new Rect(this.getLeft(), this.getTop(), 
                this.getRight(), this.getBottom());
        return rect;
    }

}
