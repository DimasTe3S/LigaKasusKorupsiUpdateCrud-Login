package com.example.ligakasuskorupsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import com.example.ligakasuskorupsi.db.DatabaseHelper; // Import DatabaseHelper
import com.example.ligakasuskorupsi.models.Kasus;     // Import Kasus model

public class ListActivity extends AppCompatActivity {

    private GridView gridView;
    private Button btnKembali, btnTambahKasus; // Added btnTambahKasus
    private List<Kasus> kasusList; // Changed to List<Kasus>
    private DatabaseHelper dbHelper; // DatabaseHelper instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        gridView = findViewById(R.id.gridView);
        btnKembali = findViewById(R.id.btnKembali);
        btnTambahKasus = findViewById(R.id.btnTambahKasus); // Initialize new button

        dbHelper = new DatabaseHelper(this); // Initialize DatabaseHelper

        // Load cases from database
        loadKasusData();

        // Klik item pada grid
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            // Pass the Kasus object or its ID
            intent.putExtra("kasusId", kasusList.get(position).getId()); // Pass ID instead of position
            startActivity(intent);
        });

        // Tombol kembali ke MainActivity
        btnKembali.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Tombol tambah kasus baru
        btnTambahKasus.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, AddEditCaseActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data every time the activity resumes (e.g., after adding/editing/deleting a case)
        loadKasusData();
    }

    private void loadKasusData() {
        kasusList = dbHelper.getAllKasus(); // Get all cases from DB
        KasusAdapter adapter = new KasusAdapter(this, kasusList);
        gridView.setAdapter(adapter);
    }
}