package ntquy.ntu.baith7_listview2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView lvTinhThanh;
    private ArrayList<String> danhSachTinhThanh;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("DanhSachTinhThanh");
        }

        // Initialize ListView
        lvTinhThanh = findViewById(R.id.lvTinhThanh);

        // Data - Danh sách các tỉnh thành VN
        danhSachTinhThanh = new ArrayList<>(Arrays.asList(
                "Hà Nội",
                "Thành phố Hồ Chí Minh",
                "Đồng Nai",
                "Bình Thuận",
                "Ninh Thuận",
                "Nha Trang",
                "Đà Nẵng",
                "Huế",
                "Hải Phòng",
                "Cần Thơ",
                "Bình Dương",
                "Khánh Hòa",
                "Long An",
                "Quảng Ninh",
                "Lâm Đồng",
                "Bà Rịa - Vũng Tàu",
                "Thanh Hóa",
                "Nghệ An",
                "Hà Tĩnh",
                "Quảng Bình"
        ));

        // Setup ArrayAdapter with simple list item layout
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, danhSachTinhThanh);

        lvTinhThanh.setAdapter(adapter);
    }
}