package thi.quy65132908.gk1.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private TextInputEditText edtMaSV, edtHoTen, edtDiem;
    private MaterialButton btnThem, btnSua, btnXoa;
    private ListView lvSinhVien;
    private TextView tvTrangThai;

    // Firebase
    private DatabaseReference databaseRef;

    // Dữ liệu
    private ArrayList<SinhVien> dsSinhVien;
    private ArrayAdapter<SinhVien> adapter;
    private String selectedKey = ""; // Key của sinh viên được chọn

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
        btnThem.setOnClickListener(v -> themSinhVien());

        // Xử lý sự kiện nút Sửa
        btnSua.setOnClickListener(v -> suaSinhVien());

        // Xử lý sự kiện nút Xóa
        btnXoa.setOnClickListener(v -> xoaSinhVien());

        // Khi chọn 1 sinh viên trong danh sách -> hiển thị lên form
        lvSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien sv = dsSinhVien.get(position);
            edtMaSV.setText(sv.getMaSV());
            edtHoTen.setText(sv.getHoTen());
            edtDiem.setText(String.valueOf(sv.getDiemTB()));
            selectedKey = sv.getMaSV(); // Dùng mã SV làm key
            Toast.makeText(this, "Đã chọn: " + sv.getHoTen(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Ánh xạ các view từ layout
     */
    private void anhXaView() {
        edtMaSV = findViewById(R.id.edtMaSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiem = findViewById(R.id.edtDiem);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        tvTrangThai = findViewById(R.id.tvTrangThai);

        // Khởi tạo danh sách
        dsSinhVien = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsSinhVien);
        lvSinhVien.setAdapter(adapter);
    }

    /**
     * Khởi tạo kết nối Firebase Realtime Database
     */
    private void khoiTaoFirebase() {
        // Lấy instance của Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Tạo reference đến node "SinhVien" trên database
        databaseRef = database.getReference("SinhVien");

        tvTrangThai.setText("✅ Đã kết nối Firebase thành công!");
        tvTrangThai.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
    }

    /**
     * Đọc dữ liệu từ Firebase (lắng nghe realtime)
     * Mỗi khi dữ liệu thay đổi trên Firebase, danh sách sẽ tự động cập nhật
     */
    private void docDuLieu() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Xóa danh sách cũ
                dsSinhVien.clear();

                // Duyệt qua từng child trong node "SinhVien"
                for (DataSnapshot data : snapshot.getChildren()) {
                    SinhVien sv = data.getValue(SinhVien.class);
                    if (sv != null) {
                        dsSinhVien.add(sv);
                    }
                }

                // Cập nhật ListView
                adapter.notifyDataSetChanged();
                tvTrangThai.setText("✅ Đã tải " + dsSinhVien.size() + " sinh viên từ Firebase");
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
     * THÊM sinh viên mới vào Firebase
     */
    private void themSinhVien() {
        String maSV = edtMaSV.getText().toString().trim();
        String hoTen = edtHoTen.getText().toString().trim();
        String diemStr = edtDiem.getText().toString().trim();

        // Kiểm tra dữ liệu
        if (maSV.isEmpty() || hoTen.isEmpty() || diemStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        double diem = Double.parseDouble(diemStr);

        // Tạo đối tượng SinhVien
        SinhVien sv = new SinhVien(maSV, hoTen, diem);

        // Đẩy dữ liệu lên Firebase (dùng maSV làm key)
        databaseRef.child(maSV).setValue(sv)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "✅ Thêm thành công!", Toast.LENGTH_SHORT).show();
                    xoaForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * SỬA thông tin sinh viên trên Firebase
     */
    private void suaSinhVien() {
        if (selectedKey.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn sinh viên cần sửa!", Toast.LENGTH_SHORT).show();
            return;
        }

        String maSV = edtMaSV.getText().toString().trim();
        String hoTen = edtHoTen.getText().toString().trim();
        String diemStr = edtDiem.getText().toString().trim();

        if (maSV.isEmpty() || hoTen.isEmpty() || diemStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        double diem = Double.parseDouble(diemStr);
        SinhVien sv = new SinhVien(maSV, hoTen, diem);

        // Cập nhật dữ liệu trên Firebase (theo key đã chọn)
        databaseRef.child(selectedKey).setValue(sv)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "✅ Sửa thành công!", Toast.LENGTH_SHORT).show();
                    xoaForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * XÓA sinh viên khỏi Firebase
     */
    private void xoaSinhVien() {
        if (selectedKey.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn sinh viên cần xóa!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Xóa node con theo key
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
        edtMaSV.setText("");
        edtHoTen.setText("");
        edtDiem.setText("");
        selectedKey = "";
    }
}