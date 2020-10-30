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
import com.example.imitationtaobao.bean.ListBean;
import com.example.imitationtaobao.bean.ShoppingCart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWaresListAdapter extends RecyclerView.Adapter<MyWaresListAdapter.WaresHotHolder> {


    private List<ListBean> WaresList;

    private CartProvider cartProvider;
    public MyWaresListAdapter(List<ListBean> waresList) {
        WaresList = waresList;
    }

    @NonNull
    @Override
    public WaresHotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cartProvider = new CartProvider(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templatelist_chart_cardview, parent, false);
        return new WaresHotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaresHotHolder holder, int position) {
        ListBean listBean = WaresList.get(position);
        holder.itemsText.setText(listBean.getName());
        holder.itemMoney.setText(String.valueOf(listBean.getPrice()));
        Glide.with(holder.itemView).load(listBean.getImgUrl()).into(holder.imageView2);
        holder.btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartProvider.put(convertDate(listBean));
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

    public void addData(List<ListBean> hot){
        addData(0,hot);
    }

    public void addData(int position,List<ListBean> hot){
        if (hot != null && hot.size() > 0){
            WaresList.addAll(hot);
            notifyItemRangeChanged(position,WaresList.size());
        }
    }

    class WaresHotHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.item_money)
        TextView itemMoney;
        @BindView(R.id.bt_buy)
        Button btBuy;
        @BindView(R.id.items_text)
        TextView itemsText;
        @BindView(R.id.cardView)
        CardView cardView;

        public WaresHotHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public ShoppingCart convertDate(ListBean waresHot){
        ShoppingCart cart = new ShoppingCart();
        cart.setId(waresHot.getId());
        cart.setImgUrl(waresHot.getImgUrl());
        cart.setName(waresHot.getName());
        cart.setPrice(waresHot.getPrice());
        return cart;
    }

}
