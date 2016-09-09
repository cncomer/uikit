package com.cncom.app.common.uikit.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by bestjoy on 16/3/10.
 */
public class SmoothScroller extends Scroller {
    private double mScrollFactor = 1.0471975511965976D;

    public SmoothScroller(Context context) {
        super(context);
    }

    public SmoothScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @SuppressLint({"NewApi"})
    public SmoothScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    public void setScrollDurationFactor(double scrollDurationFactor) {
        this.mScrollFactor = scrollDurationFactor;
    }

    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) (duration * this.mScrollFactor));
    }
}