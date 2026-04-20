package thigk2.NguyenThanhQuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Fragment chức năng 4: Thông tin sinh viên
public class ThongTinFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Nạp giao diện fragment thông tin sinh viên
        return inflater.inflate(R.layout.fragment_thong_tin, container, false);
    }
}
