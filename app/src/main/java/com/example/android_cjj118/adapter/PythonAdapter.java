package com.example.android_cjj118.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.android_cjj118.R;
import com.example.android_cjj118.bean.PythonBean;

import java.util.List;

public class PythonAdapter extends BaseQuickAdapter<PythonBean, BaseViewHolder> {

    public PythonAdapter( @NonNull List<PythonBean> data) {
        super(R.layout.item_python, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PythonBean pythonBean) {
        baseViewHolder.setText(R.id.textView, pythonBean.getAddress());
        baseViewHolder.setText(R.id.textView2, pythonBean.getContent());
        baseViewHolder.setText(R.id.textView3, pythonBean.getOpen_class());

    }
}
