package com.example.howtimeflies;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author coderXshuai
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.home_login_button)
    ButtonView loginButton;
    @BindView(R.id.home_register_button)
    ButtonView registerButton;

    //为了实现动画效果最低API为21
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);
        ButterKnife.bind(this);
    }

    //实现切换的动画最低API为21
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @OnClick({R.id.home_login_button, R.id.home_register_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_login_button:
                Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.home_register_button:
                Intent registerIntent = new Intent(HomeActivity.this, RegisterActivity.class);
                //为了实现切换的动画效果所加代码
                startActivity(registerIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            default:
                break;
        }
    }
}
