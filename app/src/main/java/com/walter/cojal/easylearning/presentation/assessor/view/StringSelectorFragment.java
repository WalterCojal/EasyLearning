package com.walter.cojal.easylearning.presentation.assessor.view;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.presentation.IBaseView;

import java.util.ArrayList;

public class StringSelectorFragment extends BottomSheetDialogFragment implements IBaseView {

    private StringAdapter stringAdapter = new StringAdapter();
    private RecyclerView rvItems;
    private OnItemClick itemClick;

    public StringSelectorFragment() {
        // Required empty public constructor
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.fragment_string_selector, null);
        dialog.setContentView(view);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)((View)view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        ((View)view.getParent()).setBackgroundColor(Color.TRANSPARENT);
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(listener);
        }
        rvItems = getDialog().findViewById(R.id.string_selector_items);
        setupViews();
        setupListeners();
    }

    @Override
    public void setupViews() {
        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvItems.setItemAnimator(new DefaultItemAnimator());
        rvItems.setAdapter(stringAdapter);
    }

    @Override
    public void setupListeners() {
        stringAdapter.setOnClickListener(new OnItemClick() {
            @Override
            public void onClick(int position) {
                itemClick.onClick(position);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void showSuccess(String message) {

    }

    void updateItems(ArrayList<String> items) {
        stringAdapter.updateItems(items);
    }

    void setOnClickListener(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    private BottomSheetBehavior.BottomSheetCallback listener = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View view, int i) {
            if (i == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View view, float v) {}
    };
}
