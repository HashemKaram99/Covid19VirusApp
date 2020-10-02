package com.example.projectcloudcomputing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    TextView nameDetailTextView,descriptionDetailTextView;
   ImageView detailsImageView;

    private void initializeWidgets(){
        nameDetailTextView= findViewById(R.id.nameDetailTextView);
        detailsImageView=findViewById(R.id.detailsImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeWidgets();
        Intent i=this.getIntent();
        String name=i.getExtras().getString("NAME_KEY");
        String imageURL=i.getExtras().getString("IMAGE_KEY");

        nameDetailTextView.setText(name);

        Picasso.get()
                .load(imageURL)
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_focused)
                .fit()
                .centerCrop()
                .into(detailsImageView);

    }

}
