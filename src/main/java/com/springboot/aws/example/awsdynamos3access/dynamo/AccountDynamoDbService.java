package com.springboot.aws.example.awsdynamos3access.dynamo;


import java.util.List;

public interface AccountDynamoDbService {

    Account create(Account account);

    void delete(String id);

    List<Account> findAll() throws Exception;

    List<Account> findAllAccounts() throws Exception;

    Account findById(String id);

    Account update(Account todo);
}