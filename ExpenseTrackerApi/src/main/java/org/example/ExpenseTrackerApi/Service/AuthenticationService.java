package org.example.ExpenseTrackerApi.Service;

import org.example.ExpenseTrackerApi.Model.AuthenticationToken;
import org.example.ExpenseTrackerApi.Model.User;
import org.example.ExpenseTrackerApi.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void saveAuth(AuthenticationToken authToken){
        authenticationRepo.save(authToken);
    }

    public AuthenticationToken findAuthByUser(User user){
      return authenticationRepo.findFirstByAuthUser(user);
    }
    public void removeAuth(AuthenticationToken authToken){
        authenticationRepo.delete(authToken);
    }
}
