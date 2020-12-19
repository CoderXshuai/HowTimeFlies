package com.example.howtimeflies.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.register_username)
    EditText registerUsername;  //用户名
    @BindView(R.id.register_password)
    PasswordEditText registerPassword;  //密码
    @BindView(R.id.password_again)
    PasswordEditText passwordAgain;     //重复密码
    @BindView(R.id.register_submit_button)
    ButtonView registerSubmitButton;    //注册
    @BindView(R.id.register_cancel_button)
    ButtonView registerCancelButton;    //重置

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page_layout);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.register_submit_button, R.id.register_cancel_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_submit_button:
                if (Objects.requireNonNull(registerPassword.getText()).toString().equals(passwordAgain.getText().toString())) {
                    System.out.println("密码相等");
                    registerMethod();   //注册
                } else {
                    showMessage("两次输入密码不相等");
                }
                break;
            case R.id.register_cancel_button:
                rest(); //重置
                break;
        }
    }

    /**
     * 注册事件 讲注册的用户保存到LeanCloud数据库中
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void registerMethod() {
        //跳转到登录
        Intent loginIntent = new Intent(RegisterActivity.this, ShowTimeActivity.class);
        //为了实现切换的动画效果所加代码
        startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
    }

    /**
     * 重置
     */
    private void rest() {
        registerUsername.setText("");
        registerPassword.setText("");
        passwordAgain.setText("");
    }
}
