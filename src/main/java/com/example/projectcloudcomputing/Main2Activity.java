package com.example.projectcloudcomputing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private Button openActivityBtn,openUploadActivityBtn ,Video,json,chat;
    String email = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        openActivityBtn = findViewById ( R.id.openActivityBtn );
        openUploadActivityBtn = findViewById ( R.id.openUploadActivityBtn );
        Video = findViewById ( R.id.Video );
        json = findViewById ( R.id.json );
        chat=findViewById(R.id.chat);
        openActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
                startActivity(i);
            }
        });
        openUploadActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(i);
            }
        });
Video.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent i =new Intent(getApplicationContext(),VideoActivity.class);
        startActivity(i);

    }
});
        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),jsonActivity.class);
                startActivity(i);

            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });
    }

}