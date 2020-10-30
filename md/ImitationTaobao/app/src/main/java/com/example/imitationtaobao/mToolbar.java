package com.example.imitationtaobao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class mToolbar extends Toolbar {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.txt_view)
    TextView txtView;
    @BindView(R.id.bt_img)
    Button btImg;

    private LayoutInflater mInflater;

    public mToolbar(@NonNull Context context) {
        this(context, null);
    }

    public mToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    @SuppressLint("RestrictedApi")
    public mToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

        if (attrs != null){
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.mToolbar, defStyleAttr, 0);

            Drawable drawable = a.getDrawable(R.styleable.mToolbar_rightButtonIcon);
            if (drawable != null){
                setRightButtonIcon(drawable);
            }


            boolean isShowSearchView = a.getBoolean(R.styleable.mToolbar_isShowSearchView, false);
            if (isShowSearchView){
                showSearchView();
                hideTitleView();
            }
            a.recycle();
        }
    }

    private void initView() {
        if (txtView == null) {
            View view = mInflater.from(getContext()).inflate(R.layout.toolbar, null);
            ButterKnife.bind(this, view);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(view, lp);
        }
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        txtView.setText(title);
        showTitleView();
    }

    public void showSearchView(){
        if (edSearch != null){
            edSearch.setVisibility(VISIBLE);
        }
    }

    public void hideSearchView(){
        if (edSearch != null){
            edSearch.setVisibility(GONE);
        }
    }

    public void showTitleView(){
        if (txtView != null){
            txtView.setVisibility(VISIBLE);
        }
    }

    public void hideTitleView(){
        if (txtView != null){
            txtView.setVisibility(GONE);
        }
    }

    public void setRightButtonIcon(Drawable icon){
        if (btImg != null){
            btImg.setBackground(icon);
            btImg.setVisibility(VISIBLE);
        }
    }

    public void setRightButtonIcon(int icon){
        setRightButtonIcon(getResources().getDrawable(icon));
    }

    public void setRightButtonText(String name){
        btImg.setText(name);
    }


    public Button getRightButton(){
        return this.btImg;
    }



}
