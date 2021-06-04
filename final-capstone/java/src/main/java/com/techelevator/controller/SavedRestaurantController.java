package com.techelevator.controller;

import com.techelevator.dao.SavedRestaurantDAO;
import com.techelevator.model.SavedRestaurant;
import com.techelevator.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * SavedRestaurantController
 */

@RestController
@RequestMapping("/api") //Needed for connecting with vue side
@CrossOrigin // to allow us to pull information from different origin
public class SavedRestaurantController {

    @Autowired
    SavedRestaurantDAO theSavedRestaurant;

    //POST when the HOST is LIKED a restaurant
    @PostMapping("/hostLikedRestaurant")
    public void hostLikedRestaurant (@RequestBody SavedRestaurant aSavedRestaurant) {
        theSavedRestaurant.saveRestaurant(aSavedRestaurant);
        logTimestamp("Add Host's Liked Selection");
    }

    //POST when the HOST is DISLIKED a restaurant
    @PostMapping("/hostDislikedRestaurant")
    public void hostDislikedRestaurant (@RequestBody SavedRestaurant aSavedRestaurant) {
        theSavedRestaurant.saveRestaurant(aSavedRestaurant);
        logTimestamp("Add Host's Disliked Selection");
    }

    //GET restaurants LIKED by HOST
    @GetMapping("/hostLikes/{id}")
    public List<SavedRestaurant> hostLikesByHostId(@PathVariable int id) {
        logTimestamp("Returning host's likes " + id);

        List<SavedRestaurant> allHostLikesById = theSavedRestaurant.getHostLikes(id);

        return allHostLikesById;
    }

    static void logTimestamp(String msg) {    // log timestamp of request to Console
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }
}
