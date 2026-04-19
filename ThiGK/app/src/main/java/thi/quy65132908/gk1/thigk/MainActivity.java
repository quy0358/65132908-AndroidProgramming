package thi.quy65132908.gk1.thigk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Khai báo các nút
    private Button nutBmi, nutMonAn, nutBaiThuoc, nutGioiThieu, nutLamThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ các nút từ layout
        anhXaView();

        // Gán sự kiện click cho các nút
        xuLySuKien();
    }

    // Phương thức ánh xạ view
    private void anhXaView() {
        nutBmi = findViewById(R.id.nutBmi);
        nutMonAn = findViewById(R.id.nutMonAn);
        nutBaiThuoc = findViewById(R.id.nutBaiThuoc);
        nutGioiThieu = findViewById(R.id.nutGioiThieu);
        nutLamThem = findViewById(R.id.nutLamThem);
    }

    // Phương thức xử lý sự kiện click
    private void xuLySuKien() {
        // Chuyển sang màn hình BMI
        nutBmi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BmiActivity.class);
            startActivity(intent);
        });

        // Chuyển sang màn hình Món ăn
        nutMonAn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
            startActivity(intent);
        });

        // Chuyển sang màn hình Bài thuốc
        nutBaiThuoc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BaiThuocActivity.class);
            startActivity(intent);
        });

        // Chuyển sang màn hình Giới thiệu
        nutGioiThieu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GioiThieuActivity.class);
            startActivity(intent);
        });

        // Chuyển sang màn hình Làm thêm
        nutLamThem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LamThemActivity.class);
            startActivity(intent);
        });
    }
}