package com.example.artworkpool.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.artworkpool.R;
import com.example.artworkpool.fragment.FavoriteFragment;
import com.example.artworkpool.fragment.LatestFragment;
import com.example.artworkpool.fragment.PopularFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PopularFragment popularFragment = new PopularFragment();
        LatestFragment latestFragment = new LatestFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_favorite){
                    setFragment(favoriteFragment);
                    return true;
                }if (id == R.id.menu_popular){
                    setFragment(popularFragment);
                    return true;
                }if (id == R.id.menu_latest){
                    setFragment(latestFragment);
                    return true;
                }
                return false;
            }
        });

        navigation.setSelectedItemId(R.id.menu_latest);

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
