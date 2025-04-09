package com.seachal.seachaltest.adapter;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/5/17 11:27
 * @Version：1.0
 */

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private AppCompatActivity context;
    private List<StartActivityBean> arrayList;

    public MyAdapter(AppCompatActivity context, List<StartActivityBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,
                false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTextView.setText(  position + "." + arrayList.get(position).getTitle());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(  context, arrayList.get(position).getActivityClass());
                startActivity(intent);
            }
        });
        holder.llroot.setBackgroundResource(R.drawable.shape_solid_white_full_corner_20dp);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llroot;
        private TextView mTextView;


        public MyViewHolder(View itemView) {
            super(itemView);
            llroot = itemView.findViewById(R.id.ll_root);
            mTextView = itemView.findViewById(R.id.tv_items);

        }
    }
}

