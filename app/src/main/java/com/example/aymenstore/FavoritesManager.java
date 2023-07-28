package com.example.aymenstore;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.aymenstore.objet.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {

    private static final String PREF_NAME = "Favorites";
    private static final String KEY_FAVORITES = "favoriteRestaurants";

    public static void addToFavorites(Context context, Restaurant restaurant) {
        List<Restaurant> favorites = getFavorites(context);
        favorites.add(restaurant);
        saveFavorites(context, favorites);
    }

    public static void removeFromFavorites(Context context, Restaurant restaurant) {
        List<Restaurant> favorites = getFavorites(context);
        favorites.remove(restaurant);
        saveFavorites(context, favorites);
    }

    public static List<Restaurant> getFavorites(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String favoritesJson = preferences.getString(KEY_FAVORITES, "");
        if (!TextUtils.isEmpty(favoritesJson)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Restaurant>>() {}.getType();
            return gson.fromJson(favoritesJson, type);
        }
        return new ArrayList<>();
    }

    private static void saveFavorites(Context context, List<Restaurant> favorites) {
        Gson gson = new Gson();
        String favoritesJson = gson.toJson(favorites);
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_FAVORITES, favoritesJson).apply();
    }
}

