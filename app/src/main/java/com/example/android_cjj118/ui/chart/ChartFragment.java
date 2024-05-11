package com.example.android_cjj118.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.android_cjj118.R;
import com.example.android_cjj118.bean.BoomMenuitemBean;
import com.example.android_cjj118.databinding.FragmentChartBinding;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class ChartFragment extends Fragment {

    private @NonNull FragmentChartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChartViewModel chartViewModel =
                new ViewModelProvider(this).get(ChartViewModel.class);

        binding = FragmentChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        BoomMenuButton bmb = root.findViewById(R.id.bmb);
        chartViewModel.getBoomMenuItemList().observe(getViewLifecycleOwner(), boomMenuitemBeans -> {
            for(BoomMenuitemBean boomMenuitemBean:boomMenuitemBeans){
                TextInsideCircleButton.Builder builer = new TextInsideCircleButton.Builder();
                builer.normalText(boomMenuitemBean.getTitle())
                        .normalImageRes(boomMenuitemBean.getImageid())
                        .listener(index -> {
                            switch (index){
                                case 0:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_lineFragment);
                                    break;
                                case 1:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_barFragment);
                                    break;
                                case 2:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_pieFragment);
                                    break;
                                case 3:
                                    //实心饼图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_pie2Fragment);
                                    break;
                                case 4:
                                    //气泡图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_bubbleFragment);
                                    break;
                                case 5:
                                    //蜡烛图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_candleFragment);
                                    break;
                                case 6:
                                    //曲线折线图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_line2Fragment);
                                    break;
                                case 7:
                                    ///水平柱状图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_horizontalBarFragment2);
                                    break;
                                case 8:
                                    //雷达图
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_radarFragment);
                                    break;
                            }
                        });
                bmb.addBuilder(builer);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}