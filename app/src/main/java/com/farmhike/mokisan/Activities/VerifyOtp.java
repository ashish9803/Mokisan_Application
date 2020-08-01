package com.farmhike.mokisan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.Models.AppContext;
import com.farmhike.mokisan.Models.UserLoginData;
import com.farmhike.mokisan.R;
import com.farmhike.mokisan.Utils.PhoneAuth;
import com.google.android.material.textfield.TextInputEditText;

public class VerifyOtp extends AppCompatActivity {

    TextView textView;
    Button next_otp, back_otp;
    CheckBox _termAndCondition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);
        AppContext.getInstance().setContext(getApplicationContext());

        textView = (TextView)(findViewById(R.id.heading_otp2));
        next_otp = (Button)(findViewById(R.id.button_next_otp));
        back_otp = (Button)(findViewById(R.id.back_otp));
        _termAndCondition = findViewById(R.id.checkBox_verifyOTP);

        PhoneAuth.getInstance().set_edittext_OTP((TextInputEditText) findViewById(R.id.editText_Login_otp));
        PhoneAuth.getInstance().set_verify_PB((ProgressBar) findViewById(R.id.progressbar_verifyOTP));
        PhoneAuth.getInstance().set_verify_IV((ImageView) findViewById(R.id.imageView_verified));
        PhoneAuth.getInstance().set_Verify_manually((TextView) findViewById(R.id.resend_otp));
        PhoneAuth.getInstance().set_verifyButton((Button) findViewById(R.id.button_verify));
        PhoneAuth.getInstance().set_nextButton(next_otp);

        String temp = UserLoginData.getInstance().getPhoneNo();
        textView.setText("Six digit code sent to "+temp);



        next_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PhoneAuth.getInstance().get_verified() && _termAndCondition.isChecked())
                    verified();
                else{
                    if (!_termAndCondition.isChecked())
                        _termAndCondition.setError("Accept to continue");
                }

            }
        });

        back_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(VerifyOtp.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void verifyOtp(View view) {
        String temp = String.valueOf(((TextInputEditText) findViewById(R.id.editText_Login_otp)).getText());
        temp.trim();
        if (temp != null)
            if (temp.length() > 6 || temp.length() < 6)
                ((TextInputEditText) findViewById(R.id.editText_Login_otp)).setError("Enter a valid code");
            else
                PhoneAuth.getInstance().verifyVerificationCode(temp);
        else
            ((TextInputEditText) findViewById(R.id.editText_Login_otp)).setError("Enter a valid code");

    }

    public void verified(){
        startActivity(new Intent(getApplicationContext(),CompleteYourProfile.class));
        finish();
    }

    public void resendOTP(View view) {
        PhoneAuth.getInstance().sendVerificationCode(UserLoginData.getInstance().getPhoneNo());
    }
}