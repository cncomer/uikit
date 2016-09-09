package com.cncom.app.common.uikit.feature.callback;

import android.graphics.Rect;

/**
 * Created by bestjoy on 16/3/11.
 */
public interface FocusCallback {

    public void afterOnFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect);

    public void afterOnWindowFocusChanged(boolean gainFocus);

    public void beforeOnFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect);

    public void beforeOnWindowFocusChanged(boolean gainFocus);
}
