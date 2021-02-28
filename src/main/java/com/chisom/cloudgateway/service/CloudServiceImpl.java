package com.chisom.cloudgateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CloudServiceImpl{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    private final String cloudGatewayHost;

    public CloudServiceImpl(
            RestTemplate restTemplate,
            @Value("${cloud-gateway-host}") String cloudGatewayHost
    ) {
        this.restTemplate = restTemplate;
        this.cloudGatewayHost = cloudGatewayHost;
    }

    /**
     * ping url every 30min to keep alive
     */
    @Scheduled(cron = "*/2 * * * *")
    public void health() {
        try {
            restTemplate.getForObject(cloudGatewayHost, Object.class);
        } catch (Exception e) {
            log.error("caught an exception :::", e);
        }
    }
}
