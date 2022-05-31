package fr.tobby.socrud.controller;

import fr.tobby.socrud.model.request.CreateAccountRequest;
import fr.tobby.socrud.model.request.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    @PostMapping(path = "login")
    public void login(@RequestBody LoginRequest loginRequest){

    }

    @PostMapping(path = "create")
    public void createAccount(@RequestBody CreateAccountRequest createAccountRequest){

    }
}
