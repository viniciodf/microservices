package br.com.udemy.microservices.ui.controllers;

import br.com.udemy.microservices.ui.model.request.UserDetailsRequestModel;
import br.com.udemy.microservices.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc") String sort){
        return "get users with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(value = "/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
        UserRest userRest = new UserRest();
        userRest.setEmail("teste@gmail.com");
        userRest.setFirstName("Vinicio");
        userRest.setLastName("Ferreira");
        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel){
        UserRest userRest = new UserRest();
        userRest.setEmail(userDetailsRequestModel.getEmail());
        userRest.setFirstName(userDetailsRequestModel.getFirstName());
        userRest.setLastName(userDetailsRequestModel.getLastName());
        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser(){
        return "update user";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user";

    }
}
