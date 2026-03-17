package tiil.edu.baith7_listview1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VatLieuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vat_lieu);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewVatLieu);

        String[] dsVatLieu = {
                "Xi măng",
                "Gạch",
                "Đá ốp lát",
                "Ống nhựa",
                "Sơn chống thấm",
                "...."
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_custom, R.id.tvItem, dsVatLieu);
        listView.setAdapter(adapter);
    }
}
