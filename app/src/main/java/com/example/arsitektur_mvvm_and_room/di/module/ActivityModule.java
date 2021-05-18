package com.example.arsitektur_mvvm_and_room.di.module;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseActivity;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.util.function.Supplier;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Provides
//    CRUDViewModel provideCrudViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
//        Supplier<CRUDViewModel> supplier = () -> new CRUDViewModel(dataManager, schedulerProvider);
//        ViewModelProviderFactory<CRUDViewModel> factory = new ViewModelProviderFactory<>(CRUDViewModel.class, supplier);
//        return new ViewModelProvider(activity, factory).get(CRUDViewModel.class);
//    }
//
//    @Provides
//    CRUDPagerAdapter providedCrudPagerAdapter() {
//        return new CRUDPagerAdapter(activity.getSupportFragmentManager());
//    }
}
