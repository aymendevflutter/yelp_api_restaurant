package com.example.aymenstore.objet;

public class Restaurant {
    private String name;
    private double rating;
    private String price;
    private String categoryType;


    private String phoneNumber;
    private String address;
    private String imageUrl;
    public Restaurant(String name, double rating, String price, String categoryType,String phoneNumber,String address,String imageUrl) {
        this.name = name;
        this.rating = rating;
        this.categoryType = categoryType;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
    }


    public String getImageUrl() {
        return imageUrl;
    }



    public String getCategoryType() {
        return categoryType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }






    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }
}

