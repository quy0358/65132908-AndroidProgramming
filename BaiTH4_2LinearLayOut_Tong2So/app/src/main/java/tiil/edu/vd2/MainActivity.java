package tiil.edu.vd2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtSoThuNhat, edtSoThuHai, edtKetQua;
    Button btnCong, btnTru, btnNhan, btnChia;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        edtSoThuNhat = findViewById(R.id.edtSoThuNhat);
        edtSoThuHai = findViewById(R.id.edtSoThuHai);
        edtKetQua = findViewById(R.id.edtKetQua);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        seekBar = findViewById(R.id.seekBar);

        // Xử lý sự kiện nút Cộng
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan('+');
            }
        });

        // Xử lý sự kiện nút Trừ
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan('-');
            }
        });

        // Xử lý sự kiện nút Nhân
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan('*');
            }
        });

        // Xử lý sự kiện nút Chia
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan('/');
            }
        });
    }

    private void tinhToan(char phepTinh) {
        String strSo1 = edtSoThuNhat.getText().toString().trim();
        String strSo2 = edtSoThuHai.getText().toString().trim();

        if (strSo1.isEmpty() || strSo2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ hai số!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double so1 = Double.parseDouble(strSo1);
            double so2 = Double.parseDouble(strSo2);
            double ketQua = 0;

            switch (phepTinh) {
                case '+':
                    ketQua = so1 + so2;
                    break;
                case '-':
                    ketQua = so1 - so2;
                    break;
                case '*':
                    ketQua = so1 * so2;
                    break;
                case '/':
                    if (so2 == 0) {
                        Toast.makeText(this, "Không thể chia cho 0!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ketQua = so1 / so2;
                    break;
            }

            edtKetQua.setText(String.valueOf(ketQua));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }
}