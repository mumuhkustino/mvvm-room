package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTime;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTimePreference;
import com.example.arsitektur_mvvm_and_room.data.others.Medical;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InsertViewModel extends BaseViewModel<InsertNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> databaseInsertTime;

    private final MutableLiveData<Long> allInsertTime;

    private final MutableLiveData<Long> viewInsertTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public InsertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.databaseInsertTime = new MutableLiveData<>();
        this.allInsertTime = new MutableLiveData<>();
        this.viewInsertTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    // Method yang digunakan untuk insert data
    public void insertDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewInsertTime = new AtomicLong(0);
        AtomicLong insertDbTime = new AtomicLong(0);
        AtomicLong insertTime = new AtomicLong(0);
        AtomicLong allInsertTime = new AtomicLong(System.currentTimeMillis());
        //Insert Hospital JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
                .concatMap(hospitals -> {
                    insertTime.set(System.currentTimeMillis());
                    return getDataManager().insertHospitals(hospitals);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        insertDbTime.set(insertDbTime.longValue() + (System.currentTimeMillis() - insertTime.longValue()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                        }, throwable -> Log.d("IVM", "insertDatabase 1: " + throwable.getMessage())
                )
        );
        //Insert Medicine JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseMedicine(numOfData)
                .concatMap(medicines -> {
                    insertTime.set(System.currentTimeMillis());
                    return getDataManager().insertMedicines(medicines);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        insertDbTime.set(insertDbTime.longValue() + (System.currentTimeMillis() - insertTime.longValue()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (aBoolean) {
                                this.numOfRecord.setValue(numOfData); //Change number of record
                                this.databaseInsertTime.setValue(insertDbTime.longValue()); //Change execution time
                                AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                                AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allInsertTime.longValue());
                                viewInsertTime.set(timeElapsed.longValue() - insertDbTime.longValue());
                                this.viewInsertTime.setValue(viewInsertTime.longValue());
                                this.allInsertTime.setValue(timeElapsed.longValue());

                                ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                                executionTime.setDatabaseInsertTime(insertDbTime.toString());
                                executionTime.setAllInsertTime(timeElapsed.toString());
                                executionTime.setViewInsertTime(viewInsertTime.toString());
                                executionTime.setNumOfRecordInsert(numOfData.toString());
                                executionTimePreference.setExecutionTime(executionTime);
                            }
                        }, throwable -> Log.d("IVM", "insertDatabase 2: " + throwable.getMessage())
                )
        );
    }

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
    }

    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
        this.medicalListLiveData = medicalListLiveData;
    }

    public LiveData<Long> getNumOfRecord() {
        return numOfRecord;
    }

    public LiveData<Long> getDatabaseInsertTime() {
        return databaseInsertTime;
    }

    public MutableLiveData<Long> getAllInsertTime() {
        return allInsertTime;
    }

    public MutableLiveData<Long> getViewInsertTime() {
        return viewInsertTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
