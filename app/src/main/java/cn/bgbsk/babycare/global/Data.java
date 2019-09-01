package cn.bgbsk.babycare.global;

import android.app.Application;

import java.sql.Timestamp;

public class Data extends Application {


    private static String url;
    private static String username;
    private static String phone;
    private static int loginStatus;
    private static Timestamp created;


    @Override
    public void onCreate() {

        /*用户信息*/
        url = "https://babycare.bgbsk.cn";
        username = "未登录";
        loginStatus = 0;
        phone = "未登录";
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


}
