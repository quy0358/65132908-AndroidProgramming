package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.BaiThuocViewHolder> {

    private List<BaiThuoc> danhSachBaiThuoc;
    private Context context;
    private OnItemClickListener listener;

    // Interface xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(BaiThuoc baiThuoc);
    }

    public BaiThuocAdapter(Context context, List<BaiThuoc> danhSachBaiThuoc, OnItemClickListener listener) {
        this.context = context;
        this.danhSachBaiThuoc = danhSachBaiThuoc;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaiThuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bai_thuoc, parent, false);
        return new BaiThuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiThuocViewHolder holder, int position) {
        BaiThuoc baiThuoc = danhSachBaiThuoc.get(position);
        holder.tvTenBaiThuoc.setText(baiThuoc.getTen());
        holder.tvThoiGianBaiThuoc.setText("Thời gian: " + baiThuoc.getThoiGian());

        // Bắt sự kiện click vào mục
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(baiThuoc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachBaiThuoc != null ? danhSachBaiThuoc.size() : 0;
    }

    static class BaiThuocViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenBaiThuoc, tvThoiGianBaiThuoc;

        public BaiThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenBaiThuoc = itemView.findViewById(R.id.tvTenBaiThuoc);
            tvThoiGianBaiThuoc = itemView.findViewById(R.id.tvThoiGianBaiThuoc);
        }
    }
}
