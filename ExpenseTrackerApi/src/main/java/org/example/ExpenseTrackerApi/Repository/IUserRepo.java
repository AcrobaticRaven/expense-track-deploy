package org.example.ExpenseTrackerApi.Repository;

import org.example.ExpenseTrackerApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserMail(String userMail);
}
