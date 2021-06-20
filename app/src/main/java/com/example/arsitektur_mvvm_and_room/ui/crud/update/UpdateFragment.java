package com.example.arsitektur_mvvm_and_room.ui.crud.update;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arsitektur_mvvm_and_room.BR;
import com.example.arsitektur_mvvm_and_room.R;
import com.example.arsitektur_mvvm_and_room.data.db.others.ExecutionTimePreference;
import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
import com.example.arsitektur_mvvm_and_room.databinding.FragmentUpdateBinding;
import com.example.arsitektur_mvvm_and_room.di.component.FragmentComponent;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class UpdateFragment extends BaseFragment<FragmentUpdateBinding, UpdateViewModel> implements UpdateNavigator,
        UpdateAdapter.UpdateAdapterListener {
    @Inject
    @Named("update")
    UpdateAdapter updateAdapter;

    FragmentUpdateBinding fragmentUpdateBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ExecutionTimePreference executionTimePreference;

    public static UpdateFragment newInstance() {
        Bundle args = new Bundle();
        UpdateFragment fragment = new UpdateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_update;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        updateAdapter.setListener(this);
        executionTimePreference = new ExecutionTimePreference(getBaseActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentUpdateBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onRetryClick() {
        if (fragmentUpdateBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentUpdateBinding.editTextNumData.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onClick() {
        if (fragmentUpdateBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(fragmentUpdateBinding.editTextNumData.getText().toString());
                viewModel.updateDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Num Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        updateAdapter.updateItems(medicalList);
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentUpdateBinding.updateRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentUpdateBinding.updateRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentUpdateBinding.updateRecyclerView.setAdapter(updateAdapter);

        if (!executionTimePreference.getExecutionTime().getDatabaseUpdateTime().isEmpty())
            fragmentUpdateBinding.textViewTimeUpdateDB
                    .setText("TIME DB (MS) : " +
                            executionTimePreference.getExecutionTime().getDatabaseUpdateTime());
        if (!executionTimePreference.getExecutionTime().getAllUpdateTime().isEmpty())
            fragmentUpdateBinding.textViewTimeUpdateAll
                    .setText("TIME ALL (MS) : " +
                            executionTimePreference.getExecutionTime().getAllUpdateTime());
        if (!executionTimePreference.getExecutionTime().getViewUpdateTime().isEmpty())
            fragmentUpdateBinding.textViewTimeUpdateView
                    .setText("TIME VIEW (MS) : " +
                            executionTimePreference.getExecutionTime().getViewUpdateTime());
        if (!executionTimePreference.getExecutionTime().getNumOfRecordUpdate().isEmpty())
            fragmentUpdateBinding.textViewRecord
                    .setText("RECORD : " +
                            executionTimePreference.getExecutionTime().getNumOfRecordUpdate());
    }
}
