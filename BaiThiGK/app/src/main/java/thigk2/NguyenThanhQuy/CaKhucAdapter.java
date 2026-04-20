package thigk2.NguyenThanhQuy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Adapter tùy chỉnh cho danh sách ca khúc
public class CaKhucAdapter extends BaseAdapter {

    private Context nguCanh;
    private List<String> danhSachCaKhuc;

    // Hàm khởi tạo
    public CaKhucAdapter(Context nguCanh, List<String> danhSachCaKhuc) {
        this.nguCanh = nguCanh;
        this.danhSachCaKhuc = danhSachCaKhuc;
    }

    @Override
    public int getCount() {
        return danhSachCaKhuc.size();
    }

    @Override
    public Object getItem(int viTri) {
        return danhSachCaKhuc.get(viTri);
    }

    @Override
    public long getItemId(int viTri) {
        return viTri;
    }

    @Override
    public View getView(int viTri, View convertView, ViewGroup parent) {
        // Nạp giao diện cho mỗi dòng
        if (convertView == null) {
            convertView = LayoutInflater.from(nguCanh).inflate(R.layout.item_ca_khuc, parent, false);
        }

        // Ánh xạ các thành phần
        TextView txtSoThuTu = convertView.findViewById(R.id.txtSoThuTu);
        TextView txtTenCaKhuc = convertView.findViewById(R.id.txtTenCaKhuc);

        // Gán số thứ tự
        txtSoThuTu.setText(String.valueOf(viTri + 1));

        // Tạo hình tròn cho số thứ tự
        GradientDrawable hinhTron = new GradientDrawable();
        hinhTron.setShape(GradientDrawable.OVAL);
        hinhTron.setColor(Color.parseColor("#C62828"));
        txtSoThuTu.setBackground(hinhTron);

        // Gán tên ca khúc
        String tenCaKhuc = danhSachCaKhuc.get(viTri);
        txtTenCaKhuc.setText(tenCaKhuc);

        // Đánh dấu bài đặc biệt (bài mang tên sinh viên)
        if (tenCaKhuc.equals("Nguyễn Thành Quý")) {
            txtTenCaKhuc.setTextColor(Color.parseColor("#C62828"));
            txtTenCaKhuc.setTextSize(17);
            txtTenCaKhuc.setText("⭐ " + tenCaKhuc + " (Bài đặc biệt)");
            hinhTron.setColor(Color.parseColor("#FF6F00"));
        } else {
            txtTenCaKhuc.setTextColor(Color.parseColor("#333333"));
            txtTenCaKhuc.setTextSize(16);
        }

        return convertView;
    }
}
