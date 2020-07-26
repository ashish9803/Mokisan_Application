package com.farmhike.mokisan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sendOtp(View view) {
        validatePhoneNo();
        startActivity(new Intent(this, VerifyOtp.class));
    }

    private void validatePhoneNo() {
        EditText editText = findViewById(R.id.editText_Login_phoneNo);
        String phoneNo = String.valueOf(editText.getText());
        /*
         *  if everything fine
         *
         *  call UserLoginData.getInstance().setPhoneNo(phoneNo);
         *
         * else
         *
         * show error
         *
         * */

    }
}