package com.jack.carebaby.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jack.carebaby.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bgbsk.babycare.global.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BabyWHeightAdapter extends RecyclerView.Adapter<BabyWHeightAdapter.ViewHolder>{
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
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            babyname = (TextView) itemView.findViewById(R.id.name);
            babybirthday = (TextView) itemView.findViewById(R.id.birthday);
            babysex = (TextView) itemView.findViewById(R.id.sex);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
        }
    }

    public BabyWHeightAdapter(List<String> idList,List<String> nameList,List<String> birthdayList,List<String> sexList){
        id = idList;
        name = nameList;
        birthday = birthdayList;
        sex = sexList;
    }

    @Override
    public BabyWHeightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_baby_wheight_item,parent,false);
        return new BabyWHeightAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final BabyWHeightAdapter.ViewHolder holder, final int position) {
        final List<String> bid = new ArrayList<>();
        final List<String> weight = new ArrayList<>();
        final List<String> height = new ArrayList<>();
        final List<String> time = new ArrayList<>();
        final String sid = id.get(position);
        String sname = name.get(position);
        String sbirthday = birthday.get(position);
        String ssex = sex.get(position);
        holder.babyname.setText(sname);
        holder.babybirthday.setText(sbirthday);
        holder.babysex.setText(ssex);
        Log.d("Get","click");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url+"/baby/data/get?phone="+phone+"&id="+sid).build();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == COMPLETED) {
                    WHeightAdapter wheightadapter = new WHeightAdapter(bid,weight,height,time);
                    GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1);
                    holder.recyclerView.setAdapter(wheightadapter);
                    holder.recyclerView.setLayoutManager(layoutManager);
                }
            }
        };
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("GetError", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject0 = jsonArray.getJSONObject(i);
                    bid.add(jsonObject0.getString("_id"));
                    weight.add(jsonObject0.getString("weight"));
                    height.add(jsonObject0.getString("height"));
                    time.add(jsonObject0.getString("time"));
                }
                Message message = new Message();
                message.what = COMPLETED;
                handler.sendMessage(message);
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
