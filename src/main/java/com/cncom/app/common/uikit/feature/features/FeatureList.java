package com.cncom.app.common.uikit.feature.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cncom.app.common.uikit.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by bestjoy on 16/3/16.
 */
public class FeatureList<T extends View> extends ArrayList<AbsFeature<? super T>>
        implements IFeatureList<T>, Comparator<AbsFeature<? super T>> {

    private static final long serialVersionUID = 5539018560951385305L;
    private T mHost;

    public FeatureList(T view) {
        this.mHost = view;
    }

    public boolean add(AbsFeature<? super T> absFeature) {
        for( AbsFeature _absFeature: this) {
            if (TextUtils.equals(_absFeature.getClass().getName(), absFeature.getClass().getName())) {
                throw new RuntimeException(_absFeature.getClass().getName() + " already add to this view");
            }
        }
        boolean added = super.add(absFeature);
        Collections.sort(this, this);
        return added;
    }

    public boolean addFeature(AbsFeature<? super T> absFeature) {
        if (absFeature != null) {
            absFeature.setHost(this.mHost);
            return add(absFeature);
        }
        return false;
    }

    public void clearFeatures() {
        clear();
        this.mHost.requestLayout();
    }

    public int compare(AbsFeature<? super T> absFeature1, AbsFeature<? super T> absFeature2) {
        return (FeatureFactory.getFeaturePriority(absFeature1.getClass().getSimpleName()) - FeatureFactory.getFeaturePriority(absFeature2.getClass().getSimpleName()));
    }

    public AbsFeature<? super T> findFeature(Class<? extends AbsFeature<? super T>> featureClass) {
        for( AbsFeature _absFeature: this) {
            if (_absFeature.getClass() == featureClass) {
               return _absFeature;
            }
        }
        return null;
    }

    public void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.FeatureNameSpace);
        if (typedArray != null) {
            ArrayList<AbsFeature<? super T>> absFeatureList = FeatureFactory.creator(this.mHost.getContext(), typedArray);
            for(AbsFeature<? super T> absFeature : absFeatureList) {
                absFeature.constructor(context, attributeSet, defStyleAttr);
                addFeature(absFeature);

            }
            typedArray.recycle();
        }
    }

    public boolean removeFeature(Class<? extends AbsFeature<? super T>> paramClass) {
        int i = size();
        for (int j = 0; j < i; ++j) {
            AbsFeature localAbsFeature = (AbsFeature) get(j);
            if (localAbsFeature.getClass() == paramClass)
                return remove(localAbsFeature);
        }
        return false;
    }
}
