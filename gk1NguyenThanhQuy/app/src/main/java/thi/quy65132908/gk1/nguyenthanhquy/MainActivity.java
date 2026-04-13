package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnBmi, btnMonAn, btnBaiThuoc, btnGioiThieu, btnLamThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các nút
        btnBmi = findViewById(R.id.btnBmi);
        btnMonAn = findViewById(R.id.btnMonAn);
        btnBaiThuoc = findViewById(R.id.btnBaiThuoc);
        btnGioiThieu = findViewById(R.id.btnGioiThieu);
        btnLamThem = findViewById(R.id.btnLamThem);

        // Xử lý sự kiện click - chuyển màn hình
        btnBmi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BmiActivity.class);
            startActivity(intent);
        });

        btnMonAn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
            startActivity(intent);
        });

        btnBaiThuoc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BaiThuocActivity.class);
            startActivity(intent);
        });

        btnGioiThieu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GioiThieuActivity.class);
            startActivity(intent);
        });

        btnLamThem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LamThemActivity.class);
            startActivity(intent);
        });
    }
}