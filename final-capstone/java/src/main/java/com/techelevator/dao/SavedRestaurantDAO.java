package com.techelevator.dao;

import com.techelevator.model.SavedRestaurant;

import java.util.List;

/**
 * SavedRestaurantDAO
 */
public interface SavedRestaurantDAO {

    void saveRestaurant(SavedRestaurant savedRestaurant);

    void dislikedRestaurant(SavedRestaurant savedRestaurant);

    List<SavedRestaurant> getHostLikes(int id);
}
