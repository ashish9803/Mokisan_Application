package com.farmhike.mokisan.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmhike.mokisan.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation top,bottom;
    ImageView logo;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        top = AnimationUtils.loadAnimation(this,R.anim.top);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom);

        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);

        logo.setAnimation(top);
        name.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.swipe_right,R.anim.swipe_left);
                finish();
            }
        },SPLASH_SCREEN);

    }
}