package com.mourato.aquarium.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class ViewHelper {

    public static Bitmap getBitmapFromFactory(Context context, int resId) {
        Options opts = getBitmapFactoryOptions();
        opts.inPreferredConfig = Config.ARGB_8888;
        
//        Bitmap bitmap = Bitmap.createBitmap(320, 240, Bitmap.Config.ARGB_8888);
//        bitmap.
//        bitmap.SetPixels(colorImageBuffer, 0, 320, 0, 0, 320, 240);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, opts);
//        bitmap.setWidth(320);
//        bitmap.setHeight(240);
//        bitmap.setConfig(Bitmap.Config.ARGB_8888);
        
        return bitmap;
    }
    
    private static Options getBitmapFactoryOptions() {
        Options options = new Options();
        options.inPurgeable = true;
//        options.inSampleSize = 4;
        return options;
    }
}
