package com.techelevator.controller;

import com.techelevator.dao.UserDetailsDAO;
import com.techelevator.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") //Needed for connecting with vue side
@CrossOrigin // to allow us to pull information from different origin
public class UserDetailsController {

    @Autowired
    UserDetailsDAO theUserDetails;

    @GetMapping(path= {"/allUsersDetails", "/"})   // indicates this method with handle HTTP GET requests for the allPuppies
    public List<UserDetails> returnUDData()  {

        logTimestamp("Getting user details for all registered users");  // log time of request
        List<UserDetails> allUsers = theUserDetails.getUDs();  // Get all users' names and email from database

        return allUsers; // return the data requested rather than the view name
    }

    @PostMapping("/newUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUser(@RequestBody UserDetails newUser) {
        theUserDetails.saveUser(newUser);
        logTimestamp("Adding new user!");
    }

    @PutMapping("/editUser/{id}")
    public void editUser(@RequestBody UserDetails userDetails, @PathVariable int id){
        theUserDetails.editUser(userDetails, id);
        logTimestamp("Updating a user");
    }

    static void logTimestamp(String msg) {    // log timestamp of request to Console
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }
}
