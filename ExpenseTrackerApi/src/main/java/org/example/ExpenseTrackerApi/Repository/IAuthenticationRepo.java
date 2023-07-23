package org.example.ExpenseTrackerApi.Repository;

import org.example.ExpenseTrackerApi.Model.AuthenticationToken;
import org.example.ExpenseTrackerApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByAuthUser(User user);
}
