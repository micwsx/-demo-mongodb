package com.example.demomongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import javax.net.ssl.SSLContext;
import java.io.File;

/**
 * @author: Michael
 * @date: 3/14/2022 9:36 PM
 * MongoClientFactoryBean或者MongoClients来创建客户端实例
 */
@Configuration
public class MongoDBConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.cert}")
    private String cert;

    /**
     * mongodb with certificate
     *
     * @return
     */
    @Bean
    public MongoDatabaseFactory mongoWithCert() {
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        try {
            File certPath = null;
            try {
                certPath = new File(getClass().getClassLoader().getResource(cert).getPath());
                sslContextBuilder.loadTrustMaterial(certPath);
            } catch (Throwable e) {
                logger.warn("failed to load trustMaterial[" + certPath.getPath() + "].");
            }
            SSLContext sslContext = sslContextBuilder.build();
            MongoClientSettings mongoClientSettings = MongoClientSettings
                    .builder()
//                    .applyConnectionString(new ConnectionString("mongodb://dbAdmin:admin@localhost:27017,172.16.7.2:30000,172.16.7.3:30000/test?authsource=admin"))
                    .applyConnectionString(new ConnectionString("mongodb://localhost:27017/test?authsource=admin"))
                    .applyToSslSettings(builder -> builder.context(sslContext).build())
                    .build();
            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
            SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, database);
            return simpleMongoClientDatabaseFactory;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * mongodb without certificate
     *
     * @return
     */
    @Primary
    @Bean
    public MongoDatabaseFactory mongoWithoutCert() {
        try {
            MongoClientSettings mongoClientSettings = MongoClientSettings
                    .builder()
                    .applyConnectionString(new ConnectionString("mongodb://dbAdmin:admin@localhost:27017/test?authsource=admin"))
                    .applyToSslSettings(builder -> builder.enabled(false).invalidHostNameAllowed(false).build())
                    .build();
            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
            SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, database);
            return simpleMongoClientDatabaseFactory;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    //    @Bean
//    public MongoClientSettings mongoClientSettings(MongoProperties properties) {
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(properties.getUri()))
//                .applyToSslSettings(builder -> builder.context(new SSLContext()).build()).build();
//        return settings;
//    }

//    @Bean
//    public MongoClientFactoryBean mongoWithCert(){
//        MongoClientFactoryBean mongoClientFactoryBean=new MongoClientFactoryBean();
//        SSLContextBuilder sslContextBuilder=new SSLContextBuilder();
//        MongoCredential credential = MongoCredential.createCredential("dbAdmin", "admin", "admin".toCharArray());
//        try {
//            URL certURL = getClass().getClassLoader().getResource("test.cert");
//            File file = new File(certURL.getPath());
//            sslContextBuilder.loadTrustMaterial(file);
//            SSLContext sslContext = sslContextBuilder.build();
//            MongoClientSettings mongoClientSettings = MongoClientSettings
//                    .builder()
////                    .applyConnectionString(new ConnectionString("mongodb://dbAdmin:admin@localhost:27017,172.16.7.2:30000,172.16.7.3:30000/test?authsource=admin"))
//                    .applyToSslSettings(builder -> builder.context(sslContext).build())
//                    .credential(credential)
////                .applyToClusterSettings(builder ->
////                        builder.hosts(Arrays.asList(
////                                new ServerAddress("",27107),
////                                new ServerAddress("",27107)
////                        )).mode(ClusterConnectionMode.SINGLE))
//                    .build();
//            mongoClientFactoryBean.setMongoClientSettings(mongoClientSettings);
//        }  catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        mongoClientFactoryBean.setHost("127.0.0.1");
//        mongoClientFactoryBean.setPort(27017);
////        mongoClientFactoryBean.setReplicaSet();
//        mongoClientFactoryBean.setCredential(new MongoCredential[]{credential});
//        return mongoClientFactoryBean;
//    }


//    @Bean
//    public MongoClientFactoryBean mongoWithoutCert(){
//        MongoClientFactoryBean mongoClientFactoryBean=new MongoClientFactoryBean();
//        MongoCredential credential = MongoCredential.createCredential("dbAdmin", "admin", "admin".toCharArray());
//        MongoClientSettings mongoClientSettings = MongoClientSettings
//                .builder()
//                .applyToSslSettings(builder -> builder.enabled(false).invalidHostNameAllowed(false).build())
//                .credential(credential)
//                .build();
//        mongoClientFactoryBean.setMongoClientSettings(mongoClientSettings);
//        mongoClientFactoryBean.setHost("127.0.0.1");
//        mongoClientFactoryBean.setPort(27017);
//        mongoClientFactoryBean.setCredential(new MongoCredential[]{credential});
//        return mongoClientFactoryBean;
//    }

//    @Bean
//    public MongoClient mongoClient(){
//        MongoCredential credential = MongoCredential.createCredential("", "", "".toCharArray());
//        MongoClientSettings mongoClientSettings = MongoClientSettings
//                .builder()
////                .applyConnectionString(new ConnectionString(properties.getUri()))
////                .applyToSslSettings(builder -> builder.context(new SSLContext()).build())
//                .credential(credential)
//                .applyToClusterSettings(builder ->
//                        builder.hosts(Arrays.asList(
//                                new ServerAddress("",27107),
//                                new ServerAddress("",27107)
//                        )).mode(ClusterConnectionMode.SINGLE))
//                .build();
//        return MongoClients.create(mongoClientSettings);
//    }


}
