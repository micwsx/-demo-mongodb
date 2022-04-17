package com.example.demomongodb.config.documentum;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Michael
 * @date: 4/17/2022 9:36 PM
 */
@Configuration
@ConfigurationProperties(prefix = "india.legal")
public class IndiaLegalDocBase extends DocBaseRepository {
    @Override
    public String identifier() {
        return IdentifierRepository.INDIA_LEGAL.name();
    }
}
