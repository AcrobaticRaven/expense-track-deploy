package org.example.ExpenseTrackerApi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productTitle;
    private String productDescription;
    private Double productPrice;
    private LocalDate purchasedDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime purchasedTime;

    @ManyToOne
    @JoinColumn(name = "fk_user-id")
    User productUser;

    public Product(LocalDate passedDate) {
        this.purchasedDate = LocalDate.of(passedDate.getYear(), passedDate.getMonth(), passedDate.getDayOfMonth());
    }
}
