package com.example.arsitektur_mvvm_and_room.ui.crud.select;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.Toast;

import com.example.arsitektur_mvvm_and_room.BR;
import com.example.arsitektur_mvvm_and_room.R;
import com.example.arsitektur_mvvm_and_room.data.db.others.ExecutionTimePreference;
import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
import com.example.arsitektur_mvvm_and_room.databinding.FragmentSelectBinding;
import com.example.arsitektur_mvvm_and_room.di.component.FragmentComponent;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class SelectFragment extends BaseFragment<FragmentSelectBinding, SelectViewModel> implements SelectNavigator,
        SelectAdapter.SelectAdapterListener {
    @Inject
    @Named("select")
    SelectAdapter selectAdapter;

    FragmentSelectBinding selectFragmentBinding;

    @Inject
    LinearLayoutManager linearLayoutManager;

    private ExecutionTimePreference executionTimePreference;

    public static SelectFragment newInstance() {
        Bundle args = new Bundle();
        SelectFragment fragment = new SelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_select;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        selectAdapter.setListener(this);

        executionTimePreference = new ExecutionTimePreference(getBaseActivity());
    }



    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onRetryClick() {

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectFragmentBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateMedical(List<Medical> medicalList) {
        selectAdapter.selectItems(medicalList);
    }

    private void setUp() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectFragmentBinding.selectRecyclerView.setLayoutManager(linearLayoutManager);
        selectFragmentBinding.selectRecyclerView.setItemAnimator(new DefaultItemAnimator());
        selectFragmentBinding.selectRecyclerView.setAdapter(selectAdapter);

        if (!executionTimePreference.getExecutionTime().getDatabaseSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectDB
                    .setText("TIME DB (MS) : " +
                            executionTimePreference.getExecutionTime().getDatabaseSelectTime());

        if (!executionTimePreference.getExecutionTime().getAllSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectAll
                    .setText("TIME ALL (MS) : " +
                            executionTimePreference.getExecutionTime().getAllSelectTime());

        if (!executionTimePreference.getExecutionTime().getViewSelectTime().isEmpty())
            selectFragmentBinding.textViewTimeSelectView
                    .setText("TIME VIEW (MS) : " +
                            executionTimePreference.getExecutionTime().getViewSelectTime());

        if (!executionTimePreference.getExecutionTime().getNumOfRecordSelect().isEmpty())
            selectFragmentBinding.textViewRecord
                    .setText("RECORDS : " +
                            executionTimePreference.getExecutionTime().getNumOfRecordSelect());

        selectFragmentBinding.fabDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFragmentBinding.selectRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        selectFragmentBinding.selectRecyclerView.scrollToPosition(selectAdapter.getItemCount() - 1);
                    }
                });
            }
        });
    }

    @Override
    public void onClick() {
        if (selectFragmentBinding.editTextNumData.getText() != null) {
            try {
                Long numOfData = Long.valueOf(selectFragmentBinding.editTextNumData.getText().toString());
                viewModel.selectDatabase(executionTimePreference, numOfData);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Amount Of Data is Not Valid", Toast.LENGTH_SHORT).show();
        }
    }
}
