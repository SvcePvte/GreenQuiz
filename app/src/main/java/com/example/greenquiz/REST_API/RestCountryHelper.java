package com.example.greenquiz.REST_API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestCountryHelper {

    final static String BASE_URL ="https://restcountries.com/v2/alpha/";

    public static void setCountryFlag(ImageView imageView, String countryCode)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlagAPIService apiService = retrofit.create(FlagAPIService.class);

        Call<JsonElement> appel = apiService.getCountryFlag(countryCode);
        appel.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()){
                    JsonElement contenu = response.body();
                    JsonObject jsonGlobal = contenu.getAsJsonObject();
                    String picture_url = jsonGlobal.getAsJsonObject("flags").get("png").getAsString();

                    Log.d("MSG", "" + picture_url);

                    new DownloadImageTask(imageView).execute(picture_url);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("MSG", "ON FAILURE, " + call.toString());
            }
        });
    }

    // Permet d'extraire une image à partir d'une URL via une tâche asynchrone (en tâche de fond)
    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
