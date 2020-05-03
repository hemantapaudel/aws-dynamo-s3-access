package com.springboot.aws.example.awsdynamos3access.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountDynamoDbServiceImpl implements AccountDynamoDbService {


    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    /**
     * * using Low label APIs
     * Map<String, AttributeValue> itemMap= new HashMap<>();
     * itemMap.put("accountNumber", new  AttributeValue().withS(account.getAccountNumber()) );
     * itemMap.put("accountType", new  AttributeValue().withS(account.getAccountType()) );
     * itemMap.put("balanceAmount", new  AttributeValue().withN(String.valueOf(account.getBalanceAmount())));
     * PutItemRequest request = new PutItemRequest("Account",itemMap);
     * this.amazonDynamoDB.putItem(request);
     *
     * @param account
     * @return
     */
    @Override
    public Account create(Account account) {
        for (int i = 100; i < 200; i++) {
            account = new Account();
            account.setAccountNumber("123" + i + "123" + i);
            account.setAccountType("saving");
            account.setBalanceAmount(240);
            dynamoDBMapper.save(account);
        }

        return account;
    }

    @Override
    public void delete(String id) {
        Account account = new Account();
        account.setAccountNumber(id);
        dynamoDBMapper.delete(account);
    }

    @Override
    public List<Account> findAll() throws Exception {
        return dynamoDBMapper.scan(Account.class, new DynamoDBScanExpression());
    }

    @Override
    public List<Account> findAllAccounts() throws Exception {
        return dynamoDBMapper.scan(Account.class, new DynamoDBScanExpression());
    }

    @Override
    public Account findById(String id) {
        return dynamoDBMapper.load(Account.class, id);
    }

    @Override
    public Account update(Account account) {
        dynamoDBMapper.save(account);
        return account;
    }

}