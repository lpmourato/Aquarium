package com.mourato.aquarium.helpers;

import android.graphics.Canvas;
import android.view.View;
import android.view.View.DragShadowBuilder;

public class DragShadowBuilderView extends DragShadowBuilder {

    public DragShadowBuilderView(View view) {
        super(view);
    }
    @Override
    public void onDrawShadow(Canvas canvas) {
        canvas.rotate(-45, getView().getPivotX(), getView().getPivotY());
        super.onDrawShadow(canvas);
    }
}
