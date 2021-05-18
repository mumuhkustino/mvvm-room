package com.example.arsitektur_mvvm_and_room.ui.base;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import javax.inject.Inject;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    private BaseActivity baseActivity;
    private View view;
    private T viewDataBinding;

    @Inject
    protected V viewModel;

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.baseActivity = baseActivity;
            baseActivity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection(getBuildComponent());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        view = viewDataBinding.getRoot();
        return view;
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.setVariable(getBindingVariable(), viewModel);
        viewDataBinding.setLifecycleOwner(this);
        viewDataBinding.executePendingBindings();
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public T getViewDataBinding() {
        return viewDataBinding;
    }

    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

    private FragmentComponent getBuildComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(((MVVMgreenDao)(getContext().getApplicationContext())).applicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }


    public abstract void performDependencyInjection(FragmentComponent buildComponent);

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
