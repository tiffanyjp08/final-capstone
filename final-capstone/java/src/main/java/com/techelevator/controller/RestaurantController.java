package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.model.Restaurant;
import com.techelevator.svc.RestaurantSearchSVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {

    @Autowired
    RestaurantSearchSVC searchSVC;

    @RequestMapping(path="/search", method= RequestMethod.GET)
    public List<Restaurant> search(@RequestParam String location) throws JsonProcessingException {

        return searchSVC.getSearchRestaurant(location);

    }




}
