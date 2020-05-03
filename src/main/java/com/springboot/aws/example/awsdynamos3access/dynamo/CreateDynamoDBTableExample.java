package com.springboot.aws.example.awsdynamos3access.dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

public class CreateDynamoDBTableExample {

    public static void main(String[] args) {
        AmazonDynamoDB amazonDynamoDB =
                AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
                        .build();

        createTable(amazonDynamoDB,"table_name");
    }



    public static void createTable( AmazonDynamoDB amazonDynamoDB,String tableName){

        CreateTableRequest createTableRequest =
                new CreateTableRequest()
                .withTableName(tableName)
                .withAttributeDefinitions(
                        new AttributeDefinition().withAttributeName("name").withAttributeType(ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement("name", KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(new Long(5), new Long(5)));


        amazonDynamoDB.createTable(createTableRequest);
    }





}
