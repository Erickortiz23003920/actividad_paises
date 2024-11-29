package com.example.paises;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.RestCountriesApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaisesActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private PaisesAdapter paisesAdapter;
private List<paises> countryList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_paises);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        paisesAdapter = new PaisesAdapter(countryList);
        recyclerView.setAdapter(paisesAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestCountriesApi api= retrofit.create(RestCountriesApi.class);
        Call<List<paises>> call= api.getAllCountries();
        call.enqueue(new Callback<List<paises>>() {
            @Override
            public void onResponse(Call<List<paises>> Call, Response<List<paises>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    countryList.addAll(response.body());
                    paisesAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<paises>> Call, Throwable t){
                Log.e("PaisesActivity", "Error al obtener los datos",t);
                    Toast.makeText(PaisesActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }

        });
    }
}