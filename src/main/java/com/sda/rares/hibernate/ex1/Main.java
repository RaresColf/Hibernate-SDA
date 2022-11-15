package com.sda.rares.hibernate.ex1;

import com.sda.rares.hibernate.ex1.model.Account;
import com.sda.rares.hibernate.ex1.model.Department;
import com.sda.rares.hibernate.ex1.model.Employee;
import com.sda.rares.hibernate.ex1.model.Project;
import com.sda.rares.hibernate.ex1.repository.*;
import com.sda.rares.hibernate.ex1.utils.SessionManager;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        SessionManager.getSessionFactory();   // ii fortam o initializare adica o pornire a sessiunii, am folosit-o doar pana cand am creeat repositoryImpl. in Impl avem deja deschiderea sesiunii

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        ProjectRepository projectRepository = new ProjectRepositoryImpl();


        System.out.println(employeeRepository.findById(1));

        Employee employee1 = new Employee(             // prima data am creeat obiectul
               "Jares",
                "Colf",
                new Date(),
                "+440145877889",
                "asdadas@ahasdasda.com",
                4500
        );

        employeeRepository.create(employee1);         // inseram in tabel employee-ul nostru

        Account account1 = new Account(
               "ROBTRL14455660",
               false,
               new Date()
        );
        account1.setEmployee(employee1);
        accountRepository.create(account1);


        Department department1 = new Department("IT");        // am creat departamentul
        departmentRepository.create(department1);         // l-am salvat
        employee1.setDepartment(department1);      // am atribuit departemantul la employee1
        employeeRepository.update(employee1);       // am salvat modificarea in baza de date


        Project project1 = new Project("project1");  // am creat un nou proiect
        projectRepository.create(project1);          //l-am salvat

        Project project2 = new Project("project2");  // am creat inca un nou proiect ca sa putem vedea relatiile de many to many
        projectRepository.create(project2);   // l-am salvat

        Set<Project> projects = new HashSet<>();  // am creat un set de proiecte
        projects.add(project1);
        projects.add(project2);                // am adaugat in set ambele proiecte
        employee1.setProjects(projects);      //luam proiectele si le asociem la employee-ul nostru

        employeeRepository.update(employee1);      // deoarece i-am modificat proiectele la care lucreaza employee1    ii dam update  ca sa se vada modificarea in baza de date


        System.out.println("----------------------------------------------------");

        Employee employee = employeeRepository.findById(1).orElseThrow();  // Am creat variabila employee ca sa simplificam lucrurile, orElseThrow  daca nu a scos nimic din baza de date arunca exceptie
        System.out.println(employee);  // cautam din nou dupa id, si de data asta trebuie sa il vedem deoarece avem inserat o pozitie.

        System.out.println(employee.getAccount());

        System.out.println(employee.getDepartment());

        Department department = departmentRepository.findById(1).orElseThrow();
        System.out.println(department);
        System.out.println(department.getEmployees());

        System.out.println(employee.getProjects());        // vrem sa vedem proiectele la care lucreaza acest employee.


        System.out.println(employeeRepository.findAllWithNameStartingWithJ());

        System.out.println(employeeRepository.findAllWithSalaryGreaterThan(6000));

        System.out.println("-----------------------------------------------------");

        employee1 = employeeRepository.findById(1).orElseThrow();   // imi aduc din baza de date cel mai recent obiect ca sa fiu sigur ca lucrez pe obiectul bun
        employee1.setSalary(5000);                   // apelez seteru de salary si ii maresc salariul
        employeeRepository.update(employee1);        // apelez metoda de update ca sa se faca operatiunea in baza de date
        System.out.println(employeeRepository.findById(1));   // si citesc din nou obiectul ca sa vad ca s-a efectuat operatiunea

        System.out.println(employeeRepository.findAll());


//        employeeRepository.delete(employee1);         // la delete nu mai are sens sa aduc din baza de date cel mai recent obiect.
//        System.out.println(employeeRepository.findById(1));

//     SessionManager.shutDown();
    }


}
