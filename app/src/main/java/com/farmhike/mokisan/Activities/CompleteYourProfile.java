package com.farmhike.mokisan.Activities;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.farmhike.mokisan.Models.AppContext;
import com.farmhike.mokisan.R;

public class CompleteYourProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_complete_your_profile);
        AppContext.getInstance().setContext(getApplicationContext());

    }
}