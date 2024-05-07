package com.example.android_traing.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.android_traing.R;

import java.util.List;

public class videoListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public videoListAdapter( @Nullable List<String> data) {
        super(R.layout.item_video_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.textView,s);
    }

}
