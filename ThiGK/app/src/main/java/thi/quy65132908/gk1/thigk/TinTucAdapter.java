package thi.quy65132908.gk1.thigk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;

// Adapter cho RecyclerView hiển thị danh sách tin tức RSS
public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.TinTucViewHolder> {

    private List<TinRss> danhSachTin;
    private Context nguCanh;

    // Constructor
    public TinTucAdapter(Context nguCanh, List<TinRss> danhSachTin) {
        this.nguCanh = nguCanh;
        this.danhSachTin = danhSachTin;
    }

    @NonNull
    @Override
    public TinTucViewHolder onCreateViewHolder(@NonNull ViewGroup nhomCha, int loaiView) {
        View view = LayoutInflater.from(nhomCha.getContext())
                .inflate(R.layout.item_tin_tuc, nhomCha, false);
        return new TinTucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinTucViewHolder holder, int viTri) {
        TinRss tin = danhSachTin.get(viTri);

        holder.tvTieuDe.setText(tin.getTieuDe());
        holder.tvMoTaTin.setText(tin.getMoTa());
        holder.tvNgayDang.setText(dinhDangNgay(tin.getNgayDang()));

        // Tải ảnh bằng Glide
        if (tin.getAnhUrl() != null && !tin.getAnhUrl().isEmpty()) {
            Glide.with(nguCanh)
                    .load(tin.getAnhUrl())
                    .transform(new CenterCrop())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(holder.anhTinTuc);
            holder.anhTinTuc.setVisibility(View.VISIBLE);
        } else {
            holder.anhTinTuc.setVisibility(View.GONE);
        }

        // Click vào tin → mở trình duyệt
        holder.itemView.setOnClickListener(v -> {
            if (tin.getDuongDan() != null && !tin.getDuongDan().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tin.getDuongDan()));
                nguCanh.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachTin != null ? danhSachTin.size() : 0;
    }

    // Cập nhật danh sách tin tức
    public void capNhatDanhSach(List<TinRss> danhSachMoi) {
        this.danhSachTin = danhSachMoi;
        notifyDataSetChanged();
    }

    // Định dạng ngày cho ngắn gọn hơn
    private String dinhDangNgay(String ngayDang) {
        if (ngayDang == null) return "";
        try {
            String[] cacPhan = ngayDang.split(",");
            if (cacPhan.length > 1) {
                String phanNgay = cacPhan[1].trim();
                int viTriCong = phanNgay.lastIndexOf('+');
                int viTriTru = phanNgay.lastIndexOf('-');
                int viTriMuiGio = Math.max(viTriCong, viTriTru);
                if (viTriMuiGio > 0) {
                    phanNgay = phanNgay.substring(0, viTriMuiGio).trim();
                }
                String[] cacPhanGio = phanNgay.split(":");
                if (cacPhanGio.length >= 2) {
                    int dauHaiChamCuoi = phanNgay.lastIndexOf(':');
                    phanNgay = phanNgay.substring(0, dauHaiChamCuoi);
                }
                return phanNgay;
            }
        } catch (Exception e) {
            // Nếu lỗi, trả về nguyên bản
        }
        return ngayDang;
    }

    // Lớp ViewHolder giữ tham chiếu đến các view
    static class TinTucViewHolder extends RecyclerView.ViewHolder {

        ImageView anhTinTuc;
        TextView tvTieuDe, tvMoTaTin, tvNgayDang;

        public TinTucViewHolder(@NonNull View itemView) {
            super(itemView);
            anhTinTuc = itemView.findViewById(R.id.anhTinTuc);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvMoTaTin = itemView.findViewById(R.id.tvMoTaTin);
            tvNgayDang = itemView.findViewById(R.id.tvNgayDang);
        }
    }
}
