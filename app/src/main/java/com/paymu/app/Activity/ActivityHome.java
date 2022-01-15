package com.paymu.app.Activity;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.paymu.app.Fragmen.FragmentHistory;
import com.paymu.app.Fragmen.FragmentHome;
import com.paymu.app.Fragmen.FragmentPayment;
import com.paymu.app.Fragmen.FragmentSetting;
import com.paymu.app.R;

public class ActivityHome extends AppCompatActivity {

    BottomNavigationView BottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNav = findViewById(R.id.bottomnav);
        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            FragmentHome FragmentHome = new FragmentHome();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, FragmentHome)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                Fragment fragment = null;
                switch (i){
                    case R.id.home:
                        fragment = new FragmentHome();
                        break;
                    case R.id.payment:
                        fragment = new FragmentPayment();
                        break;
                    case R.id.History:
                        fragment = new FragmentHistory();
                        break;
                    case R.id.Setting:
                        fragment = new FragmentSetting();
                        break;
                }
                if ( fragment !=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }else{
                   Log.e(TAG,"Error Creating Fragment");
                }

                return true;
            }
        });
    }
}