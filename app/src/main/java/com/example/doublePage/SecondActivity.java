package com.example.doublePage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView txtNama, txtNrp, txtNik, txtAlamat, txtTanggalLahir;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Bind Views
        txtNama = findViewById(R.id.txtNama);
        txtNrp = findViewById(R.id.txtNrp);
        txtNik = findViewById(R.id.txtNik);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtTanggalLahir = findViewById(R.id.txtTanggalLahir);
        btnKembali = findViewById(R.id.btnKembali);

        // Get Data from Intent
        String nama = getIntent().getStringExtra("nama");
        String nrp = getIntent().getStringExtra("nrp");
        String nik = getIntent().getStringExtra("nik");
        String alamat = getIntent().getStringExtra("alamat");
        String tglLahir = getIntent().getStringExtra("tglLahir");

        // Set Data to Views
        txtNama.setText(nama != null && !nama.isEmpty() ? nama : "-");
        txtNrp.setText(nrp != null && !nrp.isEmpty() ? nrp : "-");
        txtNik.setText(nik != null && !nik.isEmpty() ? nik : "-");
        txtAlamat.setText(alamat != null && !alamat.isEmpty() ? alamat : "-");
        txtTanggalLahir.setText(tglLahir != null && !tglLahir.isEmpty() ? tglLahir : "-");

        // Back Button to return to MainActivity
        btnKembali.setOnClickListener(v -> finish());
    }
}
