package com.example.arsitektur_mvvm_and_room.ui.crud.delete;

public class DeleteEmptyItemViewModel {
    private DeleteEmptyItemViewModelListener listener;

    public DeleteEmptyItemViewModel(DeleteEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface DeleteEmptyItemViewModelListener {
        void onRetryClick();
    }
}
