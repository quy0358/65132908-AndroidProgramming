package thi.quy65132908.gk1.nguyenthanhquy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {

    private List<MonAn> danhSachMonAn;
    private Context context;
    private OnItemClickListener listener;

    // Interface xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(MonAn monAn);
    }

    public MonAnAdapter(Context context, List<MonAn> danhSachMonAn, OnItemClickListener listener) {
        this.context = context;
        this.danhSachMonAn = danhSachMonAn;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mon_an, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monAn = danhSachMonAn.get(position);
        holder.tvTenMonAn.setText(monAn.getTen());
        holder.tvMoTaMonAn.setText(monAn.getMoTa());

        // Bắt sự kiện click vào mục
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(monAn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachMonAn != null ? danhSachMonAn.size() : 0;
    }

    static class MonAnViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenMonAn, tvMoTaMonAn;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenMonAn = itemView.findViewById(R.id.tvTenMonAn);
            tvMoTaMonAn = itemView.findViewById(R.id.tvMoTaMonAn);
        }
    }
}
