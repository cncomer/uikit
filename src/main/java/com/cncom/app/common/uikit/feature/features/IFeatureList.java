package com.cncom.app.common.uikit.feature.features;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bestjoy on 16/3/16.
 */
public interface IFeatureList<T extends View> {
    public boolean addFeature(AbsFeature<? super T> absFeature);

    public void clearFeatures();

    public AbsFeature<? super T> findFeature(Class<? extends AbsFeature<? super T>> featureClass);

    public void init(Context context, AttributeSet attributeSet, int defStyleRes);

    public boolean removeFeature(Class<? extends AbsFeature<? super T>> featureClass);
}
