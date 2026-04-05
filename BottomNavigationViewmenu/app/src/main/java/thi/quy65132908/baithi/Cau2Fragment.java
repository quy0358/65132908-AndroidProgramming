package thi.quy65132908.baithi;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Cau2Fragment extends Fragment {

    EditText editChieuCao, editCanNang;
    Button btnTinhBMI;
    CardView cardResult;
    TextView tvBMIValue, tvBMIStatus, tvBMIAdvice;
    LinearLayout layoutBMIStatus;

    public Cau2Fragment() {
        // Required empty public constructor
    }

    public static Cau2Fragment newInstance(String param1, String param2) {
        Cau2Fragment fragment = new Cau2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_cau2, container, false);

        editChieuCao = view.findViewById(R.id.editChieuCao);
        editCanNang = view.findViewById(R.id.editCanNang);
        btnTinhBMI = view.findViewById(R.id.btnTinhBMI);
        cardResult = view.findViewById(R.id.cardResult);
        tvBMIValue = view.findViewById(R.id.tvBMIValue);
        tvBMIStatus = view.findViewById(R.id.tvBMIStatus);
        tvBMIAdvice = view.findViewById(R.id.tvBMIAdvice);
        layoutBMIStatus = view.findViewById(R.id.layoutBMIStatus);

        btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        return view;
    }

    private void calculateBMI() {
        String heightStr = editChieuCao.getText().toString().trim();
        String weightStr = editCanNang.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(getContext(), "⚠️ Vui lòng nhập đầy đủ chiều cao và cân nặng!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double heightCm = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (heightCm <= 0 || weight <= 0) {
                Toast.makeText(getContext(), "⚠️ Giá trị phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);

            // Show result card with animation
            cardResult.setVisibility(View.VISIBLE);
            cardResult.setAlpha(0f);
            cardResult.animate().alpha(1f).setDuration(500).start();

            // Display BMI value
            tvBMIValue.setText(String.format("%.1f", bmi));

            // Classify BMI and set colors
            String status;
            String advice;
            int color;

            if (bmi < 18.5) {
                status = getString(R.string.bmi_underweight);
                advice = "Bạn nên tăng cường dinh dưỡng và ăn uống đầy đủ hơn.";
                color = ContextCompat.getColor(getContext(), R.color.bmi_underweight);
            } else if (bmi < 25.0) {
                status = getString(R.string.bmi_normal);
                advice = "Tuyệt vời! Chỉ số BMI của bạn ở mức bình thường. Hãy duy trì!";
                color = ContextCompat.getColor(getContext(), R.color.bmi_normal);
            } else if (bmi < 30.0) {
                status = getString(R.string.bmi_overweight);
                advice = "Bạn nên tập thể dục thường xuyên và kiểm soát chế độ ăn.";
                color = ContextCompat.getColor(getContext(), R.color.bmi_overweight);
            } else {
                status = getString(R.string.bmi_obese);
                advice = "Bạn nên tham khảo ý kiến bác sĩ về chế độ dinh dưỡng và luyện tập.";
                color = ContextCompat.getColor(getContext(), R.color.bmi_obese);
            }

            tvBMIStatus.setText(status);
            tvBMIStatus.setTextColor(color);
            tvBMIValue.setTextColor(color);
            tvBMIAdvice.setText(advice);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "⚠️ Giá trị nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }
}