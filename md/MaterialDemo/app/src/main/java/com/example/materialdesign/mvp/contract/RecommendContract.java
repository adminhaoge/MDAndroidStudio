package com.example.materialdesign.mvp.contract;

import com.example.materialdesign.mvp.base.IBasePresenter;
import com.example.materialdesign.mvp.base.IBaseVIew;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;

import java.util.List;

import javax.inject.Inject;

public interface RecommendContract {
    interface View extends IBaseVIew {
        void showRequest(List<ResultBean.StoriesBean> beans);
        void showRequestBody(String date, ContentBean contentBean);
        void showPostRequestBody(LoginBean loginBean);
    }
}
