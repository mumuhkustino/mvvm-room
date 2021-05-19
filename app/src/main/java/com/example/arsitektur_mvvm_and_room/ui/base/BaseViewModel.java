package com.example.arsitektur_mvvm_and_room.ui.base;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;


import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
public abstract class BaseViewModel<N> extends ViewModel {
    private final DataManager dataManager;

    private final ObservableBoolean isLoading = new ObservableBoolean();

    private final SchedulerProvider schedulerProvider;

    private final CompositeDisposable compositeDisposable;

    private WeakReference<N> navigator;

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }
}
