package ntquy.ntu.danhsachcaccanhquan;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private CanhQuanAdapter adapter;
    private List<CanhQuan> danhSachCanhQuan;

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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo danh sách cảnh quan
        danhSachCanhQuan = new ArrayList<>();
        danhSachCanhQuan.add(new CanhQuan("Flag Tower of Ha Noi", R.drawable.flag_tower_hanoi));
        danhSachCanhQuan.add(new CanhQuan("Eiffel Tower", R.drawable.eiffel_tower));
        danhSachCanhQuan.add(new CanhQuan("Great Wall of China", R.drawable.great_wall));
        danhSachCanhQuan.add(new CanhQuan("Taj Mahal", R.drawable.taj_mahal));
        danhSachCanhQuan.add(new CanhQuan("Statue of Liberty", R.drawable.statue_of_liberty));
        danhSachCanhQuan.add(new CanhQuan("Colosseum", R.drawable.colosseum));
        danhSachCanhQuan.add(new CanhQuan("Ha Long Bay", R.drawable.ha_long_bay));
        danhSachCanhQuan.add(new CanhQuan("Machu Picchu", R.drawable.machu_picchu));
        danhSachCanhQuan.add(new CanhQuan("Sydney Opera House", R.drawable.sydney_opera));
        danhSachCanhQuan.add(new CanhQuan("Angkor Wat", R.drawable.angkor_wat));

        // Thiết lập adapter với sự kiện click
        adapter = new CanhQuanAdapter(danhSachCanhQuan, (canhQuan, position) -> {
            Log.d(TAG, "Click detected: " + canhQuan.getTenCanhQuan());

            // Hiển thị Toast
            Toast.makeText(MainActivity.this,
                    "Bạn đã chọn: " + canhQuan.getTenCanhQuan(),
                    Toast.LENGTH_LONG).show();

            // Hiển thị AlertDialog (chắc chắn hiển thị)
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Thông báo")
                    .setMessage("Bạn đã chọn: " + canhQuan.getTenCanhQuan())
                    .setPositiveButton("OK", null)
                    .show();
        });

        recyclerView.setAdapter(adapter);
    }
}