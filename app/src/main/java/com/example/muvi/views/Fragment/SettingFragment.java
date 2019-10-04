package com.example.muvi.views.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.muvi.R;
import com.example.muvi.notification.DailyReminder;
import com.example.muvi.notification.ReleaseReminder;
import com.example.muvi.notification.ReminderPreference;


public class SettingFragment extends Fragment {
    ConstraintLayout languageSetting;
    Switch tbDaily, tbReminder;

    DailyReminder dailyReminder;
    ReleaseReminder releaseReminder;
    SharedPreferences spDaily, spRelease;
    public SharedPreferences.Editor editorspRelease, editorspDaily;
    ReminderPreference reminderPreference;


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
        dailyReminder = new DailyReminder();
        releaseReminder = new ReleaseReminder();

        reminderPreference = new ReminderPreference(view.getContext());

        spRelease = getContext().getSharedPreferences(ReminderPreference.KEY_RELEASE_ISON, Context.MODE_PRIVATE);
        spDaily = getContext().getSharedPreferences(ReminderPreference.KEY_REMINDER_ISON, Context.MODE_PRIVATE);

        boolean isReleaseOn = spRelease.getBoolean(ReminderPreference.KEY_RELEASE_ISON, false);
        boolean isDailyOn = spDaily.getBoolean(ReminderPreference.KEY_REMINDER_ISON,false);

        tbDaily = view.findViewById(R.id.switchDaily);
        tbReminder = view.findViewById(R.id.switchRelease);

        tbDaily.setChecked(isDailyOn);
        tbReminder.setChecked(isReleaseOn);

        languageSetting = view.findViewById(R.id.settingLanguage);


        tbDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editorspDaily = spDaily.edit();

                if (isChecked){
                    editorspDaily.putBoolean(ReminderPreference.KEY_REMINDER_ISON,true);
                    editorspDaily.apply();
                    editorspDaily.commit();
                    Toast.makeText(getContext(), "Daily reminder is on", Toast.LENGTH_SHORT).show();
                    dailyReminder.setAlarm(view.getContext());
                }
                else{
                    editorspDaily.putBoolean(ReminderPreference.KEY_REMINDER_ISON,false);
                    editorspDaily.apply();
                    editorspDaily.commit();
                    Toast.makeText(view.getContext(), "Daily reminder is off", Toast.LENGTH_SHORT).show();
                    dailyReminder.cancelAlarm(view.getContext());
                }
            }
        });

        tbReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editorspRelease = spRelease.edit();

                if (isChecked){
                    editorspRelease.putBoolean(ReminderPreference.KEY_RELEASE_ISON,true);
                    editorspRelease.apply();
                    editorspRelease.commit();
                    Toast.makeText(getContext(), "Release Movie Reminder is on", Toast.LENGTH_SHORT).show();
                    releaseReminder.setAlarm(view.getContext());
                }

                else{
                    editorspRelease.putBoolean(ReminderPreference.KEY_RELEASE_ISON,false);
                    editorspRelease.apply();
                    editorspRelease.commit();
                    Toast.makeText(view.getContext(), "Release Movie Reminder is off", Toast.LENGTH_SHORT).show();
                    releaseReminder.cancelAlarm(view.getContext());
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
