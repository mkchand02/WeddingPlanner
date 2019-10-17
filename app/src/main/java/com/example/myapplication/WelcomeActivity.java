package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.util.SharedPrefHelper;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName,tvEmail,tvType,tvPhone,tvGender;
    ImageView photo;
    int[] status;
    SharedPrefHelper sharedPrefHelper;
    Button btnSelectAll;
    private final static int GALLERY_PERMISSION_REQUEST_CODE = 45;
    private final static int CAMERA_PERMISSION_REQUEST_CODE = 55;
    private final static int GALLERY_REQUEST_CODE = 65;
    private final static int CAMERA_REQUEST_CODE = 75;
    private String [] applicationPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvName = findViewById(R.id.tv_name_wel);
        tvEmail = findViewById(R.id.tv_email_wel);
        tvGender = findViewById(R.id.tv_gender_wel);
        tvPhone = findViewById(R.id.tv_phone_wel);
        photo = findViewById(R.id.iv_wel);
        btnSelectAll = findViewById(R.id.btn_select_wel);
        photo.setOnClickListener(this);
        btnSelectAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_wel:
                showDialog();
                break;
            case R.id.btn_select_wel:
                startActivity(new Intent(WelcomeActivity.this, DashboardActivity.class));
                break;
        }
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(WelcomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.img_custom_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        FrameLayout mDialogNo = dialog.findViewById(R.id.frmNo);
        mDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Camera" ,Toast.LENGTH_SHORT).show();
                pickImageFromCamera();
                dialog.cancel();
            }
        });

        FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Gallery" ,Toast.LENGTH_SHORT).show();
                pickImageFromGallery();
                dialog.cancel();
            }
        });

        dialog.show();
    }
    void pickImageFromGallery() {
        if (ContextCompat.checkSelfPermission(this, applicationPermission[0]) == PackageManager.PERMISSION_GRANTED) {
            captureImageFromGallery();
            Toast.makeText(this, "We already have the Permission", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,applicationPermission[0])) {
                Toast.makeText(this, "Storage Permission Required", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, applicationPermission, GALLERY_PERMISSION_REQUEST_CODE);
            }else
                Toast.makeText(this, "Grant Permission by going into setting", Toast.LENGTH_SHORT).show();
        }
    }

    void pickImageFromCamera() {
        if (ContextCompat.checkSelfPermission(this, applicationPermission[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, applicationPermission[2]) == PackageManager.PERMISSION_GRANTED) {
            captureImageWithCamera();
            Toast.makeText(this, "We already have the Permission", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,applicationPermission[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, applicationPermission[2])) {
                Toast.makeText(this, "Storage Permission Required", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, applicationPermission, CAMERA_PERMISSION_REQUEST_CODE);
            }else
                Toast.makeText(this, "Grant Permission by going into setting", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    captureImageWithCamera();
                    Toast.makeText(this, "We now READ_captureImageWithCameraEXTERNAL_STORAGEhave camera permissions ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please provide camera permissions", Toast.LENGTH_SHORT).show();
                }
                break;
            case GALLERY_PERMISSION_REQUEST_CODE:
                boolean galleryAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (galleryAccepted) {
                    captureImageFromGallery();
                    Toast.makeText(this, "We now have gallery permissions ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please provide gallery permissions", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void captureImageWithCamera() {
        Intent intentImageCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentImageCapture, CAMERA_REQUEST_CODE);
    }

    private void captureImageFromGallery() {
        Intent imagePickerIntent = new Intent();
        imagePickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        imagePickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(imagePickerIntent,"Select Profile Pic"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bitmap);
            photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri filePath = data.getData();
            photo.setImageURI(filePath);
            photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

}