package com.example.aymenstore;

import com.example.aymenstore.objet.Restaurant;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YelpApiClient {


    private static final String API_KEY = "GxDf5x9bJGcaY-aSL6CxI8jQMc4Z2DJuahxrFYCiXLdAwLVYO2vWz8nj4vxJvYOI6msHZekJCr7ycbGAqXOxO0orLQxnT2XwsQDosP9cdorA8PM01E7ssRSw-IaHZHYx";

    private OkHttpClient client;

    public YelpApiClient() {
        client = new OkHttpClient();
    }

    public List<Restaurant> searchRestaurants( String urlx) throws IOException {



        Request request = new Request.Builder()
                .url(urlx)
                .addHeader("Authorization", "Bearer " +""+ API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            return parseSearchResults(responseBody);
        }
    }

    private List<Restaurant> parseSearchResults(String responseBody) {
        try {
            JSONObject json = new JSONObject(responseBody);
            JSONArray businesses = json.getJSONArray("businesses");

            List<Restaurant> searchResults = new ArrayList<>();

            for (int i = 0; i < businesses.length(); i++) {
                JSONObject business = businesses.getJSONObject(i);
                String name = business.getString("name");
                double rating = business.optDouble("rating", 0.0);
                String price = business.optString("price", "");
                String categoryType = business.getJSONArray("categories").getJSONObject(0).getString("title");
                        //getString("categories");
                String  image = business.optString("image_url","test");
                String phoneNumber = business.optString("display_phone", "");
                String address = business.getJSONObject("location").getJSONArray("display_address").getString(0);


                Restaurant restaurant = new Restaurant( name,  rating, price,  categoryType, phoneNumber,address,image);
                searchResults.add(restaurant);
            }

            return searchResults;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}

