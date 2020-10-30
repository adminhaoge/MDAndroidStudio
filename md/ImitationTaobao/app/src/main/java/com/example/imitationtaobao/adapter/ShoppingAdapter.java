package com.example.imitationtaobao.adapter;

import android.icu.text.Edits;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.bean.CartProvider;
import com.example.imitationtaobao.bean.ShoppingCart;
import com.example.imitationtaobao.bean.WaresHot;
import com.example.imitationtaobao.customview.NumberAddSubView;
import com.example.imitationtaobao.utils.ACache;
import com.example.imitationtaobao.utils.Constant;

import java.security.acl.LastOwnerException;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingHolder> {

    private List<ShoppingCart> carts;
    private CheckBox checkboxAll;
    private TextView txtTotal;
    private CartProvider cartProvider;

    public ShoppingAdapter(List<ShoppingCart> carts, CheckBox checkboxAll, TextView txtTotal) {
        this.carts = carts;
        this.checkboxAll =checkboxAll;
        this.txtTotal =txtTotal;

        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    checkAll_None(checkboxAll.isChecked());
                    showTotalPrice();
            }
        });

        showTotalPrice();

    }

    @NonNull
    @Override
    public ShoppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_shopping_cardview, parent, false);
        cartProvider = new CartProvider(parent.getContext());
        return new ShoppingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingHolder holder, int position) {
        ShoppingCart shoppingCart = carts.get(position);
        Glide.with(holder.itemView).load(shoppingCart.getImgUrl()).into(holder.imgCart);
        holder.txtBuy.setText("￥" + shoppingCart.getPrice());
        holder.itemsText.setText(shoppingCart.getName());
        holder.checkboxGeren.setChecked(shoppingCart.isChecked());
        holder.numView.setValue(shoppingCart.getCount());
        holder.numView.getValue();
        clicktrue();
        holder.numView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {
                shoppingCart.setCount(value);
                showTotalPrice();
                cartProvider.update(shoppingCart);
            }

            @Override
            public void onButtonSubClick(View view, int value) {
                shoppingCart.setCount(value);
                showTotalPrice();
                cartProvider.update(shoppingCart);
            }
        });
    }
    public float setCountmoney(){
        float sum = 0 ;
        if (!isNull())
            return sum;

        for (ShoppingCart cart : carts) {
            if (cart.isChecked()){
                sum += cart.getCount() * cart.getPrice();
            }
        }
        return sum;
    }

    public void showTotalPrice(){
        float total = setCountmoney();
        txtTotal.setText("合计 ￥"+total);

    }

    private boolean isNull(){
        return carts != null && carts.size() >= 0;
    }


    private void clicktrue(){
        int count = 0;
        int clickNum = 0;
        if (carts.size()>=0){
            count = carts.size();
        }
        for (ShoppingCart cart : carts) {
            if (cart.isChecked()) {
                clickNum += 1;
            } else {
                checkboxAll.setChecked(cart.isChecked());
                break;
            }
        }


            if (count == clickNum) {
                checkboxAll.setChecked(true);
            }

    }


    private void checkAll_None(boolean isChecked){
        if (!isNull())
            return;
        int i = 0;
        for (ShoppingCart cart : carts) {
            cart.setChecked(isChecked);
            notifyItemChanged(i);
            cartProvider.update(cart);
            i++;
        }
    }


    public void delCart(){
        if (!isNull())
            return;
        for (Iterator iterator = carts.iterator();iterator.hasNext();){
            ShoppingCart cart = (ShoppingCart) iterator.next();
            if (cart.isChecked()){
                int position = carts.indexOf(cart);
                cartProvider.delete(cart);
                iterator.remove();
                notifyItemRemoved(position);
            }
        }
    }


    @Override
    public int getItemCount() {
        return carts.size();
    }

    public void clearData(){
        carts.clear();
        notifyItemRangeRemoved(0,carts.size());
    }

    public void addData(List<ShoppingCart> hot){
        addData(0,hot);
    }

    public void addData(int position,List<ShoppingCart> hot){
        if (hot != null && hot.size() > 0){
            carts.addAll(hot);
            notifyItemRangeChanged(position,carts.size());
        }
    }

    class ShoppingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img_cart)
        ImageView imgCart;
        @BindView(R.id.items_text)
        TextView itemsText;
        @BindView(R.id.num_view)
        NumberAddSubView numView;
        @BindView(R.id.txt_buy)
        TextView txtBuy;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.checkbox_geren)
        CheckBox checkboxGeren;
        public ShoppingHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            checkboxGeren.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                int Position = getLayoutPosition();
                ShoppingCart shoppingCart = carts.get(Position);
                shoppingCart.setChecked(!shoppingCart.isChecked());
                clicktrue();
                showTotalPrice();
                notifyItemChanged(Position);
                cartProvider.update(shoppingCart);
        }
    }
}
