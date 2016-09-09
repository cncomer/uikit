package com.cncom.app.common.uikit.feature.callback;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface MeasureCallback {
    public void afterOnMeasure(int widthMeasureSpec, int heightMeasureSpec);

    public void beforeOnMeasure(int widthMeasureSpec, int heightMeasureSpec);
}
