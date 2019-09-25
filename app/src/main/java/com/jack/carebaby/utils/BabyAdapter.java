package com.jack.carebaby.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jack.carebaby.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import cn.bgbsk.babycare.global.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.ViewHolder>{
    private Context mContext;
    private List<String> id;
    private List<String> name;
    private List<String> birthday;
    private List<String> sex;

    private static final int COMPLETED = 0;

    String url = Data.getUrl();
    String phone = Data.getPhone();


    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView babyname;
        TextView babybirthday;
        TextView babysex;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            babyname = (TextView) itemView.findViewById(R.id.name);
            babybirthday = (TextView) itemView.findViewById(R.id.birthday);
            babysex = (TextView) itemView.findViewById(R.id.sex);
        }
    }

    public BabyAdapter(List<String> idList,List<String> nameList,List<String> birthdayList,List<String> sexList){
        id = idList;
        name = nameList;
        birthday = birthdayList;
        sex = sexList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_baby_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final BabyAdapter.ViewHolder holder, final int position) {
        final String sid = id.get(position);
        String sname = name.get(position);
        String sbirthday = birthday.get(position);
        String ssex = sex.get(position);
        holder.babyname.setText(sname);
        holder.babybirthday.setText(sbirthday);
        holder.babysex.setText(ssex);
        // 长按删除事件
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 二次确认框
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                builder.setMessage("确认删除吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Delete","click");
                        OkHttpClient okHttpClient = new OkHttpClient();
                        final Request request = new Request.Builder().url(url+"/baby/delete?id="+sid).build();

                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.d("DeleteError", e.getMessage());
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                JSONObject jsonObject = JSON.parseObject(response.body().string());
                            }
                        });
                        if (Activity.class.isInstance(mContext)) {
                            Activity activity = (Activity) mContext;
                            activity.recreate();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (name == null)
            return 0;
        else
            return name.size();
    }
}
