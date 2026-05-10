package com.trung.pharmacyservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "warehouse-service", url = "http://localhost:8082")
public interface WarehouseClient {
    @GetMapping("/api/v1/warehouses/check-stock/{productId}")
    public Boolean checkStock(@PathVariable Long productId);
}
