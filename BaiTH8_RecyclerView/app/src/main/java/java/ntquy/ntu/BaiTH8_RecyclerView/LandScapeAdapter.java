package java.ntquy.ntu.BaiTH8_RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.LandScapeViewHolder> implements Filterable {

    private Context context;
    private List<LandScape> landScapeList;
    private List<LandScape> landScapeListFull;

    public LandScapeAdapter(Context context, List<LandScape> landScapeList) {
        this.context = context;
        this.landScapeList = landScapeList;
        this.landScapeListFull = new ArrayList<>(landScapeList);
    }

    @NonNull
    @Override
    public LandScapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_landscape, parent, false);
        return new LandScapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandScapeViewHolder holder, int position) {
        LandScape landScape = landScapeList.get(position);
        holder.imgLandscape.setImageResource(landScape.getImage());
        holder.tvName.setText(landScape.getName());

        // Click listener -> open DetailActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("landscape", landScape);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return landScapeList.size();
    }

    // Remove item at position (for swipe-to-delete)
    public LandScape removeItem(int position) {
        LandScape removed = landScapeList.remove(position);
        notifyItemRemoved(position);
        return removed;
    }

    // Restore item at position (for undo)
    public void restoreItem(LandScape item, int position) {
        landScapeList.add(position, item);
        notifyItemInserted(position);
    }

    // Filterable implementation for search
    @Override
    public Filter getFilter() {
        return landScapeFilter;
    }

    private final Filter landScapeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<LandScape> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(landScapeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (LandScape item : landScapeListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            landScapeList.clear();
            landScapeList.addAll((List<LandScape>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class LandScapeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLandscape;
        TextView tvName;

        public LandScapeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLandscape = itemView.findViewById(R.id.imgLandscape);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
