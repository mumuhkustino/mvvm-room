package com.example.arsitektur_mvvm_and_room.di.component;
import android.app.Application;

import com.example.arsitektur_mvvm_and_room.MVVMRoom;
import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.di.module.ApplicationModule;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MVVMRoom mvvmRoom);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
