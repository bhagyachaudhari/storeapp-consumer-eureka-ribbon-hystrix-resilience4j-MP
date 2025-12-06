package com.example.storeapp_consumer_eureka_ribbon.controller;

import com.example.storeapp_consumer_eureka_ribbon.model.Product;
import com.example.storeapp_consumer_eureka_ribbon.service.ProductService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Scope("request")
public class ProductClientController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Timed(value = "getProductById.time", description = "Time taken to return Product")
    @GetMapping("/get-products/{id}")
    public Product getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);
        return product;
    }
}
