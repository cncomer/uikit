package com.cncom.app.common.uikit.feature.features;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bestjoy on 16/3/11.
 */
public abstract class AbsFeature<T extends View> {
    public T mHost;

    public AbsFeature() {
        constructor(null, null, 0);
    }

    public abstract void constructor(Context context, AttributeSet attributeSet, int defStyleAttr);

    public T getHost() {
        return this.mHost;
    }

    public void setHost(T host) {
        this.mHost = host;
    }
}
