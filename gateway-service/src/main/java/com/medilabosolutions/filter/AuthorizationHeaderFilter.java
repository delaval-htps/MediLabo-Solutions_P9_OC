package com.medilabosolutions.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        // TODO Auto-generated method stub
        return null;
    }

}
