package com.example.projectcloudcomputing;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class VideoActivity extends AppCompatActivity {

    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView=findViewById(R.id.videoView);
        Uri u=Uri.parse("https://firebasestorage.googleapis.com/v0/b/project-cloud-computing-33e50.appspot.com/o/Coronavirus_-_COVID-19_HIGH%5B1%5D.mp4?alt=media&token=ed6a817d-efa2-4d54-a361-7e7d903b50f3");
        videoView.setVideoURI(u);
        videoView.start();


    }
}
