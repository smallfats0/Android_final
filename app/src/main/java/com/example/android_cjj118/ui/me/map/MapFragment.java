package com.example.android_cjj118.ui.me.map;

import static com.just.agentweb.AgentActionFragment.REQUEST_CODE;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.android_cjj118.R;

public class MapFragment extends Fragment {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private  static final int REQUEST_CODE=0x1001;
    private LocationClient mLocationClient;
    private PoiSearch mPoiSearch;
    private LatLng latLng;

    public class MyPoiOverlay extends PoiOverlay{

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            if (getPoiResult().getAllPoi() != null) {
                PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
                if (poiInfo != null) {
                    Toast.makeText(getContext(),
                                    "名称:"+poiInfo.name+
                                            "\n地址:"+poiInfo.address+
                                            "\n电话:"+poiInfo.phoneNum
                                    , Toast.LENGTH_LONG)
                            .show();
                }
            }
            return super.onPoiClick(i);
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            latLng = new LatLng(location.getLatitude(),location.getLongitude());
            mBaiduMap.setMyLocationData(locData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        //获取地图控件引用
        mMapView = root.findViewById(R.id.bmapView);

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&
                getActivity().checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        android.content.pm.PackageManager.PERMISSION_GRANTED&&
                getActivity().checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                        android.content.pm.PackageManager.PERMISSION_GRANTED){
            getActivity().requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

        }
        mBaiduMap.setMyLocationEnabled(true);

        //定位初始化
        try {
            mLocationClient = new LocationClient(getContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
    //开启地图定位图层
        mLocationClient.start();

        MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS,
                true,null);
        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);

        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();

                    //创建PoiOverlay对象
                    MyPoiOverlay poiOverlay = new MyPoiOverlay(mBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                    mBaiduMap.setOnMarkerClickListener(poiOverlay);
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        EditText editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v->{
            String keywork = editText.getText().toString();
            mPoiSearch.searchNearby(new PoiNearbySearchOption()
                    .location(latLng)
                    .radius(10000)
                    //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
                    .keyword(keywork));

        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==REQUEST_CODE){
            if(grantResults[0]==android.content.pm.PackageManager.PERMISSION_GRANTED&&
                    grantResults[1]==android.content.pm.PackageManager.PERMISSION_GRANTED){
                mLocationClient.restart();
            }else {
                Toast.makeText(getActivity(),"你拒绝了GPS定位权限，无法定位",Toast.LENGTH_SHORT).show();
            }
        }
    }
}