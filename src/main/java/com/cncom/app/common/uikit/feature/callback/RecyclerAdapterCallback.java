package com.cncom.app.common.uikit.feature.callback;

import android.support.v7.widget.RecyclerView;

public interface RecyclerAdapterCallback
{

    void beforeSetAdapter(RecyclerView.Adapter adapter);

    void afterSetAdapter(RecyclerView.Adapter adapter);
}
