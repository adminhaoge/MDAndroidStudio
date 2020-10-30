package com.example.imitationtaobao.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;

import com.example.imitationtaobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberAddSubView extends LinearLayout implements View.OnClickListener{
    @BindView(R.id.btn_sub)
    Button btnSub;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.btn_add)
    Button btnAdd;
    private LayoutInflater inflater;
    private int value;
    private int minValue;
    private int maxValue;

    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
        initView();
        declare_styleable(context,attrs,defStyleAttr);
    }
    @SuppressLint("RestrictedApi")
    private void declare_styleable(Context context,AttributeSet attrs,int defStyleAttr) {
        if (attrs != null){
           TintTypedArray array = TintTypedArray.obtainStyledAttributes(context,attrs
                   ,R.styleable.NumberAddSubView,defStyleAttr,0);

            int anInt_value = array.getInt(R.styleable.NumberAddSubView_value, 0);
            setValue(anInt_value);

            int anInt_minValue = array.getInt(R.styleable.NumberAddSubView_minValue, 0);
            setMinValue(anInt_minValue);

            int anInt_maxValue = array.getInt(R.styleable.NumberAddSubView_maxValue, 0);
            setMaxValue(anInt_maxValue);

            Drawable drawableBtnSub = array.getDrawable(R.styleable.NumberAddSubView_btnSubBackground);
            Drawable drawableBtnAdd = array.getDrawable(R.styleable.NumberAddSubView_btnAddBackground);
            Drawable drawableTextView = array.getDrawable(R.styleable.NumberAddSubView_textViewBackground);

            setButtonAddDBackground(drawableBtnAdd);
            setButtonSubDBackground(drawableBtnSub);
            setTextViewDBackground(drawableTextView);
            array.recycle();

        }
    }

    public void setTextViewDBackground(Drawable drawableTextView) {
        txtNumber.setBackground(drawableTextView);
    }

    public void setTextViewIBackground(int drawableId){
        setTextViewDBackground(getResources().getDrawable(drawableId));
    }

    public void setButtonSubDBackground(Drawable drawableBtnSub) {
        btnSub.setBackground(drawableBtnSub);
    }
    public void setButtonSubIBackground(int drawableId){
        setButtonSubDBackground(getResources().getDrawable(drawableId));
    }

    public void setButtonAddDBackground(Drawable drawableBtnAdd) {
        btnAdd.setBackground(drawableBtnAdd);
    }

    public void setButtonAddIBackground(int drawableId){
        setButtonAddDBackground(getResources().getDrawable(drawableId));
    }

    private void initView() {
        View view = inflater.inflate(R.layout.custom_numberaddsubview, this, true);
        ButterKnife.bind(this,view);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sub){
            numSub();
            if (clickListener != null){
                clickListener.onButtonSubClick(v,value);
            }
        }else if (v.getId() == R.id.btn_add){
            numAdd();
            if (clickListener != null){
                clickListener.onButtonAddClick(v,value);
            }
        }
    }

    private void numAdd(){
            value +=1;
            txtNumber.setText(value + "");
    }

    private void numSub(){
        if (value > minValue) {
            value = value - 1;
            txtNumber.setText(value + "");
        }
    }

    public int getValue() {
        String txt_value = txtNumber.getText().toString();
        if (txt_value != null || !"".equals(txt_value)){
            value = Integer.parseInt(txt_value);
        }

        return value;
    }

    public void setValue(int value) {
        txtNumber.setText(value+"");
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private OnButtonClickListener clickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.clickListener = onButtonClickListener;
    }


    public interface OnButtonClickListener{
        void onButtonAddClick(View view ,int value);
        void onButtonSubClick(View view ,int value);
    }
}
