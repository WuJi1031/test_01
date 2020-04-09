package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

/**
 * Created by lwj on 2020/4/1.
 */



public class Nowweather extends AppCompatActivity{
    public SharedPreferences du;
    public SharedPreferences.Editor xie;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);

        du = getSharedPreferences("data", MODE_PRIVATE);
        xie = getSharedPreferences("data", MODE_PRIVATE).edit();

    }
    public void plmm(String weatherID) {

        HeWeather.getWeatherNow(Nowweather.this, "深圳", Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherNowBeanListener() {

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "Weather Now onError: ", e);
            }
            @Override
            public void onSuccess(Now dataObject) {
                /*  下面打印出来获得的json数据  */
                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(dataObject));
                String p;
                 p = new Gson().toJson(dataObject);
                xie.putString("shuju",p);
                xie.apply();
            //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if (Code.OK.getCode().equalsIgnoreCase(dataObject.getStatus())) {
                    //此时返回数据
                    Log.d("TAG", "get data successful");
                    /* 此时now就是获得的数据类 , 这是和风SDK的自定义类  */
                    NowBase now = dataObject.getNow();
                } else {
                    //在此查看返回数据失败的原因
                    String status = dataObject.getStatus();
                    Code code = Code.toEnum(status);
                    Log.d("TAG", "failed code: " + code);
                }
            }
        });


    }


}