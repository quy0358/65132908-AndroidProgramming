package ntquy.ntu.bailamthem3_recyclerview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String RSS_URL = "https://vnexpress.net/rss/the-gioi.rss";
    private static final String PREFS_NAME = "BookmarkPrefs";
    private static final String KEY_BOOKMARKS = "bookmarked_links";

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private SearchView searchView;
    private ProgressBar progressBar;
    private MaterialButton btnAll, btnFavorites;

    private List<RssItem> allItems = new ArrayList<>();
    private List<RssItem> displayedItems = new ArrayList<>();
    private boolean showingFavorites = false;
    private String currentQuery = "";

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        searchView = findViewById(R.id.searchView);
        progressBar = findViewById(R.id.progressBar);
        btnAll = findViewById(R.id.btnAll);
        btnFavorites = findViewById(R.id.btnFavorites);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this, displayedItems, this::onBookmarkClick);
        recyclerView.setAdapter(adapter);

        // Setup SwipeRefreshLayout - Chức năng 3: Kéo để làm mới
        swipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        swipeRefresh.setOnRefreshListener(this::loadRssData);

        // Setup SearchView - Chức năng 1: Tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentQuery = newText;
                filterAndDisplay();
                return true;
            }
        });

        // Toggle buttons - Chức năng 2: Xem yêu thích
        btnAll.setOnClickListener(v -> {
            showingFavorites = false;
            btnAll.setChecked(true);
            btnFavorites.setChecked(false);
            filterAndDisplay();
        });

        btnFavorites.setOnClickListener(v -> {
            showingFavorites = true;
            btnAll.setChecked(false);
            btnFavorites.setChecked(true);
            filterAndDisplay();
        });

        // Mặc định chọn "Tất cả"
        btnAll.setChecked(true);

        // Tải dữ liệu RSS
        loadRssData();
    }

    // Tải dữ liệu RSS từ mạng (chạy trong thread riêng)
    private void loadRssData() {
        progressBar.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            try {
                List<RssItem> items = RssParser.parse(RSS_URL);

                // Đánh dấu các tin đã bookmark
                Set<String> bookmarks = getBookmarkedLinks();
                for (RssItem item : items) {
                    if (item.getLink() != null && bookmarks.contains(item.getLink())) {
                        item.setBookmarked(true);
                    }
                }

                mainHandler.post(() -> {
                    allItems.clear();
                    allItems.addAll(items);
                    filterAndDisplay();
                    progressBar.setVisibility(View.GONE);
                    swipeRefresh.setRefreshing(false);
                    Toast.makeText(MainActivity.this,
                            "Đã tải " + items.size() + " tin tức", Toast.LENGTH_SHORT).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
                mainHandler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    swipeRefresh.setRefreshing(false);
                    Toast.makeText(MainActivity.this,
                            "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    // Lọc danh sách theo tìm kiếm và bookmark
    private void filterAndDisplay() {
        displayedItems.clear();

        for (RssItem item : allItems) {
            boolean matchesQuery = currentQuery.isEmpty() ||
                    (item.getTitle() != null &&
                            item.getTitle().toLowerCase().contains(currentQuery.toLowerCase()));

            boolean matchesFilter = !showingFavorites || item.isBookmarked();

            if (matchesQuery && matchesFilter) {
                displayedItems.add(item);
            }
        }

        adapter.updateList(displayedItems);

        if (showingFavorites && displayedItems.isEmpty()) {
            Toast.makeText(this, "Chưa có tin yêu thích nào", Toast.LENGTH_SHORT).show();
        }
    }

    // Xử lý nhấn bookmark - Chức năng 2: Lưu tin yêu thích
    private void onBookmarkClick(RssItem item, int position) {
        item.setBookmarked(!item.isBookmarked());

        // Lưu vào SharedPreferences
        saveBookmark(item);

        // Cập nhật UI
        adapter.notifyItemChanged(position);

        if (item.isBookmarked()) {
            Toast.makeText(this, "Đã lưu tin yêu thích ⭐", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đã bỏ lưu tin", Toast.LENGTH_SHORT).show();
            // Nếu đang xem yêu thích, cập nhật lại danh sách
            if (showingFavorites) {
                filterAndDisplay();
            }
        }
    }

    // Lưu/bỏ bookmark vào SharedPreferences
    private void saveBookmark(RssItem item) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> bookmarks = new HashSet<>(getBookmarkedLinks());

        if (item.isBookmarked()) {
            bookmarks.add(item.getLink());
        } else {
            bookmarks.remove(item.getLink());
        }

        prefs.edit().putStringSet(KEY_BOOKMARKS, bookmarks).apply();
    }

    // Đọc danh sách bookmark từ SharedPreferences
    private Set<String> getBookmarkedLinks() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getStringSet(KEY_BOOKMARKS, new HashSet<>());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}