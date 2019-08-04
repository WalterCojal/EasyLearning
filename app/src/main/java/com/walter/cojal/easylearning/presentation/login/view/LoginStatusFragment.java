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

public class LoginStatusFragment extends BottomSheetDialogFragment {

    TextView txtTitle, txtMessage;
    Button btnAgree, btnCancel;
    String message;
    int status;
    OnLoginStatusClick clickListener;

    public LoginStatusFragment(String message, int status) {
        this.message = message;
        this.status = status;
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
        txtTitle = view.findViewById(R.id.fls_title);
        txtMessage = view.findViewById(R.id.fls_message);
        btnAgree = view.findViewById(R.id.fls_agree);
        btnCancel = view.findViewById(R.id.fls_cancel);

        txtTitle.setText(R.string.sign_failed);
        txtMessage.setText(message);
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, status);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, status);
            }
        });
    }

    public void setOnClickListener(OnLoginStatusClick clickListener) {
        this.clickListener = clickListener;
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
