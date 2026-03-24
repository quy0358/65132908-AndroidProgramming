package ntquy.ntu.appmonan;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMonAn;
    ArrayList<MonAn> danhSachMonAn;
    MonAnAdapter adapter;

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

        lvMonAn = findViewById(R.id.lvMonAn);

        // Khởi tạo danh sách món ăn
        danhSachMonAn = new ArrayList<>();
        danhSachMonAn.add(new MonAn(R.drawable.com_tam_suon,
                "Cơm tấm sườn",
                "25,000 đ",
                "Cơm tấm sườn siu ngon có cơm và miếng sườn."));

        danhSachMonAn.add(new MonAn(R.drawable.com_tam_suon_trung,
                "Cơm tấm sườn trứng",
                "27,000 đ",
                "Cơm tấm có cơm, miếng sườn và có thêm miếng trứng."));

        danhSachMonAn.add(new MonAn(R.drawable.com_ga_xoi_mo,
                "Cơm gà xối mỡ",
                "30,000 đ",
                "Đĩa cơm gà bày ra đĩa trông bắt mắt với phần cơm vừa đủ ăn lưng bụng, thịt gà trộn bày lên trên, trang ..."));

        danhSachMonAn.add(new MonAn(R.drawable.com_tam_suon_bi_cha,
                "Cơm tấm sườn bì chả",
                "32,000 đ",
                "Cơm tấm có cơm, miếng sườn, cộng thêm bì và miếng chả siu thơm ngon."));

        danhSachMonAn.add(new MonAn(R.drawable.com_tam_dac_biet,
                "Cơm tấm đặc biệt",
                "35,000 đ",
                "Cơm tấm đặc biệt có cơm và đầy đủ topping trứng, bì, chả siu thơm ngon."));

        // Gán adapter cho ListView
        adapter = new MonAnAdapter(this, danhSachMonAn);
        lvMonAn.setAdapter(adapter);

        // Xử lý sự kiện click vào món ăn
        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                MonAn monAn = danhSachMonAn.get(position);
                Toast.makeText(MainActivity.this, "Bạn chọn: " + monAn.getTenMon() + " - " + monAn.getGia(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}