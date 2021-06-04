package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcUserDetailsDAO implements UserDetailsDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDAO(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    //GET all users' first name, last name, email
    @Override
    public List<UserDetails> getUDs(){
        String sql = "SELECT firstname, lastname, emailaddress FROM user_details";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        return mapResultsToUsers(result);
    }

    //POST a new user's first name, last name, email to the user_details table
    @Override
    public void saveUser(UserDetails userToSave) {
        String sql = "INSERT INTO user_details (user_id, firstname, lastname, emailaddress) " +
                "VALUES ( (SELECT user_id FROM users WHERE username = ?), ?, ?, ?)";

        jdbcTemplate.update(sql,userToSave.getUserName(), userToSave.getFirstName(), userToSave.getLastName(),
                userToSave.getEmail());
    }

    //PUT for updating the current user's info
    @Override
    public void editUser(UserDetails userToSave, int userId){
        String sql = "UPDATE user_details SET firstname = ?, lastname = ?, emailaddress = ? " +
                "WHERE user_id = ?";
        jdbcTemplate.update(sql, userToSave.getFirstName(), userToSave.getLastName(),
                userToSave.getEmail(), userToSave.getUserId());
    }

    private UserDetails mapResultsToUD(SqlRowSet result) {
//        int user_id = result.getInt("user_id");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String email = result.getString("emailaddress");
//        UserDetails retrieved = new UserDetails(user_id,firstname, lastname, email);
        UserDetails retrieved = new UserDetails();
//        retrieved.setUserId(user_id);
        retrieved.setFirstName(firstname);
        retrieved.setLastName(lastname);
        retrieved.setEmail(email);

        return retrieved;
    }

    private List<UserDetails> mapResultsToUsers(SqlRowSet results) {

        List<UserDetails> retrievedUDs = new ArrayList<>();
        while(results.next()) {
            retrievedUDs.add(mapResultsToUD(results));
        }

        return retrievedUDs;
    }

}
