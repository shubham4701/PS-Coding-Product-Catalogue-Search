package com.example.demo.controller;

import com.example.demo.entities.Product;
import com.example.demo.entities.Seller;
import com.example.demo.exception.BaseException;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private ProductService productService;

    @GetMapping("/search")
    public List<Product> searchAllProducts(@RequestParam Optional<String> name, @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> sku, @RequestParam Optional<String> color, @RequestParam Optional<Long> sellerId)
            throws BaseException {
        log.info("name={}, size={}, sku={}, color={}, sellerid={}", name, size, sku, color, sellerId);
        return productService.searchAll(name, size, sku, color, sellerId);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

}
