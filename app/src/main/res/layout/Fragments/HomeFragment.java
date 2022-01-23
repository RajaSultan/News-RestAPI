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

public class HomeFragment extends Fragment {

    private static final String API = "8b8f8d34a8334771a6c2e576265be84c";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String county = "us";
    private RecyclerView recyclerViewOfHome;
    private String apiKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.homefragment, null);

        recyclerViewOfHome = view.findViewById(R.id.recyclerViewHome);
        modelClassArrayList = new ArrayList<>();
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewOfHome.setAdapter(adapter);

        findNews();
        return view;

    }

    private void findNews() {
        ApiUtil.getApiInterface().getNews(county, 100, API).enqueue(new Callback<mainNews>() {
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
