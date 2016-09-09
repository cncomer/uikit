package com.cncom.app.common.uikit.feature.callback;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface ScrollCallback {
    public void afterComputeScroll();

    public void afterOnScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public void beforeComputeScroll();

    public void beforeOnScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}
