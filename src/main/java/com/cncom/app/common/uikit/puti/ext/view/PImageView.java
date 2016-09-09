package com.cncom.app.common.uikit.puti.ext.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.cncom.app.common.uikit.feature.features.RatioFeature;
import com.cncom.app.common.uikit.feature.view.TImageView;

import org.json.JSONObject;

/**
 * Created by bestjoy on 16/3/15.
 */
public class PImageView extends TImageView {
    private String pitTag;

    public PImageView(Context context) {
        this(context, null);
    }

    public PImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PImageView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) return;

        try {
            JSONObject attrJSONObject = AttrUtils.attrFrom(getContentDescription());

            if (attrJSONObject != null) {
                String aspectRatio = attrJSONObject.optString("aspectRatio");
                if (!TextUtils.isEmpty(aspectRatio)) {
                    RatioFeature ratioFeature = new RatioFeature();
                    ratioFeature.setRatio(Float.parseFloat(aspectRatio));
                    addFeature(ratioFeature);
                }
            }

        } catch(Exception localException) {
            localException.printStackTrace();
        }
    }

    public void setAspectRatio(float aspectRatio) {
        RatioFeature ratioFeature = (RatioFeature)findFeature(RatioFeature.class);
        if (ratioFeature == null) {
            ratioFeature = new RatioFeature();
            ratioFeature.setRatio(aspectRatio);
            addFeature(ratioFeature);
        } else {
            ratioFeature.setRatio(aspectRatio);
        }
    }

    public void setPitTag(String pitTag){
        this.pitTag = pitTag;
    }
}
