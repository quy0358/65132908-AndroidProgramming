package thi.quy65132908.gk1.nguyenthanhquy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LamThemActivity extends AppCompatActivity {

    private static final String RSS_URL = "https://vnexpress.net/rss/the-gioi.rss";

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private ProgressBar progressBar;

    private List<RssItem> allItems = new ArrayList<>();

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lam_them);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // Ánh xạ view
        rvNews = findViewById(R.id.rvNews);
        progressBar = findViewById(R.id.progressBar);

        // Setup RecyclerView
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this, allItems);
        rvNews.setAdapter(adapter);

        // Tải dữ liệu RSS
        loadRssData();
    }

    // Tải dữ liệu RSS từ mạng (chạy trong thread riêng)
    private void loadRssData() {
        progressBar.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            try {
                List<RssItem> items = RssParser.parse(RSS_URL);

                mainHandler.post(() -> {
                    allItems.clear();
                    allItems.addAll(items);
                    adapter.updateList(allItems);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LamThemActivity.this,
                            "Đã tải " + items.size() + " tin tức", Toast.LENGTH_SHORT).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
                mainHandler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LamThemActivity.this,
                            "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
