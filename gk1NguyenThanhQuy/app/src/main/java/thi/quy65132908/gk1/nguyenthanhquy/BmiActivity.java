package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class BmiActivity extends AppCompatActivity {

    EditText edtChieuCao, edtCanNang;
    Button btnTinhBmi;
    TextView tvChiSoBmi, tvTinhTrang;
    LinearLayout layoutKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Ánh xạ view
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        btnTinhBmi = findViewById(R.id.btnTinhBmi);
        tvChiSoBmi = findViewById(R.id.tvChiSoBmi);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);
        layoutKetQua = findViewById(R.id.layoutKetQua);

        // Xử lý tính BMI
        btnTinhBmi.setOnClickListener(v -> tinhBmi());
    }

    private void tinhBmi() {
        String strChieuCao = edtChieuCao.getText().toString().trim();
        String strCanNang = edtCanNang.getText().toString().trim();

        if (strChieuCao.isEmpty() || strCanNang.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double chieuCaoCm = Double.parseDouble(strChieuCao);
            double canNang = Double.parseDouble(strCanNang);

            if (chieuCaoCm <= 0 || canNang <= 0) {
                Toast.makeText(this, "Giá trị phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đổi cm sang m
            double chieuCaoM = chieuCaoCm / 100.0;

            // Tính BMI = cân nặng / (chiều cao ^ 2)
            double bmi = canNang / (chieuCaoM * chieuCaoM);

            // Hiển thị kết quả
            tvChiSoBmi.setText(String.format("%.1f", bmi));

            // Phân loại tình trạng
            String tinhTrang;
            int mauSac;

            if (bmi < 18.5) {
                tinhTrang = getString(R.string.bmi_underweight);
                mauSac = ContextCompat.getColor(this, R.color.bmi_underweight);
            } else if (bmi < 25) {
                tinhTrang = getString(R.string.bmi_normal);
                mauSac = ContextCompat.getColor(this, R.color.bmi_normal);
            } else if (bmi < 30) {
                tinhTrang = getString(R.string.bmi_overweight);
                mauSac = ContextCompat.getColor(this, R.color.bmi_overweight);
            } else {
                tinhTrang = getString(R.string.bmi_obese);
                mauSac = ContextCompat.getColor(this, R.color.bmi_obese);
            }

            tvTinhTrang.setText(tinhTrang);
            tvTinhTrang.setTextColor(mauSac);
            tvChiSoBmi.setTextColor(mauSac);

            // Hiện layout kết quả
            layoutKetQua.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }
}
