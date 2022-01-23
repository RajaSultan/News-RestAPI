package com.example.newsapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.Classes.ApiUtil;
import com.example.newsapplication.Classes.ModelClass;
import com.example.newsapplication.Classes.mainNews;
import com.example.newsapplication.R;
import com.example.newsapplication.RecyclerAdapter.Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    private static final String API = "8b8f8d34a8334771a6c2e576265be84c";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String county = "us";
    private RecyclerView recyclerViewOfEntertainment;
    private String apiKey;
    private String category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entertainmentfragment, null);

        recyclerViewOfEntertainment = view.findViewById(R.id.recyclerViewEntertainment);
        modelClassArrayList = new ArrayList<>();
        recyclerViewOfEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewOfEntertainment.setAdapter(adapter);

        findNews();
        return view;

    }

    private void findNews() {
        ApiUtil.getApiInterface().getCategoryNews(county, category, 100, API).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}

