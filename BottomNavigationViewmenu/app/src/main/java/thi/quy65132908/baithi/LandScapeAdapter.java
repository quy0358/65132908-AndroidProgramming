package thi.quy65132908.baithi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.LandScapeViewHolder> {

    Context context;
    ArrayList<LandScape> datas;

    public LandScapeAdapter(Context _context, ArrayList<LandScape> _datas) {
        this.context = _context;
        this.datas = _datas;
    }

    @NonNull
    @Override
    public LandScapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewItem = layoutInflater.inflate(R.layout.landscape_item, parent, false);
        LandScapeViewHolder viewHolder = new LandScapeViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LandScapeViewHolder holder, int position) {
        LandScape landScape = datas.get(position);

        // Set name
        holder.landscapeCaption.setText(landScape.getLandscapeName());

        // Set description
        if (holder.landscapeDescription != null) {
            String desc = landScape.getDescription();
            if (desc != null && !desc.isEmpty()) {
                holder.landscapeDescription.setText(desc);
                holder.landscapeDescription.setVisibility(View.VISIBLE);
            } else {
                holder.landscapeDescription.setVisibility(View.GONE);
            }
        }

        // Set image - support both mipmap and drawable
        String packageName = holder.itemView.getContext().getPackageName();
        String nameFile = landScape.getLandscapeImage();
        String resType = landScape.getResourceType();
        int imageID = holder.itemView.getResources().getIdentifier(nameFile, resType, packageName);
        if (imageID != 0) {
            holder.landscapeImage.setImageResource(imageID);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    final class LandScapeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView landscapeCaption;
        TextView landscapeDescription;
        ImageView landscapeImage;

        public LandScapeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            landscapeImage = itemView.findViewById(R.id.ivLandScape);
            landscapeCaption = itemView.findViewById(R.id.tvCaption);
            landscapeDescription = itemView.findViewById(R.id.tvDescription);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (clickedPosition == RecyclerView.NO_POSITION) return;

            LandScape landScape = datas.get(clickedPosition);

            // Show AlertDialog with details instead of just Toast
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("🏞️ " + landScape.getLandscapeName());

            String message = landScape.getDescription();
            if (message == null || message.isEmpty()) {
                message = "Địa danh nổi tiếng thế giới.";
            }
            builder.setMessage(message);
            builder.setPositiveButton("Đóng", null);
            builder.show();
        }
    }
}
