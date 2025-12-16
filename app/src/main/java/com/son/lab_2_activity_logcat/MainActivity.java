package com.son.lab_2_activity_logcat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB;
    TextView txtKetQua;
    Button btnCong, btnTru, btnNhan, btnChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        txtKetQua = findViewById(R.id.txtKetQua);

        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);

        // Gán sự kiện
        btnCong.setOnClickListener(v -> tinhToan("+"));
        btnTru.setOnClickListener(v -> tinhToan("-"));
        btnNhan.setOnClickListener(v -> tinhToan("*"));
        btnChia.setOnClickListener(v -> tinhToan("/"));
    }

    private void tinhToan(String phepTinh) {
        if (edtA.getText().toString().isEmpty() ||
                edtB.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số", Toast.LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(edtA.getText().toString());
        double b = Double.parseDouble(edtB.getText().toString());
        double ketQua = 0;

        switch (phepTinh) {
            case "+":
                ketQua = a + b;
                break;
            case "-":
                ketQua = a - b;
                break;
            case "*":
                ketQua = a * b;
                break;
            case "/":
                if (b == 0) {
                    Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                ketQua = a / b;
                break;
        }

        txtKetQua.setText("Kết quả: " + ketQua);
    }
}
