package com.walter.cojal.easylearning.presentation.main._profile.view;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.BuildConfig;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseFragment;
import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.main._profile.IProfileContract;
import com.walter.cojal.easylearning.presentation.main._profile.presenter.ProfilePresenter;
import com.walter.cojal.easylearning.utility.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;

public class ProfileFragment extends BaseFragment implements IProfileContract.IView {

    @Inject
    ProfilePresenter presenter;
    @Inject
    ProgressDialog progressDialog;
    @Inject
    Picasso picasso;

    EditText edtName, edtLastName, edtEmail, edtPhone, edtAge, edtBirthDate;
    RoundedImageView imgIcon;
    ImageButton editImage;
    Button editMain;
    boolean isEditing = false;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(getActivity()))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewReady(view, savedInstanceState);
        setupViews();
        setupListeners();
        presenter.attachView(this);
        presenter.getAssessorData();
    }

    @Override
    public void setupViews() {
        edtAge = getActivity().findViewById(R.id.profile_age);
        edtBirthDate = getActivity().findViewById(R.id.profile_birth_date);
        edtEmail = getActivity().findViewById(R.id.profile_email);
        edtLastName = getActivity().findViewById(R.id.profile_last_name);
        edtName = getActivity().findViewById(R.id.profile_name);
        edtPhone = getActivity().findViewById(R.id.profile_phone);
        imgIcon = getActivity().findViewById(R.id.profile_image);
        editImage = getActivity().findViewById(R.id.profile_edit_image);
        editMain = getActivity().findViewById(R.id.profile_edit_main);
    }

    @Override
    public void setupListeners() {
        editMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditing) {
                    presenter.updateUserData(edtName.getText().toString(),
                            edtLastName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtPhone.getText().toString(),
                            Integer.parseInt(edtAge.getText().toString()),
                            edtBirthDate.getText().toString());
                } else {
                    setMainEdition(true);
                }
                isEditing = !isEditing;
            }
        });
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public boolean validate() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Ingrese su nombre");
            edtName.requestFocus();
            return false;
        }
        if (edtLastName.getText().toString().isEmpty()) {
            edtLastName.setError("Ingrese su apellido");
            edtLastName.requestFocus();
            return false;
        }
        if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("Ingrese su número de celular");
            edtPhone.requestFocus();
            return false;
        }
        if (!edtEmail.getText().toString().isEmpty() && !Utils.verifyEmail(edtEmail.getText().toString())) {
            edtEmail.setError("Ingrese un correo válido");
            edtEmail.requestFocus();
            return false;
        }
        if (edtAge.getText().toString().isEmpty()) {
            edtAge.setError("Ingrese su edad");
            edtAge.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void setMainEdition(boolean edition) {
        edtName.setEnabled(edition);
        edtLastName.setEnabled(edition);
        edtEmail.setEnabled(edition);
        edtPhone.setEnabled(edition);
        edtAge.setEnabled(edition);
        edtBirthDate.setEnabled(edition);
        editImage.setVisibility(edition? View.VISIBLE: View.GONE);
        editMain.setText(edition? R.string.save_changed: R.string.modify_my_profile);
    }

    @Override
    public void fillUserData(User user) {
        edtName.setText(user.getName());
        edtLastName.setText(user.getLastName());
        edtPhone.setText(user.getPhone());
        edtEmail.setText(user.getEmail());
        edtAge.setText(String.valueOf(user.getAge()));
        edtBirthDate.setText(user.getBirthDate());
        fillImage(user.getImage());
    }

    @Override
    public void fillAssessorData(Assessor assessor) {

    }

    @Override
    public void fillImage(String path) {
        picasso.load(path).into(imgIcon);
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int REQUEST_TAKE_PHOTO = 123;
    private void openCamera(){
        int permissionCamera = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        int permissionWrite = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCamera != PackageManager.PERMISSION_GRANTED || permissionWrite != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
            } else {
                dispatchTakePictureIntent();
            }
        } else {
            dispatchTakePictureIntent();
        }
    }

    private String currentPhotoPath = "";
    private File mPhotoFile;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = Utils.createImageFile(getApplicationContext());
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                currentPhotoPath = photoFile.getAbsolutePath();
                mPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_REQUEST_CODE){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                showError("No puede tomar fotos, acepte los permisos");
            } else {
                dispatchTakePictureIntent();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            // picasso.load(mPhotoFile).into(imgIcon);
            resizeImage(currentPhotoPath, mPhotoFile);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), mPhotoFile);
            MultipartBody.Part image = MultipartBody.Part.createFormData("media", mPhotoFile.getName(), requestBody);
            presenter.updateUserImage(image);
        }
    }

    private void resizeImage(String path, File file) {
        byte[] bytes = Utils.getBytesPhoto(path);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
