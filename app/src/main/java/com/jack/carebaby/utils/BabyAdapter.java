package com.jack.carebaby.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jack.carebaby.R;

import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.ViewHolder>{
    private Context mContext;
    private List<String> id;
    private List<String> name;
    private List<String> birthday;
    private List<String> sex;


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
    }

    @Override
    public int getItemCount() {
        if (name == null)
            return 0;
        else
            return name.size();
    }
}
