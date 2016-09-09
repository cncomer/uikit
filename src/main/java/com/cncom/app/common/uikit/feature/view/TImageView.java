package com.cncom.app.common.uikit.feature.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.cncom.app.common.uikit.feature.callback.CanvasCallback;
import com.cncom.app.common.uikit.feature.callback.FocusCallback;
import com.cncom.app.common.uikit.feature.callback.ImageCallback;
import com.cncom.app.common.uikit.feature.callback.ImageSaveCallback;
import com.cncom.app.common.uikit.feature.callback.LayoutCallback;
import com.cncom.app.common.uikit.feature.callback.MeasureCallback;
import com.cncom.app.common.uikit.feature.callback.ScrollCallback;
import com.cncom.app.common.uikit.feature.callback.TouchEventCallback;
import com.cncom.app.common.uikit.feature.features.AbsFeature;
import com.cncom.app.common.uikit.feature.features.FeatureList;
import com.cncom.app.common.uikit.feature.features.IFeatureList;

/**
 * Created by bestjoy on 16/3/16.
 */
public class TImageView extends ImageView
        implements ViewHelper, IFeatureList<ImageView> {

    private FeatureList<ImageView> mFeatureList;

    public TImageView(Context context) {
        super(context);
        this.mFeatureList = new FeatureList(this);

    }

    public TImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TImageView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mFeatureList = new FeatureList(this);
        this.mFeatureList.init(context, attributeSet, defStyleAttr);
    }

    public boolean addFeature(AbsFeature<? super ImageView> absFeature) {
        return this.mFeatureList.addFeature(absFeature);
    }

    public void clearFeatures() {
        this.mFeatureList.clearFeatures();
    }
    @Override
    public void computeScroll() {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof ScrollCallback) {
                ((ScrollCallback) absFeature).beforeComputeScroll();
            }
        }
        super.computeScroll();
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof ScrollCallback) {
                ((ScrollCallback) absFeature).afterComputeScroll();
            }
        }
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).beforeDispatchDraw(canvas);
            }
        }
        super.dispatchDraw(canvas);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).afterDispatchDraw(canvas);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).beforeDispatchTouchEvent(motionEvent);
            }
        }
        boolean handled = super.dispatchTouchEvent(motionEvent);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).afterDispatchTouchEvent(motionEvent);
            }

        }
        return handled;
    }
    @Override
    public void draw(Canvas canvas) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).beforeDraw(canvas);
            }
        }
        super.draw(canvas);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).afterDraw(canvas);
            }
        }
    }

    public AbsFeature<? super ImageView> findFeature(Class<? extends AbsFeature<? super ImageView>> featureClass) {
        return this.mFeatureList.findFeature(featureClass);
    }

    public void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        this.mFeatureList.init(context, attributeSet, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).beforeOnDraw(canvas);
            }
        }
        super.onDraw(canvas);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).afterOnDraw(canvas);
            }
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).beforeOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof TouchEventCallback) {
                ((FocusCallback) absFeature).afterOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof LayoutCallback) {
                ((LayoutCallback) absFeature).beforeOnLayout(changed, left, top, right, bottom);
            }
        }
        super.onLayout(changed, left, top, right, bottom);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof LayoutCallback) {
                ((LayoutCallback) absFeature).afterOnLayout(changed, left, top, right, bottom);
            }
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof MeasureCallback) {
                ((MeasureCallback) absFeature).beforeOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof MeasureCallback) {
                ((MeasureCallback) absFeature).afterOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).beforeOnTouchEvent(motionEvent);
            }
        }
        boolean handled = super.onTouchEvent(motionEvent);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).afterOnTouchEvent(motionEvent);
            }
        }
        return handled;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).beforeOnWindowFocusChanged(hasWindowFocus);
            }

        }
        super.onWindowFocusChanged(hasWindowFocus);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).afterOnWindowFocusChanged(hasWindowFocus);
            }
        }
    }

    @Override
    public boolean performLongClick() {
        int featureSize = this.mFeatureList.size();
        for (int j = 0; j < featureSize; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof ImageSaveCallback) {
                ((ImageSaveCallback) absFeature).beforePerformLongClick();
            }

        }
        boolean bool = super.performLongClick();
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof ImageSaveCallback) {
                ((ImageSaveCallback) absFeature).afterPerformLongClick();
            }
        }
        return bool;
    }

    public boolean removeFeature(Class<? extends AbsFeature<? super ImageView>> featureClass) {
        return this.mFeatureList.removeFeature(featureClass);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (mFeatureList != null) {
            for (AbsFeature absFeature : mFeatureList) {
                if (absFeature instanceof ImageCallback) {
                    drawable = ((ImageCallback) absFeature).wrapImageDrawable(drawable);
                }
            }
        }
        super.setImageDrawable(drawable);
    }

    @Override
    public void setImageResource(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        setImageDrawable(drawable);
    }

    public void setMeasuredDimension(long measuredWidth, long measuredHeight) {
        super.setMeasuredDimension((int) measuredWidth, (int) measuredHeight);
    }
}