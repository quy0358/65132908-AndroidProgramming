package thi.quy65132908.gk1.thigk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Adapter tùy chỉnh cho ListView danh sách món ăn
public class MonAnAdapter extends BaseAdapter {

    private Context nguCanh;
    private List<MonAn> danhSachMonAn;

    // Constructor
    public MonAnAdapter(Context ngucCanh, List<MonAn> danhSachMonAn) {
        this.nguCanh = ngucCanh;
        this.danhSachMonAn = danhSachMonAn;
    }

    @Override
    public int getCount() {
        return danhSachMonAn != null ? danhSachMonAn.size() : 0;
    }

    @Override
    public Object getItem(int viTri) {
        return danhSachMonAn.get(viTri);
    }

    @Override
    public long getItemId(int viTri) {
        return viTri;
    }

    @Override
    public View getView(int viTri, View viewCu, ViewGroup nhomCha) {
        // Tái sử dụng view nếu có
        if (viewCu == null) {
            LayoutInflater inflater = LayoutInflater.from(nguCanh);
            viewCu = inflater.inflate(R.layout.item_mon_an, nhomCha, false);
        }

        // Lấy món ăn tại vị trí hiện tại
        MonAn monAn = danhSachMonAn.get(viTri);

        // Ánh xạ và gán dữ liệu
        TextView tvTenMonAn = viewCu.findViewById(R.id.tvTenMonAn);
        tvTenMonAn.setText(monAn.getTenMon());

        return viewCu;
    }
}
