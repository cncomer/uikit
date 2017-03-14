package com.cncom.app.common.uikit.feature.callback;

import android.widget.ListAdapter;

public interface AdapterCallback {
	void beforeSetAdapter(ListAdapter adapter);
	void afterSetAdapter(ListAdapter adapter);
}
