
package com.mourato.aquarium.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;

import com.mourato.aquarium.helpers.ScreenHelper;

public class RepositoryAquarium {

    private static final int SIZE = 4;

    private Context context;

    protected static List<Fish> list;

    protected static int displayH = 0;

    protected static int displayW = 0;

    public RepositoryAquarium(Context context) {
        displayW = ScreenHelper.getScreenSize(context).x;
        displayH = ScreenHelper.getScreenSize(context).y - 235;
        this.context = context;
        list = new ArrayList<Fish>();
        load();
    }

    private void load() {
        for (int i = 0; i < SIZE; i++) {
            Fish fis = FishFactory.create(context, getFishType(i));
            list.add(fis);
        }
    }

    public List<Fish> listAquariumPopulation() {
        return list;
    }

    private FishType getFishType(int index) {
        int diff = index % 2;
        if (diff == 0) {
            return FishType.SMALL;
        }
        return FishType.BIG;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive. The
     * difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     * 
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    static class FishFactory {
        // the main idea behind a factory is to make creation easier and simple
        // to
        // the client. The createFish method is to complex. Make it easier.
        // here is my suggestion:
        private static int MIN = 150; // could be device's display width min and
                                     // max

        private static Random rand = new Random();

        public static Fish create(Context ctx, FishType type) {
            int x = randInt(MIN, displayW - MIN - 300);
            int y = randInt(MIN, displayH - MIN - 100);
            switch (type) {
                case BIG:
                    return new BigFish(ctx, x, y);
                case SMALL:
                    return new SmallFish(ctx, x, y);
                default:
                    // default fish - only if type is null
                    return new SmallFish(ctx, x, y);
            }
        }

        public static Point getFishPosition() {
            int x = rand.nextInt(displayW - MIN);
            int y = rand.nextInt(displayH - MIN);
            int size = list.size();
            if (size == 0)
                return new Point(x, y);

            Fish fishAdded = list.get(size - 1);
            int wFisheAdded = fishAdded.getBitmap().getWidth();
            int hFisheAdded = fishAdded.getBitmap().getHeight();
            int xPosFishAdded = fishAdded.getLeft();
            int yPosFishAdded = fishAdded.getTop();
            int xPos = wFisheAdded + x + xPosFishAdded;
            xPos = xPos < displayW ? xPos : (xPos - wFisheAdded - xPosFishAdded);

            int yPos = hFisheAdded + yPosFishAdded + 10;
            yPos = yPos < displayH ? yPos : (yPos - hFisheAdded - yPosFishAdded);

            return new Point(xPos, yPos);
        }

        public static Fish createFish(FoodType food, Context context, int id, int x, int y,
                String idRes) {

            if (FoodType.BIG_FISH_FOOD.equals(food)) {
                return new BigFish(context, x, y);
            } else if (FoodType.SMALL_FISH_FOOD.equals(food)) {
                return new SmallFish(context, x, y);
            }
            return new BigFish(context, x, y);
        }

    }

}
