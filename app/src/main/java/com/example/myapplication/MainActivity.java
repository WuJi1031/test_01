package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


public class MainActivity extends AppCompatActivity {


public Button mbutto;
 public SharedPreferences du;
 public SharedPreferences.Editor xie;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutto = findViewById(R.id.button);

        du = getSharedPreferences("data", MODE_PRIVATE);
        xie = getSharedPreferences("data", MODE_PRIVATE).edit();


        if(du.getString("chenshi", "")!=null) {
            Intent intent = new Intent(MainActivity.this, Zhongjian.class);
            startActivity(intent);
            finish();
        }

//没啥用，搞个启动界面
        mbutto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Zhongjian.class);
                startActivity(intent);
                finish();

          //      xie.putString("chenshi", medit.getText().toString());
         //       xie.apply();
            }
        });

    }
}