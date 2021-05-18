package com.example.arsitektur_mvvm_and_room.ui.crud.delete;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewHolder;

import java.util.List;

public class DeleteAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private DeleteAdapterListener listener;

    public DeleteAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
        Log.d("DA", "DeleteAdapter: " + medicalList.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemDeleteViewBinding itemDeleteViewBinding = ItemDeleteViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new DeleteViewHolder(itemDeleteViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemDeleteEmptyViewBinding emptyDeleteViewBinding = ItemDeleteEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new DeleteEmptyViewHolder(emptyDeleteViewBinding);
        }
    }

    public void deleteItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
//        Log.d("CRUDA", "selectItems: " + medicalList.size());
    }

    public void clearItems() {
        this.medicalList.clear();
    }

    public void setListener(DeleteAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (this.medicalList != null && this.medicalList.size() > 0) {
            return this.medicalList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (this.medicalList != null && this.medicalList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface DeleteAdapterListener {
        void onRetryClick();
    }

    public class DeleteViewHolder extends BaseViewHolder {
        private ItemDeleteViewBinding binding;

        private DeleteItemViewModel itemViewModel;

        public DeleteViewHolder(ItemDeleteViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
            this.itemViewModel = new DeleteItemViewModel((long) position, medical);
            this.binding.setViewModel(this.itemViewModel);
            this.binding.executePendingBindings();
        }
    }

    public class DeleteEmptyViewHolder extends BaseViewHolder implements DeleteEmptyItemViewModel.DeleteEmptyItemViewModelListener {

        private ItemDeleteEmptyViewBinding binding;

        public DeleteEmptyViewHolder(ItemDeleteEmptyViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DeleteEmptyItemViewModel emptyItemViewModel = new DeleteEmptyItemViewModel(this);
            this.binding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            listener.onRetryClick();
//            binding.tvMessage.setText("The fetching process was successful");
//            binding.btnRetry.setVisibility(View.GONE);
        }
    }

}
