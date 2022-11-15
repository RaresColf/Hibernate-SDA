package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Department;
import com.sda.rares.hibernate.ex1.model.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Optional<Department> findById(Integer id);

    void create(Department department);

    void update(Department department);

    void delete(Department department);

    List<Department> findAll();


}
