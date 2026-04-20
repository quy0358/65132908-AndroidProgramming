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

// Fragment chức năng 3: Hiển thị danh mục danh nhân lịch sử Việt Nam
public class DanhNhanFragment extends Fragment {

    private ListView lvDanhSachDanhNhan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Nạp giao diện fragment danh nhân
        View giaoDien = inflater.inflate(R.layout.fragment_danh_nhan, container, false);

        // Ánh xạ ListView
        lvDanhSachDanhNhan = giaoDien.findViewById(R.id.lvDanhSachDanhNhan);

        // Tạo danh sách 5 danh nhân lịch sử
        List<DanhNhan> danhSachDanhNhan = taoDanhSachDanhNhan();

        // Tạo adapter và gán cho ListView
        DanhNhanAdapter adapter = new DanhNhanAdapter(getContext(), danhSachDanhNhan);
        lvDanhSachDanhNhan.setAdapter(adapter);

        return giaoDien;
    }

    // Phương thức tạo danh sách 5 danh nhân lịch sử (hard-code)
    private List<DanhNhan> taoDanhSachDanhNhan() {
        List<DanhNhan> danhSach = new ArrayList<>();

        // Danh nhân 1: Chủ tịch Hồ Chí Minh
        danhSach.add(new DanhNhan(
                R.drawable.anh_ho_chi_minh,
                "Hồ Chí Minh",
                "Nghệ An"
        ));

        // Danh nhân 2: Hưng Đạo Đại Vương Trần Quốc Tuấn
        danhSach.add(new DanhNhan(
                R.drawable.anh_tran_hung_dao,
                "Trần Hưng Đạo",
                "Nam Định"
        ));

        // Danh nhân 3: Nguyễn Trãi
        danhSach.add(new DanhNhan(
                R.drawable.anh_nguyen_trai,
                "Nguyễn Trãi",
                "Hải Dương"
        ));

        // Danh nhân 4: Lê Lợi
        danhSach.add(new DanhNhan(
                R.drawable.anh_le_loi,
                "Lê Lợi",
                "Thanh Hóa"
        ));

        // Danh nhân 5: Quang Trung - Nguyễn Huệ
        danhSach.add(new DanhNhan(
                R.drawable.anh_quang_trung,
                "Quang Trung - Nguyễn Huệ",
                "Bình Định"
        ));

        return danhSach;
    }
}
