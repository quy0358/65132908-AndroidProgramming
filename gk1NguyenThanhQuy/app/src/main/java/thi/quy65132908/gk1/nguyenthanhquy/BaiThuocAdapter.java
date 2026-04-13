package thi.quy65132908.gk1.nguyenthanhquy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.BaiThuocViewHolder> {

    private List<BaiThuoc> danhSachBaiThuoc;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BaiThuoc baiThuoc, int position);
    }

    public BaiThuocAdapter(List<BaiThuoc> danhSachBaiThuoc, OnItemClickListener listener) {
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
        holder.imgBaiThuoc.setImageResource(baiThuoc.getHinhAnh());
        holder.tvTenBaiThuoc.setText(baiThuoc.getTenBaiThuoc());
        holder.tvThoiGian.setText(baiThuoc.getThoiGian());
        holder.tvMoTa.setText(baiThuoc.getMoTa());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(baiThuoc, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachBaiThuoc.size();
    }

    public static class BaiThuocViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBaiThuoc;
        TextView tvTenBaiThuoc;
        TextView tvThoiGian;
        TextView tvMoTa;

        public BaiThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBaiThuoc = itemView.findViewById(R.id.imgBaiThuoc);
            tvTenBaiThuoc = itemView.findViewById(R.id.tvTenBaiThuoc);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvMoTa = itemView.findViewById(R.id.tvMoTaBaiThuoc);
        }
    }
}
