package com.example.materialdesign.mvp.view.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.utils.AdDialogUtils;

public class Home extends Fragment {

    private HomeViewModel mViewModel;
    private String URI_Image = "https://img-blog.csdn.net/20170906094014301?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzY2MjE5OTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdDialogUtils adDialog = new AdDialogUtils(requireActivity(),URI_Image);
        adDialog.onCreateView();
        adDialog.setUiBeforShow();
        //点击空白区域能不能退出
        adDialog.setCanceledOnTouchOutside(false);
        //按返回键能不能退出
        adDialog.setCancelable(false);
        adDialog.show();
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}