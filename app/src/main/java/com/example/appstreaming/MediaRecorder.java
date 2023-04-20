package com.example.appstreaming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("STREAM_URL", "http://example.com/stream");
        startActivity(intent);

public class MediaRecorder extends AppCompatActivity {


        private VideoView videoView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_secondary);

            videoView = findViewById(R.id.videoView);

            String streamUrl = getIntent().getStringExtra("STREAM_URL");

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(Uri.parse(streamUrl));
            videoView.start();
        }

}