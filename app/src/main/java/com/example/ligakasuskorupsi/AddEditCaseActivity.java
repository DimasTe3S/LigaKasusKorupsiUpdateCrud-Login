package com.example.ligakasuskorupsi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide; // BARIS INI HARUS ADA DAN BENAR
import com.example.ligakasuskorupsi.db.DatabaseHelper;
import com.example.ligakasuskorupsi.models.Kasus;

public class AddEditCaseActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private EditText etCaseName, etCaseDescription;
    private ImageView ivCaseImage;
    private Button btnChooseImage, btnSaveCase, btnCancel;

    private Uri imageUri;
    private DatabaseHelper dbHelper;
    private int kasusId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_case);

        etCaseName = findViewById(R.id.etCaseName);
        etCaseDescription = findViewById(R.id.etCaseDescription);
        ivCaseImage = findViewById(R.id.ivCaseImage);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnSaveCase = findViewById(R.id.btnSaveCase);
        btnCancel = findViewById(R.id.btnCancel);

        dbHelper = new DatabaseHelper(this);

        if (getIntent().hasExtra("kasusId")) {
            kasusId = getIntent().getIntExtra("kasusId", -1);
            if (kasusId != -1) {
                loadKasusData(kasusId);
            }
        }

        btnChooseImage.setOnClickListener(v -> checkPermissionAndPickImage());
        btnSaveCase.setOnClickListener(v -> saveKasus());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void loadKasusData(int id) {
        Kasus kasus = dbHelper.getKasusById(id);
        if (kasus != null) {
            etCaseName.setText(kasus.getNama());
            etCaseDescription.setText(kasus.getDeskripsi());
            if (kasus.getFotoPath() != null && !kasus.getFotoPath().isEmpty()) {
                imageUri = Uri.parse(kasus.getFotoPath());
                Glide.with(this).load(imageUri).into(ivCaseImage);
            }
        }
    }

    private void checkPermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_REQUEST_CODE);
            } else {
                openImageChooser();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                openImageChooser();
            }
        }
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(ivCaseImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageChooser();
            } else {
                Toast.makeText(this, "Permission denied to read your images.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveKasus() {
        String nama = etCaseName.getText().toString().trim();
        String deskripsi = etCaseDescription.getText().toString().trim();
        String fotoPath = (imageUri != null) ? imageUri.toString() : "";

        if (nama.isEmpty() || deskripsi.isEmpty()) {
            Toast.makeText(this, "Nama dan deskripsi kasus tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Kasus kasus = new Kasus();
        kasus.setNama(nama);
        kasus.setDeskripsi(deskripsi);
        kasus.setFotoPath(fotoPath);

        long result;
        if (kasusId == -1) {
            result = dbHelper.addKasus(nama, deskripsi, fotoPath);
            if (result != -1) {
                Toast.makeText(this, "Kasus berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Gagal menambahkan kasus", Toast.LENGTH_SHORT).show();
            }
        } else {
            kasus.setId(kasusId);
            result = dbHelper.updateKasus(kasus);
            if (result > 0) {
                Toast.makeText(this, "Kasus berhasil diperbarui", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Gagal memperbarui kasus", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
