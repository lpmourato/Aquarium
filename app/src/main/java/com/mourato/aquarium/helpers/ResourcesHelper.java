package com.mourato.aquarium.helpers;

import android.content.Context;

public class ResourcesHelper {

    public static int getResourceId(String pDrawableName, Context context) {
        int resourceId = context.getResources().getIdentifier(pDrawableName,
                "drawable", "com.example.drawaquariumpoc");

        return resourceId;
    }
 
}
