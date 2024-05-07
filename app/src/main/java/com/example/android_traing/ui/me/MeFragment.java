package com.example.android_traing.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.android_traing.R;
import com.example.android_traing.bean.User;
import com.example.android_traing.databinding.FragmentHomeBinding;
import com.example.android_traing.databinding.FragmentMeBinding;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

public class MeFragment extends Fragment {

    private boolean isLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_me, container, false);
        CircleImageView circleImageView=root.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(this::click);
        TextView textView=root.findViewById(R.id.textView);
        textView.setOnClickListener(this::click);
        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            textView.setText(user.getUsername());
            isLogin=true;
        } else {
            textView.setText("点击登录");
            isLogin=false;
        }
        return root;
    }

    private void click(View view) {
        if(isLogin){
            Navigation.findNavController(view).navigate(R.id.action_navigation_me_to_infoFragment);
        }else{
            Navigation.findNavController(view).navigate(R.id.action_navigation_me_to_loginFragment);
        }
    }
}