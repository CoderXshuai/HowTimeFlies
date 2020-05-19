package com.example.howtimeflies.activity;

import android.os.Build;
import android.os.Bundle;
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

    @OnClick({R.id.to_register_text, R.id.login_submit_button, R.id.login_cancel_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_register_text:
                Toast.makeText(LoginActivity.this, "to_register_text", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_submit_button:
                Toast.makeText(LoginActivity.this, "login_button", Toast.LENGTH_SHORT).show();
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
