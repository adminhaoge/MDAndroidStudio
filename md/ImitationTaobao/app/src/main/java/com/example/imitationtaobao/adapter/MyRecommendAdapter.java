package com.example.imitationtaobao.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
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
import com.example.imitationtaobao.bean.CpDataBody;
import com.example.imitationtaobao.activity.HomeWareListActivity;
import com.example.imitationtaobao.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecommendAdapter extends RecyclerView.Adapter<MyRecommendAdapter.RecommendHolder> {
    private List<CpDataBody> cpList;

    public MyRecommendAdapter(List<CpDataBody> cpList) {
        this.cpList = cpList;
    }

    @NonNull
    @Override
    public RecommendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_home_cardview, parent, false);
        return new RecommendHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendHolder holder, int position) {
        CpDataBody cpDataBody = cpList.get(position);
        holder.titleTxt.setText(cpDataBody.getTitle());
        Glide.with(holder.itemView)
                .load(cpDataBody.getCpOne().getImgUrl())
                .into(holder.imgDay1);
        Glide.with(holder.itemView)
                .load(cpDataBody.getCpThree().getImgUrl())
                .into(holder.imgDay2);
        Glide.with(holder.itemView)
                .load(cpDataBody.getCpTwo().getImgUrl())
                .into(holder.imgDay3);

    }

    @Override
    public int getItemCount() {
        return cpList.size();
    }


    public class RecommendHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        @BindView(R.id.title_txt)
        TextView titleTxt;
        @BindView(R.id.img_day1)
        ImageView imgDay1;
        @BindView(R.id.img_day2)
        ImageView imgDay2;
        @BindView(R.id.img_day3)
        ImageView imgDay3;
        @BindView(R.id.card_view)
        CardView cardView;
        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            imgDay1.setOnClickListener(this);
            imgDay2.setOnClickListener(this);
            imgDay3.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (cpList != null){
                anim(v);
            }
    }
        private void anim(View v) {
            ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)
                    .setDuration(1000);

            Intent intent = new Intent(v.getContext(), HomeWareListActivity.class);
            rotationAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    CpDataBody cpDataBody = cpList.get(getLayoutPosition());
                    switch (v.getId()){
                        case R.id.img_day1:
                        case R.id.img_day2:
                        case R.id.img_day3:
                            intent.putExtra(Constant.COMPAINGAIN,cpDataBody.getId());
                            v.getContext().startActivity(intent);
                            break;
                    }

                }
            });
            rotationAnimator.start();

        }

    }


}
