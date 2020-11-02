package br.com.udemy.microservices.userservice.impl;

import br.com.udemy.microservices.shared.Utils;
import br.com.udemy.microservices.ui.model.request.UserDetailsRequestModel;
import br.com.udemy.microservices.ui.model.response.UserRest;
import br.com.udemy.microservices.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;

    @Autowired
    Utils utils;

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetailsRequestModel.getEmail());
        returnValue.setFirstName(userDetailsRequestModel.getFirstName());
        returnValue.setLastName(userDetailsRequestModel.getLastName());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        if(users == null) users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;
    }
}
