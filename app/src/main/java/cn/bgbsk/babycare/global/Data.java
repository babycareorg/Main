package cn.bgbsk.babycare.global;

import android.app.Application;

public class Data extends Application {


    private static String url;
    private static String username;
    private static String phone;
    private static int loginStatus;

    @Override
    public void onCreate() {
        url = "https://babycare.bgbsk.cn";
        username = "";
        loginStatus = 0;
        phone = "未连接";
        super.onCreate();
    }


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

    public static void setLoginStatus(int loginStatus) {
        Data.loginStatus = loginStatus;
    }

    public static void setPhone(String phone) {
        Data.phone = phone;
    }
}
