package com.example.imitationtaobao.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imitationtaobao.R;
import com.example.imitationtaobao.mToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WareDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_detail)
    mToolbar toolbarDetail;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_detall);
        ButterKnife.bind(this);
    }
}

