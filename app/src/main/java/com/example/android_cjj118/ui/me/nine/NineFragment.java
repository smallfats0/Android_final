package com.example.android_cjj118.ui.me.nine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.android_cjj118.R;
import java.util.HashMap;
import java.util.Map;

public class NineFragment extends Fragment {

    // 定义九个图片按钮，命名方法也是00,01这样的横纵坐标
    ImageButton ib00, ib01, ib02, ib10, ib11, ib12, ib20, ib21, ib22;
    // 一个重启按钮
    Button restartBtn;
    // 一个显示时间的文本框
    TextView timeTv;
    // 定义计数时间的变量
    int time = 0;
    // 每行的图片个数
    private int imageX = 3;
    // 每列的图片个数
    private int imageY = 3;
    // 图片的总数目
    private int imgCount = imageX * imageY;
    // 空白区域的位置
    private int blankSwap = imgCount - 1;
    // 初始化空白区域的按钮id
    private int blankImgid = R.id.pt_ib_02x02;

    View root;

    // 将每张碎片的id存放到数组中，便于进行统一的管理,int型数组存放的肯定是int型变量
    private int[] image = {
            R.mipmap.img_xiaoxiong_00x00, R.mipmap.img_xiaoxiong_00x01, R.mipmap.img_xiaoxiong_00x02,
            R.mipmap.img_xiaoxiong_01x00, R.mipmap.img_xiaoxiong_01x01, R.mipmap.img_xiaoxiong_01x02,
            R.mipmap.img_xiaoxiong_02x00, R.mipmap.img_xiaoxiong_02x01, R.mipmap.img_xiaoxiong_02x02
    };

    // 声明上面图片数组下标的数组，随机排列这个数组，九张图片，下标为0-8
    private int[] imageIndex = new int[image.length];

    // 创建一个Map来保存按钮ID和索引的映射
    private Map<Integer, Integer> buttonIdToIndexMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_nine, container, false);
        // 初始化layout控件的方法
        initView();

        // 初始化按钮ID与索引的映射
        initButtonIdToIndexMap();

        disruptRandom();

        // 设置点击监听器
        for (Integer buttonId : buttonIdToIndexMap.keySet()) {
            root.findViewById(buttonId).setOnClickListener(this::onClick);
        }
        restartBtn.setOnClickListener(this::restart);

        return root;
    }

    /* 初始化控件:绑定9个图片按钮，1个显示时间的文本框，1个重启按钮*/
    private void initView() {
        ib00 = root.findViewById(R.id.pt_ib_00x00);
        ib01 = root.findViewById(R.id.pt_ib_00x01);
        ib02 = root.findViewById(R.id.pt_ib_00x02);
        ib10 = root.findViewById(R.id.pt_ib_01x00);
        ib11 = root.findViewById(R.id.pt_ib_01x01);
        ib12 = root.findViewById(R.id.pt_ib_01x02);
        ib20 = root.findViewById(R.id.pt_ib_02x00);
        ib21 = root.findViewById(R.id.pt_ib_02x01);
        ib22 = root.findViewById(R.id.pt_ib_02x02);
        timeTv = root.findViewById(R.id.pt_tv_time);
        restartBtn = root.findViewById(R.id.pt_btn_restart);
    }

    /* 初始化按钮ID与索引的映射 */
    private void initButtonIdToIndexMap() {
        buttonIdToIndexMap.put(R.id.pt_ib_00x00, 0);
        buttonIdToIndexMap.put(R.id.pt_ib_00x01, 1);
        buttonIdToIndexMap.put(R.id.pt_ib_00x02, 2);
        buttonIdToIndexMap.put(R.id.pt_ib_01x00, 3);
        buttonIdToIndexMap.put(R.id.pt_ib_01x01, 4);
        buttonIdToIndexMap.put(R.id.pt_ib_01x02, 5);
        buttonIdToIndexMap.put(R.id.pt_ib_02x00, 6);
        buttonIdToIndexMap.put(R.id.pt_ib_02x01, 7);
        buttonIdToIndexMap.put(R.id.pt_ib_02x02, 8);
    }

    // 图片按钮的点击事件
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        int id = view.getId();
        if (buttonIdToIndexMap.containsKey(id)) {
            int index = buttonIdToIndexMap.get(id);
            move(id, index);
        }
    }

    // 封装状态还原函数
    private void restore() {
        // 拼图游戏重新开始，允许移动碎片按钮
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
        // 还原被点击的图片按钮变成初始化的模样
        ImageButton clickBtn = root.findViewById(blankImgid);
        clickBtn.setVisibility(View.VISIBLE);
        // 默认隐藏第九张图片
        ImageButton blankBtn = root.findViewById(R.id.pt_ib_02x02);
        blankBtn.setVisibility(View.INVISIBLE);
        // 初始化空白区域的按钮id
        blankImgid = R.id.pt_ib_02x02;
        blankSwap = imgCount - 1;
    }

    // 交换数组指定角标上的数据
    private void swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    // 随机打乱数组当中元素，以不规则的形式进行图片显示
    private void disruptRandom() {
        // 给下标数组每个元素赋值，下标是i,值就为i
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        // 随机打乱数组
        int rand1, rand2;
        for (int j = 0; j < 20; j++) {
            rand1 = (int) (Math.random() * (imageIndex.length - 1));
            do {
                rand2 = (int) (Math.random() * (imageIndex.length - 1));
                if (rand1 != rand2) {
                    break;
                }
            } while (true);
            swap(rand1, rand2);
        }
        // 随机排列到指定的控件上
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib10.setImageResource(image[imageIndex[3]]);
        ib11.setImageResource(image[imageIndex[4]]);
        ib12.setImageResource(image[imageIndex[5]]);
        ib20.setImageResource(image[imageIndex[6]]);
        ib21.setImageResource(image[imageIndex[7]]);
        ib22.setImageResource(image[imageIndex[8]]);
    }

    /* 表示移动指定位置的按钮的函数，将图片和空白区域进行交换 */
    private void move(int imagebuttonId, int site) {
        int sitex = site / imageX;
        int sitey = site % imageY;
        int blankx = blankSwap / imageX;
        int blanky = blankSwap % imageY;
        int x = Math.abs(sitex - blankx);
        int y = Math.abs(sitey - blanky);
        if ((x == 0 && y == 1) || (y == 0 && x == 1)) {
            ImageButton clickButton = root.findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);
            ImageButton blankButton = root.findViewById(blankImgid);
            blankButton.setImageResource(image[imageIndex[site]]);
            blankButton.setVisibility(View.VISIBLE);
            swap(site, blankSwap);
            blankSwap = site;
            blankImgid = imagebuttonId;
        }

        boolean loop = true;
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i] != i) {
                loop = false;
                break;
            }
        }

        if (loop) {
            ib00.setClickable(false);
            ib01.setClickable(false);
            ib02.setClickable(false);
            ib10.setClickable(false);
            ib11.setClickable(false);
            ib12.setClickable(false);
            ib20.setClickable(false);
            ib21.setClickable(false);
            ib22.setClickable(false);
            ib22.setImageResource(image[8]);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("恭喜，拼图成功！")
                    .setPositiveButton("确认", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /* 重新开始按钮的点击事件 */
    public void restart(View view) {
        restore();
        disruptRandom();
    }
}
