#  Yelp Api Restaurant- Restaurant Finder App

## Introduction

Welcome to "YOUR_NAME Yelp," a restaurant finder application that allows you to discover and explore various restaurants based on your preferences. This app utilizes the Yelp Fusion API to fetch accurate and up-to-date information about restaurants, including their names, addresses, ratings, reviews, and more.

## Features

1. **Restaurant List**: When you launch the app for the first time, you will see a list of default restaurants. You can choose your preferred restaurant based on your taste and location.

2. **Search Functionality**: The app allows you to search for restaurants by name, location, or cuisine type. Simply enter your query in the search bar, and the app will display relevant results.

3. **Sorting Options**: You can sort the search results based on the following criteria:
   - **Rating**: Restaurants will be ordered from the highest rating to the lowest rating. If rating information is unavailable, the restaurant will be placed at the bottom of the list.
   - **Price**: Restaurants will be ordered from the cheapest to the most expensive. If price information is unavailable, the restaurant will be placed at the top of the list.

4. **Restaurant Details**: Clicking on a restaurant item in the search results will display detailed information about the selected restaurant, including its address, contact details, reviews, and rating.

5. **Add to Favorites**: You can add a restaurant to your favorites list by clicking on the restaurant item and confirming the action in the displayed dialog. The favorite restaurants will be stored in the app's database for persistence across application restarts.

6. **Favorites Section**: To access your favorite restaurants, swipe right on the screen to open the navigation drawer and choose the "Favorites" option. The navigation drawer will also include a header displaying the application's name.

<p align="center">
  <img src="https://github.com/aymendevflutter/yelp_api_restaurant3/assets/132212405/dd0331f1-cd0b-45df-94f8-2f208d4a36e6" alt="res1" width="300" />
  <img src="https://github.com/aymendevflutter/yelp_api_restaurant3/assets/132212405/9e292e8b-547c-4611-9afa-27a2c5b11363" alt="res2" width="300" />
  <img src="https://github.com/aymendevflutter/yelp_api_restaurant3/assets/132212405/286ab14c-1156-4394-b35a-bd31a8471527" alt="res3" width="300" />
</p>


## Getting Started

To run this application on your local machine, follow these steps:

1. Clone the repository from GitHub:
   ```
   git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
   ```

2. Obtain a Yelp Fusion API key by following the instructions on [Yelp Fusion API Documentation](https://www.yelp.com/developers/documentation/v3/get_started).

3. Open the project in Android Studio.

4. Locate the `Constants.java` file in the project and replace the placeholder `YOUR_YELP_API_KEY` with your actual Yelp Fusion API key:
   ```java
   public class Constants {
       public static final String YELP_API_KEY = "YOUR_YELP_API_KEY";
   }
   ```

5. Build and run the application on an Android emulator or physical device.

6. Once the app is launched, you can start exploring restaurants, sorting them based on your preferences, and adding your favorite ones.

## Design and Customization

Feel free to customize the design, color scheme, and font style of the application according to your preferences. You can modify the layout, styles, and assets to create a unique and visually appealing user interface.

## Note

Please ensure that your app supports landscape mode to provide a seamless user experience on different screen orientations.

## Acknowledgments

- This project was made possible by integrating the Yelp Fusion API, which provides comprehensive restaurant data.
- Special thanks to the developers and contributors of the libraries and resources used in this project.

## Feedback and Contributions

If you have any feedback, suggestions, or bug reports, feel free to raise an issue on the GitHub repository. Contributions to the project are also welcome through pull requests.

Happy restaurant hunting with "Restaurant Yelp Api"! 🍔🍕🍣🍝
