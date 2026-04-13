package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChiTietMonActivity extends AppCompatActivity {

    ImageView imgChiTiet;
    TextView tvTenMonChiTiet, tvGiaChiTiet, tvMoTaChiTiet, tvNguyenLieuChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Ánh xạ view
        imgChiTiet = findViewById(R.id.imgChiTiet);
        tvTenMonChiTiet = findViewById(R.id.tvTenMonChiTiet);
        tvGiaChiTiet = findViewById(R.id.tvGiaChiTiet);
        tvMoTaChiTiet = findViewById(R.id.tvMoTaChiTiet);
        tvNguyenLieuChiTiet = findViewById(R.id.tvNguyenLieuChiTiet);

        // Nhận dữ liệu từ Intent
        String tenMon = getIntent().getStringExtra("tenMon");
        String gia = getIntent().getStringExtra("gia");
        String moTa = getIntent().getStringExtra("moTa");
        String nguyenLieu = getIntent().getStringExtra("nguyenLieu");
        int hinhAnh = getIntent().getIntExtra("hinhAnh", R.drawable.ic_food_placeholder);

        // Hiển thị dữ liệu
        tvTenMonChiTiet.setText(tenMon);
        tvGiaChiTiet.setText(gia);
        tvMoTaChiTiet.setText(moTa);
        tvNguyenLieuChiTiet.setText(nguyenLieu);
        imgChiTiet.setImageResource(hinhAnh);
    }
}
