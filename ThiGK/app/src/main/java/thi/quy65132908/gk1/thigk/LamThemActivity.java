package thi.quy65132908.gk1.thigk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LamThemActivity extends AppCompatActivity {

    // URL RSS VN Express - Thế giới
    private static final String RSS_URL = "https://vnexpress.net/rss/the-gioi.rss";

    // Khai báo các view
    private RecyclerView recyclerViewTinTuc;
    private TinTucAdapter adapter;
    private ProgressBar thanhTaiDuLieu;

    // Danh sách tin tức
    private List<TinRss> tatCaTin = new ArrayList<>();

    // Xử lý đa luồng
    private ExecutorService boThucThi = Executors.newSingleThreadExecutor();
    private Handler xuLyChinh = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lam_them);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLamThem), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ view
        recyclerViewTinTuc = findViewById(R.id.recyclerViewTinTuc);
        thanhTaiDuLieu = findViewById(R.id.thanhTaiDuLieu);

        // Cấu hình RecyclerView
        recyclerViewTinTuc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TinTucAdapter(this, tatCaTin);
        recyclerViewTinTuc.setAdapter(adapter);

        // Tải dữ liệu RSS
        taiDuLieuRss();
    }

    // Tải dữ liệu RSS từ mạng (chạy trong thread riêng)
    private void taiDuLieuRss() {
        thanhTaiDuLieu.setVisibility(View.VISIBLE);

        boThucThi.execute(() -> {
            try {
                List<TinRss> danhSachTin = BoXuLyRss.phanTich(RSS_URL);

                xuLyChinh.post(() -> {
                    tatCaTin.clear();
                    tatCaTin.addAll(danhSachTin);
                    adapter.capNhatDanhSach(tatCaTin);
                    thanhTaiDuLieu.setVisibility(View.GONE);
                    Toast.makeText(LamThemActivity.this,
                            "Đã tải " + danhSachTin.size() + " tin tức", Toast.LENGTH_SHORT).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
                xuLyChinh.post(() -> {
                    thanhTaiDuLieu.setVisibility(View.GONE);
                    Toast.makeText(LamThemActivity.this,
                            "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng thread khi Activity bị hủy
        if (boThucThi != null && !boThucThi.isShutdown()) {
            boThucThi.shutdown();
        }
    }
}
