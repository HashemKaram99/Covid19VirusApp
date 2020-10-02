package com.example.projectcloudcomputing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public abstract class ItemsActivity extends AppCompatActivity implements RecyclerAdapter
        .OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<data> mData;

    private void openDetailActivity(String[] data) {
    Intent intent = new Intent(this, DetailsActivity.class);
    intent.putExtra("NAME_KEY", data[0]);
    intent.putExtra("IMAGE_KEY", data[2]);
    startActivity(intent);
    }

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mData = new ArrayList<>();
        mAdapter = new RecyclerAdapter(ItemsActivity.this, mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(ItemsActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mData.clear();

                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    data upload = s.getValue(data.class);
                    upload.setKey(s.getKey());
                    mData.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ItemsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void onItemClick(int position) {
        data clickedData = mData.get(position);
        String[] Data = {clickedData.getName(), clickedData.getDescription(), clickedData.getImageUrl()};
        openDetailActivity(Data);
    }

    @Override
    public void onShowItemClick(int position) {
        data clickedData = mData.get(position);
        String[] Data = {clickedData.getName(), clickedData.getDescription(), clickedData.getImageUrl()};
        openDetailActivity(Data);
    }

    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);


    }
}