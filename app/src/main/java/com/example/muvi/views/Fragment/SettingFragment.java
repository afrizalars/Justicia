package com.example.muvi.views.Fragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.muvi.R;
import com.example.muvi.alarmManager.DailyNotification;


public class SettingFragment extends Fragment {
    ConstraintLayout languageSetting;
    Switch tbDaily, tbReminder;
    DailyNotification dailyNotification = new DailyNotification();


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        languageSetting = view.findViewById(R.id.settingLanguage);
        tbDaily = view.findViewById(R.id.switchDaily);

        tbDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getContext(), "CHECKED DAILY", Toast.LENGTH_SHORT).show();
                    dailyNotification.setAlarm(view.getContext());
                }
                else{
                    Toast.makeText(view.getContext(), "UN-CHECKED DAILY", Toast.LENGTH_SHORT).show();
                    dailyNotification.cancelAlarm(view.getContext());
                }
            }
        });

        languageSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }
        });
    }
}
