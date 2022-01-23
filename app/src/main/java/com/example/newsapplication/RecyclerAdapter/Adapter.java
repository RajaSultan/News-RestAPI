package com.example.newsapplication.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapplication.Activities.webView;
import com.example.newsapplication.Classes.ModelClass;
import com.example.newsapplication.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.time.setText("Published At:-" + modelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(modelClassArrayList.get(position).getAuthor());
        holder.mainHeading.setText(modelClassArrayList.get(position).getTitle());
        holder.content.setText(modelClassArrayList.get(position).getDescription());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView mainHeading, content, author, time;
        CardView cardView;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mainHeading = itemView.findViewById(R.id.mainHeading);
            content = itemView.findViewById(R.id.content);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
