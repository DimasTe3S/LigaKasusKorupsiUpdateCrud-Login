package com.example.ligakasuskorupsi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ligakasuskorupsi.R; // FIX: Added import for R class
import com.example.ligakasuskorupsi.models.Kasus;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LigaKasusKorupsi.db";
    private static final int DATABASE_VERSION = 1;

    // Table Users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password"; // In a real app, hash passwords!

    // Table Cases
    private static final String TABLE_KASUS = "kasus";
    private static final String COLUMN_KASUS_ID = "id";
    private static final String COLUMN_KASUS_NAMA = "nama";
    private static final String COLUMN_KASUS_DESKRIPSI = "deskripsi";
    private static final String COLUMN_KASUS_FOTO_PATH = "foto_path";

    // SQL for creating Users table
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT UNIQUE,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    // SQL for creating Kasus table
    private static final String CREATE_TABLE_KASUS = "CREATE TABLE " + TABLE_KASUS + "("
            + COLUMN_KASUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_KASUS_NAMA + " TEXT,"
            + COLUMN_KASUS_DESKRIPSI + " TEXT,"
            + COLUMN_KASUS_FOTO_PATH + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_KASUS);

        // Pre-populate some cases if the database is empty initially
        insertInitialKasus(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KASUS);
        onCreate(db);
    }

    // --- User related methods ---
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password); // Remember to hash in production!

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1; // Returns true if insert was successful
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USER_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    // --- Kasus related methods ---

    private void insertInitialKasus(SQLiteDatabase db) {
        // Only insert if the table is empty to prevent duplicates on upgrade
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_KASUS, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) { // If count is 0, table is empty
                addKasus(db, "Kasus PT. Pertamina",
                        "Kejaksaan Agung telah menetapkan sembilan tersangka dalam kasus dugaan korupsi tata kelola minyak mentah di PT Pertamina periode 2018-2023 dengan menyebabkan kerugian negara sebesar Rp193,7 triliun per tahun. Namun pada perkembangannya Kejaksaan Agung menyebutkan jika negara rugi Rp 193,7 triliun per tahun sejak 2018, maka total kerugian negara dalam lima tahun bisa mencapai Rp968,5 triliun atau hampir mencapai Rp1 kuadriliun. Namun, perhitungan ini masih butuh analisis lebih lanjut.\n\nDirektur Penyidikan Jampidsus Kejagung, Abdul Qohar, menetapkan dua tersangka baru Maya Kusmaya, Direktur Pemasaran Pusat dan Niaga, serta Edward Corne, VP Trading Produk Pertamina Patra Niaga.\n\nSebelumnya, tujuh tersangka telah ditetapkan pada 24 Februari 2025. Dari pihak penyelenggara negara, tersangka meliputi Riva Siahaan (Dirut PT Pertamina Patra Niaga), Sani Dinar Saifuddin (Direktur Feedstock & Product Optimization PT Kilang Pertamina Internasional), Yoki Firnandi (Dirut PT Pertamina International Shipping), dan Agus Purwono (VP Feedstock Management PT Kilang Pertamina Internasional). ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_1); // FIX: R.mipmap.ic_kasus_1
                addKasus(db, "Kasus PT. Timah",
                        "Kejaksaan RI menerima hasil audit BPKP terkait dugaan korupsi tata niaga timah di IUP PT Timah Tbk (2015-2022) dengan serah terima berlangsung di Kejaksaan Agung pada 29 Mei 2024. \n\nMenurut Kepala Pusat Penerangan Hukum Kejaksaan Agung, Ketut Sumedana, sejumlah oknum direksi PT Timah pada 2018-2019 melakukan kerja sama dengan smelter untuk melegalkan penambangan timah ilegal melalui modus sewa peralatan peleburan, menyebabkan kerugian negara dengan mencapai Rp300 triliun. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_2); // FIX: R.mipmap.ic_kasus_2
                addKasus(db, "Kasus PT. BLBI",
                        "Kasus Bantuan Likuiditas Bank Indonesia (BLBI) merupakan salah satu skandal terbesar yang belum tuntas. Hasil audit BPK pada tahun 2000 menyebutkan kerugian negara sebesar Rp138,4 triliun. Melansir dari Indonesia Corruption Watch (ICW), sebelum ditangani KPK, kasus ini diwarnai dengan kontroversi. Hingga 2006, kejaksaan telah memeriksa 65 debitor BLBI, tetapi hanya 16 orang yang diproses ke pengadilan. Sebagian besar pelaku telah menjalani hukuman, sementara 11 kasus dihentikan dan 38 lainnya tidak jelas statusnya. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_3); // FIX: R.mipmap.ic_kasus_3
                addKasus(db, "Kasus PT. Duta Palma Group",
                        "Kejagung telah menyelidiki kasus korupsi lahan PT Duta Palma dengan kerugian negara sebesar dalam sejarah mencapai Rp78 triliun. Dalam rapat Komisi III DPR, Jaksa Agung S.T. Burhanuddin, mengungkapkan bahwa pemilik PT Duta Palma Group, Surya Darmadi, diduga menyerobot kawasan hutan lindung. \n\nSurya Darmadi diduga melakukan tindak pidana korupsi dan pencucian uang dalam pembukaan lahan dan pengelolaan perkebunan kelapa sawit tanpa izin dari Kementerian Lingkungan Hidup dan Kehutanan serta tanpa adanya hak guna usaha dari Badan Pertanahan Nasional. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_4); // FIX: R.mipmap.ic_kasus_4
                addKasus(db, "Kasus PT. TPPI",
                        "Dua terdakwa kasus kondensat migas PT Trans Pacific Petrochemical Indonesia (TPPI) senilai USD 2,7 miliar setara dengan Rp37,8 triliun. Raden Priyono dan Djoko Haryono divonis hukuman 4 tahun penjara setelah berbukti bersalah. \n\nKasus ini berawal dari krisis 1998 yang membuat PT TPPI terpuruk dan mendapat bantuan pemerintah. Namun, pada 2008, perusahaan kembali mengalami kesulitan keuangan akibat tingginya harga bahan baku dan rendahnya harga jual yang menyebabkan kerugian besar. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_5); // FIX: R.mipmap.ic_kasus_5
                addKasus(db, "Kasus PT. Asabri",
                        "Kasus korupsi di PT Asabri melibatkan pengelolaan dana investasi yang menyebabkan kerugian negara sebesar Rp22,7 triliun. Skandal ini terjadi antara 2012-2019, dengan delapan tersangka yang ditetapkan oleh Kejaksaan Agung. Diantaranya, Benny Tjokrosaputro dan Heru Hidayat juga ikut terlibat dalam kasus ini. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_6); // FIX: R.mipmap.ic_kasus_6
                addKasus(db, "Kasus PT. Jiwasraya",
                        "Kasus korupsi di perusahaan asuransi jiwa tertua di Indonesia, PT Jiwasraya, merugikan negara sebesar Rp16,8 triliun. Kementerian Badan Usaha Milik Negara (BUMN) di bawah kepemimpinan Erick Thohir melaporkan adanya indikasi kecurangan di Jiwasraya ke Kejaksaan Agung. ",
                        "android.resource://com.example.ligakasuskorupsi/" + R.mipmap.ic_kasus_7); // FIX: R.mipmap.ic_kasus_7
                addKasus(db, "Kasus To be continue 1", "Informasi akan diperbarui...", "");
                addKasus(db, "Kasus To be continue 2", "Informasi akan diperbarui...", "");
                addKasus(db, "Kasus To be continue 3", "Informasi akan diperbarui...", "");
            }
            cursor.close();
        }
    }

    // Overloaded addKasus for initial population (takes SQLiteDatabase object)
    public long addKasus(SQLiteDatabase db, String nama, String deskripsi, String fotoPath) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_KASUS_NAMA, nama);
        values.put(COLUMN_KASUS_DESKRIPSI, deskripsi);
        values.put(COLUMN_KASUS_FOTO_PATH, fotoPath);
        return db.insert(TABLE_KASUS, null, values);
    }

    // Add Kasus (for use outside onCreate)
    public long addKasus(String nama, String deskripsi, String fotoPath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KASUS_NAMA, nama);
        values.put(COLUMN_KASUS_DESKRIPSI, deskripsi);
        values.put(COLUMN_KASUS_FOTO_PATH, fotoPath);
        long result = db.insert(TABLE_KASUS, null, values);
        db.close();
        return result;
    }

    public Kasus getKasusById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_KASUS,
                new String[]{COLUMN_KASUS_ID, COLUMN_KASUS_NAMA, COLUMN_KASUS_DESKRIPSI, COLUMN_KASUS_FOTO_PATH},
                COLUMN_KASUS_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        Kasus kasus = null;
        if (cursor != null && cursor.moveToFirst()) {
            kasus = new Kasus(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_KASUS_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_NAMA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_DESKRIPSI)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_FOTO_PATH))
            );
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return kasus;
    }

    public List<Kasus> getAllKasus() {
        List<Kasus> kasusList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_KASUS + " ORDER BY " + COLUMN_KASUS_ID + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Kasus kasus = new Kasus(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_KASUS_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_NAMA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_DESKRIPSI)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KASUS_FOTO_PATH))
                );
                kasusList.add(kasus);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return kasusList;
    }

    public int updateKasus(Kasus kasus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KASUS_NAMA, kasus.getNama());
        values.put(COLUMN_KASUS_DESKRIPSI, kasus.getDeskripsi());
        values.put(COLUMN_KASUS_FOTO_PATH, kasus.getFotoPath());

        int rowsAffected = db.update(TABLE_KASUS, values, COLUMN_KASUS_ID + " = ?",
                new String[]{String.valueOf(kasus.getId())});
        db.close();
        return rowsAffected;
    }

    public boolean deleteKasus(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_KASUS, COLUMN_KASUS_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
}
