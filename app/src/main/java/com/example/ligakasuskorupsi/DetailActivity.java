package com.example.ligakasuskorupsi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView caseImage, caseAdditionalImage1, caseAdditionalImage2;
    private TextView caseDescription;
    private Button btnKembali, btnSebelumnya, btnSelanjutnya;

    private int kasusId;
    private String[] kasusNames = {
            "Kasus PT. Pertamina",
            "Kasus PT. Timah",
            "Kasus PT. BLBI",
            "Kasus PT. Duta Palma Group",
            "Kasus PT. TPPI",
            "Kasus PT. Asabri",
            "Kasus PT. Jiwasraya",
            "Kasus To be continue",
            "Kasus To be continue",
            "Kasus To be continue"
    };

    private String[] kasusDescriptions = {
            "Kejaksaan Agung telah menetapkan sembilan tersangka dalam kasus dugaan korupsi tata kelola minyak mentah di PT Pertamina periode 2018-2023 dengan menyebabkan kerugian negara sebesar Rp193,7 triliun per tahun. Namun pada perkembangannya Kejaksaan Agung menyebutkan jika negara rugi Rp 193,7 triliun per tahun sejak 2018, maka total kerugian negara dalam lima tahun bisa mencapai Rp968,5 triliun atau hampir mencapai Rp1 kuadriliun. Namun, perhitungan ini masih butuh analisis lebih lanjut.\n" +
                    "\n" +
                    "Direktur Penyidikan Jampidsus Kejagung, Abdul Qohar, menetapkan dua tersangka baru Maya Kusmaya, Direktur Pemasaran Pusat dan Niaga, serta Edward Corne, VP Trading Produk Pertamina Patra Niaga.\n" +
                    "\n" +
                    "Sebelumnya, tujuh tersangka telah ditetapkan pada 24 Februari 2025. Dari pihak penyelenggara negara, tersangka meliputi Riva Siahaan (Dirut PT Pertamina Patra Niaga), Sani Dinar Saifuddin (Direktur Feedstock & Product Optimization PT Kilang Pertamina Internasional), Yoki Firnandi (Dirut PT Pertamina International Shipping), dan Agus Purwono (VP Feedstock Management PT Kilang Pertamina Internasional). ",
            "Kejaksaan RI menerima hasil audit BPKP terkait dugaan korupsi tata niaga timah di IUP PT Timah Tbk (2015-2022) dengan serah terima berlangsung di Kejaksaan Agung pada 29 Mei 2024. \n" +
                    "\n" +
                    "Menurut Kepala Pusat Penerangan Hukum Kejaksaan Agung, Ketut Sumedana, sejumlah oknum direksi PT Timah pada 2018-2019 melakukan kerja sama dengan smelter untuk melegalkan penambangan timah ilegal melalui modus sewa peralatan peleburan, menyebabkan kerugian negara dengan mencapai Rp300 triliun. ",
            "Kasus Bantuan Likuiditas Bank Indonesia (BLBI) merupakan salah satu skandal terbesar yang belum tuntas. Hasil audit BPK pada tahun 2000 menyebutkan kerugian negara sebesar Rp138,4 triliun. Melansir dari Indonesia Corruption Watch (ICW), sebelum ditangani KPK, kasus ini diwarnai dengan kontroversi. Hingga 2006, kejaksaan telah memeriksa 65 debitor BLBI, tetapi hanya 16 orang yang diproses ke pengadilan. Sebagian besar pelaku telah menjalani hukuman, sementara 11 kasus dihentikan dan 38 lainnya tidak jelas statusnya. ",
            "Kejagung telah menyelidiki kasus korupsi lahan PT Duta Palma dengan kerugian negara sebesar dalam sejarah mencapai Rp78 triliun. Dalam rapat Komisi III DPR, Jaksa Agung S.T. Burhanuddin, mengungkapkan bahwa pemilik PT Duta Palma Group, Surya Darmadi, diduga menyerobot kawasan hutan lindung. \n" +
                    "\n" +
                    "Surya Darmadi diduga melakukan tindak pidana korupsi dan pencucian uang dalam pembukaan lahan dan pengelolaan perkebunan kelapa sawit tanpa izin dari Kementerian Lingkungan Hidup dan Kehutanan serta tanpa adanya hak guna usaha dari Badan Pertanahan Nasional. ",
            "Dua terdakwa kasus kondensat migas PT Trans Pacific Petrochemical Indonesia (TPPI) senilai USD 2,7 miliar setara dengan Rp37,8 triliun. Raden Priyono dan Djoko Haryono divonis hukuman 4 tahun penjara setelah berbukti bersalah. \n" +
                    "\n" +
                    "Kasus ini berawal dari krisis 1998 yang membuat PT TPPI terpuruk dan mendapat bantuan pemerintah. Namun, pada 2008, perusahaan kembali mengalami kesulitan keuangan akibat tingginya harga bahan baku dan rendahnya harga jual yang menyebabkan kerugian besar. ",
            "Kasus korupsi di PT Asabri melibatkan pengelolaan dana investasi yang menyebabkan kerugian negara sebesar Rp22,7 triliun. Skandal ini terjadi antara 2012-2019, dengan delapan tersangka yang ditetapkan oleh Kejaksaan Agung.  Diantaranya, Benny Tjokrosaputro dan Heru Hidayat juga ikut terlibat dalam kasus ini. ",
            "Kasus korupsi di perusahaan asuransi jiwa tertua di Indonesia, PT Jiwasraya, merugikan negara sebesar Rp16,8 triliun. Kementerian Badan Usaha Milik Negara (BUMN) di bawah kepemimpinan Erick Thohir melaporkan adanya indikasi kecurangan di Jiwasraya ke Kejaksaan Agung. ",
            "Informasi akan diperbarui...",
            "Informasi akan diperbarui...",
            "Informasi akan diperbarui..."
    };

    private int[][] kasusAdditionalImages = {
            {R.mipmap.ic_kasus_1_extra1, R.mipmap.ic_kasus_1_extra2},    // Kasus PT. Pertamina
            {R.mipmap.ic_kasus_2_extra1, R.mipmap.ic_kasus_2_extra2},    // Kasus PT. Timah
            {R.mipmap.ic_kasus_3_extra1, R.mipmap.ic_kasus_3_extra2},    // Kasus PT. BLBI
            {R.mipmap.ic_kasus_4_extra1, R.mipmap.ic_kasus_4_extra2},    // Kasus PT. Duta Palma Group
            {R.mipmap.ic_kasus_5_extra1, R.mipmap.ic_kasus_5_extra2},    // Kasus PT. TPPI
            {R.mipmap.ic_kasus_6_extra1, R.mipmap.ic_kasus_6_extra2},    // Kasus PT. Asabri
            {R.mipmap.ic_kasus_7_extra1, R.mipmap.ic_kasus_7_extra2},    // Kasus PT. Jiwasraya

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        caseImage = findViewById(R.id.caseImage);
        caseAdditionalImage1 = findViewById(R.id.caseAdditionalImage1);
        caseAdditionalImage2 = findViewById(R.id.caseAdditionalImage2);
        caseDescription = findViewById(R.id.caseDescription);
        btnKembali = findViewById(R.id.btnKembaliDetail);
        btnSebelumnya = findViewById(R.id.btnSebelumnya);
        btnSelanjutnya = findViewById(R.id.btnSelanjutnya);

        // Terima data dari ListActivity
        kasusId = getIntent().getIntExtra("kasusId", 0);
        updateDetail(kasusId);

        btnKembali.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, ListActivity.class);
            startActivity(intent);
            finish();
        });

        btnSebelumnya.setOnClickListener(v -> {
            if (kasusId > 0) {
                kasusId--;
                updateDetail(kasusId);
            }
        });

        btnSelanjutnya.setOnClickListener(v -> {
            if (kasusId < kasusNames.length - 1) {
                kasusId++;
                updateDetail(kasusId);
            }
        });
    }

    private void updateDetail(int id) {
        caseDescription.setText(kasusDescriptions[id]);
        int iconResId = getKasusImageResource(kasusNames[id]);
        caseImage.setImageResource(iconResId);

        // Set gambar tambahan
        if (id < kasusAdditionalImages.length) {
            caseAdditionalImage1.setImageResource(kasusAdditionalImages[id][0]);
            caseAdditionalImage2.setImageResource(kasusAdditionalImages[id][1]);
        } else {
            caseAdditionalImage1.setImageResource(R.mipmap.ic_launcher); // Default jika tidak ditemukan
            caseAdditionalImage2.setImageResource(R.mipmap.ic_launcher);
        }

        btnSebelumnya.setEnabled(id > 0);
        btnSelanjutnya.setEnabled(id < kasusNames.length - 1);
    }

    private int getKasusImageResource(String kasusName) {
        return getResources().getIdentifier("ic_kasus_" + (kasusId + 1), "mipmap", getPackageName());
    }
}