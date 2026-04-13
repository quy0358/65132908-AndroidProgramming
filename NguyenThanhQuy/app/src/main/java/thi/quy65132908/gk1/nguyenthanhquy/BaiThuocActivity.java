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

public class BaiThuocActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBaiThuoc;
    private BaiThuocAdapter adapter;
    private List<BaiThuoc> danhSachBaiThuoc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_thuoc);

        // Ánh xạ RecyclerView
        recyclerViewBaiThuoc = findViewById(R.id.recyclerViewBaiThuoc);
        recyclerViewBaiThuoc.setLayoutManager(new LinearLayoutManager(this));

        // Đọc dữ liệu từ JSON
        loadBaiThuocFromJson();

        // Thiết lập adapter với sự kiện click
        adapter = new BaiThuocAdapter(this, danhSachBaiThuoc, baiThuoc -> {
            // Chuyển sang màn hình chi tiết
            Intent intent = new Intent(BaiThuocActivity.this, ChiTietBaiThuocActivity.class);
            intent.putExtra("ten", baiThuoc.getTen());
            intent.putExtra("moTa", baiThuoc.getMoTa());
            intent.putExtra("thanhPhan", baiThuoc.getThanhPhan());
            intent.putExtra("cachDung", baiThuoc.getCachDung());
            intent.putExtra("thoiGian", baiThuoc.getThoiGian());
            startActivity(intent);
        });
        recyclerViewBaiThuoc.setAdapter(adapter);
    }

    // Đọc dữ liệu bài thuốc từ file JSON trong assets
    private void loadBaiThuocFromJson() {
        try {
            InputStream is = getAssets().open("bai_thuoc.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                BaiThuoc baiThuoc = new BaiThuoc();
                baiThuoc.setTen(obj.getString("ten"));
                baiThuoc.setMoTa(obj.getString("moTa"));
                baiThuoc.setThanhPhan(obj.getString("thanhPhan"));
                baiThuoc.setCachDung(obj.getString("cachDung"));
                baiThuoc.setThoiGian(obj.getString("thoiGian"));
                danhSachBaiThuoc.add(baiThuoc);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi đọc dữ liệu bài thuốc", Toast.LENGTH_SHORT).show();
        }
    }
}
