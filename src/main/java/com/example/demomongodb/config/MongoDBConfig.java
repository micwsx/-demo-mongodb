package com.example.demomongodb.config;

import com.example.demomongodb.config.converter.PropertyValueReadConverter;
import com.example.demomongodb.config.converter.PropertyValueWriteConverter;
import com.example.demomongodb.config.converter.TestDocumentReadConverter;
import com.example.demomongodb.config.converter.TestDocumentWriteConverter;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 3/14/2022 9:36 PM
 * MongoClientFactoryBean或者MongoClients来创建客户端实例
 */
@Configuration
public class MongoDBConfig {

    private final Logger logger = Logger.getLogger(getClass());


    @Value("${spring.data.mongodb.readTimeout:10}")
    private int readTimeout;

    @Value("${spring.data.mongodb.connectTimeout:10}")
    private int connectTimeout;

    @Value("${spring.data.mongodb.uri:}")
    private String mongoDbConnectionString;


    @Value("${spring.data.mongodb.database.dmc}")
    private String dmcDatabase;

    @Value("${spring.data.mongodb.database.cdm}")
    private String cdmDatabase;

    @Value("${spring.data.mongodb.cert}")
    private String cert;

    /**
     * mongodb with certificate
     *
     * @return
     */
    public MongoDatabaseFactory createMongoDbFactory(String dbName) {
        SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = new SimpleMongoClientDatabaseFactory(createMongoClient(), dbName);
        if (simpleMongoClientDatabaseFactory != null) return simpleMongoClientDatabaseFactory;
        return null;
    }

    /**
     * 如果是多个数据库操作支持事务，则使用mongoClient对象手动操作事务，可参考官方示例：
     * https://www.mongodb.com/docs/v4.4/core/transactions-in-applications/
     */
    @Bean
    public MongoClient createMongoClient() {
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        try {
            File certPath = ResourceUtils.getFile(cert);
            SSLContext sslContext = sslContextBuilder.loadTrustMaterial(certPath).build();
            MongoClientSettings mongoClientSettings = MongoClientSettings
                    .builder()
                    .applyConnectionString(new ConnectionString(mongoDbConnectionString))
                    .applyToSslSettings(builder -> builder.context(sslContext).build())
                    .applyToSocketSettings(b->{
                        b.readTimeout(readTimeout, TimeUnit.MINUTES)
                                .connectTimeout(connectTimeout,TimeUnit.MILLISECONDS);
                    })
                    .build();
            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
            return mongoClient;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // transactionManager只能对单数据库事务生效。
    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
        TransactionOptions transactionOptions=TransactionOptions.builder()
                .readPreference(ReadPreference.primary())
                .readConcern(ReadConcern.LOCAL)
                .writeConcern(WriteConcern.MAJORITY)
                .build();
        return new MongoTransactionManager(dbFactory,transactionOptions);
    }

    /******************************Secondary database*********************************/
    @Bean("mongoTemplateDMC")
    public MongoTemplate mongoTemplateDMC(@Qualifier("mongoDatabaseDMCFactory") MongoDatabaseFactory mongoDbFactory,
                                          MongoMappingContext context) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        List<Converter<?,?>> converts=new ArrayList<>();
        converts.add(new PropertyValueReadConverter());
        MongoCustomConversions customConversions=new MongoCustomConversions(converts);
        mappingConverter.setCustomConversions(customConversions);
        // 这里一定要执行afterPropertiesSet()方法，因为这里Bean没有交Spring管理需要手动触发。
        mappingConverter.afterPropertiesSet();
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory, mappingConverter);
//        mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        return mongoTemplate;
    }

    @ConditionalOnProperty(name = "spring.data.mongodb.database.dmc",matchIfMissing = false)
    @Bean("mongoDatabaseDMCFactory")
    public MongoDatabaseFactory mongoDatabaseDMCFactory(){
        return createMongoDbFactory(dmcDatabase);
    }

    /******************************Primary database*********************************/
    @Primary
    @Bean("mongoTemplate")
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
        MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory, mappingMongoConverter);
//        mongoTemplate.setWriteConcern(WriteConcern.MAJORITY);
        return mongoTemplate;
    }

    @Primary
    @Bean("mongoDatabaseCDMFactory")
    public MongoDatabaseFactory mongoDatabaseCDMFactory(){
        return createMongoDbFactory(cdmDatabase);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context,
                                                       MongoCustomConversions customConversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(customConversions);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new TestDocumentReadConverter());
        converterList.add(new TestDocumentWriteConverter());
        converterList.add(new PropertyValueReadConverter());
//        converterList.add(new PropertyValueWriteConverter());
        return new MongoCustomConversions(converterList);
    }



    /**
     * mongodb without certificate
     *
     * @return
     */
//    @Primary
//    @Bean
//    public MongoDatabaseFactory mongoWithoutCert() {
//        try {
//            MongoClientSettings mongoClientSettings = MongoClientSettings
//                    .builder()
//                    .applyConnectionString(new ConnectionString("mongodb://dbAdmin:admin@localhost:27017/test?authsource=admin"))
//                    .applyToSslSettings(builder -> builder.enabled(false).invalidHostNameAllowed(false).build())
//                    .build();
//            MongoClient mongoClient = MongoClients.create(mongoClientSettings);
//            SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, database);
//            return simpleMongoClientDatabaseFactory;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }


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
