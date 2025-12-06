package com.example.storeapp_consumer_eureka_ribbon.service;

import com.example.storeapp_consumer_eureka_ribbon.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(value = "productService")
@Scope("singleton")
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Retry(name="product-service")
    @CircuitBreaker(name="product-service#getProductById", fallbackMethod = "fallbackGetProductById")
    public Product getProductById(Long id) {
        Product product = restTemplate.getForObject("http://product-service/products" + id, Product.class);
        return product;
    }

    public Product fallbackGetProductById(Long id, Throwable cause) {
        System.out.println(cause.getMessage());
        Product product = new Product();
        return product;
    }
}
