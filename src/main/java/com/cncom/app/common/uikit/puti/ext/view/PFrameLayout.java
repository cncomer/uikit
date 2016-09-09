package com.cncom.app.common.uikit.puti.ext.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by bestjoy on 16/3/11.
 */
public class PFrameLayout extends FrameLayout {
    public PFrameLayout(Context context) {
        super(context);
        init();
    }

    public PFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PFrameLayout(Context context, AttributeSet attributeSet, int defStyleRes) {
        super(context, attributeSet, defStyleRes);
        init();
    }

    private void init() {
        ViewInit.init(this);
    }
}
