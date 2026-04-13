package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietMonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        // Ánh xạ view
        TextView tvTen = findViewById(R.id.tvTenMonAnChiTiet);
        TextView tvMoTa = findViewById(R.id.tvMoTaChiTiet);
        TextView tvNguyenLieu = findViewById(R.id.tvNguyenLieu);
        TextView tvCachLam = findViewById(R.id.tvCachLam);
        TextView tvThoiGian = findViewById(R.id.tvThoiGian);

        // Nhận dữ liệu từ Intent
        String ten = getIntent().getStringExtra("ten");
        String moTa = getIntent().getStringExtra("moTa");
        String nguyenLieu = getIntent().getStringExtra("nguyenLieu");
        String cachLam = getIntent().getStringExtra("cachLam");
        String thoiGian = getIntent().getStringExtra("thoiGian");

        // Hiển thị dữ liệu
        tvTen.setText(ten);
        tvMoTa.setText(moTa);
        tvNguyenLieu.setText(nguyenLieu);
        tvCachLam.setText(cachLam);
        tvThoiGian.setText(thoiGian);
    }
}
