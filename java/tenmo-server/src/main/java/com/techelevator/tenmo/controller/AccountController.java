package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {
    private AccountDAO accountDAO;
    private UserDAO userDAO;

    public AccountController(AccountDAO accountDAO, UserDAO userDAO){
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/accounts/{id}/balance", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(@PathVariable int id)
    {

        BigDecimal balance = accountDAO.getAccountBalance(id);

        return balance;
    }
}
