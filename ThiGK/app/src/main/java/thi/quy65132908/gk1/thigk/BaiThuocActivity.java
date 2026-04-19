package thi.quy65132908.gk1.thigk;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BaiThuocActivity extends AppCompatActivity {

    // Khai báo các biến
    private RecyclerView rvDanhSachBaiThuoc;
    private BaiThuocAdapter adapter;
    private List<BaiThuoc> danhSachBaiThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_thuoc);

        // Xử lý thanh hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainBaiThuoc), (v, insets) -> {
            Insets thanhHeThong = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(thanhHeThong.left, thanhHeThong.top, thanhHeThong.right, thanhHeThong.bottom);
            return insets;
        });

        // Ánh xạ view
        rvDanhSachBaiThuoc = findViewById(R.id.rvDanhSachBaiThuoc);

        // Tạo dữ liệu bài thuốc
        danhSachBaiThuoc = taoDuLieuBaiThuoc();

        // Cấu hình RecyclerView
        rvDanhSachBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaiThuocAdapter(this, danhSachBaiThuoc);
        rvDanhSachBaiThuoc.setAdapter(adapter);
    }

    // Phương thức tạo dữ liệu mẫu cho danh sách bài thuốc
    private List<BaiThuoc> taoDuLieuBaiThuoc() {
        List<BaiThuoc> danhSach = new ArrayList<>();

        danhSach.add(new BaiThuoc(
                "Bài thuốc trị cảm cúm",
                "155 phút",
                "Bài thuốc dân gian giúp giảm các triệu chứng cảm cúm, sổ mũi, đau đầu.",
                "Gừng tươi, lá tía tô, hành trắng, đường phèn",
                "Sắc với 3 bát nước còn 1 bát, uống khi còn ấm, ngày 2 lần."
        ));

        danhSach.add(new BaiThuoc(
                "Bài thuốc bổ phổi",
                "120 phút",
                "Giúp bổ phổi, tăng cường hô hấp, hỗ trợ điều trị ho lâu ngày.",
                "Bách hợp, kỷ tử, đại táo, mật ong",
                "Hấp cách thủy 2 tiếng, ăn cả nước và cái, ngày 1 lần."
        ));

        danhSach.add(new BaiThuoc(
                "Bài thuốc giải nhiệt",
                "90 phút",
                "Thanh nhiệt, giải độc cơ thể, phù hợp cho mùa hè.",
                "Rau má, râu ngô, atiso, cam thảo",
                "Đun sôi tất cả nguyên liệu với 2 lít nước, để nguội uống trong ngày."
        ));

        danhSach.add(new BaiThuoc(
                "Bài thuốc an thần",
                "60 phút",
                "Giúp ngủ ngon, giảm căng thẳng, thư giãn tinh thần.",
                "Tâm sen, lá vông, nhãn nhục, táo nhân",
                "Sắc uống trước khi ngủ 30 phút, dùng liên tục 7 ngày."
        ));

        danhSach.add(new BaiThuoc(
                "Bài thuốc bổ máu",
                "180 phút",
                "Bổ máu, tăng cường sức khỏe, phù hợp cho người thiếu máu.",
                "Đương quy, thục địa, bạch thược, xuyên khung",
                "Sắc với 4 bát nước còn 1 bát, ngày uống 2 lần sau bữa ăn."
        ));

        return danhSach;
    }
}
