package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //Needed for connecting with vue side
@CrossOrigin
public class InvitationController {
    //automapping

    @PostMapping("/createInvitation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createInvitation() {

    }
}
