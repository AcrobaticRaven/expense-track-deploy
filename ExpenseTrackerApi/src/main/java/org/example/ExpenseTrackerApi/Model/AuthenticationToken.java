package org.example.ExpenseTrackerApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreatedDate;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User authUser;

    public AuthenticationToken(User foundUser) {
        this.authUser = foundUser;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreatedDate=LocalDateTime.now();
    }
}
