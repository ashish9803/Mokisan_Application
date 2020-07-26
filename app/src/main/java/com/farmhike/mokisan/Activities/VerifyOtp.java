package com.farmhike.mokisan.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.Models.UserLoginData;
import com.farmhike.mokisan.R;

public class VerifyOtp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
    }

    public void verifyOtp(View view) {
        /*EditText edTemp = findViewById(R.id.editText_Login_otp);
        String temp = String.valueOf(edTemp.getText());
        */
        String temp = UserLoginData.getInstance().getPhoneNo();
        Toast.makeText(this, "" + temp, Toast.LENGTH_SHORT).show();
    }
}