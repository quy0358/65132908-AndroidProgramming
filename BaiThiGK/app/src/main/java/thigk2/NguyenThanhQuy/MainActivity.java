package thigk2.NguyenThanhQuy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Khai báo thanh điều hướng
    private BottomNavigationView thanhDieuHuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ thanh điều hướng
        thanhDieuHuong = findViewById(R.id.thanhDieuHuong);

        // Hiển thị Fragment đầu tiên khi mở app (Đổi tiền)
        if (savedInstanceState == null) {
            chuyenFragment(new DoiTienFragment());
        }

        // Xử lý sự kiện khi chọn tab trên thanh điều hướng
        thanhDieuHuong.setOnItemSelectedListener(item -> {
            Fragment fragmentDuocChon = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_doi_tien) {
                fragmentDuocChon = new DoiTienFragment();
            } else if (itemId == R.id.nav_ca_khuc) {
                fragmentDuocChon = new CaKhucFragment();
            } else if (itemId == R.id.nav_danh_nhan) {
                fragmentDuocChon = new DanhNhanFragment();
            } else if (itemId == R.id.nav_thong_tin) {
                fragmentDuocChon = new ThongTinFragment();
            }

            if (fragmentDuocChon != null) {
                chuyenFragment(fragmentDuocChon);
            }
            return true;
        });
    }

    // Phương thức chuyển đổi Fragment
    private void chuyenFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.khungChuaFragment, fragment)
                .commit();
    }
}