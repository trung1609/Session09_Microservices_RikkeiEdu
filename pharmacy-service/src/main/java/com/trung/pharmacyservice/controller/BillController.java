package com.trung.pharmacyservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bills")
@RefreshScope
public class BillController {
    @Value("${pharmacy.vat-rate}")
    private double vatRate;

    @PostMapping
    public ResponseEntity<String> solveBill(@RequestParam double amount) {
        double total = amount + (amount * vatRate);
        return ResponseEntity.ok("Total amount with VAT: " + total);
    }
}
