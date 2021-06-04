package com.techelevator.dao;

import com.techelevator.model.SavedRestaurant;
import com.techelevator.model.UserDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcSavedRestaurantDAO
 * */

@Component
public class JdbcSavedRestaurantDAO implements SavedRestaurantDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcSavedRestaurantDAO(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource);}


    @Override
    public void saveRestaurant(SavedRestaurant savedRestaurant) {

        //POST request to take the restaurant object to TABLE: restaurant_display
        String sql = "INSERT INTO restaurant_display (id, name, location, displayaddress, category, image, displayphone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, savedRestaurant.getId(), savedRestaurant.getName(), savedRestaurant.getLocation(),
                    savedRestaurant.getDisplayAddress(), savedRestaurant.getCategory(), savedRestaurant.getImage(),
                    savedRestaurant.getDisplayPhone());

        //THEN

        //POST request to take in the current user.id, the restaurant_id, and isLike is TRUE
        sql = "INSERT INTO restaurant_user (host_id, restaurant_id, isLiked)VALUES (?, ?, true)";

        jdbcTemplate.update(sql, savedRestaurant.getHostId(), savedRestaurant.getId());

        //THEN

        //POST LIKED restaurant to the restaurant_likes table



    }

    @Override
    public void dislikedRestaurant(SavedRestaurant savedRestaurant) {

        //POST request to take the restaurant object to TABLE: restaurant_display
        String sql = "INSERT INTO restaurant_display (id, name, location, displayaddress, category, image, displayphone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, savedRestaurant.getId(), savedRestaurant.getName(), savedRestaurant.getLocation(),
                savedRestaurant.getDisplayAddress(), savedRestaurant.getCategory(), savedRestaurant.getImage(),
                savedRestaurant.getDisplayPhone());

        //THEN

        //POST request to take in the current user.id, the restaurant_id, and isLike is FALSE

        sql = "INSERT INTO restaurant_user (host_id, restaurant_id, isLiked)VALUES (?, ?, false)";

        jdbcTemplate.update(sql, savedRestaurant.getHostId(), savedRestaurant.getId());

        //THEN

        //POST LIKED restaurant to the restaurant_likes table
        sql = "INSERT INTO restaurants_likes (invite_id, restaurant_id) VALUES " +
                "((SELECT invite_id FROM invitation WHERE host_id = ? AND zipcode = ? AND isfinal = false), ?)";

        jdbcTemplate.update(sql, savedRestaurant.getHostId(), savedRestaurant.getZipcode(), savedRestaurant.getId());

    }







    //GET host's likes for guests to select
    @Override
    public List<SavedRestaurant> getHostLikes(int id) {
        String sql = "SELECT rd.id, rd.name, rd.location, rd.displayaddress, rd.category, rd.image, rd.displayphone " +
                "FROM restaurant_display rd JOIN restaurant_user ru ON ru.restaurant_id = rd.id " +
                "WHERE ru.host_id = ? AND ru.isliked = true";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        return mapResultsToHostLikes(result);

    }


    private SavedRestaurant mapResultsToHostLike(SqlRowSet result) {
        //rd.id
        int id = result.getInt("id");
        //rd.name
        String name = result.getString("name");
        //rd.location
        String location = result.getString("location");
        //rd.displayaddress
        String displayAddress = result.getString("displayaddress");
        //rd.category
        String category = result.getString("category");
        //rd.image
        String image = result.getString("image");
        //rd.displayphone
        String displayPhone = result.getString("displayphone");

        SavedRestaurant retrieved = new SavedRestaurant();

        retrieved.setId(id);
        retrieved.setName(name);
        retrieved.setLocation(location);
        retrieved.setDisplayAddress(displayAddress);
        retrieved.setCategory(category);
        retrieved.setImage(image);
        retrieved.setDisplayPhone(displayPhone);

        return retrieved;
    }

    private List<SavedRestaurant> mapResultsToHostLikes(SqlRowSet results) {

        List<SavedRestaurant> retrievedGetHostLikes = new ArrayList<>();
        while(results.next()) {
            retrievedGetHostLikes.add(mapResultsToHostLike(results));
        }

        return retrievedGetHostLikes;
    }


}
