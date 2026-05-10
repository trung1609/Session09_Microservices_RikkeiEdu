package com.trung.pharmacyservice.controller;

import com.trung.pharmacyservice.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PostMapping("/process-order")
    public String processOrder(@RequestParam Long productId) {
        return pharmacyService.processOrder(productId);
    }

    @PostMapping("/create-invoice")
    public String createInvoice(){
        return pharmacyService.createInvoice();
    }

    @GetMapping("/validate-insurance")
    public CompletableFuture<String> validateInsurance(){
        return pharmacyService.validateInsurance();
    }
}
