package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.ReviewModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<ReviewModel> reviewModelList;
    private Context context;


    public CommentAdapter(Context context, List<ReviewModel> reviewModelList) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView app_name, user_review, sentimate, polarity, subjectivity;


        public ViewHolder(@NonNull View view) {
            super(view);
            app_name = view.findViewById(R.id.app_name);
            user_review = view.findViewById(R.id.user_review);
            sentimate = view.findViewById(R.id.sentimate);
            polarity = view.findViewById(R.id.polarity);
            subjectivity = view.findViewById(R.id.subjectivity);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewModel reviewModel = reviewModelList.get(position);
        holder.app_name.setText(reviewModel.getApp_name());
        holder.user_review.setText("User Review : " + reviewModel.getUser_Review());
        holder.sentimate.setText("Sentimate : " + reviewModel.getSentimate());
        holder.polarity.setText("Polarity : " + reviewModel.getPolarity());
        holder.subjectivity.setText("Subjectivity : " + reviewModel.getSubjectivity());

    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public void updateReviewDataSet(List<ReviewModel> reviewModelList) {
        this.reviewModelList = reviewModelList;
        notifyDataSetChanged();
    }


}
