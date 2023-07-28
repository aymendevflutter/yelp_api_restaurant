package com.example.aymenstore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aymenstore.adapter.RestaurantAdapter;
import com.example.aymenstore.objet.Restaurant;
import java.util.List;
public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter();
        recyclerView.setAdapter(restaurantAdapter);

        List<Restaurant> favorites = FavoritesManager.getFavorites(this);
       restaurantAdapter.setRestaurants(favorites,true);
    }
}
