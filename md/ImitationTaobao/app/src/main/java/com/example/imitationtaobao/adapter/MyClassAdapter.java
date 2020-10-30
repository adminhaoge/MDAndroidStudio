package com.example.imitationtaobao.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.bean.ClassWares;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyClassAdapter extends RecyclerView.Adapter<MyClassAdapter.ClassHolder> {

    private List<ClassWares> classWares;

    public MyClassAdapter(List<ClassWares> classWares) {
        this.classWares = classWares;
    }
    public MyClassAdapter(){}

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_class_cardview, parent, false);
        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        ClassWares classWares = this.classWares.get(position);
        Glide.with(holder.itemView).load(classWares.getImgUrl()).into(holder.iconImage);
        holder.txtTitle.setText(classWares.getName());
        holder.txtMoney.setText(String.valueOf(classWares.getPrice()));
    }

    @Override
    public int getItemCount() {
        return classWares.size();
    }

    class ClassHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_image)
        ImageView iconImage;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_money)
        TextView txtMoney;
        @BindView(R.id.cardView)
        CardView cardView;
        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
