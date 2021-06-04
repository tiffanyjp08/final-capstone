package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.model.Invitation;

import javax.sql.DataSource;

/**
 * JdbcInvitationDAO
 * */

@Component
public class JdbcInvitationDAO implements InvitationDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcInvitationDAO(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public void createInvitation(Invitation invitation) {
        String sql = "INSERT INTO invitation (host_id, zipcode, datetime, restaurant_id, isfinal) " +
                "VALUES (?, ?, ?, ?, );";

        //jdbcTemplate.update(sql, invitation.getHostId(), "43147", invitation.getDeadlineDate(), invitation.)
    }
}
