package com.cncom.app.common.uikit.control;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import com.cncom.app.common.uikit.utils.TouchEventUtil;

import java.lang.reflect.Field;

/**
 * Created by bestjoy on 16/3/10.
 */
public class SmoothViewPager extends ViewPager {
    private static final String TAG = "SmoothViewPager";
    private double SCROLL_ANGLE_THRESHOLD = 1.0471975511965976D;
    private float mActionDownX;
    private float mActionDownY;
    private boolean mInterceptTouch = false;
    private SmoothScroller mScroller = null;

    public SmoothViewPager(Context context) {
        super(context);
        postInitViewPager();
    }

    public SmoothViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        postInitViewPager();
    }

    private void postInitViewPager() {
        if (isInEditMode()){
            return;
        }
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field sInterpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            sInterpolatorField.setAccessible(true);
            mScroller = new SmoothScroller(getContext(), (Interpolator) sInterpolatorField.get(null));
            scrollerField.set(this, this.mScroller);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void checkDisallowInterceptTouchEventOrNot(boolean handled, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.mActionDownX = motionEvent.getX();
                this.mActionDownY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if((double)Math.abs((motionEvent.getY() - this.mActionDownY) / (motionEvent.getX() - this.mActionDownX)) < this.SCROLL_ANGLE_THRESHOLD && this.mInterceptTouch && handled) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    return;
                }
                break;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean bool = super.onInterceptTouchEvent(motionEvent);
        checkDisallowInterceptTouchEventOrNot(bool, motionEvent);
        return bool;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(TAG, "onTouchEvent " + TouchEventUtil.getTouchAction(motionEvent.getAction()));
        boolean bool = super.onTouchEvent(motionEvent);
        checkDisallowInterceptTouchEventOrNot(bool, motionEvent);
        return bool;
    }

    public void setInterceptTouch(boolean interceptTouch) {
        this.mInterceptTouch = interceptTouch;
    }

    public void setScrollDurationFactor(double scrollDurationFactor) {
        if (this.mScroller != null)
            this.mScroller.setScrollDurationFactor(scrollDurationFactor);
    }
}
