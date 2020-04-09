package com.example.myapplication;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lwj on 2020/4/1.
 */

public class Fenxi {
private String tianqi,fengxaing,shijian,dizhi;
private Integer wendu;

    public Fenxi() { }
    public Fenxi(String s) {

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(s);
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            JsonObject now = object.getAsJsonObject("now");
            tianqi = now.get("cond_txt").getAsString();
            wendu = now.get("tmp").getAsInt();
            fengxaing = now.get("wind_dir").getAsString();
            Log.i("TAG", tianqi + wendu + fengxaing);
            JsonObject update = object.getAsJsonObject("update");
            shijian = update.get("loc").getAsString();
            int s3 = shijian.indexOf(" ");
            shijian = shijian.substring(s3);
            Log.i("TAG", shijian);
            JsonObject basic = object.getAsJsonObject("basic");
            dizhi = basic.get("location").getAsString();
            Log.i("TAG", dizhi);


        }

    }
}