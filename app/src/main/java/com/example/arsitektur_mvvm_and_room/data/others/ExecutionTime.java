package com.example.arsitektur_mvvm_and_room.data.others;

import android.os.Parcel;
import android.os.Parcelable;

public class ExecutionTime implements Parcelable {
    String databaseDeleteTime, allDeleteTime, viewDeleteTime, numOfRecordDelete;
    String databaseInsertTime, allInsertTime, viewInsertTime, numOfRecordInsert;
    String databaseSelectTime, allSelectTime, viewSelectTime, numOfRecordSelect;
    String databaseUpdateTime, allUpdateTime, viewUpdateTime, numOfRecordUpdate;

    public ExecutionTime() {
    }

    protected ExecutionTime(Parcel in) {
        databaseDeleteTime = in.readString();
        allDeleteTime = in.readString();
        viewDeleteTime = in.readString();
        numOfRecordDelete = in.readString();
        databaseInsertTime = in.readString();
        allInsertTime = in.readString();
        viewInsertTime = in.readString();
        numOfRecordInsert = in.readString();
        databaseSelectTime = in.readString();
        allSelectTime = in.readString();
        viewSelectTime = in.readString();
        numOfRecordSelect = in.readString();
        databaseUpdateTime = in.readString();
        allUpdateTime = in.readString();
        viewUpdateTime = in.readString();
        numOfRecordUpdate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(databaseDeleteTime);
        dest.writeString(allDeleteTime);
        dest.writeString(viewDeleteTime);
        dest.writeString(numOfRecordDelete);
        dest.writeString(databaseInsertTime);
        dest.writeString(allInsertTime);
        dest.writeString(viewInsertTime);
        dest.writeString(numOfRecordInsert);
        dest.writeString(databaseSelectTime);
        dest.writeString(allSelectTime);
        dest.writeString(viewSelectTime);
        dest.writeString(numOfRecordSelect);
        dest.writeString(databaseUpdateTime);
        dest.writeString(allUpdateTime);
        dest.writeString(viewUpdateTime);
        dest.writeString(numOfRecordUpdate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExecutionTime> CREATOR = new Creator<ExecutionTime>() {
        @Override
        public ExecutionTime createFromParcel(Parcel in) {
            return new ExecutionTime(in);
        }

        @Override
        public ExecutionTime[] newArray(int size) {
            return new ExecutionTime[size];
        }
    };

    public String getNumOfRecordDelete() {
        return numOfRecordDelete;
    }

    public void setNumOfRecordDelete(String numOfRecordDelete) {
        this.numOfRecordDelete = numOfRecordDelete;
    }

    public String getNumOfRecordInsert() {
        return numOfRecordInsert;
    }

    public void setNumOfRecordInsert(String numOfRecordInsert) {
        this.numOfRecordInsert = numOfRecordInsert;
    }

    public String getNumOfRecordSelect() {
        return numOfRecordSelect;
    }

    public void setNumOfRecordSelect(String numOfRecordSelect) {
        this.numOfRecordSelect = numOfRecordSelect;
    }

    public String getNumOfRecordUpdate() {
        return numOfRecordUpdate;
    }

    public void setNumOfRecordUpdate(String numOfRecordUpdate) {
        this.numOfRecordUpdate = numOfRecordUpdate;
    }

    public String getDatabaseDeleteTime() {
        return databaseDeleteTime;
    }

    public void setDatabaseDeleteTime(String databaseDeleteTime) {
        this.databaseDeleteTime = databaseDeleteTime;
    }

    public String getAllDeleteTime() {
        return allDeleteTime;
    }

    public void setAllDeleteTime(String allDeleteTime) {
        this.allDeleteTime = allDeleteTime;
    }

    public String getViewDeleteTime() {
        return viewDeleteTime;
    }

    public void setViewDeleteTime(String viewDeleteTime) {
        this.viewDeleteTime = viewDeleteTime;
    }

    public String getDatabaseInsertTime() {
        return databaseInsertTime;
    }

    public void setDatabaseInsertTime(String databaseInsertTime) {
        this.databaseInsertTime = databaseInsertTime;
    }

    public String getAllInsertTime() {
        return allInsertTime;
    }

    public void setAllInsertTime(String allInsertTime) {
        this.allInsertTime = allInsertTime;
    }

    public String getViewInsertTime() {
        return viewInsertTime;
    }

    public void setViewInsertTime(String viewInsertTime) {
        this.viewInsertTime = viewInsertTime;
    }

    public String getDatabaseSelectTime() {
        return databaseSelectTime;
    }

    public void setDatabaseSelectTime(String databaseSelectTime) {
        this.databaseSelectTime = databaseSelectTime;
    }

    public String getAllSelectTime() {
        return allSelectTime;
    }

    public void setAllSelectTime(String allSelectTime) {
        this.allSelectTime = allSelectTime;
    }

    public String getViewSelectTime() {
        return viewSelectTime;
    }

    public void setViewSelectTime(String viewSelectTime) {
        this.viewSelectTime = viewSelectTime;
    }

    public String getDatabaseUpdateTime() {
        return databaseUpdateTime;
    }

    public void setDatabaseUpdateTime(String databaseUpdateTime) {
        this.databaseUpdateTime = databaseUpdateTime;
    }

    public String getAllUpdateTime() {
        return allUpdateTime;
    }

    public void setAllUpdateTime(String allUpdateTime) {
        this.allUpdateTime = allUpdateTime;
    }

    public String getViewUpdateTime() {
        return viewUpdateTime;
    }

    public void setViewUpdateTime(String viewUpdateTime) {
        this.viewUpdateTime = viewUpdateTime;
    }
}
