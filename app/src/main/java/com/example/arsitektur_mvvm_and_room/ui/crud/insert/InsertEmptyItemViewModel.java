package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

public class InsertEmptyItemViewModel {
    private InsertEmptyItemViewModelListener listener;

    public InsertEmptyItemViewModel(InsertEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface InsertEmptyItemViewModelListener {
        void onRetryClick();
    }
}
