package com.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;

/**
 * Created By Tania Dehghan
 *       05/03/2024
 *  In this configuration,Connection creditional defined inside our file
 * **/

@Configuration
public class MongoDBConfigurationV2 {


    ConnectionString connectionString = new ConnectionString("mongodb://admin:2583**@localhost:27017/mongo");

    @Bean
    public MongoClient mongoClients(){
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build()).build();

        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate(){
          return new MongoTemplate(mongoClients(), Objects.requireNonNull(connectionString.getDatabase()));
    }



}
