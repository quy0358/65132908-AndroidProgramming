package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChiTietBaiThuocActivity extends AppCompatActivity {

    ImageView imgChiTietBaiThuoc;
    TextView tvTenBaiThuocChiTiet, tvThoiGianChiTiet, tvMoTaChiTietBaiThuoc, tvHuongDanChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_thuoc);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Ánh xạ view
        imgChiTietBaiThuoc = findViewById(R.id.imgChiTietBaiThuoc);
        tvTenBaiThuocChiTiet = findViewById(R.id.tvTenBaiThuocChiTiet);
        tvThoiGianChiTiet = findViewById(R.id.tvThoiGianChiTiet);
        tvMoTaChiTietBaiThuoc = findViewById(R.id.tvMoTaChiTietBaiThuoc);
        tvHuongDanChiTiet = findViewById(R.id.tvHuongDanChiTiet);

        // Nhận dữ liệu từ Intent
        String tenBaiThuoc = getIntent().getStringExtra("tenBaiThuoc");
        String thoiGian = getIntent().getStringExtra("thoiGian");
        String moTa = getIntent().getStringExtra("moTa");
        String huongDan = getIntent().getStringExtra("huongDan");
        int hinhAnh = getIntent().getIntExtra("hinhAnh", R.drawable.ic_medicine_placeholder);

        // Hiển thị dữ liệu
        tvTenBaiThuocChiTiet.setText(tenBaiThuoc);
        tvThoiGianChiTiet.setText(thoiGian);
        tvMoTaChiTietBaiThuoc.setText(moTa);
        tvHuongDanChiTiet.setText(huongDan);
        imgChiTietBaiThuoc.setImageResource(hinhAnh);
    }
}
