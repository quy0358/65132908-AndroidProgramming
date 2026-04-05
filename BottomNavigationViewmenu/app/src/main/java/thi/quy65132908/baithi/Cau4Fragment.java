package thi.quy65132908.baithi;

import static android.content.Context.MODE_PRIVATE;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cau4Fragment extends Fragment {

    ArrayList<Book> dsSach;
    ArrayList<Book> dsSachFiltered;
    BookAdapter bookAdapter;
    RecyclerView rvBooks;
    EditText editSearch;
    TextView tvBookCount;

    public Cau4Fragment() {
    }

    public static Cau4Fragment newInstance(String param1, String param2) {
        Cau4Fragment fragment = new Cau4Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // ===== Bước 0. Tạo file cơ sở dữ liệu =====
        SQLiteDatabase dbCreate = getActivity().openOrCreateDatabase("QLSach.db",
                MODE_PRIVATE,
                null
        );

        // B1. Tạo bảng nếu chưa có
        String sqlTaoBang = "CREATE TABLE IF NOT EXISTS Books( BookID integer PRIMARY KEY, " +
                "BookName text, " +
                "Page integer, " +
                "Price Float, " +
                "Description text);";
        dbCreate.execSQL(sqlTaoBang);

        // Kiểm tra xem bảng đã có dữ liệu chưa
        Cursor checkCursor = dbCreate.rawQuery("SELECT COUNT(*) FROM Books", null);
        checkCursor.moveToFirst();
        int count = checkCursor.getInt(0);
        checkCursor.close();

        // Chỉ thêm dữ liệu nếu bảng rỗng
        if (count == 0) {
            String sqlThem1 = "INSERT INTO Books VALUES(1, 'Java', 100, 9.99, 'Sách về Java cơ bản');";
            String sqlThem2 = "INSERT INTO Books VALUES(2, 'Android', 320, 19.00, 'Android cơ bản và nâng cao');";
            String sqlThem3 = "INSERT INTO Books VALUES(3, 'Học làm giàu', 120, 0.99, 'Sách đọc cho vui');";
            String sqlThem4 = "INSERT INTO Books VALUES(4, 'Từ điển Anh-Việt', 1000, 29.50, 'Từ điển 100.000 từ');";
            String sqlThem5 = "INSERT INTO Books VALUES(5, 'CNXH', 1, 1, 'Chuyện cổ tích');";
            dbCreate.execSQL(sqlThem1);
            dbCreate.execSQL(sqlThem2);
            dbCreate.execSQL(sqlThem3);
            dbCreate.execSQL(sqlThem4);
            dbCreate.execSQL(sqlThem5);
        }
        dbCreate.close();

        // ===== B2. Đọc dữ liệu từ CSDL =====
        SQLiteDatabase db = getActivity().openOrCreateDatabase("QLSach.db",
                MODE_PRIVATE,
                null
        );
        String sqlSelect = "SELECT * FROM Books;";
        Cursor cs = db.rawQuery(sqlSelect, null);

        dsSach = new ArrayList<Book>();
        while (cs.moveToNext()) {
            int idSach = cs.getInt(0);
            String tenSach = cs.getString(1);
            int soTrang = cs.getInt(2);
            float gia = cs.getFloat(3);
            String mota = cs.getString(4);
            Book b = new Book(idSach, tenSach, soTrang, gia, mota);
            dsSach.add(b);
        }
        cs.close();
        db.close();

        // Copy to filtered list
        dsSachFiltered = new ArrayList<Book>(dsSach);

        // ===== B3. Hiển thị lên RecyclerView =====
        View viewCau4 = inflater.inflate(R.layout.fragment_cau4, container, false);

        rvBooks = viewCau4.findViewById(R.id.rvBooks);
        editSearch = viewCau4.findViewById(R.id.editSearch);
        tvBookCount = viewCau4.findViewById(R.id.tvBookCount);

        // Setup RecyclerView
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), dsSachFiltered);
        rvBooks.setAdapter(bookAdapter);

        // Update count
        updateBookCount();

        // ===== TÍNH NĂNG TÌM KIẾM =====
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return viewCau4;
    }

    private void filterBooks(String query) {
        dsSachFiltered.clear();
        if (query.isEmpty()) {
            dsSachFiltered.addAll(dsSach);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Book book : dsSach) {
                if (book.getBookName().toLowerCase().contains(lowerQuery) ||
                        book.getDescription().toLowerCase().contains(lowerQuery)) {
                    dsSachFiltered.add(book);
                }
            }
        }
        bookAdapter.notifyDataSetChanged();
        updateBookCount();
    }

    private void updateBookCount() {
        if (tvBookCount != null) {
            tvBookCount.setText(String.format(getString(R.string.book_count), dsSachFiltered.size()));
        }
    }
}