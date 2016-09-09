package com.cncom.app.common.uikit.puti;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by bestjoy on 16/3/10.
 */
public class Template implements Serializable {
    private static final long serialVersionUID = 0L;
    public boolean canUsePreset = false;
    public String downloadUrl = "";
    public String name = "";
    public int presetId = 0;
    public int version = 0;

    public Template(String name, int version, int layoutId) {
        this(name, version, null, layoutId);
    }

    public Template(String name, int version, String presetId) {
        this(name, version, presetId, 0);
    }

    public Template(String name, int version, String downloadUrl, int presetId) {
        this.canUsePreset = true;
        if (name != null)
            this.name = name.trim();
        this.version = version;
        this.downloadUrl = downloadUrl;
        this.presetId = presetId;
    }

    public boolean equals(Template template) {
        if (this == template)  {
            return true;
        } else if (template == null) {
            return false;
        } else if (this.version == template.version) {
            if (super.getClass() != template.getClass()) {
                return false;
            }
            if (!this.name.equals(template.name)) {
                return false;
            }
        }
        return true;
    }


    public int hashCode() {
        int hashCode;
        if (!TextUtils.isEmpty(name)) {
            hashCode = name.hashCode() + version;
        } else {
            hashCode = version;
        }
        return hashCode;
    }
}
