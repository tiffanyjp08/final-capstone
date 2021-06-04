package com.techelevator.dao;

import com.techelevator.model.UserDetails;

import java.util.List;

/**
 * UserDetailsDAO
 * */

public interface UserDetailsDAO {

    List<UserDetails> getUDs();

    void saveUser(UserDetails userToSave);

    void editUser(UserDetails userToSave, int userId);
}
