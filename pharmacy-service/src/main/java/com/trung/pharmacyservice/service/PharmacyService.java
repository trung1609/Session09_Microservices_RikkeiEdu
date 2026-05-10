package com.trung.pharmacyservice.service;

import com.trung.pharmacyservice.client.WarehouseClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyService {
    private final WarehouseClient warehouseClient;

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkStockFallback")
    public String processOrder(Long productId) {
        Boolean hasStock = warehouseClient.checkStock(productId);
        return hasStock ? "Product is available in warehouse" : "Product is not available in warehouse";
    }

    public String checkStockFallback(Long productId, Throwable throwable){
        log.error("Error occurred while checking stock for product ID: " + productId, throwable);
        return "Warehouse Server response is delayed or failed. Please try again later for product ID: " + productId;
    }
}
