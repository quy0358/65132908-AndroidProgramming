package tiil.edu.bailamthem2_listview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NhaCungCapActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "NhaCungCapPrefs";
    private static final String KEY_DS_NHA_CUNG_CAP = "dsNhaCungCap";
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dsNhaCungCap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nha_cung_cap);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewNhaCungCap);
        EditText edtTimKiem = findViewById(R.id.edtTimKiem);
        EditText edtThemNhaCungCap = findViewById(R.id.edtThemNhaCungCap);
        Button btnThem = findViewById(R.id.btnThem);

        // Tải danh sách nhà cung cấp từ SharedPreferences
        dsNhaCungCap = loadData();

        adapter = new ArrayAdapter<>(this, R.layout.list_item_custom, R.id.tvItem, dsNhaCungCap);
        listView.setAdapter(adapter);

        // Tính năng 1: Tìm kiếm / Lọc danh sách
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Tính năng 2: Thêm nhà cung cấp mới
        btnThem.setOnClickListener(v -> {
            String tenNhaCungCap = edtThemNhaCungCap.getText().toString().trim();
            if (!tenNhaCungCap.isEmpty()) {
                dsNhaCungCap.add(tenNhaCungCap);
                saveData();
                // Tạo lại adapter để đồng bộ danh sách
                adapter = new ArrayAdapter<>(this, R.layout.list_item_custom, R.id.tvItem, dsNhaCungCap);
                listView.setAdapter(adapter);
                edtTimKiem.setText("");
                edtThemNhaCungCap.setText("");
                Toast.makeText(this, "Đã thêm: " + tenNhaCungCap, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng nhập tên nhà cung cấp!", Toast.LENGTH_SHORT).show();
            }
        });

        // Tính năng 3: Xem chi tiết khi nhấn vào item
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = adapter.getItem(position);
            Toast.makeText(this, "Bạn chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
        });
    }

    // Lưu danh sách vào SharedPreferences
    private void saveData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<>(dsNhaCungCap);
        editor.putStringSet(KEY_DS_NHA_CUNG_CAP, set);
        editor.apply();
    }

    // Tải danh sách từ SharedPreferences
    private ArrayList<String> loadData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> set = prefs.getStringSet(KEY_DS_NHA_CUNG_CAP, null);
        if (set != null) {
            return new ArrayList<>(set);
        } else {
            // Danh sách mặc định
            return new ArrayList<>(Arrays.asList(
                    "Công ty FPT",
                    "Công ty Thế Giới Di Động",
                    "Công ty Phong Vũ",
                    "Công ty Nguyễn Kim",
                    "Công ty CellphoneS",
                    "Công ty Hoàng Hà Mobile"
            ));
        }
    }
}
