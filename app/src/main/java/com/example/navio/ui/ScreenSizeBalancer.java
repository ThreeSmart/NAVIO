package com.example.navio.ui;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenSizeBalancer {
    private static final int DESIGNED_SCREEN_WIDTH = 411;
    private static final int DESIGNED_SCREEN_HEIGHT = 731;

    private final int screenWidth;
    private final int screenHeight;

    public ScreenSizeBalancer(final Resources resources) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.screenHeight = displayMetrics.heightPixels;
        this.screenWidth = displayMetrics.widthPixels;
    }

    public int getHeightFor(final int value) {
        return ((screenHeight * value) / DESIGNED_SCREEN_HEIGHT);
    }

    public int getWidthFor(final int value) {
        return ((screenWidth * value) / DESIGNED_SCREEN_WIDTH);
    }

    public int getTextSize(final int textSize) {
        return ((textSize * (screenWidth * screenHeight)) / (DESIGNED_SCREEN_WIDTH * DESIGNED_SCREEN_HEIGHT));
    }

}
