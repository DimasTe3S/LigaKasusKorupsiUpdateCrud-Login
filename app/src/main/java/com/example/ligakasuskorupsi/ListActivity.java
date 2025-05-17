package com.example.ligakasuskorupsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private GridView gridView;
    private Button btnKembali;
    private List<String> kasusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        gridView = findViewById(R.id.gridView);
        btnKembali = findViewById(R.id.btnKembali);

        // Inisialisasi list kasus
        kasusList = new ArrayList<>();
        kasusList.add("Kasus PT. Pertamina");
        kasusList.add("Kasus PT. Timah");
        kasusList.add("Kasus PT. BLBI");
        kasusList.add("Kasus PT. Duta Palma Group");
        kasusList.add("Kasus PT. TPPI");
        kasusList.add("Kasus PT. Asabri");
        kasusList.add("Kasus PT. Jiwasraya");
        kasusList.add("Kasus To be continue");
        kasusList.add("Kasus To be continue");
        kasusList.add("Kasus To be continue");

        // Set adapter
        KasusAdapter adapter = new KasusAdapter(this, kasusList);
        gridView.setAdapter(adapter);

        // Klik item pada grid
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            intent.putExtra("kasusId", position);
            intent.putExtra("kasusName", kasusList.get(position));
            startActivity(intent);
        });

        // Tombol kembali ke MainActivity
        btnKembali.setOnClickListener(v -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}