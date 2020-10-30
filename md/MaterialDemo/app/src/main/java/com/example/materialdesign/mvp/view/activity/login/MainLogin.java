package com.example.materialdesign.mvp.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.materialdesign.R;
import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.User;
import com.example.materialdesign.mvp.customview.LoadingButton;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.di.component.DaggerRecommendComponent;
import com.example.materialdesign.mvp.di.module.RecommendModule;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpEngine;
import com.example.materialdesign.mvp.presenter.PostHttpPresenterBody;
import com.example.materialdesign.mvp.utils.ValidatorUtils;
import com.example.materialdesign.mvp.view.activity.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding4.InitialValueObservable;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import kotlin.Unit;

public class MainLogin extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_mobi)
    EditText textMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.text_password)
    EditText textPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;
    @Inject
    PostHttpPresenterBody mPostHttp;
    @BindView(R.id.temporary_no_login)
    TextView temporaryNoLogin;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent
                .builder()
                .recommendModule(new RecommendModule(this))
                .appComponent(appComponent)
                .build()
                .injcet(this);
    }

    @Override
    protected void initData() {
        mPostHttp.attachView(this);

        initView();
        temporaryNoLogin.setOnClickListener(this);
    }

    private void initView() {
        InitialValueObservable<CharSequence> obMobi = RxTextView.textChanges(textMobi);
        InitialValueObservable<CharSequence> obPassword = RxTextView.textChanges(textPassword);
        InitialValueObservable.combineLatest(obMobi, obPassword, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence mobi, CharSequence password) throws Throwable {
                return isPhoneValid(mobi.toString()) && isPasswordValid(password.toString());
            }
        }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                btnLogin.setEnabled(aBoolean);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        RxView.clicks(btnLogin).subscribe(new Observer<Unit>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Unit unit) {

                String mobi = textMobi.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                if (ValidatorUtils.isMobile(mobi)) {
                    new HttpEngine(mobi, password);
                    mPostHttp.fetch();
                    viewMobiWrapper.setErrorEnabled(false);
                } else {
                    viewMobiWrapper.setError("手机号码格式不正确");
                }


            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void showPostRequestBody(LoginBean loginBean) {
        Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void showLoading() {
        btnLogin.showLoading();
    }

    @Override
    public void dismissLoading() {
        btnLogin.showButtonText();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplication(), MainActivity.class));
        this.finish();
    }

}
