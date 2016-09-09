package com.cncom.app.common.uikit.feature.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.cncom.app.common.uikit.feature.callback.AdapterCallback;
import com.cncom.app.common.uikit.feature.callback.CanvasCallback;
import com.cncom.app.common.uikit.feature.callback.FocusCallback;
import com.cncom.app.common.uikit.feature.callback.InterceptTouchEventCallback;
import com.cncom.app.common.uikit.feature.callback.LayoutCallback;
import com.cncom.app.common.uikit.feature.callback.MeasureCallback;
import com.cncom.app.common.uikit.feature.callback.ScrollCallback;
import com.cncom.app.common.uikit.feature.callback.TouchEventCallback;
import com.cncom.app.common.uikit.feature.features.AbsFeature;
import com.cncom.app.common.uikit.feature.features.FeatureList;
import com.cncom.app.common.uikit.feature.features.IFeatureList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestjoy on 16/3/16.
 */
public class TListView extends ListView
        implements AbsListView.OnScrollListener, ViewGroupHelper, ViewHelper, IFeatureList<ListView> {
    private FeatureList<ListView> mFeatureList;
    private List<OnScrollListener> mOnScrollListeners;
    private boolean unScroll = false;

    public TListView(Context context) {
        this(context, null);
    }

    public TListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TListView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mFeatureList = new FeatureList(this);
        this.mFeatureList.init(context, attributeSet, defStyleAttr);
        this.mOnScrollListeners = new ArrayList();
        this.unScroll = false;
        super.setOnScrollListener(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

    }

    public boolean addFeature(AbsFeature<? super ListView> absFeature) {
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
    public void dispatchDraw(Canvas canvas) {
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
        boolean bool = super.dispatchTouchEvent(motionEvent);
        for (int k = featureSize - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).afterDispatchTouchEvent(motionEvent);
            }
        }
        return bool;
    }
    @Override
    public void draw(Canvas canvas) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).beforeDraw(canvas);
            }
        }
        super.draw(canvas);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).afterDraw(canvas);
            }
        }
    }
    public boolean drawChild(Canvas paramCanvas, View child, long drawingTime, int paramInt) {
        return super.drawChild(paramCanvas, child, drawingTime);
    }

    public AbsFeature<? super ListView> findFeature(Class<? extends AbsFeature<? super ListView>> featureClass) {
        return this.mFeatureList.findFeature(featureClass);
    }

    public void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        this.mFeatureList.init(context, attributeSet, defStyleAttr);
    }

    public boolean isUnScroll() {
        return this.unScroll;
    }

    public void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec, int paramInt) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).beforeOnDraw(canvas);
            }
        }
        super.onDraw(canvas);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof CanvasCallback) {
                ((CanvasCallback) absFeature).afterOnDraw(canvas);
            }
        }
    }

    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).beforeOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).afterOnFocusChanged(gainFocus, direction, previouslyFocusedRect);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean handled = super.onInterceptTouchEvent(motionEvent);
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof InterceptTouchEventCallback) {
                handled |= ((InterceptTouchEventCallback) absFeature).onInterceptTouchEvent(motionEvent);
            }
        }
        return handled;

    }

    public void onLayout(boolean changed, int l, int t, int r, int b) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof LayoutCallback) {
                ((LayoutCallback) absFeature).beforeOnLayout(changed, l, t, r, b);
            }
        }
        super.onLayout(changed, l, t, r, b);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof LayoutCallback) {
                ((LayoutCallback) absFeature).afterOnLayout(changed, l, t, r, b);
            }
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.unScroll) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
            return;
        }
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof MeasureCallback) {
                ((MeasureCallback) absFeature).beforeOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = this.mFeatureList.get(k);
            if (absFeature instanceof MeasureCallback) {
                ((MeasureCallback) absFeature).afterOnMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        for (OnScrollListener onScrollListener : mOnScrollListeners) {
            onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        for (OnScrollListener onScrollListener : mOnScrollListeners) {
            onScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).beforeOnTouchEvent(motionEvent);
            }
        }
        boolean bool = super.onTouchEvent(motionEvent);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof TouchEventCallback) {
                ((TouchEventCallback) absFeature).afterOnTouchEvent(motionEvent);
            }
        }
        return bool;
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).beforeOnWindowFocusChanged(hasWindowFocus);
            }
        }
        super.onWindowFocusChanged(hasWindowFocus);
        for (int k = i - 1; k >= 0; --k) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(k);
            if (absFeature instanceof FocusCallback) {
                ((FocusCallback) absFeature).afterOnWindowFocusChanged(hasWindowFocus);
            }
        }
    }

    public boolean removeFeature(Class<? extends AbsFeature<? super ListView>> featureClass) {
        return this.mFeatureList.removeFeature(featureClass);
    }

    public void removeOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        if (onScrollListener != null) {
            this.mOnScrollListeners.remove(onScrollListener);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        int i = this.mFeatureList.size();
        for (int j = 0; j < i; ++j) {
            AbsFeature absFeature = (AbsFeature) this.mFeatureList.get(j);
            if (absFeature instanceof AdapterCallback) {
                listAdapter = ((AdapterCallback) absFeature).wrapAdapter(listAdapter);
            }
        }
        super.setAdapter(listAdapter);
    }

    @Override
    public void setMeasuredDimension(long measuredWidth, long measuredHeight) {
        super.setMeasuredDimension((int) measuredWidth, (int) measuredHeight);
    }

    @Override
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListeners.add(onScrollListener);
    }
    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(mode);
    }

    public void setUnScroll(boolean unScroll) {
        this.unScroll = unScroll;
    }
}
