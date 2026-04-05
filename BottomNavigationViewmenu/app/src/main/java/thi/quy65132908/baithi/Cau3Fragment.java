package thi.quy65132908.baithi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Cau3Fragment extends Fragment {

    LandScapeAdapter adapter;
    ArrayList<LandScape> list;
    RecyclerView recyclerViewLandScape;

    public Cau3Fragment() {
        // Required empty public constructor
    }

    public static Cau3Fragment newInstance(String param1, String param2) {
        Cau3Fragment fragment = new Cau3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Prepare data with descriptions
        list = new ArrayList<LandScape>();

        // Existing landmarks (using mipmap images)
        list.add(new LandScape("Cột cờ Hà Nội", "hanoi_flag_tower",
                "Cột cờ Hà Nội là một kỳ đài nằm trong khu vực Hoàng thành Thăng Long, được xây dựng vào năm 1812 dưới triều Nguyễn. Cao 33.4m, là một biểu tượng lịch sử của Thủ đô.",
                "mipmap"));
        list.add(new LandScape("Tháp Eiffel", "eiffel_tower",
                "Tháp Eiffel là một công trình kiến trúc bằng sắt nổi tiếng tại Paris, Pháp. Được xây dựng năm 1889, cao 330m, là biểu tượng của nước Pháp.",
                "mipmap"));
        list.add(new LandScape("Cung điện Buckingham", "buckingham_palace",
                "Cung điện Buckingham là nơi ở chính thức của Hoàng gia Anh tại London. Được xây dựng từ năm 1703, có 775 phòng và là nơi diễn ra các sự kiện hoàng gia.",
                "mipmap"));
        list.add(new LandScape("Tượng nữ thần tự do", "nu_than_tu_do",
                "Tượng Nữ thần Tự do là một tác phẩm điêu khắc khổng lồ tại New York, Mỹ. Được Pháp tặng năm 1886, cao 93m, là biểu tượng tự do và dân chủ.",
                "mipmap"));

        // New landmarks (using drawable vector illustrations)
        list.add(new LandScape("Đấu trường La Mã", "ic_place_colosseum",
                "Đấu trường La Mã (Colosseum) là công trình kiến trúc cổ đại nổi tiếng nhất tại Rome, Ý. Được xây dựng từ năm 72-80 sau Công Nguyên, có sức chứa 50.000-80.000 khán giả.",
                "drawable"));
        list.add(new LandScape("Vạn Lý Trường Thành", "ic_place_great_wall",
                "Vạn Lý Trường Thành là công trình kiến trúc kỳ vĩ nhất của Trung Quốc. Được xây dựng qua nhiều triều đại, dài hơn 21.000 km, là Di sản Thế giới UNESCO.",
                "drawable"));
        list.add(new LandScape("Đền Taj Mahal", "ic_place_taj_mahal",
                "Taj Mahal là một lăng mộ bằng đá cẩm thạch trắng tại Agra, Ấn Độ. Được vua Shah Jahan xây năm 1632 để tưởng nhớ vợ, là một trong 7 kỳ quan thế giới mới.",
                "drawable"));
        list.add(new LandScape("Kim tự tháp Giza", "ic_place_pyramids",
                "Kim tự tháp Giza là quần thể kim tự tháp cổ đại tại Ai Cập. Kim tự tháp Khufu là lớn nhất, cao 146.5m, là kỳ quan cổ đại duy nhất còn tồn tại.",
                "drawable"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewCau3 = inflater.inflate(R.layout.fragment_cau3, container, false);

        recyclerViewLandScape = viewCau3.findViewById(R.id.ryCau3);
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(viewCau3.getContext());
        recyclerViewLandScape.setLayoutManager(layoutLinear);

        adapter = new LandScapeAdapter(viewCau3.getContext(), list);
        recyclerViewLandScape.setAdapter(adapter);

        return viewCau3;
    }
}