package com.example.android_cjj118.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.android_cjj118.R;
import com.example.android_cjj118.bean.VideoBean;
import com.example.android_cjj118.utils.NetUtils;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    public VideoAdapter( @Nullable List<VideoBean> data) {
        super(R.layout.item_video, data);
    }



    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, VideoBean videoBean) {
        Glide.with(baseViewHolder.itemView).load(NetUtils.BASE_URL + videoBean.getImg())
                .into((ImageView) baseViewHolder.getView(R.id.imageView));
    }
}
