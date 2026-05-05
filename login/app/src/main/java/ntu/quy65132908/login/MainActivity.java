package ntu.quy65132908.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra trạng thái đăng nhập và chuyển hướng
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Đã đăng nhập -> chuyển sang Home
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else {
            // Chưa đăng nhập -> chuyển sang Login
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        finish();
    }
}