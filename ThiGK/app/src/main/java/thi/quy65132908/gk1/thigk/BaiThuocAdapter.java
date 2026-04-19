package thi.quy65132908.gk1.thigk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter cho RecyclerView hiển thị danh sách bài thuốc
public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.BaiThuocViewHolder> {

    private List<BaiThuoc> danhSachBaiThuoc;
    private Context nguCanh;

    // Constructor
    public BaiThuocAdapter(Context nguCanh, List<BaiThuoc> danhSachBaiThuoc) {
        this.nguCanh = nguCanh;
        this.danhSachBaiThuoc = danhSachBaiThuoc;
    }

    @NonNull
    @Override
    public BaiThuocViewHolder onCreateViewHolder(@NonNull ViewGroup nhomCha, int loaiView) {
        // Tạo view từ layout item_bai_thuoc
        View view = LayoutInflater.from(nhomCha.getContext())
                .inflate(R.layout.item_bai_thuoc, nhomCha, false);
        return new BaiThuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiThuocViewHolder holder, int viTri) {
        // Lấy bài thuốc tại vị trí hiện tại
        BaiThuoc baiThuoc = danhSachBaiThuoc.get(viTri);

        // Gán dữ liệu lên view
        holder.tvTenBaiThuoc.setText(baiThuoc.getTenBaiThuoc());
        holder.tvThoiGian.setText(baiThuoc.getThoiGian());

        // Bắt sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            // Thông báo mục được click
            Toast.makeText(nguCanh, "Đã chọn: " + baiThuoc.getTenBaiThuoc(), Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình chi tiết bài thuốc
            Intent intent = new Intent(nguCanh, ChiTietBaiActivity.class);
            intent.putExtra("tenBaiThuoc", baiThuoc.getTenBaiThuoc());
            intent.putExtra("thoiGian", baiThuoc.getThoiGian());
            intent.putExtra("moTa", baiThuoc.getMoTa());
            intent.putExtra("thanhPhan", baiThuoc.getThanhPhan());
            intent.putExtra("cachDung", baiThuoc.getCachDung());
            nguCanh.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return danhSachBaiThuoc != null ? danhSachBaiThuoc.size() : 0;
    }

    // Lớp ViewHolder giữ tham chiếu đến các view trong item
    static class BaiThuocViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBaiThuoc;
        TextView tvTenBaiThuoc, tvThoiGian;

        public BaiThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBaiThuoc = itemView.findViewById(R.id.imgBaiThuoc);
            tvTenBaiThuoc = itemView.findViewById(R.id.tvTenBaiThuoc);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
        }
    }
}
