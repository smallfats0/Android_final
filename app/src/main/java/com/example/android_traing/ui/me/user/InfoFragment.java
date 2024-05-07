package com.example.android_traing.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_traing.R;
import com.example.android_traing.base.BaseFragment2;
import com.example.android_traing.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;


public class InfoFragment extends BaseFragment2 {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = root.findViewById(R.id.textView);
        TextView textView2 = root.findViewById(R.id.textView2);
        TextView textView3 = root.findViewById(R.id.textView3);
        TextView textView4 = root.findViewById(R.id.textView4);
        TextView textView5 = root.findViewById(R.id.textView5);

        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            textView.setText(user.getUsername());
            textView2.setText(user.getNickName());
            textView3.setText(user.isSex()?"男":"女");
            textView4.setText(user.getEmail());
            textView5.setText(user.getInfo());
        }
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(this::logOut);
        return root;
    }

    private void logOut(View view) {
        BmobUser.logOut();
        Snackbar.make(view, "退出登录成功" , Snackbar.LENGTH_LONG).show();
        Navigation.findNavController(view).navigateUp();
    }
}