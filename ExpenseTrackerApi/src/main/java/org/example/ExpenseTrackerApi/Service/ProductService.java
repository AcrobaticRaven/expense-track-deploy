package org.example.ExpenseTrackerApi.Service;

import org.example.ExpenseTrackerApi.Model.Product;
import org.example.ExpenseTrackerApi.Model.User;
import org.example.ExpenseTrackerApi.Repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    IProductRepo productRepo;

    public List<Product> findItemsByPurchasedDate(LocalDate purchaseDate){
        return productRepo.findByPurchasedDate(purchaseDate);
    }

    public String addProduct(Product product){
        productRepo.save(product);
        return "Product added successfully";
    }

    public String deleteProduct(Integer Id){
        Optional<Product>productOptional = productRepo.findById(Id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            productRepo.delete(product);
            return "Product has been deleted";
        }return "Product not found";
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public Product findAProduct(Integer Id){
        return productRepo.findById(Id).orElse(null);
    }

    public String updateProduct(Product product){
        Optional<Product>productOptional = productRepo.findById(product.getProductId());
        if(productOptional.isPresent()){
            Product product1 = productOptional.get();
            product1.setProductDescription(product.getProductDescription());
            product1.setProductTitle(product.getProductTitle());
            product1.setProductPrice(product.getProductPrice());
            product1.setPurchasedDate(product.getPurchasedDate());
            product1.setPurchasedTime(product.getPurchasedTime());
            product1.setProductUser(product.getProductUser());
            productRepo.save(product1);
            return "Product has been updated";
        }return "Product not found of Id : " + product.getProductId();
    }

    public Double getExpenditureOfMonth(Integer uId, Month month){
        List<Product>desiredProductList = new ArrayList<>();
        Double expense = 0.0;
        for(Product product : productRepo.findAll()){
            User user1 = product.getProductUser();
            Integer user1Id = user1.getUserId();
            Month month1 = product.getPurchasedDate().getMonth();
            if(user1Id.equals(uId) && month1.equals(month)){
                desiredProductList.add(product);
            }
        }
        for(Product product : desiredProductList){
            expense += product.getProductPrice();
        }return expense;
    }
}
