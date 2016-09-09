package com.cncom.app.common.uikit.feature.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.cncom.app.common.uikit.R;
import com.cncom.app.common.uikit.feature.callback.MeasureCallback;
import com.cncom.app.common.uikit.feature.view.ViewHelper;

/**
 * Created by bestjoy on 16/3/15.
 */
public class RatioFeature extends AbsFeature<View>
        implements MeasureCallback {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static float sDefaultRatio = 0.5F;
    private int mOrientation;
    private float mRatio;

    public RatioFeature() {
        this.mRatio = sDefaultRatio;
        this.mOrientation = HORIZONTAL;
    }

    public void afterOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mRatio > 0F) {
            int heightSpecSize = 0;
            int widthSpecSize = 0;
            boolean supportOrientation = true;
            if (this.mOrientation == HORIZONTAL) {
                widthSpecSize = View.MeasureSpec.getSize(widthMeasureSpec);
                heightSpecSize = (int) (widthSpecSize * this.mRatio);
            } else if (this.mOrientation == VERTICAL) {
                heightSpecSize = View.MeasureSpec.getSize(heightMeasureSpec);
                widthSpecSize = (int) (heightSpecSize * this.mRatio);
            } else {
                supportOrientation = false;
            }
            if (supportOrientation && getHost() instanceof ViewHelper) {
                ((ViewHelper) getHost()).setMeasuredDimension(widthSpecSize, heightSpecSize);
            }
        }

    }

    public void beforeOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }

    public void constructor(Context context, AttributeSet attributeSet, int defStyleRes) {
        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RatioFeature);
            if (typedArray != null) {
                this.mRatio = typedArray.getFloat(R.styleable.RatioFeature_uik_ratio, sDefaultRatio);
                this.mOrientation = typedArray.getInt(R.styleable.RatioFeature_uik_orientation, HORIZONTAL);
                typedArray.recycle();
            }
        }
    }

    public void setHost(View hostView) {
        super.setHost(hostView);
        hostView.requestLayout();
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public void setRatio(float ratio) {
        if (ratio > 0F && this.mRatio != ratio) {
            this.mRatio = ratio;
            if (getHost() != null) {
                getHost().requestLayout();
            }
        }
    }
}