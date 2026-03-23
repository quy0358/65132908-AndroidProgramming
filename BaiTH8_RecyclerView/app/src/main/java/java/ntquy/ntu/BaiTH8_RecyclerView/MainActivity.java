package java.ntquy.ntu.BaiTH8_RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LandScapeAdapter adapter;
    private List<LandScape> landScapeList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set title
        setTitle("Recyclerview_LandScape");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare data
        landScapeList = new ArrayList<>();

        landScapeList.add(new LandScape("Flag Tower of Ha Noi",
                R.drawable.flag_tower_hanoi,
                "Cột cờ Hà Nội là một kỳ đài nằm trong khu vực Hoàng thành Thăng Long, được xây dựng vào năm 1812 dưới triều vua Gia Long. Đây là một trong những công trình kiến trúc tiêu biểu của Hà Nội, cao 33.4m, gồm 3 tầng đế và 1 trụ cờ hình bát giác."));

        landScapeList.add(new LandScape("Effel Tower",
                R.drawable.effel_tower,
                "Tháp Eiffel là một tháp lưới thép trên Champ de Mars ở Paris, Pháp. Được đặt theo tên kỹ sư Gustave Eiffel, được xây dựng năm 1887-1889, cao 330m. Đây là công trình kiến trúc nổi tiếng nhất nước Pháp và là biểu tượng của thành phố Paris."));

        landScapeList.add(new LandScape("Colosseum",
                R.drawable.colosseum,
                "Đấu trường La Mã (Colosseum) là một đấu trường khổng lồ ở trung tâm thành phố Roma, Ý. Được xây dựng vào năm 70-80 sau Công nguyên, chứa được 50.000-80.000 khán giả. Đây là một trong 7 kỳ quan thế giới mới."));

        landScapeList.add(new LandScape("Great Wall of China",
                R.drawable.great_wall,
                "Vạn Lý Trường Thành là một công trình kiến trúc vĩ đại nhất trong lịch sử nhân loại, trải dài hơn 21.000 km qua miền Bắc Trung Quốc. Được xây dựng từ thế kỷ 7 trước Công nguyên, đây là một trong 7 kỳ quan thế giới mới."));

        landScapeList.add(new LandScape("Taj Mahal",
                R.drawable.taj_mahal,
                "Taj Mahal là một lăng mộ bằng đá cẩm thạch trắng ở Agra, Ấn Độ. Được xây dựng vào năm 1632-1653 bởi hoàng đế Mughal Shah Jahan để tưởng nhớ người vợ Mumtaz Mahal. Đây là biểu tượng của tình yêu vĩnh cửu và là một trong 7 kỳ quan thế giới mới."));

        landScapeList.add(new LandScape("Sydney Opera House",
                R.drawable.sydney_opera,
                "Nhà hát Opera Sydney là một trung tâm nghệ thuật biểu diễn nổi tiếng thế giới, nằm bên cảng Sydney, Úc. Được thiết kế bởi kiến trúc sư Jørn Utzon, khánh thành năm 1973, với mái hình vỏ sò độc đáo. Đây là Di sản Thế giới UNESCO."));

        landScapeList.add(new LandScape("Ha Long Bay",
                R.drawable.ha_long_bay,
                "Vịnh Hạ Long nằm ở tỉnh Quảng Ninh, Việt Nam, nổi tiếng với hàng nghìn hòn đảo đá vôi và hang động tuyệt đẹp. Được UNESCO công nhận là Di sản Thiên nhiên Thế giới vào năm 1994. Đây là một trong những kỳ quan thiên nhiên đẹp nhất thế giới."));

        // Set adapter
        adapter = new LandScapeAdapter(this, landScapeList);
        recyclerView.setAdapter(adapter);

        // Feature 1: Search/Filter
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        // Feature 3: Swipe-to-Delete with Undo
        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                LandScape deletedItem = adapter.removeItem(position);

                Snackbar.make(recyclerView, deletedItem.getName() + " đã bị xóa", Snackbar.LENGTH_LONG)
                        .setAction("HOÀN TÁC", v -> {
                            adapter.restoreItem(deletedItem, position);
                            recyclerView.scrollToPosition(position);
                        })
                        .show();
            }
        };
        new ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView);
    }
}