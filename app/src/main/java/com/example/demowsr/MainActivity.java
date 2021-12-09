package com.example.demowsr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demowsr.MovieAdapter;
import com.example.demowsr.ApiClient;
import com.example.demowsr.Movies;
import com.example.demowsr.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.demowsr.ApiClient.getMovies;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView1;
    Button btnWatch;
    List<Movies> mMovies;
    List<Movies> mMovies1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovies = new ArrayList<>();
        mMovies1 = new ArrayList<>();
        btnWatch = findViewById(R.id.btnWatch);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView1 = (RecyclerView) findViewById(R.id.recycleView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView1.setLayoutManager(layoutManager1);

        MovieAdapter movieAdapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(movieAdapter);



        MovieAdapter movieAdapter1 = new MovieAdapter(mMovies1);
        mRecyclerView1.setAdapter(movieAdapter1);

        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilmActivity.class);
                startActivity(intent);
            }
        });


        final Call<List<Movies>> call1 = ApiClient.getMovies().getMovies();
        call1.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call1, Response<List<Movies>> response) {
                if (response.isSuccessful()) {
                    mMovies.addAll(response.body());
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                } else {

                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        final Call<List<Movies>> call = ApiClient.getTrend().getTrend();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call1, Response<List<Movies>> response) {
                if (response.isSuccessful()) {
                    mMovies.addAll(response.body());
                    mRecyclerView1.getAdapter().notifyDataSetChanged();
                } else {

                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }

}