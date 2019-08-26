package cn.bgbsk.babycare.global;

import android.app.Application;

public class Data extends Application {


    private static String url;
    private String username;
    private String phone;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
