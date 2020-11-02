package br.com.udemy.microservices.ui.controllers;

import br.com.udemy.microservices.exceptions.UserServiceException;
import br.com.udemy.microservices.shared.Utils;
import br.com.udemy.microservices.ui.model.request.UpdateUserDetailsRequestModel;
import br.com.udemy.microservices.ui.model.request.UserDetailsRequestModel;
import br.com.udemy.microservices.ui.model.response.UserRest;
import br.com.udemy.microservices.userservice.UserService;
import br.com.udemy.microservices.userservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

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

        if(users.containsKey(userId)){
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel){

        UserRest returnValue = userService.createUser(userDetailsRequestModel);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest updateUser(@PathVariable String userId,
                             @RequestBody UpdateUserDetailsRequestModel userDetails){
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        users.remove(id);
        return ResponseEntity.noContent().build();

    }
}
