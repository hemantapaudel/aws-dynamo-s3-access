package com.springboot.aws.example.awsdynamos3access.controller;


import com.springboot.aws.example.awsdynamos3access.dynamo.Account;
import com.springboot.aws.example.awsdynamos3access.dynamo.AccountDynamoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/dynamodb")
public class DynamoDbController {

    @Autowired
    private AccountDynamoDbService accountDynamoDbService;


    @RequestMapping(value = {"/accounts"}, produces = {"application/json"},
            method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = accountDynamoDbService.findAll();
        return accounts;
    }

    @RequestMapping(value = {"/createaccounts"}, produces = {"application/json"}, method = {RequestMethod.GET})
    public Account createAccount() {
        return accountDynamoDbService.create(null);
    }


    @RequestMapping(value = {"/deleteaccounts"}, produces = {"application/json"}, method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public List<Account> deleteAll() throws Exception {
        List<Account> accountList = accountDynamoDbService.findAll();
        accountList.parallelStream().forEach(account -> accountDynamoDbService.delete(account.getAccountNumber()));
        return accountList;
    }

}
