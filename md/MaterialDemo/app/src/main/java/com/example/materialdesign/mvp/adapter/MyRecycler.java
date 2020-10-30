package com.example.materialdesign.mvp.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.example.materialdesign.R;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpEngine;
import com.example.materialdesign.mvp.view.activity.InfoActivity;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecycler extends RecyclerView.Adapter<MyRecycler.MyViewHolder> {


    private List<ResultBean.StoriesBean> beanList;
    private int id;
    public static List<Integer> idarr = new ArrayList<>();
    private AdapterView.OnItemClickListener mListener;
    public MyRecycler(List<ResultBean.StoriesBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_cardview_item, parent, false);
        return new MyViewHolder(view);
        }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


            for (int i = 0; i < beanList.size(); i++) {
                id = beanList.get(position).getId();
                holder.itemsText.setText(beanList.get(position).getTitle());
                holder.textView.setText(beanList.get(position).getHint());
                    List<String> images = beanList.get(position).getImages();
                    for (String image : images) {
                        Glide.with(holder.itemView)
                                .load(image)
                            .transition(GenericTransitionOptions.<Drawable>with(R.anim.anim_glide))
                            .into((holder).imageView2);
                }
            }
            idarr.add(id);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppApplication appApplication = AppApplication.get(v.getContext());
                    appApplication.setView(v);
                    switch (position) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            Intent intent = new Intent(v.getContext(), InfoActivity.class);
                            int bodyID = idarr.get(position);
                            new HttpEngine(bodyID);
                            v.getContext().startActivity(intent);
                            break;
                    }
                }
            });
        }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.items_text)
        TextView itemsText;
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.cardView)
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

}
