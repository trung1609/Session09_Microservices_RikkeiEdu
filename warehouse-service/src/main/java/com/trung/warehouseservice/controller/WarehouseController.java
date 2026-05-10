package com.trung.warehouseservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    @GetMapping("/check-stock/{productId}")
    public ResponseEntity<Boolean> checkStock(@PathVariable Long productId) {
        return ResponseEntity.ok(true);
    }
}
