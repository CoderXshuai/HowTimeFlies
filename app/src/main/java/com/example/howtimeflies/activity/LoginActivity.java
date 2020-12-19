package com.example.howtimeflies.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.to_register_text)
    TextView toRegister;
    @BindView(R.id.login_submit_button)
    ButtonView loginButton;
    @BindView(R.id.login_cancel_button)
    ButtonView cancelButton;
    @BindView(R.id.login_username)
    EditText username;
    @BindView(R.id.login_password)
    EditText password;

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
                //跳转到注册
                Intent loginIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                break;
            case R.id.login_submit_button:
                //跳转到登录
                Intent intent = new Intent(LoginActivity.this, ShowTimeActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                break;
            case R.id.login_cancel_button:
                username.setText(null);
                password.setText(null);
                break;
            default:
                break;
        }
    }
}
