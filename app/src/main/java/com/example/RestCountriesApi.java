package com.example;
import com.example.paises.paises;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestCountriesApi {
    @GET("all")
    Call<List<paises>> getAllCountries();
}
