package com.sda.rares.hibernate.ex1.repository;

import com.sda.rares.hibernate.ex1.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(Integer id);

    void create(Project Project);

    void update(Project Project);

    void delete(Project Project);

    List<Project> findAll();


}
