package me.arsnotfound.myfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import me.arsnotfound.myfirstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private final ActivityResultLauncher<String> loginActivityLauncher = registerForActivityResult(new LoginActivity.LoginActivityContract(), creds ->{
        Toast.makeText(MainActivity.this, "Вы вошли как: " + creds.getUsername() + "\nВаш пароль: " + creds.getPassword(), Toast.LENGTH_SHORT).show();
        binding.startLoginActivity.setVisibility(View.GONE);
        binding.continueButton.setVisibility(View.VISIBLE);
        binding.usernameTextView.setVisibility(View.VISIBLE);
        binding.usernameTextView.setText(creds.getUsername());
        binding.passwordTextView.setVisibility(View.VISIBLE);
        binding.passwordTextView.setText(creds.getPassword());
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.startLoginActivity.setOnClickListener(view ->
                loginActivityLauncher.launch("Test string pls ignore")
        );

        binding.continueButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        });
    }
}
