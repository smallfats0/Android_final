package com.example.android_traing.base;

import android.content.Context;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android_traing.activity.MainActivity;

public class BaseFragment2 extends Fragment implements OnFragmentKeyDownListener{
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnFragmentKeyDownListener(this);
    }
}
