package com.example.demo.service;

import com.example.demo.entities.Product;
import com.example.demo.entities.Seller;
import com.example.demo.exception.BaseException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private SellerRepository sellerRepository;

    public List<Product> searchAll(Optional<String> name, Optional<Integer> size, Optional<String> sku,
            Optional<String> color, Optional<Long> sellerId) throws BaseException {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Product exampleProduct = new Product();
        exampleProduct.setColor(color.orElse(null));
        exampleProduct.setName(name.orElse(null));
        exampleProduct.setSku(sku.orElse(null));
        exampleProduct.setSize(size.orElse(null));
        if(sellerId.isPresent()) {
            final Optional<Seller> sellerOptional = sellerRepository.findById(sellerId.get());
            if(sellerOptional.isPresent()) {
                exampleProduct.setSeller(sellerOptional.get());
            } else {
                throw new BaseException("Seller not found");
            }
        }
        return productRepository.findAll(Example.of(exampleProduct, matcher));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
