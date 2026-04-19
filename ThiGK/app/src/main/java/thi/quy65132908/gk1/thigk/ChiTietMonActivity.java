package thi.quy65132908.gk1.thigk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietMonActivity extends AppCompatActivity {

    // Khai báo view
    private TextView tvTenMon, tvMoTa, tvNguyenLieu, tvThoiGianNau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_mon);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainChiTietMon), (v, insets) -> {
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
        tvTenMon = findViewById(R.id.tvTenMon);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvNguyenLieu = findViewById(R.id.tvNguyenLieu);
        tvThoiGianNau = findViewById(R.id.tvThoiGianNau);
    }

    // Phương thức nhận dữ liệu từ Intent
    private void nhanDuLieu() {
        Bundle duLieu = getIntent().getExtras();
        if (duLieu != null) {
            String tenMon = duLieu.getString("tenMon", "");
            String moTa = duLieu.getString("moTa", "");
            String nguyenLieu = duLieu.getString("nguyenLieu", "");
            String thoiGianNau = duLieu.getString("thoiGianNau", "");

            // Hiển thị lên giao diện
            tvTenMon.setText(tenMon);
            tvMoTa.setText(moTa);
            tvNguyenLieu.setText(nguyenLieu);
            tvThoiGianNau.setText(thoiGianNau);
        }
    }
}
