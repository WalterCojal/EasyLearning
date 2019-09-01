package com.walter.cojal.easylearning.presentation.assessor.view;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.walter.cojal.easylearning.BuildConfig;
import com.walter.cojal.easylearning.R;
import com.walter.cojal.easylearning.base.BaseActivity;
import com.walter.cojal.easylearning.di.component.DaggerPresentationComponent;
import com.walter.cojal.easylearning.di.module.PresentationModule;
import com.walter.cojal.easylearning.presentation.assessor.IAssessorContract;
import com.walter.cojal.easylearning.presentation.assessor.presenter.AssessorPresenter;
import com.walter.cojal.easylearning.presentation.main.view.MainActivity;
import com.walter.cojal.easylearning.utility.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AssessorActivity extends BaseActivity implements IAssessorContract.IView {

    @Inject
    AssessorPresenter presenter;
    @Inject
    ProgressDialog progressDialog;
    @Inject
    Picasso picasso;
    @Inject
    StringSelectorFragment selectorFragment;

    EditText edtGenre, edtDocument, edtAcademic;
    Button btnGenre, btnDocument, btnAcademic, btnSave;
    RoundedImageView imgIcon;
    Toolbar toolbar;
    boolean hasImage = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_assessor;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerPresentationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presentationModule(new PresentationModule(this))
                .build().inject(this);
    }

    @Override
    protected void onViewReady(Bundle saveInstanceState, Intent intent) {
        super.onViewReady(saveInstanceState, intent);
        setupViews();
        setupListeners();
        presenter.attachView(this);
        presenter.getLists();
    }

    @Override
    public void setupViews() {
        edtGenre = findViewById(R.id.assessor_genre);
        edtDocument = findViewById(R.id.assessor_document);
        edtAcademic = findViewById(R.id.assessor_academic);
        btnGenre = findViewById(R.id.assessor_add_genre);
        btnDocument = findViewById(R.id.assessor_add_document);
        btnAcademic = findViewById(R.id.assessor_add_academic);
        btnSave = findViewById(R.id.assessor_save);
        imgIcon = findViewById(R.id.assessor_image);
        toolbar = findViewById(R.id.assessor_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.signup_as_assessor);
    }

    @Override
    public void setupListeners() {
        btnAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setupAcademics();
            }
        });
        btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setupGenres();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addAssessor(edtGenre.getText().toString(),
                        edtDocument.getText().toString(),
                        edtAcademic.getText().toString(),
                        new ArrayList<String>());
            }
        });
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Espere...");
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
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public boolean validate() {
        if (!hasImage) {
            showError("Debe actualizar su fotografía");
            return false;
        }
        if (edtGenre.getText().toString().isEmpty()) {
            edtGenre.setError("Ingresar género");
            edtGenre.requestFocus();
            return false;
        }
        if (edtAcademic.getText().toString().isEmpty()) {
            edtAcademic.setError("Ingresar grado académico");
            edtAcademic.requestFocus();
            return false;
        }
        if (edtDocument.getText().toString().isEmpty()) {
            edtDocument.setError("Ingresar documento de identidad");
            edtDocument.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void showGenreOptions(final ArrayList<String> items) {
        selectorFragment.updateItems(items);
        selectorFragment.show(getSupportFragmentManager(), "Genres");
        selectorFragment.setOnClickListener(new OnItemClick() {
            @Override
            public void onClick(int position) {
                edtGenre.setText(items.get(position));
                selectorFragment.dismiss();
            }
        });
    }

    @Override
    public void showAcademicOptions(final ArrayList<String> items) {
        selectorFragment.updateItems(items);
        selectorFragment.show(getSupportFragmentManager(), "Academics");
        selectorFragment.setOnClickListener(new OnItemClick() {
            @Override
            public void onClick(int position) {
                edtAcademic.setText(items.get(position));
                selectorFragment.dismiss();
            }
        });
    }

    @Override
    public void updateImage() {
        picasso.load(mPhotoFile).into(imgIcon);
        hasImage = true;
    }

    @Override
    public void clearImage() {
        picasso.load(R.drawable.ic_user).into(imgIcon);
        hasImage = false;
    }

    @Override
    public void enableEditors() {
        edtGenre.setFocusable(true);
        edtAcademic.setFocusable(true);
        btnGenre.setVisibility(View.GONE);
        btnAcademic.setVisibility(View.GONE);
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int REQUEST_TAKE_PHOTO = 123;
    private void openCamera(){
        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = Utils.createImageFile(getApplicationContext());
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
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
            presenter.setImage(image);
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
