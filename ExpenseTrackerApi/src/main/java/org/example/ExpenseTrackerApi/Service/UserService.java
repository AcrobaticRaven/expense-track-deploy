package org.example.ExpenseTrackerApi.Service;

import org.example.ExpenseTrackerApi.Model.*;
import org.example.ExpenseTrackerApi.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUpUser(User user){
        String signUpStatusMessage = null;
        boolean signUpStatus = false;

        User foundUser = userRepo.findFirstByUserMail(user.getUserMail());
        if(foundUser != null){
            signUpStatusMessage="email already registered";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            signUpStatusMessage = "Signed Up successfully";
            signUpStatus = true;
            return new SignUpOutput(signUpStatusMessage, signUpStatus);
        }catch(Exception e){
            signUpStatusMessage = "Internal error during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }
    }

    public String signInUser(SignInInput signInInput){
        String signInStatusMessage = null;
        User foundUser = userRepo.findFirstByUserMail(signInInput.getMail());
        if(foundUser == null){
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            String connectedPassword = foundUser.getUserPassword();
            if (connectedPassword.equals(encryptedPassword)) {
                AuthenticationToken authToken = new AuthenticationToken(foundUser);
                authenticationService.saveAuth(authToken);
                return authToken.toString();
            } else {
                signInStatusMessage = "Invalid credentials";
                return signInStatusMessage;
            }
        }catch(Exception e){
            signInStatusMessage = "Error occured during signIn";
            return signInStatusMessage;
        }
    }

    public String signOutUser(String email){
        User connectedUser = userRepo.findFirstByUserMail(email);
        AuthenticationToken authToken = authenticationService.findAuthByUser(connectedUser);
            authenticationService.removeAuth(authToken);
            return "Signed out successfully";

    }
}
