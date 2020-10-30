package com.example.materialdesign.mvp.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.materialdesign.R;
import com.flyco.dialog.widget.base.BaseDialog;

/**
 * 弹出式广告工具类
 */
public class AdDialogUtils extends BaseDialog<AdDialogUtils> {
    private ImageView iv_ad;
    private ImageView back;
    private Context context;
    private String url_image;

    public AdDialogUtils(Context context, String url_image) {
        super(context);
        this.context = context;
        this.url_image = url_image;
    }

    @Override
    //该方法用来出来数据初始化代码
    public View onCreateView() {
        widthScale(0.85f);
        //填充弹窗布局
        View inflate = View.inflate(context, R.layout.utils_flycodialog, null);
        //用来放整个图片的控件
        iv_ad = (ImageView) inflate.findViewById(R.id.iv_ad);
        //放在透明部分和错号上的隐形控件，用来点击使弹窗消失
        back = (ImageView) inflate.findViewById(R.id.ad_back);
        //用来加载网络图片，填充iv_ad控件，注意要添加网络权限.
        Glide.with(context)
                .load(url_image)
                .into(iv_ad);
        return inflate;
    }

    @Override
    //该方法用来处理逻辑代码
    public void setUiBeforShow() {
        //点击弹窗相应位置，处理相关逻辑。
        iv_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"哈哈", Toast.LENGTH_SHORT).show();
                //处理完逻辑关闭弹框的代码
                dismiss();
            }
        });
        //点×关闭弹框的代码
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭弹框的代码
                dismiss();
            }
        });
    }
}
