package thi.quy65132908.gk1.thigk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MonAnActivity extends AppCompatActivity {

    // Khai báo các biến
    private ListView lvDanhSachMonAn;
    private MonAnAdapter adapter;
    private List<MonAn> danhSachMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mon_an);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainMonAn), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ view
        lvDanhSachMonAn = findViewById(R.id.lvDanhSachMonAn);

        // Đọc dữ liệu từ file JSON
        danhSachMonAn = docDuLieuTuJson();

        // Tạo adapter và gán cho ListView
        adapter = new MonAnAdapter(this, danhSachMonAn);
        lvDanhSachMonAn.setAdapter(adapter);

        // Bắt sự kiện click vào item trong danh sách
        lvDanhSachMonAn.setOnItemClickListener((parent, view, viTri, id) -> {
            MonAn monDuocChon = danhSachMonAn.get(viTri);

            // Thông báo mục được click
            Toast.makeText(this, "Đã chọn: " + monDuocChon.getTenMon(), Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình chi tiết món ăn
            Intent intent = new Intent(MonAnActivity.this, ChiTietMonActivity.class);
            intent.putExtra("tenMon", monDuocChon.getTenMon());
            intent.putExtra("moTa", monDuocChon.getMoTa());
            intent.putExtra("nguyenLieu", monDuocChon.getNguyenLieu());
            intent.putExtra("thoiGianNau", monDuocChon.getThoiGianNau());
            startActivity(intent);
        });
    }

    // Phương thức đọc dữ liệu từ file JSON trong assets
    private List<MonAn> docDuLieuTuJson() {
        List<MonAn> danhSach = new ArrayList<>();
        try {
            // Mở file JSON từ thư mục assets
            InputStream luongNhap = getAssets().open("danh_sach_mon_an.json");
            int kichThuoc = luongNhap.available();
            byte[] mangByte = new byte[kichThuoc];
            luongNhap.read(mangByte);
            luongNhap.close();

            // Chuyển đổi byte thành chuỗi JSON
            String chuoiJson = new String(mangByte, StandardCharsets.UTF_8);

            // Phân tích chuỗi JSON thành danh sách
            JSONArray mangJson = new JSONArray(chuoiJson);
            for (int i = 0; i < mangJson.length(); i++) {
                JSONObject doiTuong = mangJson.getJSONObject(i);

                MonAn monAn = new MonAn(
                        doiTuong.getString("tenMon"),
                        doiTuong.getString("moTa"),
                        doiTuong.getString("nguyenLieu"),
                        doiTuong.getString("thoiGianNau")
                );
                danhSach.add(monAn);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi đọc dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return danhSach;
    }
}
