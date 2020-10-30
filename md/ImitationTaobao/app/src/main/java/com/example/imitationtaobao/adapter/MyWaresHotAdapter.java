package com.example.imitationtaobao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.bean.CartProvider;
import com.example.imitationtaobao.bean.ShoppingCart;
import com.example.imitationtaobao.bean.WaresHot;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWaresHotAdapter extends RecyclerView.Adapter<MyWaresHotAdapter.WaresHotHolder> {


    private List<WaresHot> WaresList;

    private CartProvider cartProvider;
    public MyWaresHotAdapter(List<WaresHot> waresList) {
        WaresList = waresList;
    }

    @NonNull
    @Override
    public WaresHotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cartProvider = new CartProvider(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_chart_cardview, parent, false);
        return new WaresHotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaresHotHolder holder, int position) {
        WaresHot waresHot = WaresList.get(position);
        holder.title.setText(waresHot.getName());
        holder.itemMoney.setText(String.valueOf(waresHot.getPrice()));
        Glide.with(holder.itemView).load(waresHot.getImgUrl()).into(holder.img_header);
        holder.go_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cartProvider.put(convertDate(waresHot));
                Toast.makeText(v.getContext(),"已添加到购物车",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return WaresList.size();
    }

    public void clearData(){
        WaresList.clear();
        notifyItemRangeRemoved(0,WaresList.size());
    }

    public void addData(List<WaresHot> hot){
        addData(0,hot);
    }

    public void addData(int position,List<WaresHot> hot){
        if (hot != null && hot.size() > 0){
            WaresList.addAll(hot);
            notifyItemRangeChanged(position,WaresList.size());
        }
    }

    class WaresHotHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_header)
        ImageView img_header;
        @BindView(R.id.item_money)
        TextView itemMoney;
        @BindView(R.id.bt_go_load)
        Button go_load;
        @BindView(R.id.title_text)
        TextView title;
        @BindView(R.id.cardView)
        CardView cardView;

        public WaresHotHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
//    public ShoppingCart convertDate(WaresHot waresHot){
//        ShoppingCart cart = new ShoppingCart();
//        cart.setId(waresHot.getId());
//        cart.setImgUrl(waresHot.getImgUrl());
//        cart.setName(waresHot.getName());
//        cart.setPrice(waresHot.getPrice());
//        return cart;
//    }

}
