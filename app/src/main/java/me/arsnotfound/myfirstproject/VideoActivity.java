package me.arsnotfound.myfirstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import me.arsnotfound.myfirstproject.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {
    private ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VideoView videoView = binding.videoView;
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.forty_four_bratuha;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(mp -> {
            Intent intent = new Intent(VideoActivity.this, MainActivity.class);
            startActivity(intent);
        });

        videoView.start();
    }

}