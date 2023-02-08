package com.example.jetpacktest02.utils;



import android.annotation.TargetApi;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.Manifest;
import android.content.pm.PackageManager;

import com.example.jetpacktest02.config.UsersApplication;
import com.example.jetpacktest02.screen.LocationDetails;

/**
 * Created by Administrator on 2018/4/17.
 * 获取用户的地理位置
 */
public class GPSUtils {

    private static GPSUtils instance;
    private LocationManager locationManager;
    public static final int LOCATION_CODE = 1000;
    public static final int OPEN_GPS_CODE = 1001;

    public  String province = "";
    public static GPSUtils getInstance() {
        if (instance == null) {
            instance = new GPSUtils();
        }
        return instance;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public LocationDetails  getProvince() {
        Log.i("GPS: ", "getProvince");
        locationManager = (LocationManager) UsersApplication.instance.getSystemService(Context.LOCATION_SERVICE);        // 默认Android GPS定位实例

        Location location = null;
        // 是否已经授权
        if (UsersApplication.instance.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            //判断GPS是否开启，没有开启，则开启
//            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//                //跳转到手机打开GPS页面
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                //设置完成后返回原来的界面
//                AppActivity.instance.startActivityForResult(intent,OPEN_GPS_CODE);
//            }
//
//            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);      // GPS芯片定位 需要开启GPS
//            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);      // 利用网络定位 需要开启GPS
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);      // 其他应用使用定位更新了定位信息 需要开启GPS
        }

        String p = "";
        LocationDetails result=new LocationDetails(0.0f,0.0f);
        if(location != null)
        {
            Log.i("GPS: ", "获取位置信息成功");
            Log.i("GPS: ","经度：" + location.getLatitude());
            Log.i("GPS: ","纬度：" + location.getLongitude());

            // 获取地址信息
            p = getAddress(location.getLatitude(),location.getLongitude());
            Log.i("GPS: ","location：" + p);
            result = new LocationDetails(location.getLatitude(),location.getLongitude());
        }
        else
        {
            Log.i("GPS: ", "获取位置信息失败，请检查是够开启GPS,是否授权");
        }

        return result;
    }

    /*
     * 根据经度纬度 获取国家，省份
     * */
    public String getAddress(double latitude, double longitude) {
        String cityName = "";
        List<Address> addList = null;
        Geocoder ge = new Geocoder(UsersApplication.instance);
        try {
            addList = ge.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addList != null && addList.size() > 0) {
            for (int i = 0; i < addList.size(); i++) {
                Address ad = addList.get(i);
                cityName += ad.getCountryName() + " " + ad.getLocality();
            }
        }
        return cityName;
    }
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    private static double EARTH_RADIUS1 = 6371000;

    public double getDistance(double lon1,double lat1,double lon2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS1;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    public double getAngle1(double lat_a, double lng_a, double lat_b, double lng_b) {

        double y = Math.sin(lng_b-lng_a) * Math.cos(lat_b);
        double x = Math.cos(lat_a)*Math.sin(lat_b) - Math.sin(lat_a)*Math.cos(lat_b)*Math.cos(lng_b-lng_a);
        double brng = Math.atan2(y, x);
        brng = Math.toDegrees(brng);
        if(brng < 0)
            brng = brng +360;
        return brng;
    }
    //x距离
    public double getA(double lat_a, double lng_a, double lat_b, double lng_b){
        double distance = getDistance(lng_a,lat_a,lng_b,lat_b);
        double angle =  getAngle1(lat_a,lng_a,lat_b,lng_b);
        double a = distance *  Math.cos(angle);
        return a;
    }
    //y距离
    public double getB(double lat_a, double lng_a, double lat_b, double lng_b){
        double distance = getDistance(lng_a,lat_a,lng_b,lat_b);
        double angle =  getAngle1(lat_a,lng_a,lat_b,lng_b);
        double b = distance *  Math.sin(angle);
        return b;
    }

    public  Float mockFloatBetween2(Float begin, Float end) {
        BigDecimal bigDecimal = new BigDecimal(end - begin);
        BigDecimal point = new BigDecimal(Math.random());
        BigDecimal pointBetween = point.multiply(bigDecimal);
        BigDecimal result = pointBetween.add(new BigDecimal(begin)).setScale(2, BigDecimal.ROUND_FLOOR);
        return result.floatValue();
    }


//    public double getOffset(double lon1,double lat1,double lon2, double lat2){
//        if (lon1<=lon2){
//
//        }else{
//
//        }
//        if (lat1<=lat2){
//
//        }else{
//
//        }
//        double distance = getDistance(lon1,lat1,lon2,lat2);
//        return
//    }
}
