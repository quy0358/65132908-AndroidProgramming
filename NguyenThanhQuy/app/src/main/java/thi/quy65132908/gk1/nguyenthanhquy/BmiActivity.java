package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    private EditText edtCanNang, edtChieuCao;
    private Button btnTinhBMI;
    private TextView tvKetQuaBMI, tvTinhTrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // Ánh xạ view
        edtCanNang = findViewById(R.id.edtCanNang);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        btnTinhBMI = findViewById(R.id.btnTinhBMI);
        tvKetQuaBMI = findViewById(R.id.tvKetQuaBMI);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);

        // Xử lý sự kiện click nút Tính BMI
        btnTinhBMI.setOnClickListener(v -> tinhBMI());
    }

    private void tinhBMI() {
        String strCanNang = edtCanNang.getText().toString().trim();
        String strChieuCao = edtChieuCao.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (strCanNang.isEmpty() || strChieuCao.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ cân nặng và chiều cao", Toast.LENGTH_SHORT).show();
            return;
        }

        double canNang = Double.parseDouble(strCanNang);
        double chieuCao = Double.parseDouble(strChieuCao);

        // Kiểm tra giá trị hợp lệ
        if (canNang <= 0 || chieuCao <= 0) {
            Toast.makeText(this, "Cân nặng và chiều cao phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đổi chiều cao từ cm sang m
        double chieuCaoMet = chieuCao / 100.0;

        // Tính BMI = cân nặng / (chiều cao * chiều cao)
        double bmi = canNang / (chieuCaoMet * chieuCaoMet);

        // Hiển thị kết quả BMI
        tvKetQuaBMI.setText(String.format("%.2f", bmi));

        // Xác định tình trạng
        String tinhTrang;
        int color;
        if (bmi < 18.5) {
            tinhTrang = "Gầy (Thiếu cân)";
            color = 0xFF2196F3; // Xanh dương
        } else if (bmi < 25) {
            tinhTrang = "Bình thường";
            color = 0xFF4CAF50; // Xanh lá
        } else if (bmi < 30) {
            tinhTrang = "Thừa cân";
            color = 0xFFFF9800; // Cam
        } else {
            tinhTrang = "Béo phì";
            color = 0xFFF44336; // Đỏ
        }

        tvTinhTrang.setText(tinhTrang);
        tvTinhTrang.setTextColor(color);
    }
}
