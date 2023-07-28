package com.example.aymenstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aymenstore.adapter.RestaurantAdapter;
import com.example.aymenstore.objet.Restaurant;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;
    private YelpApiClient yelpApiClient;
    private TextView sortby;
    private String selectedSortOption = ""; // Track the selected sorting option (Rating/Price)
    private AlertDialog dialog;
    String urlx ;
    private ProgressDialog progressDialog;
    private static final String BASE_URL = "https://api.yelp.com/v3/";
      DrawerLayout drawerLayout ;
      boolean b =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortby = findViewById(R.id.sortedby);
        sortby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingPriceDialog();
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter();
        recyclerView.setAdapter(restaurantAdapter);
        // Find the NavigationView
        NavigationView navigationView = findViewById(R.id.navigationView);

// Set a listener for navigation item selection
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item selection
                int itemId = item.getItemId();
                if (itemId == R.id.menu_favorites) {
                   Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                   startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;

                }
                if (itemId == R.id.menu_search) {
                   Intent intent = new Intent(MainActivity.this, MainActivity.class);
                   startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;

                }
                // Handle other item selections if needed
                return false;
            }
        });


        yelpApiClient = new YelpApiClient();

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        new Thread(() -> {
            String  url =  BASE_URL + "businesses/search?location=CA";
            try {
                List<Restaurant> searchResults = yelpApiClient.searchRestaurants(url);
                runOnUiThread(() ->  sortRestaurants("Rating", searchResults));


            } catch (IOException e) {
                e.printStackTrace();
                // Handle API call failure
            }
        }).start();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Handle search query submission
        searchRestaurants(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Handle search query changes
        searchRestaurants(newText);

        return true;
    }

    private void searchRestaurants(String query) {
        if (query.equals("")) {
            urlx = BASE_URL + "businesses/search?location=CA";
        } else {
            urlx = BASE_URL + "businesses/search?term=" + query + "&location=CA";
        }

      //  showProgressDialog(); // Show the progress dialog

        new Thread(() -> {
            try {
                List<Restaurant> searchResults = yelpApiClient.searchRestaurants(urlx);
                runOnUiThread(() -> {
                   // restaurantAdapter.setRestaurants(searchResults,false);
                    if(b){
                    sortRestaurants("Price", searchResults);}
                    else {
                        sortRestaurants("Rating", searchResults);
                    }

                    if (progressDialog != null && progressDialog.isShowing()) {
                     //   dismissProgressDialog(); // Dismiss the progress dialog if it's currently shown
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                // Handle API call failure
                if (progressDialog != null && progressDialog.isShowing()) {
                   // dismissProgressDialog(); // Dismiss the progress dialog in case of an error
                }
            }
        }).start();
    }





    private void showRatingPriceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        // Create an array of options
        String[] options = {"Rating", "Price"};

        // Inflate a custom layout for the dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialogie, null);
        builder.setView(dialogView);

        // Find the option buttons in the custom layout
        Button ratingButton = dialogView.findViewById(R.id.rating_button);
        Button priceButton = dialogView.findViewById(R.id.price_button);


        // Set click listeners for the options
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSortOption = "Rating";
                List<Restaurant> sortedList = new ArrayList<>(restaurantAdapter.originalRestaurantList);
                sortRestaurants(selectedSortOption,sortedList);
                b = false;
                if (dialog != null) {
                    dialog.dismiss(); // Dismiss the dialog using the field reference
                }
                sortby.setText("Rating");
            }
        });

        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSortOption = "Price";
                List<Restaurant> sortedList = new ArrayList<>(restaurantAdapter.originalRestaurantList);
                sortRestaurants(selectedSortOption,sortedList);
                b = true;
                if (dialog != null) {
                    dialog.dismiss(); // Dismiss the dialog using the field reference
                }
                sortby.setText("Price");
            }
        });

        // Create and show the alert dialog
        dialog = builder.create(); // Assign the created dialog to the field
        dialog.show();
    }


    private void sortRestaurants(String sortOption,List<Restaurant> res) {
        if (sortOption.equals("Rating")) {
            // Sort by rating
           // List<Restaurant> sortedList = new ArrayList<>(restaurantAdapter.originalRestaurantList);
            Collections.sort(res, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    return Float.compare((float)  r2.getRating(),(float) r1.getRating());
                }
            });
            restaurantAdapter.setRestaurants(res,false);
        } else if (sortOption.equals("Price")) {
            // Sort by price
          //  List<Restaurant> sortedList = new ArrayList<>(restaurantAdapter.originalRestaurantList);
            Collections.sort(res, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant r1, Restaurant r2) {
                    int length1 = r1.getPrice().length();
                    int length2 = r2.getPrice().length();
                    return Integer.compare(length1, length2);
                }
            });
            restaurantAdapter.setRestaurants(res,false);
        } else {
            // Default sorting (no sorting)
            restaurantAdapter.setRestaurants(restaurantAdapter.originalRestaurantList,false);
        }
    }

     void showProgressDialog() {
        progressDialog = new ProgressDialog(this);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    void moove(AppCompatActivity activity){
        Intent intent = new Intent(MainActivity.this,activity.getClass());
        startActivity(intent);

    }


}

