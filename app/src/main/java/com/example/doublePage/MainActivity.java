package com.example.doublePage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout layoutNama, layoutNrp, layoutNik, layoutAlamat, layoutTanggalLahir;
    private TextInputEditText edtNama, edtNrp, edtNik, edtAlamat, edtTanggalLahir;
    private Button btnPindah;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind layouts
        layoutNama = findViewById(R.id.layoutNama);
        layoutNrp = findViewById(R.id.layoutNrp);
        layoutNik = findViewById(R.id.layoutNik);
        layoutAlamat = findViewById(R.id.layoutAlamat);
        layoutTanggalLahir = findViewById(R.id.layoutTanggalLahir);

        // Bind edit texts
        edtNama = findViewById(R.id.edtNama);
        edtNrp = findViewById(R.id.edtNrp);
        edtNik = findViewById(R.id.edtNik);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);

        // Bind button
        btnPindah = findViewById(R.id.btnPindah);

        // Setup Date Picker Dialog
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
            edtTanggalLahir.setText(sdf.format(calendar.getTime()));
            layoutTanggalLahir.setError(null);
        };

        View.OnClickListener datePickerClick = v -> new DatePickerDialog(
                MainActivity.this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();

        edtTanggalLahir.setOnClickListener(datePickerClick);
        layoutTanggalLahir.setEndIconOnClickListener(datePickerClick);

        // Submit Button Click
        btnPindah.setOnClickListener(v -> {
            if (validateInputs()) {
                String nama = edtNama.getText().toString().trim();
                String nrp = edtNrp.getText().toString().trim();
                String nik = edtNik.getText().toString().trim();
                String alamat = edtAlamat.getText().toString().trim();
                String tglLahir = edtTanggalLahir.getText().toString().trim();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("nrp", nrp);
                intent.putExtra("nik", nik);
                intent.putExtra("alamat", alamat);
                intent.putExtra("tglLahir", tglLahir);

                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        String nama = edtNama.getText() != null ? edtNama.getText().toString().trim() : "";
        String nrp = edtNrp.getText() != null ? edtNrp.getText().toString().trim() : "";
        String nik = edtNik.getText() != null ? edtNik.getText().toString().trim() : "";
        String alamat = edtAlamat.getText() != null ? edtAlamat.getText().toString().trim() : "";
        String tglLahir = edtTanggalLahir.getText() != null ? edtTanggalLahir.getText().toString().trim() : "";

        // Reset errors
        layoutNama.setError(null);
        layoutNrp.setError(null);
        layoutNik.setError(null);
        layoutAlamat.setError(null);
        layoutTanggalLahir.setError(null);

        // 1. Validasi Nama
        if (nama.isEmpty()) {
            layoutNama.setError("Nama tidak boleh kosong");
            if (isValid) edtNama.requestFocus();
            isValid = false;
        }

        // 2. Validasi NRP (Wajib 12 digit angka)
        if (nrp.isEmpty()) {
            layoutNrp.setError("NRP tidak boleh kosong");
            if (isValid) edtNrp.requestFocus();
            isValid = false;
        } else if (nrp.length() != 12) {
            layoutNrp.setError("NRP harus terdiri dari tepat 12 digit angka");
            if (isValid) edtNrp.requestFocus();
            isValid = false;
        }

        // 3. Validasi NIK (Wajib 16 digit angka)
        if (nik.isEmpty()) {
            layoutNik.setError("NIK tidak boleh kosong");
            if (isValid) edtNik.requestFocus();
            isValid = false;
        } else if (nik.length() != 16) {
            layoutNik.setError("NIK harus terdiri dari tepat 16 digit angka");
            if (isValid) edtNik.requestFocus();
            isValid = false;
        }

        // 4. Validasi Alamat
        if (alamat.isEmpty()) {
            layoutAlamat.setError("Alamat tidak boleh kosong");
            if (isValid) edtAlamat.requestFocus();
            isValid = false;
        }

        // 5. Validasi Tanggal Lahir
        if (tglLahir.isEmpty()) {
            layoutTanggalLahir.setError("Tanggal lahir harus dipilih");
            if (isValid) edtTanggalLahir.requestFocus();
            isValid = false;
        }

        if (!isValid) {
            Toast.makeText(this, "Mohon periksa dan lengkapi data dengan benar", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }
}
