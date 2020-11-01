package br.com.udemy.microservices.ui.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam("page") int page,
                           @RequestParam("limit") int limit){
        return "get users with page = " + page + " and limite = " + limit;
    }

    @GetMapping("/{userId}")
    public String getUser(){
        return "get users";

    }

    @PostMapping
    public String createUser(){
        return "create user";

    }

    @PutMapping
    public String updateUser(){
        return "update user";
    }

    @DeleteMapping
    public void deleteUser(){

    }
}
