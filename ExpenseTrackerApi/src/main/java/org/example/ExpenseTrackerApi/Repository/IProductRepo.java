package org.example.ExpenseTrackerApi.Repository;

import org.example.ExpenseTrackerApi.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findByPurchasedDate(LocalDate purchaseDate);
}
