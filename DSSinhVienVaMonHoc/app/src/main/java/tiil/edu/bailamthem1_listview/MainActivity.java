package tiil.edu.bailamthem1_listview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button btnSinhVien = findViewById(R.id.btnSinhVien);
        Button btnMonHoc = findViewById(R.id.btnMonHoc);

        btnSinhVien.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SinhVienActivity.class);
            startActivity(intent);
        });

        btnMonHoc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonHocActivity.class);
            startActivity(intent);
        });
    }
}