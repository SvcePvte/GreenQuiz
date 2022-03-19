package com.example.greenquiz.REST_API;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FlagAPIService {
    @GET("{country}?fields=flags")
    Call<JsonElement> getCountryFlag(@Path("country") String country);
}
