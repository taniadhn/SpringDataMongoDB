package com.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created By Tania
 *    05/03/2024
 *  In this Configuration, Connection creditional come this configuration from outside
 * **/

@Configuration
public class MongoDBConfiguratıionV1 {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    public String getDatabase() {
        return database;
    }

    @Bean
    public MongoClient getMongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(getConnectionString()))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        return MongoClients.create(mongoClientSettings);
    }


    @Bean
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(getMongoClient(), getDatabase());
    }

    public String getConnectionString(){
           return String.format("", host, port,username,password);
    }

}