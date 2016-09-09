package com.cncom.app.common.uikit.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cncom.app.common.uikit.R;


/**
 * 砖块布局
 * Created by bestjoy on 16/3/10.
 */
public class BrickLayout extends ViewGroup {
    private int mGap;
    private int mMaxLines;

    public BrickLayout(Context context) {
        this(context, null);
    }

    public BrickLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BrickLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mGap = 0;
        this.mMaxLines = Integer.MAX_VALUE;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BrickLayout, defStyleAttr, 0);
        if (typedArray != null) {
            this.mMaxLines = typedArray.getInt(R.styleable.BrickLayout_brickMaxLines, this.mMaxLines);
            this.mGap = typedArray.getDimensionPixelSize(R.styleable.BrickLayout_brickGap, this.mGap);
            typedArray.recycle();
        }
    }

    public void addView(View child, int index, LayoutParams layoutParams) {
        super.addView(child, index, new BrickLayoutParams(layoutParams));
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new BrickLayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int childCount = getChildCount();
        for (int j = 0; j < childCount; ++j) {
            View child = getChildAt(j);
            if (child.getVisibility() != View.GONE) {
                BrickLayoutParams brickLayoutParams = (BrickLayoutParams) child.getLayoutParams();
                int x = brickLayoutParams.x;
                int y = brickLayoutParams.y;
                child.layout(x, y, x + brickLayoutParams.width, y + brickLayoutParams.height);
            }
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int childSpecSize = MeasureSpec.makeMeasureSpec(widthSpecSize, MeasureSpec.AT_MOST);

        int height = 0;
        int childCount = getChildCount();
        int line = 0;
        int usedWidth = 0;
        int top = 0;
        int lineMaxHeight = 0;//行的最大高度
        for(int index=0; index<childCount;index++ ) {
            View child = getChildAt(index);
            child.measure(childSpecSize, heightMeasureSpec);
            BrickLayoutParams brickLayoutParams = (BrickLayoutParams) child.getLayoutParams();
            if (brickLayoutParams.height <= 0) {
                brickLayoutParams.height = child.getMeasuredHeight();
            }
            if (brickLayoutParams.width <= 0) {
                brickLayoutParams.width = child.getMeasuredWidth();
            }

            if (widthSpecSize - usedWidth < brickLayoutParams.width) {
                ++line;
                top += lineMaxHeight + this.mGap;
                usedWidth = 0;
                //重置行的最大高度，下一行重新计算
                lineMaxHeight = 0;
            }
            brickLayoutParams.x = usedWidth;
            brickLayoutParams.y = (top + brickLayoutParams.topMargin);
            lineMaxHeight = Math.max(lineMaxHeight, brickLayoutParams.height + brickLayoutParams.topMargin + brickLayoutParams.bottomMargin);
            usedWidth += brickLayoutParams.width + this.mGap;
            if (line < mMaxLines) {
                height = top + lineMaxHeight;
            }
        }

        setMeasuredDimension(widthSpecSize, height);
    }

    public static class BrickLayoutParams extends MarginLayoutParams {
        int x;
        int y;

        public BrickLayoutParams(int width, int height) {
            super(width, height);
            this.x = 0;
            this.y = 0;
        }

        public BrickLayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.x = 0;
            this.y = 0;
        }

        public BrickLayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.x = 0;
            this.y = 0;
        }
    }
}