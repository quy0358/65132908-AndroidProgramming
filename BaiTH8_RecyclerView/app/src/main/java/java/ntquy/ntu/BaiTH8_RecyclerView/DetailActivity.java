package java.ntquy.ntu.BaiTH8_RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Enable back button in ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get data from Intent
        LandScape landScape = (LandScape) getIntent().getSerializableExtra("landscape");

        if (landScape != null) {
            setTitle(landScape.getName());

            ImageView imgDetail = findViewById(R.id.imgDetail);
            TextView tvDetailName = findViewById(R.id.tvDetailName);
            TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
            Button btnShare = findViewById(R.id.btnShare);

            imgDetail.setImageResource(landScape.getImage());
            tvDetailName.setText(landScape.getName());
            tvDetailDescription.setText(landScape.getDescription());

            // Feature 2: Share
            btnShare.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, landScape.getName());
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        landScape.getName() + "\n\n" + landScape.getDescription());
                startActivity(Intent.createChooser(shareIntent, "Chia sẻ qua"));
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
