package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

/**
 * Created by lwj on 2020/4/1.
 */

public class Zhongjian extends AppCompatActivity {
    public SharedPreferences du;
    public SharedPreferences.Editor xie;
    public static String tianqi, fengxaing, shijian, dizhi;
    public static Integer wendu;
//    public TextView textView;
    public TextView cpd,thd,citynow,pm25,aqi,wrjb,ssd,cyf,sport;
    public TextView rq1,rq2,rq3,rq4,rq5,rq6;
    public TextView zhouji1,zhouji2,zhouji3,zhouji4,zhouji5,zhouji6;
    public TextView btqw1,btqw2,btqw3,btqw4,btqw5,btqw6;
    public TextView wsqw1,wsqw2,wsqw3,wsqw4,wsqw5,wsqw6,mgfx;
    public ImageView bttq1,bttq2,bttq3,bttq4,bttq5,bttq6,wstq1,wstq2,wstq3,wstq4,wstq5,wstq6,fx1,fx2,fx3,fx4,fx5,fx6,tbnow,fengx;
    public EditText texthuiche;
//    public EditText textView1;
    public Button button,button2,button3,button4;
    public ImageView imageView1,imageView2;
//    public WebView webView;





     public  SpeechSynthesizer mSpeechSynthesizer;
//    public ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather01);
        du = getSharedPreferences("data", MODE_PRIVATE);
        xie = getSharedPreferences("data", MODE_PRIVATE).edit();



        initPermission();
        initTTs();


        //    scrollView = findViewById(R.id.scro);

//        textView = findViewById(R.id.jieguo);
//        textView1 = findViewById(R.id.dizhi);
//        textView2 = findViewById(R.id.jieguo1);
//        textView3 = findViewById(R.id.kqzl);
//        textView4 = findViewById(R.id.cyzs);
//        button = findViewById(R.id.qeihuan);
//        button2 = findViewById(R.id.shuaxin);
//        button3 = findViewById(R.id.toutiao);
//        button4 = findViewById(R.id.speak1);
//        imageView = findViewById(R.id.tupian);
//        webView = findViewById(R.id.web);
//        //text文档显示可以滑动
//        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
//        textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
//        textView3.setMovementMethod(ScrollingMovementMethod.getInstance());
//        textView4.setMovementMethod(ScrollingMovementMethod.getInstance());
        //---------------------------------------划线区属于语音区域------------------------------------------
        //---------------------------------------划线区属于语音区域------------------------------------------
        //---------------------------------------划线区属于语音区域------------------------------------------
        cpd = findViewById(R.id.cpd);
        thd = findViewById(R.id.thd);
        citynow = findViewById(R.id.citynow);
        tbnow =findViewById(R.id.tbnow);
        fengx =findViewById(R.id.fengx);
        texthuiche =findViewById(R.id.texthuiche);
         pm25=findViewById(R.id.pm25);
         aqi=findViewById(R.id.aqi);
        wrjb=findViewById(R.id.wrjb);
        ssd=findViewById(R.id.ssd);
         cyf=findViewById(R.id.cyf);
       sport=findViewById(R.id.sport);
        rq1=findViewById(R.id.rq1);
        rq2=findViewById(R.id.rq2);
       rq3=findViewById(R.id.rq3);
        rq4=findViewById(R.id.rq4);
       rq5=findViewById(R.id.rq5);
       rq6=findViewById(R.id.rq6);
       zhouji1=findViewById(R.id.zhouji1);
       zhouji2=findViewById(R.id.zhouji2);
       zhouji3=findViewById(R.id.zj3);
       zhouji4=findViewById(R.id.zj4);
       zhouji5=findViewById(R.id.zj5);
       zhouji6=findViewById(R.id.zj6);
        btqw1=findViewById(R.id.btqw1);
        btqw2=findViewById(R.id.btqw2);
        btqw3=findViewById(R.id.btqw3);
        btqw4=findViewById(R.id.btqw4);
        btqw5=findViewById(R.id.btqw5);
        btqw6=findViewById(R.id.btqw6);
        wsqw1=findViewById(R.id.wsqw1);
        wsqw2=findViewById(R.id.wsqw2);
        wsqw3=findViewById(R.id.wsqw3);
        wsqw4=findViewById(R.id.wsqw4);
        wsqw5=findViewById(R.id.wsqw5);
        wsqw6=findViewById(R.id.wsqw6);
        bttq1=findViewById(R.id.bttq1);
        bttq2=findViewById(R.id.bttq2);
        bttq3=findViewById(R.id.bttq3);
        bttq4=findViewById(R.id.bttq4);
        bttq5=findViewById(R.id.bttq5);
        bttq6=findViewById(R.id.bttq6);
        wstq1=findViewById(R.id.wstq1);
        wstq2=findViewById(R.id.wstq2);
        wstq3=findViewById(R.id.wstq3);
        wstq4=findViewById(R.id.wstq4);
        wstq5=findViewById(R.id.wstq5);
        wstq6=findViewById(R.id.wstq6);
        fx1 = findViewById(R.id.fx1);
        fx2 = findViewById(R.id.fx2);
        fx3 = findViewById(R.id.fx3);
        fx4 = findViewById(R.id.fx4);
        fx5 = findViewById(R.id.fx5);
        fx6 = findViewById(R.id.fx6);
        mgfx = findViewById(R.id.mgsb);
        button = findViewById(R.id.speaking);
   //     Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/100n.png").into(imageView);
//        Glide.with(this).load("https://cdn.heweather.com/cond_icon/100n.png").into(imageView1);



