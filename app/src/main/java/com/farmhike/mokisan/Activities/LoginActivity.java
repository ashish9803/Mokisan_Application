package com.farmhike.mokisan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.Models.UserLoginData;
import com.farmhike.mokisan.Models.AppContext;
import com.farmhike.mokisan.Models.UserLoginData;
import com.farmhike.mokisan.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.farmhike.mokisan.Utils.PhoneAuth;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button next_login;
    TextInputLayout editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        next_login = (Button)(findViewById(R.id.next_login));
        editText = (TextInputLayout)(findViewById(R.id.editText_Login_phoneNo));
        AppContext.getInstance().setContext(getApplicationContext());

        PhoneAuth.setFirebaseAuth(FirebaseAuth.getInstance());

    }

        next_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePhoneNo();
            }
        });
    }

    public void sendOtp() {
        Intent i = new Intent(LoginActivity.this,VerifyOtp.class);
        startActivity(i);
    }

    private void validatePhoneNo() {
        //EditText editText = findViewById(R.id.editText_Login_phoneNo);
        String phoneNo = editText.getEditText().getText().toString();

        if(phoneNo.length()==0 || phoneNo.length()>10)
        {
            Toast.makeText(getApplicationContext(),"Please Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        else if(!phoneNo.matches("[0-9]{10}"))
        {
            editText.setError("Enter 10 digit mobile number");
        }
        else
        {
            UserLoginData.getInstance().setPhoneNo(phoneNo);
            sendOtp();
        }

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