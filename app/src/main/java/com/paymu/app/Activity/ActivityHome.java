package com.paymu.app.Activity;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);
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
                switch (i) {
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
                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error Creating Fragment");
                }

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout1) {
            startActivity(new Intent(this, ActivityLogin.class));
            Toast.makeText(this, "LOG-OUT Berhasil!!!", Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}

