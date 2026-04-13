package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MonAnActivity extends AppCompatActivity {

    ListView lvMonAn;
    ArrayList<MonAn> danhSachMonAn;
    MonAnAdapter adapter;

    // Mảng hình ảnh tương ứng với từng món ăn trong JSON
    int[] hinhAnhMonAn = {
            R.drawable.ic_food_placeholder,
            R.drawable.ic_food_placeholder,
            R.drawable.ic_food_placeholder,
            R.drawable.ic_food_placeholder,
            R.drawable.ic_food_placeholder
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        lvMonAn = findViewById(R.id.lvMonAn);

        // Đọc dữ liệu từ file JSON
        danhSachMonAn = docDuLieuTuJson();

        // Gán adapter cho ListView
        adapter = new MonAnAdapter(this, danhSachMonAn);
        lvMonAn.setAdapter(adapter);

        // Xử lý sự kiện click - chuyển sang ChiTietMonActivity
        lvMonAn.setOnItemClickListener((parent, view, position, id) -> {
            MonAn monAn = danhSachMonAn.get(position);
            Intent intent = new Intent(MonAnActivity.this, ChiTietMonActivity.class);
            intent.putExtra("tenMon", monAn.getTenMon());
            intent.putExtra("gia", monAn.getGia());
            intent.putExtra("moTa", monAn.getMoTa());
            intent.putExtra("nguyenLieu", monAn.getNguyenLieu());
            intent.putExtra("hinhAnh", monAn.getHinhAnh());
            startActivity(intent);
        });
    }

    // Đọc dữ liệu từ file JSON trong assets
    private ArrayList<MonAn> docDuLieuTuJson() {
        ArrayList<MonAn> danhSach = new ArrayList<>();
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
                String tenMon = obj.getString("tenMon");
                String gia = obj.getString("gia");
                String moTa = obj.getString("moTa");
                String nguyenLieu = obj.getString("nguyenLieu");
                int hinhAnh = (i < hinhAnhMonAn.length) ? hinhAnhMonAn[i] : R.drawable.ic_food_placeholder;

                danhSach.add(new MonAn(hinhAnh, tenMon, gia, moTa, nguyenLieu));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
}
