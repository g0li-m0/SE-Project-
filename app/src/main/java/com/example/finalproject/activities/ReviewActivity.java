package com.example.finalproject.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.AppViewModel;
import com.example.finalproject.CommentAdapter;
import com.example.finalproject.R;
import com.example.finalproject.db.RoomDBRepository;
import com.example.finalproject.model.ReviewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translated__review);

        TextView app_name = findViewById(R.id.app_name);
        TextView user_review = findViewById(R.id.user_review);
        TextView sentimate = findViewById(R.id.sentimate);
        TextView polarity = findViewById(R.id.polarity);
        TextView subjectivity = findViewById(R.id.subjectivity);
        FloatingActionButton insertComment = findViewById(R.id.insertFab);
        RoomDBRepository roomDBRepository = new RoomDBRepository(getApplication());

        // Init list
        initializeRecyclerView();

        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        String name = getIntent().getStringExtra("comment");
        if (name == null && name.isEmpty()) {
            // fetch all of reviews
            appViewModel.fetchAllReviews();
        } else {
            // fetch review by appName
            appViewModel.fetchReviewsApps(name);
        }
        appViewModel.getAppsByApps_name().observe(this, reviewModelList -> {
            if (reviewModelList.isEmpty()) {
                Toast.makeText(ReviewActivity.this, "No comments for this app", Toast.LENGTH_SHORT).show();
            } else {
                commentAdapter.updateReviewDataSet(reviewModelList);
            }
        });

        // comment
        insertComment.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(ReviewActivity.this);
            //set AlertDialog for comment
            AlertDialog dialog = new AlertDialog.Builder(ReviewActivity.this)
                    .setTitle("Insert comment")
                    .setMessage("What do you think?")
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialog1, which) -> {
                        String text = taskEditText.getText().toString();
                        if (text.isEmpty()) {
                            Toast.makeText(ReviewActivity.this, "Enter something", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ReviewModel r = new ReviewModel();
                        r.setApp_name(name);
                        r.setPolarity("1.0");
                        r.setSentimate("Neutral");
                        r.setSubjectivity("1.0");
                        r.setUser_Review(text);
                        appViewModel.insertReview(r);
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        });
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<ReviewModel> reviewModels = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, reviewModels);
        recyclerView.setAdapter(commentAdapter);
    }
}