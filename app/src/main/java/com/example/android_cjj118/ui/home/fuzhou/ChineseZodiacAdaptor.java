package com.example.android_cjj118.ui.home.fuzhou;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_cjj118.R;

import java.util.ArrayList;
import java.util.Collections;

public class ChineseZodiacAdaptor extends RecyclerView.Adapter<ChineseZodiacAdaptor.ChineseZodiacHolder> {
    private final ArrayList<ChineseZodiac> data;

    public ChineseZodiacAdaptor(ArrayList<ChineseZodiac> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public ChineseZodiacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ChineseZodiacHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChineseZodiacHolder holder, int position) {
        ChineseZodiac chineseZodiac = data.get(position);
        holder.imageView.setImageResource(chineseZodiac.getImageId());
        holder.textView.setText(chineseZodiac.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public boolean swap(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition,toPosition);
        if(fromPosition<toPosition){
            for (int i = fromPosition;i<toPosition;i++){
                Collections.swap(data,i,i+1);
            }
        }else {
            for (int i = fromPosition;i>toPosition;i--){
                Collections.swap(data,i,i-1);
            }
        }
        for (ChineseZodiac chineseZodiac : data) {
            Log.i("swap:",chineseZodiac.getName());
        }
        return true;
    }

    public void remove(int position) {
        notifyItemRemoved(position);
        data.remove(position);
        for (ChineseZodiac chineseZodiac : data) {
            Log.i("remove:",chineseZodiac.getName());
        }
    }

    public static class ChineseZodiacHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        public ChineseZodiacHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
