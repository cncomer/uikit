package com.cncom.app.common.uikit.feature.callback;

import android.graphics.Canvas;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface CanvasCallback {
    public void afterDispatchDraw(Canvas canvas);

    public void afterDraw(Canvas canvas);

    public void afterOnDraw(Canvas canvas);

    public void beforeDispatchDraw(Canvas canvas);

    public void beforeDraw(Canvas canvas);

    public void beforeOnDraw(Canvas canvas);
}
