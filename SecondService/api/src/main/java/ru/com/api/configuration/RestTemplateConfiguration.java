package ru.com.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration(value = "IntegrationRestTemplateConfiguration")
public class RestTemplateConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }

    @Bean
    @Qualifier("serviceErrorHandler")
    @ConditionalOnMissingBean(name = "serviceErrorHandler")
    public Exception serviceErrorHandler(ObjectMapper objectMapper) {
        return new Exception(String.valueOf(objectMapper));
    }

}
