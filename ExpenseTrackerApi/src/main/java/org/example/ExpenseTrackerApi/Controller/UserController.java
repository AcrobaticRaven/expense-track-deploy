package org.example.ExpenseTrackerApi.Controller;

import jakarta.validation.Valid;
import org.example.ExpenseTrackerApi.Model.SignInInput;
import org.example.ExpenseTrackerApi.Model.SignUpOutput;
import org.example.ExpenseTrackerApi.Model.User;
import org.example.ExpenseTrackerApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("User/signUp")
    public SignUpOutput signUpUser(@Valid @RequestBody User user){
      return userService.signUpUser(user);
    }

    @PostMapping("User/signIn")
    public String signInUser(@Valid @RequestBody SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("User")
    public String signOutUser(@Valid @RequestParam String email){
        return userService.signOutUser(email);
    }
}
