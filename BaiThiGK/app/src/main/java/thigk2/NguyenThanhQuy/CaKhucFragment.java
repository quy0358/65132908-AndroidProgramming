package thigk2.NguyenThanhQuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

// Fragment chức năng 2: Hiển thị danh mục ca khúc cách mạng Việt Nam
public class CaKhucFragment extends Fragment {

    private ListView lvDanhSachCaKhuc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Nạp giao diện fragment ca khúc
        View giaoDien = inflater.inflate(R.layout.fragment_ca_khuc, container, false);

        // Ánh xạ ListView
        lvDanhSachCaKhuc = giaoDien.findViewById(R.id.lvDanhSachCaKhuc);

        // Tạo danh sách 10 ca khúc cách mạng
        List<String> danhSachCaKhuc = taoDanhSachCaKhuc();

        // Tạo adapter và gán cho ListView
        CaKhucAdapter adapter = new CaKhucAdapter(getContext(), danhSachCaKhuc);
        lvDanhSachCaKhuc.setAdapter(adapter);

        return giaoDien;
    }

    // Phương thức tạo danh sách 10 ca khúc cách mạng (hard-code)
    private List<String> taoDanhSachCaKhuc() {
        List<String> danhSach = new ArrayList<>();
        danhSach.add("Tiến Quân Ca");
        danhSach.add("Giải Phóng Miền Nam");
        danhSach.add("Như Có Bác Hồ Trong Ngày Vui Đại Thắng");
        danhSach.add("Bác Đang Cùng Chúng Cháu Hành Quân");
        danhSach.add("Nguyễn Thanh Quý");  // Bài đặc biệt - Họ và Tên sinh viên
        danhSach.add("Hò Kéo Pháo");
        danhSach.add("Trường Sơn Đông Trường Sơn Tây");
        danhSach.add("Cô Gái Mở Đường");
        danhSach.add("Đoàn Vệ Quốc Quân");
        danhSach.add("Lên Đàng");
        return danhSach;
    }
}
