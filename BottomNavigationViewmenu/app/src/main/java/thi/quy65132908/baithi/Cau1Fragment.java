package thi.quy65132908.baithi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cau1Fragment extends Fragment {
    EditText editText_MET;
    EditText editText_KiloMET;
    Button btnChuyen;

    // Temperature conversion
    EditText editCelsius;
    EditText editFahrenheit;
    Button btnDoiNhietDo;

    public Cau1Fragment() {
        // Required empty public constructor
    }

    public static Cau1Fragment newInstance(String param1, String param2) {
        Cau1Fragment fragment = new Cau1Fragment();
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
        View viewCau1 = inflater.inflate(R.layout.fragment_cau1, container, false);

        // === Length conversion ===
        editText_MET = viewCau1.findViewById(R.id.editSoMet);
        editText_KiloMET = viewCau1.findViewById(R.id.editSoKiloM);
        btnChuyen = viewCau1.findViewById(R.id.btnDoi);

        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duLieuMet = editText_MET.getText().toString().trim();
                String duLieuKiloMet = editText_KiloMET.getText().toString().trim();

                if (!duLieuMet.isEmpty()) {
                    // Convert m -> km
                    try {
                        double met = Double.parseDouble(duLieuMet);
                        double km = met / 1000.0;
                        editText_KiloMET.setText(String.valueOf(km));
                        Toast.makeText(viewCau1.getContext(), "✅ m → km", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(viewCau1.getContext(), "⚠️ Số không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else if (!duLieuKiloMet.isEmpty()) {
                    // Convert km -> m
                    try {
                        double km = Double.parseDouble(duLieuKiloMet);
                        double met = km * 1000.0;
                        editText_MET.setText(String.valueOf(met));
                        Toast.makeText(viewCau1.getContext(), "✅ km → m", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(viewCau1.getContext(), "⚠️ Số không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(viewCau1.getContext(), "⚠️ Vui lòng nhập một giá trị!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // === Temperature conversion (NEW FEATURE) ===
        editCelsius = viewCau1.findViewById(R.id.editCelsius);
        editFahrenheit = viewCau1.findViewById(R.id.editFahrenheit);
        btnDoiNhietDo = viewCau1.findViewById(R.id.btnDoiNhietDo);

        btnDoiNhietDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String celsiusStr = editCelsius.getText().toString().trim();
                String fahrenheitStr = editFahrenheit.getText().toString().trim();

                if (!celsiusStr.isEmpty()) {
                    // Convert °C -> °F
                    try {
                        double celsius = Double.parseDouble(celsiusStr);
                        double fahrenheit = celsius * 9.0 / 5.0 + 32.0;
                        editFahrenheit.setText(String.format("%.2f", fahrenheit));
                        Toast.makeText(viewCau1.getContext(), "✅ °C → °F", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(viewCau1.getContext(), "⚠️ Số không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else if (!fahrenheitStr.isEmpty()) {
                    // Convert °F -> °C
                    try {
                        double fahrenheit = Double.parseDouble(fahrenheitStr);
                        double celsius = (fahrenheit - 32.0) * 5.0 / 9.0;
                        editCelsius.setText(String.format("%.2f", celsius));
                        Toast.makeText(viewCau1.getContext(), "✅ °F → °C", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(viewCau1.getContext(), "⚠️ Số không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(viewCau1.getContext(), "⚠️ Vui lòng nhập một giá trị!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return viewCau1;
    }
}