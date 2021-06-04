package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Invitation
 * */

public class Invitation {

    private long inviteId;
    private long hostId;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private String invitee_firstName;
    private String invitee_lastName;
    private String invitee_email;
    private Boolean isChoosing;

    public long getInviteId() {
        return inviteId;
    }

    public void setInviteId(long inviteId) {
        this.inviteId = inviteId;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getInvitee_firstName() {
        return invitee_firstName;
    }

    public void setInvitee_firstName(String invitee_firstName) {
        this.invitee_firstName = invitee_firstName;
    }

    public String getInvitee_lastName() {
        return invitee_lastName;
    }

    public void setInvitee_lastName(String invitee_lastName) {
        this.invitee_lastName = invitee_lastName;
    }

    public String getInvitee_email() {
        return invitee_email;
    }

    public void setInvitee_email(String invitee_email) {
        this.invitee_email = invitee_email;
    }

    public Boolean getChoosing() {
        return isChoosing;
    }

    public void setChoosing(Boolean choosing) {
        isChoosing = choosing;
    }
}