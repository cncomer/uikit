package com.cncom.app.common.uikit.puti.ext.view;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

/**
 * Created by bestjoy on 16/3/15.
 */
public class AttrUtils {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("\\|");

    public static JSONObject attrFrom(CharSequence contentDescription) throws JSONException {
        if (TextUtils.isEmpty(contentDescription)) {
            return null;
        }

        String str = contentDescription.toString().trim();
        if (!TextUtils.isEmpty(str)
                && str.contains("{")
                && str.contains("}")) {
                return new JSONObject(str);
        }
        return null;
    }
}
