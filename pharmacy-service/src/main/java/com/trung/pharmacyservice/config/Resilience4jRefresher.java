package com.trung.pharmacyservice.config;

import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Resilience4jRefresher {
    private final TimeLimiterRegistry timeLimiterRegistry;

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh(RefreshScopeRefreshedEvent event){
        timeLimiterRegistry.remove("checkInsurance");
        log.info("TimeLimiter 'checkInsurance' removed from registry after configuration refresh");
    }
}
