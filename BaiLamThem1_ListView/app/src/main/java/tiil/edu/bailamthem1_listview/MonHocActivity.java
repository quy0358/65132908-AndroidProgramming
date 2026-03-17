package tiil.edu.bailamthem1_listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MonHocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mon_hoc);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewMonHoc);

        String[] dsMonHoc = {
                "Lập trình Android",
                "Cơ sở dữ liệu",
                "Mạng máy tính",
                "Trí tuệ nhân tạo",
                "Cấu trúc dữ liệu",
                "Toán rời rạc"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_custom, R.id.tvItem, dsMonHoc);
        listView.setAdapter(adapter);
    }
}
