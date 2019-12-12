package com.example.muvi.views.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.example.muvi.views.Fragment.ChatFragment;
import com.example.muvi.views.Fragment.SearchLawyerFragment;
import com.example.muvi.views.Fragment.Favorite;
import com.example.muvi.R;
import com.example.muvi.views.Fragment.MovieFragment;
import com.example.muvi.views.Fragment.SettingFragment;
import com.example.muvi.views.Fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    private int menuId;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_movie:
                        //Fragment initialization
                        MovieFragment movieFragment = new MovieFragment();
                        //Fragment Transaction
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_content, movieFragment);
                        fragmentTransaction.commit();
                        return true;

                    case R.id.navigation_tvshow:
                        //Fragment initialization
                        ChatFragment chatFragment = new ChatFragment();
                        //Fragment Transaction
                        FragmentTransaction fragmentTvShowTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTvShowTransaction.replace(R.id.fragment_content, chatFragment);
                        fragmentTvShowTransaction.commit();
                        return true;

                    case R.id.navigation_favorite:

                        //Fragment initialization
                        Favorite favorite = new Favorite();

                        //Fragment Transaction
                        FragmentTransaction fragmentSettingTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentSettingTransaction.replace(R.id.fragment_content, favorite);
                        fragmentSettingTransaction.commit();
                        return true;

                    case R.id.navigation_search:

                        //Fragment initialization
                        SearchLawyerFragment searchFragment = new SearchLawyerFragment();

                        //Fragment Transaction
                        FragmentTransaction fragmentSearchTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentSearchTransaction.replace(R.id.fragment_content, searchFragment);
                        fragmentSearchTransaction.commit();
                        return true;

                    case R.id.navigation_setting:

                        //Fragment initialization
                        SettingFragment settingFragment = new SettingFragment();

                        //Fragment Transaction
                        FragmentTransaction fragmentSetting2Transaction = getSupportFragmentManager().beginTransaction();
                        fragmentSetting2Transaction.replace(R.id.fragment_content, settingFragment);
                        fragmentSetting2Transaction.commit();
                        return true;

                    default:
                        //Fragment initialization
                        MovieFragment movieFragment2 = new MovieFragment();
                        //Fragment Transaction
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.fragment_content, movieFragment2);
                        fragmentTransaction2.commit();
                        return true;

                }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
