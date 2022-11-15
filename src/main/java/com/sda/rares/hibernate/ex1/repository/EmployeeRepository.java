package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Integer id);

    void create(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

    List<Employee> findAll();

    List<Employee> findAllWithNameStartingWithJ();

    List<Employee> findAllWithSalaryGreaterThan(Integer salary);


}
