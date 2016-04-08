package com.mourato.aquarium.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {

    public static void makeToast(Context ctx, int res) {
        Toast.makeText(ctx, res, Toast.LENGTH_SHORT).show();
    }
    
    public static void makeToast(Context ctx, String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }
}
