package thi.quy65132908.baithi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Fade-in animation for the entire content
        LinearLayout homeContainer = view.findViewById(R.id.homeContainer);
        homeContainer.setAlpha(0f);
        homeContainer.animate()
                .alpha(1f)
                .setDuration(800)
                .setStartDelay(200)
                .start();

        // Staggered animation for feature cards
        animateCard(view.findViewById(R.id.feature1Card), 400);
        animateCard(view.findViewById(R.id.feature2Card), 550);
        animateCard(view.findViewById(R.id.feature3Card), 700);
        animateCard(view.findViewById(R.id.feature4Card), 850);

        return view;
    }

    private void animateCard(View card, long delay) {
        card.setAlpha(0f);
        card.setTranslationY(30f);
        card.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(delay)
                .start();
    }
}