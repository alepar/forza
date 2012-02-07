package ru.alepar.forza.android.ui.activities;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class BlinkAnimation extends Animation {

    private final View view;

    public BlinkAnimation(View view) {
        this.view = view;
        setInterpolator(new LinearInterpolator());
        setDuration(100L);
//        setAnimationListener(new BlinkAnimationListener());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime < 0.5) {
            fadeIn(interpolatedTime * 2);
        } else {
            fadeOut((interpolatedTime - 0.5) * 2);
        }
    }

    @Override
    public boolean willChangeTransformationMatrix() {
        return false;
    }

    @Override
    public boolean willChangeBounds() {
        return false;
    }

    private void fadeIn(double interpolatedTime) {
        final int colorByte = (int) (interpolatedTime * 255);
        view.setBackgroundColor(Color.rgb(colorByte, colorByte, colorByte));
    }

    private void fadeOut(double interpolatedTime) {
        final int colorByte = 255 - (int) (interpolatedTime * 255);
        view.setBackgroundColor(Color.rgb(colorByte, colorByte, colorByte));
    }

    private class BlinkAnimationListener implements AnimationListener {
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            view.setBackgroundColor(Color.rgb(0, 0, 0));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
}
