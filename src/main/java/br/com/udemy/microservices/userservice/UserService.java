package br.com.udemy.microservices.userservice;

import br.com.udemy.microservices.ui.model.request.UserDetailsRequestModel;
import br.com.udemy.microservices.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
