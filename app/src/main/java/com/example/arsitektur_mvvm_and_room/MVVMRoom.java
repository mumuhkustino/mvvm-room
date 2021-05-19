package com.example.arsitektur_mvvm_and_room;

import android.app.Application;

import com.example.arsitektur_mvvm_and_room.di.component.ApplicationComponent;
import com.example.arsitektur_mvvm_and_room.di.component.DaggerApplicationComponent;

public class MVVMRoom extends Application {

    public ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();

        applicationComponent.inject(this);
    }
}
