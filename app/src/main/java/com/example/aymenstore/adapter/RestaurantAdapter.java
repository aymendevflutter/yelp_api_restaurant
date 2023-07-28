package com.example.aymenstore.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aymenstore.FavoritesManager;
import com.example.aymenstore.R;
import com.example.aymenstore.objet.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;





public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList = new ArrayList<>();
    public List<Restaurant> originalRestaurantList = new ArrayList<>();
    private Context context;
    boolean b =false;

    public void setRestaurants(List<Restaurant> restaurants,boolean b) {
        this.restaurantList = restaurants;
        this.originalRestaurantList = new ArrayList<>(restaurants);
        this.b =b;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        holder.textName.setText((position + 1) + " " + restaurant.getName());
        holder.textAddress.setText(restaurant.getAddress());
        holder.textPrice.setText(restaurant.getPrice());

        // Set the rating value to the RatingBar
        float rating = (float) restaurant.getRating();
        holder.ratingBar.setRating(rating);

        // Set the rating value as stars
        int numStars = 5; // Number of stars in the RatingBar
        holder.ratingBar.setNumStars(numStars);
        holder.ratingBar.setRating(rating);
        holder.ratingBar.setStepSize(1);
        holder.ratingBar.setIsIndicator(true);
        holder.phoneview.setText(restaurant.getPhoneNumber());
        holder.CategoryView.setText("." + restaurant.getCategoryType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!b){
                    allerdialogue(restaurant);
                }



            }
        });

        // Load image using Picasso or any other image loading library
        if (restaurant.getImageUrl() != null && !restaurant.getImageUrl().isEmpty()) {
            Picasso.get().load(restaurant.getImageUrl()).into(holder.imageView);
        } else {
            // Handle empty or null image path
        }



    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textName, textAddress, textPrice;
        RatingBar ratingBar;
        TextView phoneview;
        TextView CategoryView;
        CardView cardView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textName = itemView.findViewById(R.id.text_name);
            textAddress = itemView.findViewById(R.id.text_address);
            textPrice = itemView.findViewById(R.id.text_price);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            phoneview = itemView.findViewById(R.id.phone);
            CategoryView = itemView.findViewById(R.id.category);
            cardView = itemView.findViewById(R.id.card);

        }
    }
    void allerdialogue( Restaurant  restaurant){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add to Favorites");
        builder.setMessage("Do you want to add this item to your favorites?");

// Add positive button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FavoritesManager.addToFavorites(context.getApplicationContext(),restaurant);
            }
        });

// Add negative button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

// Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}

