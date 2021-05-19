package com.example.arsitektur_mvvm_and_room.ui.crud.update;

public class UpdateEmptyItemViewModel {
    private UpdateEmptyItemViewModelListener listener;

    public UpdateEmptyItemViewModel(UpdateEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface UpdateEmptyItemViewModelListener {
        void onRetryClick();
    }
}
