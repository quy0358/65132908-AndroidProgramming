package ntquy.ntu.danhsachcaccanhquan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CanhQuanAdapter extends RecyclerView.Adapter<CanhQuanAdapter.CanhQuanViewHolder> {

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
        return new CanhQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CanhQuanViewHolder holder, int position) {
        CanhQuan canhQuan = danhSachCanhQuan.get(position);
        holder.imgCanhQuan.setImageResource(canhQuan.getHinhAnh());
        holder.tvTenCanhQuan.setText(canhQuan.getTenCanhQuan());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(canhQuan, position);
            }
        });
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
