package com.cncom.app.common.uikit.feature.callback;

import android.view.MotionEvent;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface TouchEventCallback {
    public void afterDispatchTouchEvent(MotionEvent motionEvent);

    public void afterOnTouchEvent(MotionEvent motionEvent);

    public void beforeDispatchTouchEvent(MotionEvent motionEvent);

    public void beforeOnTouchEvent(MotionEvent motionEvent);
}
