package com.example.android_traing.ui.home.web;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android_traing.R;
import com.just.agentweb.AgentWeb;

public class webFragment extends Fragment {

    private AgentWeb mAgentWeb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web, container, false);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) root, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(getArguments() != null ? getArguments().getString("url")
                        : "http://www.jd.com");
        return root;
    }
    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }
    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }

}