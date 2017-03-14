package com.cncom.app.common.uikit.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cncom.app.common.uikit.R;

/**
 * 选择
 * Created by bestjoy on 16/9/27.
 */

public class NumerPickerLinearLayout extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "NumerPickerLinearLayout";
    private ImageView mBtnPlus, mBtnMinus;
    private TextView mCountInput;

    private OnCountChangedCallback mOnCountChangedCallback;
    /**允许的最大值*/
    protected int mAllowMaxCount = 99999;
    /**允许的最小值*/
    protected int mAllowMinCount;

    public static interface OnCountChangedCallback {
        public void onCountChanged(NumerPickerLinearLayout numerPickerLinearLayout, int count);
    }

    public void setOnCountChangedCallback(OnCountChangedCallback callback) {
        mOnCountChangedCallback = callback;
    }

    public NumerPickerLinearLayout(Context context) {
        this(context, null);
    }

    public NumerPickerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberPickerLinearLayout);

        int layoutResId = typedArray.getResourceId(R.styleable.NumberPickerLinearLayout_layout, R.layout.number_picker_linearlayout);
        LayoutInflater.from(context).inflate(layoutResId, this, true);

        mBtnPlus = (ImageView) this.findViewById(R.id.button_plus);
        Drawable iconPlus = typedArray.getDrawable(R.styleable.NumberPickerLinearLayout_iconPlus);
        if (iconPlus != null) {
            mBtnPlus.setImageDrawable(iconPlus);
        }
        mBtnPlus.setOnClickListener(this);

        mBtnMinus = (ImageView) this.findViewById(R.id.button_minus);
        Drawable iconMinus = typedArray.getDrawable(R.styleable.NumberPickerLinearLayout_iconMinus);
        if (iconMinus != null) {
            mBtnMinus.setImageDrawable(iconPlus);
        }
        mBtnMinus.setOnClickListener(this);


        mCountInput = (TextView) this.findViewById(R.id.item_count);

        Drawable itemCountBackground = typedArray.getDrawable(R.styleable.NumberPickerLinearLayout_itemCountBackground);
        if (itemCountBackground != null) {
            mCountInput.setBackgroundDrawable(itemCountBackground);
        }
        mCountInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        mCountInput.setText("1");
        mCountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mCountInput.setText(String.valueOf(1));
                    notifyCountChanged(1);
                } else {
                    int newCount = Integer.valueOf(s.toString());
                    notifyCountChanged(newCount);
                }
            }
        });

        typedArray.recycle();

    }


    public void setCurrentCount(int count) {
        if (count <= 0) {
            count = 1;
        }
        mCountInput.setText(String.valueOf(count));
    }


    protected void init() {
        mBtnPlus = (ImageView) this.findViewById(R.id.button_plus);
        mBtnMinus = (ImageView) this.findViewById(R.id.button_minus);


        mBtnPlus.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);

        mCountInput = (TextView) this.findViewById(R.id.item_count);

        mCountInput.setText("1");
        mCountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mCountInput.setText(String.valueOf(1));
                    notifyCountChanged(1);
                } else {
                    int newCount = Integer.valueOf(s.toString());
                    notifyCountChanged(newCount);
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public int getCount() {
        return Integer.valueOf(mCountInput.getText().toString());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int count = getCount();

        if (id == R.id.button_minus) {
            if (count > 1){
                mCountInput.setText(String.valueOf(count - 1));
            }
        } else if (id == R.id.button_plus) {
            if (count < mAllowMaxCount) {
                //最多只能输入99
                mCountInput.setText(String.valueOf(count + 1));
            }
        }
    }


    protected void notifyCountChanged(int count) {
        if (mOnCountChangedCallback != null) {
            mOnCountChangedCallback.onCountChanged(this, count);
        }
    }

}
