package cn.bgbsk.babycare.global;

import android.app.Application;

public class Data extends Application {


    private static String url;
    private static String username;
    private static String phone;

    @Override
    public void onCreate() {
        url = "https://babycare.bgbsk.cn";
        username = "";
        phone = "未连接";
        super.onCreate();
    }


    public static String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }


    public String getPhone() {
        return phone;
    }

    public static void setUsername(String username) {
        Data.username = username;
    }

    public static void setPhone(String phone) {
        Data.phone = phone;
    }
}
