package com.example.howtimeflies;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.base.BaseActivity;
import com.xuexiang.xui.widget.button.ButtonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author coderXshuai
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.to_register_text)
    TextView toRegister;
    @BindView(R.id.home_login_button)
    ButtonView loginButton;
    @BindView(R.id.user_name)
    EditText userName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.to_register_text, R.id.home_login_button})
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
                userName.setText(null);
                break;
            default:
                break;
        }
    }
}
