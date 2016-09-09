package com.cncom.app.common.uikit.feature.callback;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface LayoutCallback {
    public void afterOnLayout(boolean changed, int left, int top, int right, int bottom);
    public void beforeOnLayout(boolean changed, int left, int top, int right, int bottom);
}
