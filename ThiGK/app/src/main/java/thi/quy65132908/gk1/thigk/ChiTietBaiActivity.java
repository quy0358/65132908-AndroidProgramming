package thi.quy65132908.gk1.thigk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietBaiActivity extends AppCompatActivity {

    // Khai báo view
    private TextView tvTenBaiThuoc, tvThoiGian, tvMoTa, tvThanhPhan, tvCachDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_bai);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainChiTietBai), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ view
        anhXaView();

        // Nhận dữ liệu từ Intent và hiển thị
        nhanDuLieu();
    }

    // Phương thức ánh xạ view
    private void anhXaView() {
        tvTenBaiThuoc = findViewById(R.id.tvTenBaiThuocChiTiet);
        tvThoiGian = findViewById(R.id.tvThoiGianChiTiet);
        tvMoTa = findViewById(R.id.tvMoTaChiTiet);
        tvThanhPhan = findViewById(R.id.tvThanhPhanChiTiet);
        tvCachDung = findViewById(R.id.tvCachDungChiTiet);
    }

    // Phương thức nhận dữ liệu từ Intent
    private void nhanDuLieu() {
        Bundle duLieu = getIntent().getExtras();
        if (duLieu != null) {
            String tenBaiThuoc = duLieu.getString("tenBaiThuoc", "");
            String thoiGian = duLieu.getString("thoiGian", "");
            String moTa = duLieu.getString("moTa", "");
            String thanhPhan = duLieu.getString("thanhPhan", "");
            String cachDung = duLieu.getString("cachDung", "");

            // Hiển thị lên giao diện
            tvTenBaiThuoc.setText(tenBaiThuoc);
            tvThoiGian.setText(thoiGian);
            tvMoTa.setText(moTa);
            tvThanhPhan.setText(thanhPhan);
            tvCachDung.setText(cachDung);
        }
    }
}
