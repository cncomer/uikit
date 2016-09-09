package com.cncom.app.common.uikit.feature.view;

import android.graphics.Canvas;
import android.view.View;

/**
 * Created by bestjoy on 16/3/16.
 */
public interface ViewGroupHelper {
    public boolean drawChild(Canvas canvas, View child, long drawingTime, int paramInt);
    public void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec, int paramInt);
}
