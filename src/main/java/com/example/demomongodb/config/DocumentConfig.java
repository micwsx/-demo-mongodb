package com.example.demomongodb.config;

import com.example.demomongodb.config.documentum.DocBaseRepository;
import com.example.demomongodb.config.documentum.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Michael
 * @date: 4/17/2022 9:51 PM
 */
@Configuration
@PropertySource(value = "classpath:dfc.properties")
public class DocumentConfig {

    public Map<String, DocBaseRepository> repositoryMap=new HashMap<>();

    @Autowired
    public void setRepositories(List<Repository> repositories){
        for (Repository repository : repositories) {
            repositoryMap.put(repository.identifier(),(DocBaseRepository)repository);
        }
    }

    public Map<String, DocBaseRepository> getRepositoryMap() {
        return repositoryMap;
    }

    public DocBaseRepository getRepository(String identifier){
        return this.repositoryMap.get(identifier);
    }
}
