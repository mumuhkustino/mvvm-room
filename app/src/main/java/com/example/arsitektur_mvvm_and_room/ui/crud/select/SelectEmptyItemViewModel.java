package com.example.arsitektur_mvvm_and_room.ui.crud.select;

public class SelectEmptyItemViewModel {
    private SelectEmptyItemViewModelListener listener;

    public SelectEmptyItemViewModel(SelectEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface SelectEmptyItemViewModelListener {
        void onRetryClick();
    }
}
