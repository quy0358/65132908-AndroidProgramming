package tiil.edu.bailamthem1_listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SinhVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sinh_vien);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewSinhVien);

        String[] dsSinhVien = {
                "Nguyễn Văn A",
                "Trần Thị B",
                "Lê Văn C",
                "Phạm Thị D",
                "Hoàng Văn E",
                "Đỗ Thị F"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_custom, R.id.tvItem, dsSinhVien);
        listView.setAdapter(adapter);
    }
}
