package com.farmhike.mokisan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.farmhike.mokisan.R;
import com.google.android.material.navigation.NavigationView;


public class Cart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MeowBottomNavigation nav;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        nav = (MeowBottomNavigation)(findViewById(R.id.bottom_nav));
        drawerLayout = (DrawerLayout)(findViewById(R.id.drawer_layout));
        navigationView = (NavigationView)(findViewById(R.id.navigation_view));
        menu_icon = (ImageView)(findViewById(R.id.menu_icon));

        bottomNavigation();
        navigationDrawer();

    }

    private void bottomNavigation() {

        nav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        nav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_bookmarks_24));
        nav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_shopping_cart_24));
        nav.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_headset_mic_24));
        nav.show(3, true);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }

}