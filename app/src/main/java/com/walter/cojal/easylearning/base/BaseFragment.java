package com.walter.cojal.easylearning.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.walter.cojal.easylearning.MyApplication;
import com.walter.cojal.easylearning.di.component.ApplicationComponent;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(getContentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewReady(view, savedInstanceState);
    }

    public abstract int getContentView();

    protected void onViewReady(@NonNull View view, @Nullable Bundle savedInstanceState) {
        resolveDaggerDependency();
    }

    protected void resolveDaggerDependency() {
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication)getActivity().getApplication()).getApplicationComponent();
    }

}
