package thigk2.NguyenThanhQuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Fragment chức năng 4: Hiển thị thông tin cá nhân sinh viên
public class ThongTinFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Nạp giao diện fragment thông tin sinh viên
        // Dữ liệu đã được hard-code trực tiếp trong layout XML
        View giaoDien = inflater.inflate(R.layout.fragment_thong_tin, container, false);
        return giaoDien;
    }
}
