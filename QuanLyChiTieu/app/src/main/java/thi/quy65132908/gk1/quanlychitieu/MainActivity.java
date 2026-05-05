package thi.quy65132908.gk1.quanlychitieu;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Khai báo các view
    private TextInputEditText edtTenChiTieu, edtSoTien;
    private Spinner spinnerDanhMuc;
    private MaterialButton btnThem, btnSua, btnXoa;
    private ListView lvChiTieu;
    private TextView tvTrangThai, tvTongChiTieu;

    // Firebase
    private DatabaseReference databaseRef;

    // Dữ liệu
    private ArrayList<ChiTieu> dsChiTieu;
    private ArrayAdapter<ChiTieu> adapter;
    private String selectedKey = ""; // Key của chi tiêu được chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        anhXaView();

        // Khởi tạo Firebase Realtime Database
        khoiTaoFirebase();

        // Đọc dữ liệu từ Firebase (realtime)
        docDuLieu();

        // Xử lý sự kiện nút Thêm
        btnThem.setOnClickListener(v -> themChiTieu());

        // Xử lý sự kiện nút Sửa
        btnSua.setOnClickListener(v -> suaChiTieu());

        // Xử lý sự kiện nút Xóa
        btnXoa.setOnClickListener(v -> xoaChiTieu());

        // Khi chọn 1 chi tiêu trong danh sách -> hiển thị lên form
        lvChiTieu.setOnItemClickListener((parent, view, position, id) -> {
            ChiTieu ct = dsChiTieu.get(position);
            edtTenChiTieu.setText(ct.getTenChiTieu());
            edtSoTien.setText(String.valueOf(ct.getSoTien()));
            selectedKey = ct.getId();

            // Chọn đúng danh mục trong Spinner
            ArrayAdapter<CharSequence> spinnerAdapter = (ArrayAdapter<CharSequence>) spinnerDanhMuc.getAdapter();
            for (int i = 0; i < spinnerAdapter.getCount(); i++) {
                if (spinnerAdapter.getItem(i).toString().equals(ct.getDanhMuc())) {
                    spinnerDanhMuc.setSelection(i);
                    break;
                }
            }

            Toast.makeText(this, "Đã chọn: " + ct.getTenChiTieu(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Ánh xạ các view từ layout
     */
    private void anhXaView() {
        edtTenChiTieu = findViewById(R.id.edtTenChiTieu);
        edtSoTien = findViewById(R.id.edtSoTien);
        spinnerDanhMuc = findViewById(R.id.spinnerDanhMuc);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        lvChiTieu = findViewById(R.id.lvChiTieu);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        tvTongChiTieu = findViewById(R.id.tvTongChiTieu);

        // Khởi tạo danh sách
        dsChiTieu = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsChiTieu);
        lvChiTieu.setAdapter(adapter);
    }

    /**
     * Khởi tạo kết nối Firebase Realtime Database
     */
    private void khoiTaoFirebase() {
        // Lấy instance của Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Tạo reference đến node "ChiTieu" trên database
        databaseRef = database.getReference("ChiTieu");

        tvTrangThai.setText("✅ Đã kết nối Firebase!");
        tvTrangThai.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
    }

    /**
     * Đọc dữ liệu từ Firebase (lắng nghe realtime)
     */
    private void docDuLieu() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsChiTieu.clear();

                double tongTien = 0;

                for (DataSnapshot data : snapshot.getChildren()) {
                    ChiTieu ct = data.getValue(ChiTieu.class);
                    if (ct != null) {
                        dsChiTieu.add(ct);
                        tongTien += ct.getSoTien();
                    }
                }

                adapter.notifyDataSetChanged();

                // Cập nhật tổng chi tiêu
                tvTongChiTieu.setText(String.format("%,.0f đ", tongTien));
                tvTrangThai.setText("✅ Có " + dsChiTieu.size() + " khoản chi tiêu");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tvTrangThai.setText("❌ Lỗi: " + error.getMessage());
                tvTrangThai.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                Toast.makeText(MainActivity.this,
                        "Lỗi đọc dữ liệu: " + error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * THÊM chi tiêu mới vào Firebase
     */
    private void themChiTieu() {
        String tenChiTieu = edtTenChiTieu.getText().toString().trim();
        String soTienStr = edtSoTien.getText().toString().trim();
        String danhMuc = spinnerDanhMuc.getSelectedItem().toString();

        if (tenChiTieu.isEmpty() || soTienStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và số tiền!", Toast.LENGTH_SHORT).show();
            return;
        }

        double soTien = Double.parseDouble(soTienStr);

        // Tạo key tự động từ Firebase
        String id = databaseRef.push().getKey();

        ChiTieu ct = new ChiTieu(id, tenChiTieu, soTien, danhMuc);

        databaseRef.child(id).setValue(ct)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "✅ Thêm thành công!", Toast.LENGTH_SHORT).show();
                    xoaForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * SỬA chi tiêu trên Firebase
     */
    private void suaChiTieu() {
        if (selectedKey.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn khoản chi tiêu cần sửa!", Toast.LENGTH_SHORT).show();
            return;
        }

        String tenChiTieu = edtTenChiTieu.getText().toString().trim();
        String soTienStr = edtSoTien.getText().toString().trim();
        String danhMuc = spinnerDanhMuc.getSelectedItem().toString();

        if (tenChiTieu.isEmpty() || soTienStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và số tiền!", Toast.LENGTH_SHORT).show();
            return;
        }

        double soTien = Double.parseDouble(soTienStr);
        ChiTieu ct = new ChiTieu(selectedKey, tenChiTieu, soTien, danhMuc);

        databaseRef.child(selectedKey).setValue(ct)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "✅ Sửa thành công!", Toast.LENGTH_SHORT).show();
                    xoaForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * XÓA chi tiêu khỏi Firebase
     */
    private void xoaChiTieu() {
        if (selectedKey.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn khoản chi tiêu cần xóa!", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseRef.child(selectedKey).removeValue()
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "✅ Xóa thành công!", Toast.LENGTH_SHORT).show();
                    xoaForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * Xóa dữ liệu trên form nhập liệu
     */
    private void xoaForm() {
        edtTenChiTieu.setText("");
        edtSoTien.setText("");
        spinnerDanhMuc.setSelection(0);
        selectedKey = "";
    }
}