package thigk2.NguyenThanhQuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

// Fragment chức năng 1: Đổi tiền sang VNĐ
public class DoiTienFragment extends Fragment {

    // Khai báo các thành phần giao diện
    private EditText edtSoTien, edtTiGia;
    private Button btnDoiTien;
    private TextView txtKetQua;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Nạp giao diện fragment đổi tiền
        View giaoDien = inflater.inflate(R.layout.fragment_doi_tien, container, false);

        // Ánh xạ các thành phần giao diện
        edtSoTien = giaoDien.findViewById(R.id.edtSoTien);
        edtTiGia = giaoDien.findViewById(R.id.edtTiGia);
        btnDoiTien = giaoDien.findViewById(R.id.btnDoiTien);
        txtKetQua = giaoDien.findViewById(R.id.txtKetQua);

        // Xử lý sự kiện khi nhấn nút Quy Đổi
        btnDoiTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDoiTien();
            }
        });

        return giaoDien;
    }

    // Phương thức xử lý quy đổi tiền
    private void xuLyDoiTien() {
        // Lấy chuỗi nhập vào
        String chuoiSoTien = edtSoTien.getText().toString().trim();
        String chuoiTiGia = edtTiGia.getText().toString().trim();

        // Kiểm tra dữ liệu rỗng
        if (chuoiSoTien.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập số tiền!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (chuoiTiGia.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập tỉ giá!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Chuyển đổi sang số
            double soTien = Double.parseDouble(chuoiSoTien);
            double tiGia = Double.parseDouble(chuoiTiGia);

            // Tính kết quả quy đổi
            double ketQua = soTien * tiGia;

            // Định dạng số có dấu phân cách hàng nghìn
            DecimalFormat dinhDang = new DecimalFormat("#,###.##");
            String ketQuaDinhDang = dinhDang.format(ketQua);

            // Hiển thị kết quả
            txtKetQua.setText(dinhDang.format(soTien) + " x " + dinhDang.format(tiGia)
                    + "\n= " + ketQuaDinhDang + " VNĐ");

        } catch (NumberFormatException e) {
            // Xử lý lỗi khi nhập sai định dạng số
            Toast.makeText(getContext(), "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }
}
