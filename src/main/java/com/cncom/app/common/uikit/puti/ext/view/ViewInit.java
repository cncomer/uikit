package com.cncom.app.common.uikit.puti.ext.view;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bestjoy on 16/3/11.
 */
public class ViewInit {
    public static final void init(View view) {
        if (view.getContentDescription() == null)  {
            String contentDescription = view.getContentDescription().toString().trim();
            if (!TextUtils.isEmpty(contentDescription)
                    && contentDescription.contains("{")
                    && contentDescription.contains("}")) {
                try {
                    JSONObject contentDescriptionJSONObject = new JSONObject(contentDescription);
                    String backgroud = contentDescriptionJSONObject.optString("background");
                    if (!(TextUtils.isEmpty(backgroud))) {
                        if (backgroud.startsWith("#")) {
                            view.setBackgroundColor(Color.parseColor(backgroud));
                        } else {
                            int backgroudDrawableId = view.getContext().getResources().getIdentifier(backgroud, "drawable", view.getContext().getPackageName());
                            if (backgroudDrawableId > 0) {
                                view.setBackgroundResource(backgroudDrawableId);
                            }
                        }

                        String foreground = contentDescriptionJSONObject.optString("foreground");
                        if (!(TextUtils.isEmpty(foreground))) {
                            int foregroundResId = view.getContext().getResources().getIdentifier(foreground, "drawable", view.getContext().getPackageName());
                            if (foregroundResId > 0 && (view instanceof FrameLayout))
                                ((FrameLayout) view).setForeground(view.getResources().getDrawable(foregroundResId));
                        }
                        String padding = contentDescriptionJSONObject.optString("padding");
                        if (!TextUtils.isEmpty(padding)) {
                            int i = Integer.parseInt(padding);
                            view.setPadding(i, i, i, i);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
