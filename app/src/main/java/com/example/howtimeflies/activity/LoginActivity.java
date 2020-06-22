package com.example.howtimeflies.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.to_register_text)
    TextView toRegister;    //跳转注册
    @BindView(R.id.login_submit_button)
    ButtonView loginButton; //登录
    @BindView(R.id.login_cancel_button)
    ButtonView cancelButton;    //重置
    @BindView(R.id.login_username)
    EditText username;          //用户名
    @BindView(R.id.login_password)
    EditText password;          //密码

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_layout);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.to_register_text, R.id.login_submit_button, R.id.login_cancel_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_register_text:
                //注册
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(registerIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.login_submit_button:
                login();    //登录
                break;
            case R.id.login_cancel_button:
                username.setText(null);
                password.setText(null);
                break;
            default:
                break;
        }
    }


    /**
     * 登录
     */
    private void login(){
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        AVUser.logIn(usernameString, passwordString).subscribe(new Observer<AVUser>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onNext(AVUser avUser) {
                //登录成功
                Log.d("login","登陆成功");
                //跳转到首页
                Intent loginIntent = new Intent(LoginActivity.this, ShowTimeActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());

            }

            @Override
            public void onError(Throwable e) {
                //登陆失败
                showMessage("用户名或密码错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}