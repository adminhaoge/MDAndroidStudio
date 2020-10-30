package com.example.materialdesign.mvp.customview;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.materialdesign.R;


public class LoadingButton extends RelativeLayout {

    static final int DEFAULT_COLOR = android.R.color.white;
    static final int IN_FROM_RIGHT = 0;
    static final int IN_FROM_LEFT = 1 ;


    private ProgressBar mProgressBar;
    private TextView mTextView;
    private String mLoadingText;
    private int mDefaultTextSize;
    private String mButtonText;
    private float mTextSize;
    private int mTextColor;
    private boolean mIsLoadingShowing;
    private Typeface mTypeface;
    private Animation inRight;
    private Animation inLeft;
    private int mCurrentInDirection;
    private boolean mTextSwitcherReady;

    public LoadingButton(Context context) {
        this(context,null);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @SuppressLint("ResourceAsColor")
    private void init(Context context , AttributeSet attrs){
        mDefaultTextSize = 14;
        mIsLoadingShowing = false;
        LayoutInflater.from(getContext()).inflate(R.layout.loading_button,this,true);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTextView = (TextView) findViewById(R.id.text);
        if (attrs !=null){
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0);
            try {
                float PixelSize = typedArray.getDimensionPixelSize(R.styleable.LoadingButton_textSize, mDefaultTextSize);
                setTextSize(PixelSize);
                String text = getContext().getString(R.string.login_loading);
                setText(text);
                mLoadingText = typedArray.getString(R.styleable.LoadingButton_loadingText);
                if (mLoadingText == null){
                    mLoadingText = getContext().getString(R.string.default_loading);
                }
                int progressColor = typedArray.getColor(R.styleable.LoadingButton_progressColor, DEFAULT_COLOR);
                setProgressColor(progressColor);
                int textColor = typedArray.getColor(R.styleable.LoadingButton_textColor, DEFAULT_COLOR);
                setTextColor(textColor);
            }finally {
                typedArray.recycle();
            }
        }else {
            int white = getResources().getColor(DEFAULT_COLOR);
            mLoadingText = getContext().getString(R.string.default_loading);
            setProgressColor(white);
            setTextColor(white);
            setTextSize(10);
        }
    }

    private void setTextColor(int textColor) {
        this.mTextColor =textColor;
        mTextView.setTextColor(textColor);
    }

    private void setProgressColor(int progressColor) {
        mProgressBar.getIndeterminateDrawable()
                .mutate()
                .setColorFilter(progressColor, PorterDuff.Mode.SRC_ATOP);
    }

    private void setText(String text) {
        if (text != null){
            mButtonText = text;
            mTextView.setText(text);
        }
    }

    private void setTextSize(float pixelSize) {
        mTextSize = pixelSize;
        mTextView.setTextSize(pixelSize);
    }

    public void setLoadingText(String text){
        if (text != null){
            mLoadingText =text;
        }
    }

    public void showLoading(){
        if (!mIsLoadingShowing){
            mProgressBar.setVisibility(View.VISIBLE);
            mTextView.setText(mLoadingText);
            mIsLoadingShowing = true;
            setEnabled(false);
        }

    }

    public void showButtonText(){
        if (mIsLoadingShowing){
            mProgressBar.setVisibility(View.INVISIBLE);
            mTextView.setText(mButtonText);
            mIsLoadingShowing = false;
            setEnabled(true);

        }
    }

}
