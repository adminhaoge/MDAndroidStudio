package com.example.imitationtaobao.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.bean.ClassWares;
import com.example.imitationtaobao.bean.SlideImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SliderAndRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SLIDER = 1;
    public static final int TYPE_RECYCLE = 2;
    private List<ClassWares> classWares;
    private LayoutInflater inflater;
    private List<SlideImage> slideImages;
    private MyClassAdapter myClassAdapter;

    public SliderAndRecycleAdapter(List<ClassWares> classWares, List<SlideImage> slideImages) {
        this.classWares = classWares;
        this.slideImages = slideImages;

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SLIDER;
        } else if (position == 1) {
            return TYPE_RECYCLE;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SLIDER) {
            View view = inflater.from(parent.getContext()).inflate(R.layout.template_silde_image, parent, false);
            return new MySliderHolder(view);
        } else if (viewType == TYPE_RECYCLE) {
            View view = inflater.from(parent.getContext()).inflate(R.layout.template_recycleview, null, false);
            return new MyRecycleViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            MySliderHolder mySliderHolder = (MySliderHolder) holder;
            for (SlideImage slideImage : slideImages) {
                TextSliderView textSliderView = new TextSliderView(holder.itemView.getContext());
                textSliderView
                        .description(slideImage.getName())
                        .image(slideImage.getImgUrl());
                mySliderHolder.slider.startAutoCycle();
                mySliderHolder.slider.addSlider(textSliderView);
                mySliderHolder.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mySliderHolder.slider.setPresetTransformer(SliderLayout.Transformer.Tablet);
                mySliderHolder.slider.setDuration(3000);
            }


        } else if (position == 1) {
            MyRecycleViewHolder myRecycleViewHolder = (MyRecycleViewHolder) holder;
            myClassAdapter = new MyClassAdapter(classWares);
            GridLayoutManager manager = new GridLayoutManager(myRecycleViewHolder.itemView.getContext(), 2);
            myRecycleViewHolder.recyclerDay2.setLayoutManager(manager);
            myRecycleViewHolder.recyclerDay2.setAdapter(myClassAdapter);
            myRecycleViewHolder.recyclerDay2.setItemAnimator(new DefaultItemAnimator());
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public void clearData() {
        classWares.clear();
        notifyItemRangeRemoved(0, classWares.size());
    }

    public void addDataWares(List<ClassWares> hot) {
        addDataWares(0, hot);
    }

    public void addDataWares(int position, List<ClassWares> hot) {
        if (hot != null && hot.size() > 0) {
            classWares.addAll(hot);
            notifyItemRangeChanged(position, classWares.size());
        }
    }


    class MySliderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.slider)
        SliderLayout slider;
        public MySliderHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyRecycleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_day2)
        RecyclerView recyclerDay2;
        public MyRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
