package com.example.imitationtaobao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationtaobao.R;
import com.example.imitationtaobao.bean.ClassLeftBean;
import com.example.imitationtaobao.bean.ClassWares;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.ClassHolder>{

    private List<ClassLeftBean> classList;
    public MyLeftAdapter(List<ClassLeftBean> classList) {
        this.classList = classList;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_class_textlist, parent, false);
        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        ClassLeftBean classBean = classList.get(position);
        holder.classText.setText(classBean.getName());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


    public void clear() {
        classList.clear();
        notifyItemRangeRemoved(0, classList.size());
    }

    public void addData(List<ClassLeftBean>  hot) {
        addData(0, hot);
    }

    public void addData(int position, List<ClassLeftBean>  hot) {
        if (hot != null && hot.size() > 0) {
            classList.addAll(hot);
            notifyItemRangeChanged(position, classList.size());
        }
    }

    class ClassHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.class_text)
        TextView classText;
        public ClassHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            classText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ClassLeftBean classBean = classList.get(getAdapterPosition());
            beanClick.setClick(classBean);
        }
    }

    private OnClassBeanClick beanClick;

    public void setListener( OnClassBeanClick beanClick){
        this.beanClick = beanClick;
    }

   public interface OnClassBeanClick{
        void setClick(ClassLeftBean classBean);
    }

}
