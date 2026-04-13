package thi.quy65132908.gk1.nguyenthanhquy;

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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<RssItem> newsList;
    private Context context;

    public NewsAdapter(Context context, List<RssItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        RssItem item = newsList.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPubDate.setText(formatDate(item.getPubDate()));

        // Load ảnh bằng Glide
        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(item.getImageUrl())
                    .transform(new CenterCrop())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(holder.imgNews);
            holder.imgNews.setVisibility(View.VISIBLE);
        } else {
            holder.imgNews.setVisibility(View.GONE);
        }

        // Click vào tin → mở trình duyệt
        holder.itemView.setOnClickListener(v -> {
            if (item.getLink() != null && !item.getLink().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void updateList(List<RssItem> newList) {
        this.newsList = newList;
        notifyDataSetChanged();
    }

    // Format ngày cho ngắn gọn hơn
    private String formatDate(String pubDate) {
        if (pubDate == null) return "";
        try {
            String[] parts = pubDate.split(",");
            if (parts.length > 1) {
                String datePart = parts[1].trim();
                int plusIdx = datePart.lastIndexOf('+');
                int minusIdx = datePart.lastIndexOf('-');
                int tzIdx = Math.max(plusIdx, minusIdx);
                if (tzIdx > 0) {
                    datePart = datePart.substring(0, tzIdx).trim();
                }
                String[] timeParts = datePart.split(":");
                if (timeParts.length >= 2) {
                    int lastColon = datePart.lastIndexOf(':');
                    datePart = datePart.substring(0, lastColon);
                }
                return datePart;
            }
        } catch (Exception e) {
            // Nếu lỗi, trả về nguyên bản
        }
        return pubDate;
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView tvTitle, tvDescription, tvPubDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.imgNews);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPubDate = itemView.findViewById(R.id.tvPubDate);
        }
    }
}
