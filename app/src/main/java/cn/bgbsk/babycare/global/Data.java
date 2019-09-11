package cn.bgbsk.babycare.global;

import android.app.Application;
import android.content.SharedPreferences;

import java.sql.Timestamp;

public class Data extends Application {

    private static String url;
    private static String username;
    private static String phone;
    private static int loginStatus;
    private static Timestamp created;
    private static boolean mqttStatus;

    private static SharedPreferences loginSP;
    private static SharedPreferences.Editor loginEdit;

    public static String phoneNumber="18056929880";//预设手机号

    public static boolean boxsStatus=true;


    @Override
    public void onCreate() {
        /*用户信息*/
        url = "https://babycare.bgbsk.cn";
        username = "未登录";
        loginStatus = 0;
        phone = "未登录";
        mqttStatus = false;

        super.onCreate();
    }


    /**用户相关函数*/
    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setUsername(String username) {
        Data.username = username;
    }

    public static int getLoginStatus() {
        return loginStatus;
    }

    public static Timestamp getCreated() {
        return created;
    }

    public static void setCreated(Timestamp created) {
        Data.created = created;
    }

    public static void setLoginStatus(int loginStatus) {
        Data.loginStatus = loginStatus;
    }

    public static void setPhone(String phone) {
        Data.phone = phone;
    }

    public static boolean isMqttStatus() {
        return mqttStatus;
    }

    public static void setMqttStatus(boolean mqttStatus) {
        Data.mqttStatus = mqttStatus;
    }

    public static SharedPreferences getLoginSP() {
        return loginSP;
    }

    public static void setLoginSP(SharedPreferences loginSP) {
        Data.loginSP = loginSP;
    }

    public static SharedPreferences.Editor getLoginEdit() {
        return loginEdit;
    }

    public static void setLoginEdit(SharedPreferences.Editor loginEdit) {
        Data.loginEdit = loginEdit;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
