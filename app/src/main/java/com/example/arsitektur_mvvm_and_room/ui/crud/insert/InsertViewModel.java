package com.example.arsitektur_mvvm_and_room.ui.crud.insert;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;

import java.util.List;
import io.reactivex.Flowable;

public class InsertViewModel extends BaseViewModel<InsertNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public InsertViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void insertDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        //Insert Hospital JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseHospital(numOfData)
                .concatMap(Flowable::fromIterable)
                .concatMap(hospital -> getDataManager().insertHospital(hospital))
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                        } , throwable -> Log.d("IVM", "insertDatabase 1: " + throwable.getMessage())
                )
        );
        //Insert Medicine JSON to DB
        getCompositeDisposable().add(getDataManager()
                .seedDatabaseMedicine(numOfData)
                .concatMap(Flowable::fromIterable)
                .concatMap(medicine -> getDataManager().insertMedicine(medicine))
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (aBoolean) {
                                this.numOfRecord.setValue(numOfData); //Change number of record
                                long endTime = System.currentTimeMillis();
                                long timeElapsed = endTime - startTime; //In MilliSeconds
                                this.executionTime.setValue(timeElapsed); //Change execution time
                            }
                        } , throwable -> Log.d("IVM", "insertDatabase 2: " + throwable.getMessage())
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

    public LiveData<Long> getExecutionTime() {
        return executionTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
