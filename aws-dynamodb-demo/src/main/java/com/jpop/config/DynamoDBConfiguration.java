package com.jpop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${amazon.aws.accesskey}")
    private String dynamodbAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String dynamodbSecretKey;


    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                   new AwsClientBuilder.EndpointConfiguration("dynamodb.ap-south-1.amazonaws.com","ap-south-1"))
                .withCredentials(new AWSStaticCredentialsProvider(
                   new BasicAWSCredentials("AKIAXJIFHDPVJJ5VK3JP","F3yxMMGV/KB2AMAByAT/3yNBVGMi1HJRh3dNdqF0")))
                .build();
    }
}
