package ntquy.ntu.danhsachcaccanhquan;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CanhQuanAdapter extends RecyclerView.Adapter<CanhQuanAdapter.CanhQuanViewHolder> {

    private static final String TAG = "CanhQuanAdapter";
    private List<CanhQuan> danhSachCanhQuan;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CanhQuan canhQuan, int position);
    }

    public CanhQuanAdapter(List<CanhQuan> danhSachCanhQuan, OnItemClickListener listener) {
        this.danhSachCanhQuan = danhSachCanhQuan;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CanhQuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_canh_quan, parent, false);

        CanhQuanViewHolder holder = new CanhQuanViewHolder(view);

        // Gắn click listener ngay khi tạo ViewHolder
        view.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                CanhQuan canhQuan = danhSachCanhQuan.get(position);
                Log.d(TAG, "Item clicked: " + canhQuan.getTenCanhQuan() + " at position " + position);
                listener.onItemClick(canhQuan, position);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CanhQuanViewHolder holder, int position) {
        CanhQuan canhQuan = danhSachCanhQuan.get(position);
        holder.imgCanhQuan.setImageResource(canhQuan.getHinhAnh());
        holder.tvTenCanhQuan.setText(canhQuan.getTenCanhQuan());
    }

    @Override
    public int getItemCount() {
        return danhSachCanhQuan.size();
    }

    public static class CanhQuanViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCanhQuan;
        TextView tvTenCanhQuan;

        public CanhQuanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCanhQuan = itemView.findViewById(R.id.imgCanhQuan);
            tvTenCanhQuan = itemView.findViewById(R.id.tvTenCanhQuan);
        }
    }
}

