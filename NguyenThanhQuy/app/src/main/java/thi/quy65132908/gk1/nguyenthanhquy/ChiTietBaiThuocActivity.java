package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietBaiThuocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_thuoc);

        // Ánh xạ view
        TextView tvTen = findViewById(R.id.tvTenBaiThuocChiTiet);
        TextView tvMoTa = findViewById(R.id.tvMoTaBaiThuocChiTiet);
        TextView tvThanhPhan = findViewById(R.id.tvThanhPhan);
        TextView tvCachDung = findViewById(R.id.tvCachDung);
        TextView tvThoiGian = findViewById(R.id.tvThoiGianChiTiet);

        // Nhận dữ liệu từ Intent
        String ten = getIntent().getStringExtra("ten");
        String moTa = getIntent().getStringExtra("moTa");
        String thanhPhan = getIntent().getStringExtra("thanhPhan");
        String cachDung = getIntent().getStringExtra("cachDung");
        String thoiGian = getIntent().getStringExtra("thoiGian");

        // Hiển thị dữ liệu
        tvTen.setText(ten);
        tvMoTa.setText(moTa);
        tvThanhPhan.setText(thanhPhan);
        tvCachDung.setText(cachDung);
        tvThoiGian.setText(thoiGian);
    }
}
