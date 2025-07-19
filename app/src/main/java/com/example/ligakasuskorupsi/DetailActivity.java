package com.example.ligakasuskorupsi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide; // BARIS INI HARUS ADA DAN BENAR
import java.util.List;

import com.example.ligakasuskorupsi.db.DatabaseHelper;
import com.example.ligakasuskorupsi.models.Kasus;

public class DetailActivity extends AppCompatActivity {

    private ImageView caseImage, caseAdditionalImage1, caseAdditionalImage2;
    private TextView caseDescription, caseTitle;
    private Button btnKembali, btnSebelumnya, btnSelanjutnya, btnEdit, btnDelete;

    private int currentKasusId;
    private DatabaseHelper dbHelper;
    private Kasus currentKasus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        caseImage = findViewById(R.id.caseImage);
        caseAdditionalImage1 = findViewById(R.id.caseAdditionalImage1);
        caseAdditionalImage2 = findViewById(R.id.caseAdditionalImage2);
        caseDescription = findViewById(R.id.caseDescription);
        caseTitle = findViewById(R.id.caseTitle);
        btnKembali = findViewById(R.id.btnKembaliDetail);
        btnSebelumnya = findViewById(R.id.btnSebelumnya);
        btnSelanjutnya = findViewById(R.id.btnSelanjutnya);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        dbHelper = new DatabaseHelper(this);

        currentKasusId = getIntent().getIntExtra("kasusId", -1);
        if (currentKasusId != -1) {
            updateDetail(currentKasusId);
        } else {
            Toast.makeText(this, "Kasus tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnKembali.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        });

        btnSebelumnya.setOnClickListener(v -> {
            List<Kasus> allKasus = dbHelper.getAllKasus();
            int currentIndex = findKasusIndex(allKasus, currentKasusId);
            if (currentIndex > 0) {
                currentKasusId = allKasus.get(currentIndex - 1).getId();
                updateDetail(currentKasusId);
            }
        });

        btnSelanjutnya.setOnClickListener(v -> {
            List<Kasus> allKasus = dbHelper.getAllKasus();
            int currentIndex = findKasusIndex(allKasus, currentKasusId);
            if (currentIndex < allKasus.size() - 1) {
                currentKasusId = allKasus.get(currentIndex + 1).getId();
                updateDetail(currentKasusId);
            }
        });

        btnEdit.setOnClickListener(v -> {
            if (currentKasus != null) {
                Intent intent = new Intent(DetailActivity.this, AddEditCaseActivity.class);
                intent.putExtra("kasusId", currentKasus.getId());
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (currentKasus != null) {
                showDeleteConfirmationDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentKasusId != -1) {
            updateDetail(currentKasusId);
        }
    }

    private void updateDetail(int id) {
        currentKasus = dbHelper.getKasusById(id);
        if (currentKasus != null) {
            caseTitle.setText(currentKasus.getNama());
            caseDescription.setText(currentKasus.getDeskripsi());

            if (currentKasus.getFotoPath() != null && !currentKasus.getFotoPath().isEmpty()) {
                Glide.with(this).load(currentKasus.getFotoPath()).into(caseImage);
            } else {
                caseImage.setImageResource(R.mipmap.ic_launcher);
            }

            caseAdditionalImage1.setImageResource(R.mipmap.ic_launcher);
            caseAdditionalImage2.setImageResource(R.mipmap.ic_launcher);

            List<Kasus> allKasus = dbHelper.getAllKasus();
            int currentIndex = findKasusIndex(allKasus, id);
            btnSebelumnya.setEnabled(currentIndex > 0);
            btnSelanjutnya.setEnabled(currentIndex < allKasus.size() - 1);

        } else {
            Toast.makeText(this, "Kasus tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private int findKasusIndex(List<Kasus> kasusList, int id) {
        for (int i = 0; i < kasusList.size(); i++) {
            if (kasusList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Kasus")
                .setMessage("Apakah Anda yakin ingin menghapus kasus ini?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    if (dbHelper.deleteKasus(currentKasus.getId())) {
                        Toast.makeText(DetailActivity.this, "Kasus berhasil dihapus", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DetailActivity.this, "Gagal menghapus kasus", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
