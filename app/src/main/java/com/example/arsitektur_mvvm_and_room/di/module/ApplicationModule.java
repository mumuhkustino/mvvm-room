package com.example.arsitektur_mvvm_and_room.di.module;
import android.app.Application;
import android.content.Context;

import com.example.arsitektur_mvvm_and_room.data.AppDataManager;
import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.db.AppDbHelper;
import com.example.arsitektur_mvvm_and_room.data.db.DbHelper;
import com.example.arsitektur_mvvm_and_room.di.DatabaseInfo;
import com.example.arsitektur_mvvm_and_room.utils.AppConstants;
import com.example.arsitektur_mvvm_and_room.utils.rx.AppSchedulerProvider;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @Singleton
    Context provideContext(Application application) { return application;}

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() { return new AppSchedulerProvider(); }
}
