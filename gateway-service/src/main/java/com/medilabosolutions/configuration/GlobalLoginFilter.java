package com.medilabosolutions.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalLoginFilter extends AbstractGatewayFilterFactory<GlobalLoginFilter.Config> {

    public static final Logger logger = LogManager.getLogger(GlobalLoginFilter.class);


    public GlobalLoginFilter() {
        super(Config.class);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {
        private Mono<java.security.Principal> principal;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(
                (exchange, chain) -> {
                   
                    logger.info("CA MARCHE ----------------"+ exchange.getPrincipal().subscribe());
                    config.setPrincipal( exchange.getPrincipal());
                   
                    return chain.filter(exchange);
                }, 0);

    }


}
