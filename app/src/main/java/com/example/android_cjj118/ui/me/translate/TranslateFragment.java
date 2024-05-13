package com.example.android_cjj118.ui.me.translate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.example.android_cjj118.bean.RespondBean;
import com.example.android_cjj118.ui.me.translate.util.BaiduTranslateService;
import com.example.android_cjj118.ui.me.translate.util.MD5Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateFragment extends BaseFragment2 {

    String TAG = "MainActivity";
    Button button;
    EditText editText;
    TextView textView;
    static String to;//目标译文 可变 zh中文 en英文

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_translate, container, false);
        button = root.findViewById(R.id.bt_translate);
        editText = root.findViewById(R.id.et_word);
        textView = root.findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate(root);
            }
        });
        return root;
    }



    //Button onclick属性对应的监听函数
    public void translate(View view) {

        //准备请求百度翻译接口需要的参数
        String word = editText.getText().toString();//需查询的单词 q
        String from = "auto";//源语种 en 英语 zh 中文

        //String中英文占用一个字节，中文占用两个字节，
        //利用String的这个存储特性可以用来判断String中有没有中文。
        //原文链接：https://blog.csdn.net/u012005549/article/details/82685063
        if (word.length() == word.getBytes().length) {//成立则说明没有汉字，否则由汉字。
            to = "zh"; //没有汉字 英译中
        } else {
            to = "en";//含有汉字 中译英
        }
        String appid = "20240513002050008";//appid 管理控制台有
        String salt = (int) (Math.random() * 100 + 1) + "";//随机数 这里范围是[0,100]整数 无强制要求
        String key = "xFa7Li3ZrOi8_jU8RGma";//密钥 管理控制台有
        String string1 = appid + word + salt + key;// string1 = appid+q+salt+密钥
        String sign = MD5Utils.getMD5Code(string1);// 签名 = string1的MD5加密 32位字母小写
        Log.d(TAG, "string1：" + string1);
        Log.d(TAG, "sign: " + sign);

        Retrofit retrofitBaidu = new Retrofit.Builder()
                .baseUrl("https://fanyi-api.baidu.com/api/trans/vip/")
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .build();
        BaiduTranslateService baiduTranslateService = retrofitBaidu.create(BaiduTranslateService.class);


        Call<RespondBean> call = baiduTranslateService.translate(word, from, to, appid, salt, sign);
        call.enqueue(new Callback<RespondBean>() {
            @Override
            public void onResponse(Call<RespondBean> call, Response<RespondBean> response) {
                //请求成功
                Log.d(TAG, "onResponse: 请求成功");
                RespondBean respondBean = response.body();//返回的JSON字符串对应的对象
                String result = respondBean.getTrans_result().get(0).getDst();//获取翻译的字符串String
                Log.d(TAG, "英译中结果" + result);
                textView.setText(result);
            }

            @Override
            public void onFailure(Call<RespondBean> call, Throwable t) {
                //请求失败 打印异常
                Log.d(TAG, "onResponse: 请求失败 " + t);
            }
        });
    }
}

