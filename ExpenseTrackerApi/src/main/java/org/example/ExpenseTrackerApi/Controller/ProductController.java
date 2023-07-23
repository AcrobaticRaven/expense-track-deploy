package org.example.ExpenseTrackerApi.Controller;

import org.example.ExpenseTrackerApi.Model.Product;
import org.example.ExpenseTrackerApi.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("Products/Date/{purchaseDate}")
    public List<Product> getProductsByPurchaseDate(@PathVariable LocalDate purchaseDate){
        return productService.findItemsByPurchasedDate(purchaseDate);
    }

    @PostMapping("Product")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("Product/{Id}")
    public String deleteProduct(@PathVariable Integer Id){
        return productService.deleteProduct(Id);
    }

    @GetMapping("Products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("Product/Id/{Id}")
    public Product findAProduct(@PathVariable Integer Id){
        return productService.findAProduct(Id);
    }

    @PutMapping("Product")
    public String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @GetMapping("Product/expense")
    public Double getProductExpense(@RequestParam Integer uId, @RequestParam Month month){
        return productService.getExpenditureOfMonth(uId,month);
    }
}
