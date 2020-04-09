package com.example.myapplication;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

/**
 * Created by lwj on 2020/4/1.
 */


public class Login {

    public static  void login(){
        HeConfig.init("HE2003291843541953", "e588ac07a7604914a815006830ae8c6a");
      //  HeConfig.switchToFreeServerNode();
        HeConfig.switchToCNBusinessServerNode();
      //  try{Thread.sleep(1000);}catch (Exception o){}
    }
}
