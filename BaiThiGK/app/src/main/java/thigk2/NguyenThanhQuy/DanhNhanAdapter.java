package thigk2.NguyenThanhQuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Adapter tùy chỉnh cho danh sách danh nhân lịch sử
public class DanhNhanAdapter extends BaseAdapter {

    private Context nguCanh;
    private List<DanhNhan> danhSachDanhNhan;

    // Hàm khởi tạo
    public DanhNhanAdapter(Context nguCanh, List<DanhNhan> danhSachDanhNhan) {
        this.nguCanh = nguCanh;
        this.danhSachDanhNhan = danhSachDanhNhan;
    }

    @Override
    public int getCount() {
        return danhSachDanhNhan.size();
    }

    @Override
    public Object getItem(int viTri) {
        return danhSachDanhNhan.get(viTri);
    }

    @Override
    public long getItemId(int viTri) {
        return viTri;
    }

    @Override
    public View getView(int viTri, View convertView, ViewGroup parent) {
        // Nạp giao diện cho mỗi dòng
        if (convertView == null) {
            convertView = LayoutInflater.from(nguCanh).inflate(R.layout.item_danh_nhan, parent, false);
        }

        // Lấy đối tượng danh nhân tại vị trí hiện tại
        DanhNhan danhNhan = danhSachDanhNhan.get(viTri);

        // Ánh xạ các thành phần giao diện
        ImageView imgDanhNhan = convertView.findViewById(R.id.imgDanhNhan);
        TextView txtHoTen = convertView.findViewById(R.id.txtHoTenDanhNhan);
        TextView txtQueQuan = convertView.findViewById(R.id.txtQueQuanDanhNhan);

        // Gán dữ liệu lên giao diện
        imgDanhNhan.setImageResource(danhNhan.getHinhAnh());
        txtHoTen.setText(danhNhan.getHoTen());
        txtQueQuan.setText("Quê quán: " + danhNhan.getQueQuan());

        return convertView;
    }
}