        //now



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dule = du.getString("speaking","");
                speak(dule);
            }
                                  });


        //---------------------------------------划线区属于语音区域------------------------------------------
        //---------------------------------------划线区属于语音区域------------------------------------------
        //---------------------------------------划线区属于语音区域------------------------------------------



        //---------------------------------------划线区属于按钮控制天气区------------------------------------------
        //---------------------------------------划线区属于按钮控制天气区------------------------------------------
        //---------------------------------------划线区属于按钮控制天气区------------------------------------------
//        if (du.getString("chenshi", "") != null) {
//            textView1.setVisibility(View.GONE);
//            Login.login();
//            jiexi(du.getString("chenshi", ""));
//            jiexi2_0(du.getString("chenshi", ""));
//            jiexi3(du.getString("chenshi", ""));
//            jiexi4(du.getString("chenshi", ""));
//        }
//提交数据
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (textView1.getVisibility() == View.VISIBLE) {
//                    textView1.setVisibility(View.GONE);
//                    String s1 = textView1.getText().toString();
//                    //      textView1.setText("");
//                    xie.putString("chenshi", s1);
//                    xie.apply();
//                    String qq = du.getString("chenshi", "");
//                    Login.login();
//                    Log.i("TAG", qq);
//                    jiexi(qq);
//                    jiexi2_0(qq);
//                    jiexi3(qq);
//                    jiexi4(qq);
//                } else {
//                    textView1.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//控制刷新
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String qq = du.getString("chenshi", "");
//                Login.login();
//                jiexi(qq);
//                jiexi2_0(qq);
//                jiexi3(qq);
//                jiexi4(qq);
//            }
//        });

        //---------------------------------------划线区属于按钮控制天气区------------------------------------------
        //---------------------------------------划线区属于按钮控制天气区------------------------------------------
        //---------------------------------------划线区属于按钮控制天气区------------------------------------------


        //测试区
        //    jiexi3(du.getString("chenshi", ""));
        //    jiexi4(du.getString("chenshi", ""));

        //     webView.getSettings().setJavaScriptEnabled(true);
        //      webView.setWebViewClient(new Myweb());
        //      webView.loadUrl("https://www.baidu.com/");
        //     webView.canGoBack();
        //      webView.setBottom(button3);

//天气循环接口
//        forecastLayout.removeAllViews();
////        if (forecastList != null) {
////            for (Forecast forecast : forecastList) {
////                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
////                TextView txtForecastDate = (TextView) view.findViewById(R.id.txt_forecast_date);
////                TextView txtForecastWeather = (TextView) view.findViewById(R.id.txt_forecast_weather);
////                TextView txtForecastMin = (TextView) view.findViewById(R.id.txt_forecast_min);
////                TextView txtForecastMax = (TextView) view.findViewById(R.id.txt_forecast_max);
////                txtForecastDate.setText(forecast.date);
////                txtForecastMin.setText(forecast.temperature.min);
////                txtForecastMax.setText(forecast.temperature.max);
////                txtForecastWeather.setText(forecast.more.info);
////                forecastLayout.addView(view);
////            }

    //}





        //Editext触发器，无聊加个声明
        texthuiche.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {


                    String s = texthuiche.getText().toString();
                    if(s.equals("版权声明")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Zhongjian.this);
                        builder.setTitle("版权声明").setMessage("本天气软件所有人享视科技\n创建于2020年4月8日").setPositiveButton("好", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Zhongjian.this, "版权声明", Toast.LENGTH_SHORT).show();
                                texthuiche.setText("");
                            }
                        }).show();
                        s="";
                    }
                Log.i("TAG",s);
                    xie.putString("chenshi", s);
                    xie.apply();
                    Login.login();
                jiexi(s);
                jiexi2_0(s);
                jiexi3(s);
               jiexi4(s);

                    return false;   //返回true，保留软键盘。false，隐藏软键盘
                }
                return true;
            }
        });


    }

    //Webview
    public  class Myweb extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    //解析当前天气
    public void jiexi(String s){
        HeWeather.getWeatherNow(Zhongjian.this,s,Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherNowBeanListener() {

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "Weather Now onError: ", e);

            }
            @Override
            public void onSuccess(Now dataObject) {

                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(dataObject));

                if (Code.OK.getCode().equalsIgnoreCase(dataObject.getStatus())) {
                    //此时返回数据
                    Log.d("TAG", "get data successful");
                    /* 此时now就是获得的数据类 , 这是和风SDK的自定义类  */
                    NowBase now = dataObject.getNow();
                    new Gson().toJson(dataObject);
                    String p;
                    p=new Gson().toJson(dataObject);
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(p);
                    if (element.isJsonObject()) {
                        JsonObject object = element.getAsJsonObject();
                        JsonObject now2 = object.getAsJsonObject("now");
                        String tupian = now2.get("cond_code").getAsString();
                        tianqi = now2.get("cond_txt").getAsString();
                        String wendu = now2.get("tmp").getAsString();
                        fengxaing = now2.get("wind_dir").getAsString();
                //        Log.i("TAG", tianqi + wendu + fengxaing);
                        JsonObject update = object.getAsJsonObject("update");
                        shijian = update.get("loc").getAsString();
                        int s3 = shijian.indexOf(" ");
                        shijian = shijian.substring(s3);
                //        Log.i("TAG", shijian);
                        JsonObject basic = object.getAsJsonObject("basic");
                        dizhi = basic.get("location").getAsString();
                  //      Log.i("TAG", dizhi);
                  String ks =     dizhi+"市今日天气："+tianqi+"......"+"当前温度"+wendu+"摄氏度"+"......"+fengxaing;
                    xie.putString("speaking",ks);
                    xie.apply();

                        cpd.setText(wendu+"℃");
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tupian+".png").into(tbnow);
                        citynow.setText(dizhi);
                        getfengxaing(fengxaing,fengx);
                        mgfx.setText(fengxaing);

//                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tupian+".png").into(imageView);

                } else {
                        //在此查看返回数据失败的原因
                        String status = dataObject.getStatus();
                        Code code = Code.toEnum(status);
                        Log.d("TAG", "failed code: " + code);
                        Toast.makeText(Zhongjian.this,"您输入的城市有误"+code,Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    //解析三天天气
    public void jiexi2_0(String s){
    //    s=null;
        HeWeather.getWeatherForecast(Zhongjian.this,s,Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherForecastBeanListener() {

            @Override
            public void onError(Throwable e) {

                Log.i("TAG", "Weather Now onError: ", e);
}

            @Override
            public void onSuccess(Forecast forecast) {
                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(forecast));
//                String p2_0;
//                p2_0 = new Gson().toJson(forecast);
//                JsonParser parser = new JsonParser();
//                JsonElement element = parser.parse(p2_0);
//                if(element.isJsonObject()){
//                    JsonObject object = element.getAsJsonObject();
//                    JsonObject daily = object.getAsJsonObject();
//                    Log.i("TAG", " Json: " + daily);
//                    JsonArray daily2_0 = object.getAsJsonArray("daily_forecast");
//
//                    JsonObject array1_0 = daily2_0.get(0).getAsJsonObject();
//                    Log.i("TAG", " Json: " + array1_0);
//                    String tianqi1_0 = array1_0.get("cond_txt_d").getAsString();
//                    Integer wendu1_a = array1_0.get("tmp_max").getAsInt();
//                    Integer wendu1_b = array1_0.get("tmp_min").getAsInt();
//                    String fengxaing1_0 = array1_0.get("wind_dir").getAsString();
//                    Log.i("TAG", " Json: " + tianqi1_0+wendu1_a+wendu1_b+fengxaing1_0);
//
//                    JsonObject array2_0 = daily2_0.get(1).getAsJsonObject();
//                    Log.i("TAG", " Json: " + array2_0);
//                    String tianqi2_0 = array2_0.get("cond_txt_d").getAsString();
//                    Integer wendu2_a = array2_0.get("tmp_max").getAsInt();
//                    Integer wendu2_b = array2_0.get("tmp_min").getAsInt();
//                    String fengxaing2_0 = array2_0.get("wind_dir").getAsString();
//                    Log.i("TAG", " Json: " + tianqi2_0+wendu2_a+wendu2_b+fengxaing2_0);
//
//                    JsonObject array3_0 = daily2_0.get(2).getAsJsonObject();
//                    Log.i("TAG", " Json: " + array3_0);
//                    String tianqi3_0 = array3_0.get("cond_txt_d").getAsString();
//                    Integer wendu3_a = array3_0.get("tmp_max").getAsInt();
//                    Integer wendu3_b = array3_0.get("tmp_min").getAsInt();
//                    String fengxaing3_0 = array3_0.get("wind_dir").getAsString();
//                    Log.i("TAG", " Json: " + tianqi3_0+wendu3_a+wendu3_b+fengxaing3_0);
//
//                    textView2.setText("第一天："+"天气"+tianqi1_0+"最高温度"+wendu1_a+"最低温度"+wendu1_b+"风向"+fengxaing1_0+"\n"+"第二天："+"天气"+tianqi2_0+"最高温度"+wendu2_a+"最低温度"+wendu2_b+"风向"+fengxaing2_0+"\n"+"第三天："+"天气"+tianqi3_0+"最高温度"+wendu3_a+"最低温度"+wendu3_b+"风向"+fengxaing3_0+"\n");
                if (Code.OK.getCode().equalsIgnoreCase(forecast.getStatus())) {
                    //此时返回数据
                    Log.d("TAG", "get data successful");


                    String p2_0;
                    p2_0 = new Gson().toJson(forecast);
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(p2_0);
                    if (element.isJsonObject()) {
                        JsonObject object = element.getAsJsonObject();
                        JsonObject daily = object.getAsJsonObject();
                  //      Log.i("TAG", " Json: " + daily);
                        JsonArray daily2_0 = object.getAsJsonArray("daily_forecast");

                        JsonObject array1_0 = daily2_0.get(0).getAsJsonObject();
                 //       Log.i("TAG", " Json: " + array1_0);
                        String tianqi1_0 = array1_0.get("cond_txt_d").getAsString();
                        String tianqi1_1 = array1_0.get("cond_code_n").getAsString();
                        Integer wendu1_a = array1_0.get("tmp_max").getAsInt();
                        Integer wendu1_b = array1_0.get("tmp_min").getAsInt();
                        String fengxaing1_0 = array1_0.get("wind_dir").getAsString();
                        thd.setText(wendu1_b+"/"+wendu1_a+"℃");

                    //    Log.i("TAG", " Json: " + tianqi1_0 + wendu1_a + wendu1_b + fengxaing1_0);

                        JsonObject array2_0 = daily2_0.get(1).getAsJsonObject();
                //        Log.i("TAG", " Json: " + array2_0);
                        String tianqi2_0 = array2_0.get("cond_code_d").getAsString();
                        String tianqi2_a = array2_0.get("cond_code_n").getAsString();
                        String wendu2_a = array2_0.get("tmp_max").getAsString();
                        String wendu2_b = array2_0.get("tmp_min").getAsString();
                        String fengxaing2_0 = array2_0.get("wind_dir").getAsString();
                         String riqi1 = array2_0.get("date").getAsString();
                        //      Log.i("TAG", " Json: " + tianqi2_0 + wendu2_a + wendu2_b + fengxaing2_0);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi2_0+".png").into(bttq1);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi2_a+".png").into(wstq1);
                        getfengxaing(fengxaing2_0,fx1);
                        btqw1.setText(wendu2_a+"℃");
                        wsqw1.setText(wendu2_b+"℃");
                        try {
                            String s =getriqi(riqi1);
                            Log.i("TAG",s);
                            rq1.setText(getriqi(riqi1));
                        }catch (Exception o){ }
                        zhouji1.setText(dateToWeek(riqi1));
                        Log.i("LAG",tianqi2_0+tianqi2_a+fengxaing2_0+wendu2_a+wendu2_b+dateToWeek(riqi1));



                        JsonObject array3_0 = daily2_0.get(2).getAsJsonObject();
                  //      Log.i("TAG", " Json: " + array3_0);
                        String tianqi3_0 = array3_0.get("cond_code_d").getAsString();
                        String tianqi3_a = array3_0.get("cond_code_n").getAsString();
                        String wendu3_a = array3_0.get("tmp_max").getAsString();
                        String wendu3_b = array3_0.get("tmp_min").getAsString();
                        String fengxaing3_0 = array3_0.get("wind_dir").getAsString();
                        String riqi2 = array3_0.get("date").getAsString();
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi3_0+".png").into(bttq2);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi3_a+".png").into(wstq2);
                        getfengxaing(fengxaing3_0,fx2);
                        btqw2.setText(wendu3_a+"℃");
                        wsqw2.setText(wendu3_b+"℃");
                        try {
                            rq2.setText(getriqi(riqi2));
                        }catch (Exception o){ }
                        zhouji2.setText(dateToWeek(riqi2));

                        JsonObject array4_0 = daily2_0.get(3).getAsJsonObject();
                        //      Log.i("TAG", " Json: " + array3_0);
                        String tianqi4_0 = array4_0.get("cond_code_d").getAsString();
                        String tianqi4_a = array4_0.get("cond_code_n").getAsString();
                        String wendu4_a = array4_0.get("tmp_max").getAsString();
                        String wendu4_b = array4_0.get("tmp_min").getAsString();
                        String fengxaing4_0 = array4_0.get("wind_dir").getAsString();
                        String riqi3 = array4_0.get("date").getAsString();
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi4_0+".png").into(bttq3);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi4_a+".png").into(wstq3);
                        getfengxaing(fengxaing4_0,fx3);
                        btqw3.setText(wendu4_a+"℃");
                        wsqw3.setText(wendu4_b+"℃");
                        try {
                            rq3.setText(getriqi(riqi3));
                        }catch (Exception o){ }
                        zhouji3.setText(dateToWeek(riqi3));

                        JsonObject array5_0 = daily2_0.get(4).getAsJsonObject();
                        //      Log.i("TAG", " Json: " + array3_0);
                        String tianqi5_0 = array5_0.get("cond_code_d").getAsString();
                        String tianqi5_a = array5_0.get("cond_code_n").getAsString();
                        String wendu5_a = array5_0.get("tmp_max").getAsString();
                        String wendu5_b = array5_0.get("tmp_min").getAsString();
                        String fengxaing5_0 = array5_0.get("wind_dir").getAsString();
                        String riqi4 = array5_0.get("date").getAsString();
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi5_0+".png").into(bttq4);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi5_a+".png").into(wstq4);
                        getfengxaing(fengxaing5_0,fx4);
                        btqw4.setText(wendu5_a+"℃");
                        wsqw4.setText(wendu5_b+"℃");
                        try {
                            rq4.setText(getriqi(riqi4));
                        }catch (Exception o){ }
                        zhouji4.setText(dateToWeek(riqi4));

                        JsonObject array6_0 = daily2_0.get(5).getAsJsonObject();
                        //      Log.i("TAG", " Json: " + array3_0);
                        String tianqi6_0 = array6_0.get("cond_code_d").getAsString();
                        String tianqi6_a = array6_0.get("cond_code_n").getAsString();
                        String wendu6_a = array6_0.get("tmp_max").getAsString();
                        String wendu6_b = array6_0.get("tmp_min").getAsString();
                        String fengxaing6_0 = array6_0.get("wind_dir").getAsString();
                        String riqi5 = array6_0.get("date").getAsString();
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi6_0+".png").into(bttq5);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi6_a+".png").into(wstq5);
                        getfengxaing(fengxaing6_0,fx5);
                        btqw5.setText(wendu6_a+"℃");
                        wsqw5.setText(wendu6_b+"℃");
                        try {
                            rq5.setText(getriqi(riqi5));
                        }catch (Exception o){ }
                        zhouji5.setText(dateToWeek(riqi5));

                        JsonObject array7_0 = daily2_0.get(6).getAsJsonObject();
                        //      Log.i("TAG", " Json: " + array3_0);
                        String tianqi7_0 = array7_0.get("cond_code_d").getAsString();
                        String tianqi7_a = array7_0.get("cond_code_n").getAsString();
                        String wendu7_a = array7_0.get("tmp_max").getAsString();
                        String wendu7_b = array7_0.get("tmp_min").getAsString();
                        String fengxaing7_0 = array7_0.get("wind_dir").getAsString();
                        String riqi6 = array7_0.get("date").getAsString();
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi7_0+".png").into(bttq6);
                        Glide.with(Zhongjian.this).load("https://cdn.heweather.com/cond_icon/"+tianqi7_a+".png").into(wstq6);
                        getfengxaing(fengxaing7_0,fx6);
                        btqw6.setText(wendu7_a+"℃");
                        wsqw6.setText(wendu7_b+"℃");
                        try {
                            rq6.setText(getriqi(riqi6));
                        }catch (Exception o){ }
                        zhouji6.setText(dateToWeek(riqi6));
































                    } else {
                        //在此查看返回数据失败的原因
                        String status = forecast.getStatus();
                        Code code = Code.toEnum(status);
                        Log.d("TAG", "failed code: " + code);
                        Toast.makeText(Zhongjian.this,"您输入的城市有误"+code,Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    //解析空气质量
    public void jiexi3(String s){
            HeWeather.getAirNow(Zhongjian.this, s, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultAirNowBeansListener() {
                @Override
                public void onError(Throwable e) {
                    Log.i("TAG", "Weather Now onError: ", e);
                }

                @Override
                public void onSuccess(AirNow airNow) {
                    Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(airNow));

                    if (Code.OK.getCode().equalsIgnoreCase(airNow.getStatus())) {
                        //此时返回数据
                        Log.d("TAG", "get data successful");

                        String kq = new Gson().toJson(airNow);

                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(kq);

                        if(element.isJsonObject()){
                            JsonObject object = element.getAsJsonObject();

                            JsonObject object2 =    object.getAsJsonObject("air_now_city");
                            String sqi = object2.get("aqi").getAsString();
                            String pm256 = object2.get("pm25").getAsString();
                            String kqzlpl = object2.get("qlty").getAsString();

                        aqi.setText("AQI:"+sqi);
                        pm25.setText("PM2.5:"+pm256);
                        wrjb.setText("污染等级:"+kqzlpl);
                        }



                    }else {
                        //在此查看返回数据失败的原因
                        String status = airNow.getStatus();
                        Code code = Code.toEnum(status);
                        Log.d("TAG", "failed code: " + code);
                    }
                }
            });
        }

     //解析生活指数
    public void jiexi4(String s){
        HeWeather.getWeatherLifeStyle(Zhongjian.this, s, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherLifeStyleBeanListener() {
            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "Weather Now onError: ", e);
            }
            @Override
            public void onSuccess(Lifestyle lifestyle) {
                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(lifestyle));
                if (Code.OK.getCode().equalsIgnoreCase(lifestyle.getStatus())) {
                    //此时返回数据
                    Log.d("TAG", "get data successful");

                    String wcn = new Gson().toJson(lifestyle);

                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(wcn);

                    if (element.isJsonObject()) {
                        JsonObject object = element.getAsJsonObject();
                        JsonObject life = object.getAsJsonObject();
                        //                  Log.i("TAG", " Json: " + life);
                        JsonArray life2 = object.getAsJsonArray("lifestyle");

                        JsonObject array0 = life2.get(0).getAsJsonObject();
                        //舒适度指数
                        String txt = array0.get("txt").getAsString();
                        ssd.setText("舒适度指数："+txt);
                        //穿衣指数
                        JsonObject array1 = life2.get(1).getAsJsonObject();
                        String dc = array1.get("txt").getAsString();
                        cyf.setText("穿衣指数："+dc);
                        //运动指数
                        JsonObject array2 = life2.get(3).getAsJsonObject();
                        String yd = array2.get("txt").getAsString();
                        sport.setText("运动指数："+yd);


                    }
                }else {
                    //在此查看返回数据失败的原因
                    String status = lifestyle.getStatus();
                    Code code = Code.toEnum(status);
                    Log.d("TAG", "failed code: " + code);
                }
            }
        });
    }

    //动态获取权限
    public void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    //百度语音合成
    public void initTTs() {

        String appId ="19221289";
        String appKey = "QVtOMbAEHpaxGCOAP3nVXVWX";
        String secretKey ="sM03tQMl6VM3vvLOgKFHj0Sh7rLbOLIH";
        TtsMode ttsMode = TtsMode.ONLINE;
        LoggerProxy.printable(true); // 日志打印在logcat中





        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);


        mSpeechSynthesizer.setAppId(appId);

        mSpeechSynthesizer.setApiKey(appKey, secretKey);



        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);

        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "4");
        // 设置合成的语调，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");

        // 6. 初始化
        mSpeechSynthesizer.initTts(ttsMode);





    }
    //语音播放speak
    public  void speak(String s){
        mSpeechSynthesizer.speak(s);
    }
    //判断星期几
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }
    //判断风向
    public void getfengxaing(String s,ImageView img){
        switch (s){
            case "无持续风向":{img.setImageResource(R.drawable.bqdfx);break;}
            case "旋转风":{img.setImageResource(R.drawable.xzf);break;}
            case "北风":{img.setImageResource(R.drawable.bf);break;}
            case "东北风":{img.setImageResource(R.drawable.dbf);break;}
            case "东风":{img.setImageResource(R.drawable.df);break;}
            case "东南风":{img.setImageResource(R.drawable.dnf);break;}
            case "南风":{img.setImageResource(R.drawable.nf);break;}
            case "西南风":{img.setImageResource(R.drawable.xnf);break;}
            case "西风":{img.setImageResource(R.drawable.xf);break;}
            case "西北风":{img.setImageResource(R.drawable.xbf);break;}
                default:{}
                    break;
        }
    }
    //判断几月几号
    public  String getriqi(String s) throws ParseException {

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
            String now = new SimpleDateFormat("MM月dd日").format(date);
        if (now.startsWith("0")) {
            String sd = now.substring(1);
            return sd;

        }else {
            return now;
        }
    }
    @ Override
    protected void onStart() {
        super.onStart();
        String s =du.getString("chenshi", "");
        if (s != null) {

            Login.login();
            jiexi(s);
            jiexi2_0(s);
            jiexi3(s);
            jiexi4(s);
        }else{
            s="深圳";
            Login.login();
            jiexi(s);
            jiexi2_0(s);
            jiexi3(s);
            jiexi4(s);
        }
        Log.e("TAG", "onStart");
    } @
            Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume");
    } @
            Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "onPause");
    } @
            Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG", "onStop");
    }@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");
    } @
            Override
    protected void onRestart() {
        super.onRestart();
        Log.e("TAG", "onRestart");
    }
}