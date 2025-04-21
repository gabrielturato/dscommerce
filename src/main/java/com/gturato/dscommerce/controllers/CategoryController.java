package com.gturato.dscommerce.controllers;


import com.gturato.dscommerce.dto.CategoryDTO;
import com.gturato.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
