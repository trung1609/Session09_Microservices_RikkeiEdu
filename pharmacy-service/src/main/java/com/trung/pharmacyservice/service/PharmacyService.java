package com.trung.pharmacyservice.service;

import com.trung.pharmacyservice.client.WarehouseClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    @RateLimiter(name = "invoiceRL", fallbackMethod = "invoiceFallback")
    @Retry(name = "invoiceRetry", fallbackMethod = "invoiceFallback")
    public String createInvoice(){
        return "Export invoice successfully";
    }

    public String invoiceFallback(Throwable throwable){
        if (throwable instanceof RequestNotPermitted){
            log.warn("Rate limit exceeded for invoice creation");
            return "Too many requests for invoice creation. Please try again later.";
        }
        log.error("Error after 3 retries for invoice creation", throwable.getMessage());
        return "Invoice Service is currently unavailable. Please try again later.";
    }
}
