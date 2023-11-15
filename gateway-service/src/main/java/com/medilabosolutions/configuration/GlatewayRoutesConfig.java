package com.medilabosolutions.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.medilabosolutions.predicate.AuthorizationHeaderFilter;
import com.medilabosolutions.predicate.AuthorizationHeaderFilter.Config;

@Configuration
public class GlatewayRoutesConfig {

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, AuthorizationHeaderFilter authorizationHeaderFilter) {


                return builder.routes()
                                // route for patient-service
                                .route("patient-service", r -> r.path("/api/v1/patients/**")
                                                .and()
                                                .header("jwtoken", "(.*)")

                                                // no need to change path because patient-service has as path "/patients": need to delete first and second prefix "/api/v1"
                                                .filters(f -> f.stripPrefix(2)
                                                                .filter(authorizationHeaderFilter.apply(new Config()), 1))
                                                .uri("lb://PATIENT-SERVICE"))

                                // route for auth-service
                                .route("authentication", r -> r.method("POST")
                                                .and()
                                                .path("/login")
                                                .uri("lb://AUTH-SERVER"))

                                .build();
        }


}
