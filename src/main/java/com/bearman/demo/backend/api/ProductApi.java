package com.bearman.demo.backend.api;

import com.bearman.demo.backend.business.ProductBusiness;
import com.bearman.demo.backend.exception.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws ProductException {
        String response = productBusiness.getProductById(id);
        return ResponseEntity.ok(response);
    }
}
