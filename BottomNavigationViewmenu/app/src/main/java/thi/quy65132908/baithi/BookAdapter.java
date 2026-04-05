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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    ArrayList<Book> datas;

    public BookAdapter(Context context, ArrayList<Book> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void updateList(ArrayList<Book> newList) {
        this.datas = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = datas.get(position);
        holder.tvBookName.setText(book.getBookName());
        holder.tvBookDesc.setText(book.getDescription());
        holder.tvBookPrice.setText(String.format("$%.2f", book.getPrice()));
        holder.tvBookPages.setText(book.getPage() + " trang");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvBookName, tvBookDesc, tvBookPrice, tvBookPages;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookDesc = itemView.findViewById(R.id.tvBookDesc);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            tvBookPages = itemView.findViewById(R.id.tvBookPages);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            Book book = datas.get(pos);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("📚 " + book.getBookName());
            String message = "📄 Số trang: " + book.getPage() + "\n"
                    + "💰 Giá: $" + String.format("%.2f", book.getPrice()) + "\n"
                    + "📝 Mô tả: " + book.getDescription();
            builder.setMessage(message);
            builder.setPositiveButton("Đóng", null);
            builder.show();
        }
    }
}
