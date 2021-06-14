package com.example.arsitektur_mvvm_and_room.ui.crud.update;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;
public class UpdateViewModel extends BaseViewModel<UpdateNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> databaseUpdateTime;

    private final MutableLiveData<Long> allUpdateTime;

    private final MutableLiveData<Long> viewUpdateTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public UpdateViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.databaseUpdateTime = new MutableLiveData<>();
        this.allUpdateTime = new MutableLiveData<>();
        this.viewUpdateTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void updateDatabase(Long numOfData) {
        AtomicLong viewUpdateTime = new AtomicLong(0);
        AtomicLong updateDbTime = new AtomicLong(0);
        AtomicLong updateTime = new AtomicLong(0);
        AtomicLong allUpdateTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger index = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        getCompositeDisposable().add(getDataManager()
                //Get All Hospital with Limit
                .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                //Get All Medicine with same hospital Id
                .concatMap(hospital -> Flowable.zip(
                        getDataManager().getMedicinesForHospitalId(hospital.id),
                        Flowable.just(hospital),
                        ((medicineList, hospital1) -> medicineList)
                ))
                //Update medicine name with addition new
                .concatMap(medicineList -> {
                    for (int i = 0; i < medicineList.size(); i++) {
                        if (index.get() < numOfData) {
                            medicineList.get(i).name = medicineList.get(i).name + " NEW";
                            index.getAndIncrement();
                        } else
                            break;
                    }
                    return Flowable.fromIterable(medicineList);
                })
                .concatMap(medicine -> {
                    updateTime.set(System.currentTimeMillis());
                    return getDataManager().updateDatabaseMedicine(medicine);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean)
                        updateDbTime.set(updateDbTime.get() + (System.currentTimeMillis() - updateTime.get()));
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (index.get() == numOfData) {
                                this.numOfRecord.setValue(index.longValue()); //Change number of record
                                this.databaseUpdateTime.setValue(updateDbTime.get()); //Change execution time
                                AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                                AtomicLong timeElapsed = new AtomicLong(endTime.get() - allUpdateTime.get());
                                viewUpdateTime.set(timeElapsed.get() - updateDbTime.get());
                                this.viewUpdateTime.setValue(viewUpdateTime.get());
                                this.allUpdateTime.setValue(timeElapsed.get());
                                Log.d("UVM", "updateDatabase: " + index.get());
                                index.getAndIncrement();
                            }
                        }, throwable -> Log.d("UVM", "updateDatabase: " + throwable.getMessage())
                )
        );
    }

    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
        this.medicalListLiveData = medicalListLiveData;
    }

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
    }

    public LiveData<Long> getNumOfRecord() {
        return numOfRecord;
    }

    public LiveData<Long> getDatabaseUpdateTime() {
        return databaseUpdateTime;
    }

    public MutableLiveData<Long> getAllUpdateTime() {
        return allUpdateTime;
    }

    public MutableLiveData<Long> getViewUpdateTime() {
        return viewUpdateTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
