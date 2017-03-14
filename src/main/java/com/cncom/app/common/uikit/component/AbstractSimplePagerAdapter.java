package com.cncom.app.common.uikit.component;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CycleIconPagerAdapter;

/**
 * Item的单击操作，请复写onClick(View v)方法
 *
 * Created by bestjoy on 16/9/19.
 */
public abstract class AbstractSimplePagerAdapter<T extends View> extends PagerAdapter
        implements CycleIconPagerAdapter, View.OnClickListener{
    private static final String TAG = "AbstractSimplePagerAdapter";
    private SparseArray<T> bannerItems = new SparseArray();

    public int getItemPosition(Object paramObject) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @SuppressLint("LongLogTag")
    public final void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        T cachedView = bannerItems.get(position);
        if (cachedView != null && cachedView.getParent() != null) {
            Log.d(TAG, "destroyItem " + position);
            viewPager.removeView(cachedView);
        }
    }

    @Override
    public int getActualCount() {
        return getCount();
    }

    @Override
    public int getInstanceCount() {
        return getCount();
    }

    public abstract T getView(ViewGroup container, int position);

    @SuppressLint("LongLogTag")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem " + position);
        ViewPager viewPager = (ViewPager) container;
        T cachedView = bannerItems.get(position);
        if (cachedView == null ) {
            cachedView = getView(container, position);
            bannerItems.put(position, cachedView);
        }

        if (cachedView.getParent() != null) {
            viewPager.removeView(cachedView);
        }
        cachedView.setOnClickListener(this);
        viewPager.addView(cachedView);
        return cachedView;
    }

    @Override
    public void onClick(View v) {
    }
}