package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BaiThuocActivity extends AppCompatActivity {

    RecyclerView rvBaiThuoc;
    List<BaiThuoc> danhSachBaiThuoc;
    BaiThuocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_thuoc);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        rvBaiThuoc = findViewById(R.id.rvBaiThuoc);

        // Khởi tạo danh sách bài thuốc
        danhSachBaiThuoc = taoDanhSachBaiThuoc();

        // Cấu hình RecyclerView
        rvBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaiThuocAdapter(danhSachBaiThuoc, (baiThuoc, position) -> {
            // Chuyển sang ChiTietBaiThuocActivity
            Intent intent = new Intent(BaiThuocActivity.this, ChiTietBaiThuocActivity.class);
            intent.putExtra("tenBaiThuoc", baiThuoc.getTenBaiThuoc());
            intent.putExtra("thoiGian", baiThuoc.getThoiGian());
            intent.putExtra("moTa", baiThuoc.getMoTa());
            intent.putExtra("huongDan", baiThuoc.getHuongDan());
            intent.putExtra("hinhAnh", baiThuoc.getHinhAnh());
            startActivity(intent);
        });
        rvBaiThuoc.setAdapter(adapter);
    }

    private List<BaiThuoc> taoDanhSachBaiThuoc() {
        List<BaiThuoc> danhSach = new ArrayList<>();

        danhSach.add(new BaiThuoc(
                "Trà gừng mật ong",
                "15 phút",
                "Giúp giảm ho, đau họng và tăng cường sức đề kháng.",
                "1. Gừng tươi rửa sạch, thái lát mỏng.\n2. Đun sôi 300ml nước, cho gừng vào đun 10 phút.\n3. Để nguội bớt, thêm 1 thìa mật ong.\n4. Uống ấm, ngày 2-3 lần.",
                R.drawable.ic_medicine_placeholder
        ));

        danhSach.add(new BaiThuoc(
                "Nước chanh sả",
                "20 phút",
                "Thanh nhiệt, giải độc, hỗ trợ tiêu hóa và giảm mệt mỏi.",
                "1. Sả 3 cây, đập dập, cắt khúc.\n2. Đun sôi 500ml nước với sả trong 15 phút.\n3. Để nguội, vắt thêm nửa quả chanh.\n4. Có thể thêm đường phèn. Uống ngày 2 lần.",
                R.drawable.ic_medicine_placeholder
        ));

        danhSach.add(new BaiThuoc(
                "Cháo tía tô",
                "30 phút",
                "Trị cảm lạnh, giải cảm, ra mồ hôi và giảm sốt.",
                "1. Gạo 100g vo sạch, nấu cháo loãng.\n2. Tía tô 10 lá rửa sạch, thái nhỏ.\n3. Khi cháo chín, cho tía tô vào khuấy đều.\n4. Ăn nóng, đắp chăn để ra mồ hôi.",
                R.drawable.ic_medicine_placeholder
        ));

        danhSach.add(new BaiThuoc(
                "Nước rau má",
                "10 phút",
                "Giải nhiệt, mát gan, đẹp da và thanh lọc cơ thể.",
                "1. Rau má 200g rửa sạch, ngâm nước muối.\n2. Xay nhuyễn với 300ml nước lọc.\n3. Lọc qua rây lấy nước.\n4. Thêm đường hoặc mật ong. Uống mát ngày 1 lần.",
                R.drawable.ic_medicine_placeholder
        ));

        danhSach.add(new BaiThuoc(
                "Trà hoa cúc",
                "155 phút",
                "An thần, giảm stress, hỗ trợ giấc ngủ ngon.",
                "1. Hoa cúc khô 5g cho vào ấm.\n2. Đổ 250ml nước sôi, đậy nắp ủ 5 phút.\n3. Có thể thêm mật ong hoặc kỷ tử.\n4. Uống trước khi ngủ 30 phút.",
                R.drawable.ic_medicine_placeholder
        ));

        return danhSach;
    }
}
