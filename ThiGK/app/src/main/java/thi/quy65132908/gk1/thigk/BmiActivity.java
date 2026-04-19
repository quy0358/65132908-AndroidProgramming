package thi.quy65132908.gk1.thigk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BmiActivity extends AppCompatActivity {

    // Khai báo các view
    private EditText edtChieuCao, edtCanNang;
    private TextView tvKetQuaBmi, tvTinhTrang;
    private Button nutTinhBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainBmi), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ view
        anhXaView();

        // Xử lý sự kiện
        xuLySuKien();
    }

    // Phương thức ánh xạ view
    private void anhXaView() {
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        tvKetQuaBmi = findViewById(R.id.tvKetQuaBmi);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);
        nutTinhBmi = findViewById(R.id.nutTinhBmi);
    }

    // Phương thức xử lý sự kiện click nút tính
    private void xuLySuKien() {
        nutTinhBmi.setOnClickListener(v -> {
            tinhBmi();
        });
    }

    // Phương thức tính chỉ số BMI
    private void tinhBmi() {
        String chuoiChieuCao = edtChieuCao.getText().toString().trim();
        String chuoiCanNang = edtCanNang.getText().toString().trim();

        // Kiểm tra dữ liệu nhập vào
        if (chuoiChieuCao.isEmpty() || chuoiCanNang.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ chiều cao và cân nặng!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double chieuCao = Double.parseDouble(chuoiChieuCao);
            double canNang = Double.parseDouble(chuoiCanNang);

            // Kiểm tra giá trị hợp lệ
            if (chieuCao <= 0 || canNang <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tính chỉ số BMI = cân nặng / (chiều cao * chiều cao)
            double chiSoBmi = canNang / (chieuCao * chieuCao);

            // Hiển thị kết quả BMI (làm tròn 2 chữ số thập phân)
            String ketQua = String.format("%.2f", chiSoBmi);
            tvKetQuaBmi.setText(ketQua);

            // Phân loại tình trạng sức khỏe
            String tinhTrang = phanLoaiBmi(chiSoBmi);
            tvTinhTrang.setText(tinhTrang);

            // Thông báo kết quả bằng Toast
            Toast.makeText(this, "BMI: " + ketQua + " - " + tinhTrang, Toast.LENGTH_LONG).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức phân loại BMI theo tiêu chuẩn WHO
    private String phanLoaiBmi(double chiSoBmi) {
        if (chiSoBmi < 18.5) {
            return "Gầy (Thiếu cân)";
        } else if (chiSoBmi < 24.9) {
            return "Bình thường";
        } else if (chiSoBmi < 29.9) {
            return "Thừa cân";
        } else {
            return "Béo phì";
        }
    }
}
