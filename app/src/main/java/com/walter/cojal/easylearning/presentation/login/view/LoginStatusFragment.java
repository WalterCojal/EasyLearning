package com.walter.cojal.easylearning.presentation.login.view;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.presentation.IBaseView;

public class LoginStatusFragment extends BottomSheetDialogFragment implements IBaseView {

    TextView txtTitle, txtMessage;
    Button btnAgree, btnCancel;
    OnLoginStatusClick clickListener;

    public LoginStatusFragment() {
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.fragment_login_status, null);
        dialog.setContentView(view);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)((View)view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        ((View)view.getParent()).setBackgroundColor(Color.TRANSPARENT);
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(listener);
        }
        setupViews();
        setupListeners();
    }

    @Override
    public void setupViews() {
        txtTitle = getActivity().findViewById(R.id.fls_title);
        txtMessage = getActivity().findViewById(R.id.fls_message);
        btnAgree = getActivity().findViewById(R.id.fls_agree);
        btnCancel = getActivity().findViewById(R.id.fls_cancel);
        txtTitle.setText(R.string.sign_failed);
    }

    @Override
    public void setupListeners() {
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v);
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

    void setOnClickListener(OnLoginStatusClick clickListener) {
        this.clickListener = clickListener;
    }

    void setMessage(String message) {
        txtMessage.setText(message);
    }

    void setAgreeText(String text) {
        btnAgree.setText(text);
    }

    void setCancelText(String text) {
        btnCancel.setText(text);
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
