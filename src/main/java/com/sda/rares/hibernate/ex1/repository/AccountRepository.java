package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Account;
import com.sda.rares.hibernate.ex1.model.Employee;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Integer id);

    void create(Account account);

    void update(Account account);

    void delete(Account account);

    List<Account> findAll();


}
