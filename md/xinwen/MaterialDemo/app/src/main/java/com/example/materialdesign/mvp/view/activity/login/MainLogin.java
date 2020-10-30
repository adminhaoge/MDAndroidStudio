package com.example.materialdesign.mvp.view.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.contract.LoginContract;
import com.example.materialdesign.mvp.customview.LoadingButton;
import com.example.materialdesign.mvp.di.component.AppComponent;


import com.example.materialdesign.mvp.di.component.DaggerLoginComponent;
import com.example.materialdesign.mvp.di.module.LoginModule;
import com.example.materialdesign.mvp.presenter.LoginPresenter;
import com.example.materialdesign.mvp.utils.ValidatorUtils;
import com.example.materialdesign.mvp.view.activity.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.jakewharton.rxbinding4.InitialValueObservable;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;

public class MainLogin extends BaseActivity implements View.OnClickListener, LoginContract.LoginView{

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
    @BindView(R.id.temporary_no_login)
    TextView temporaryNoLogin;
    @Inject
    LoginPresenter mPostHttp;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
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
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                btnLogin.setEnabled(aBoolean);
            }
        });



        RxView.clicks(btnLogin).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Throwable {
                String mobi = textMobi.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                mPostHttp.login(mobi,password);
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
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号码格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setErrorEnabled(false);
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
