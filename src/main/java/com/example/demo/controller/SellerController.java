package com.example.demo.controller;

import com.example.demo.entities.Seller;
import com.example.demo.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sellers")
@AllArgsConstructor
public class SellerController {

    private SellerRepository sellerRepository;

    @GetMapping
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }
}
