package com.farmhike.mokisan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.Models.UserLoginData;
import com.farmhike.mokisan.R;

public class VerifyOtp extends AppCompatActivity {

    TextView textView;
    Button next_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);

        textView = (TextView)(findViewById(R.id.heading_otp2));
        next_otp = (Button)(findViewById(R.id.next_otp));

        String temp = UserLoginData.getInstance().getPhoneNo();
        textView.setText(temp);

        next_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerifyOtp.this,CompleteYourProfile.class);
                startActivity(i);
            }
        });
    }

    //public void verifyOtp(View view) {
        /*EditText edTemp = findViewById(R.id.editText_Login_otp);
        String temp = String.valueOf(edTemp.getText());
        */
       // String temp = UserLoginData.getInstance().getPhoneNo();
        //Toast.makeText(this, "" + temp, Toast.LENGTH_SHORT).show();
    //}
}