package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MonAnActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMonAn;
    private MonAnAdapter adapter;
    private List<MonAn> danhSachMonAn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);

        // Ánh xạ RecyclerView
        recyclerViewMonAn = findViewById(R.id.recyclerViewMonAn);
        recyclerViewMonAn.setLayoutManager(new LinearLayoutManager(this));

        // Đọc dữ liệu từ JSON
        loadMonAnFromJson();

        // Thiết lập adapter với sự kiện click
        adapter = new MonAnAdapter(this, danhSachMonAn, monAn -> {
            // Chuyển sang màn hình chi tiết
            Intent intent = new Intent(MonAnActivity.this, ChiTietMonActivity.class);
            intent.putExtra("ten", monAn.getTen());
            intent.putExtra("moTa", monAn.getMoTa());
            intent.putExtra("nguyenLieu", monAn.getNguyenLieu());
            intent.putExtra("cachLam", monAn.getCachLam());
            intent.putExtra("thoiGian", monAn.getThoiGian());
            startActivity(intent);
        });
        recyclerViewMonAn.setAdapter(adapter);
    }

    // Đọc dữ liệu món ăn từ file JSON trong assets
    private void loadMonAnFromJson() {
        try {
            InputStream is = getAssets().open("mon_an.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                MonAn monAn = new MonAn();
                monAn.setTen(obj.getString("ten"));
                monAn.setMoTa(obj.getString("moTa"));
                monAn.setNguyenLieu(obj.getString("nguyenLieu"));
                monAn.setCachLam(obj.getString("cachLam"));
                monAn.setThoiGian(obj.getString("thoiGian"));
                danhSachMonAn.add(monAn);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi đọc dữ liệu món ăn", Toast.LENGTH_SHORT).show();
        }
    }
}
