package com.example.demomongodb.config;

import com.example.demomongodb.config.documentum.DocBaseRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Michael
 * @date: 4/17/2022 10:00 PM
 */
@SpringBootTest
class DocumentConfigTest {

    private final Logger logger =Logger.getLogger(getClass());

    @Autowired
    private DocumentConfig documentConfig;

    @Test
    void getRepositoryMap() {
        for (Map.Entry<String, DocBaseRepository> kv : documentConfig.repositoryMap.entrySet()) {
            logger.info(kv.getKey()+":"+kv.getValue());
        }
    }
}