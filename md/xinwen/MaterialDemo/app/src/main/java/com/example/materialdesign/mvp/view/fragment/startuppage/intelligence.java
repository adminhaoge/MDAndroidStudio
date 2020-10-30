package com.example.materialdesign.mvp.view.fragment.startuppage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class intelligence extends Fragment {
    public static final String IMG_ResID = "IMG_ID";
    @BindView(R.id.img_bg)
    ImageView imgBg;



    public static intelligence newInstance(int imaResId) {
        Bundle args = new Bundle();
        args.putInt(IMG_ResID, imaResId);
        intelligence fragment = new intelligence();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData() {
        Bundle arg = getArguments();
        int bg_img = arg.getInt(IMG_ResID);
        imgBg.setImageResource(bg_img);
    }
}