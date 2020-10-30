package com.example.imitationtaobao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitationtaobao.activity.MainActivity;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.adapter.ShoppingAdapter;
import com.example.imitationtaobao.bean.CartProvider;
import com.example.imitationtaobao.bean.ShoppingCart;
import com.example.imitationtaobao.mToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingFragment extends Fragment implements View.OnClickListener{

    public static final int ACTION_CAMPLATE = 1;
    public static final int ACTION_COMPLETE = 2;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.txt_total)
    TextView txtTotal;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.btn_del)
    Button btnDel;

    private ShoppingAdapter shoppingAdapter;
    private CartProvider cartProvider;
    private mToolbar tb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shopping_fragment, container, false);
        ButterKnife.bind(this, view);
        cartProvider = new CartProvider(getContext());
        showDate();
        return view;
    }

    @OnClick(R.id.btn_del)
    public void delCart(View view){
        shoppingAdapter.delCart();
    }

    private void showDate(){
        List<ShoppingCart> carts = cartProvider.getAll();
        if (carts != null){
            shoppingAdapter = new ShoppingAdapter(carts,checkboxAll,txtTotal);
            recyclerView.setAdapter(shoppingAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        }else {

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            MainActivity activity = (MainActivity) context;
            tb = activity.findViewById(R.id.toolbar_activity_info);

            changeToolber();

        }
    }

    private void changeToolber() {
        tb.hideSearchView();
        tb.showTitleView();
        tb.setTitle(R.string.gwc);
        tb.getRightButton().setVisibility(View.VISIBLE);
        tb.setRightButtonText("编辑");
        tb.getRightButton().setOnClickListener(this);
        tb.getRightButton().setTag(ACTION_CAMPLATE);
    }

    private void setComplate(){
        tb.setRightButtonText("完成");
        txtTotal.setVisibility(View.GONE);
        btnOrder.setVisibility(View.GONE);
        btnDel.setVisibility(View.VISIBLE);
        tb.getRightButton().setTag(ACTION_COMPLETE);

        checkboxAll.setChecked(false);
    }

    private void setCamplete(){
        tb.setRightButtonText("编辑");
        txtTotal.setVisibility(View.VISIBLE);
        btnOrder.setVisibility(View.VISIBLE);
        btnDel.setVisibility(View.GONE);
        tb.getRightButton().setTag(ACTION_CAMPLATE);

        checkboxAll.setChecked(false);
    }


    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        if (ACTION_CAMPLATE == tag){
            setComplate();
        }else if (ACTION_COMPLETE == tag){
            setCamplete();
        }
    }
}