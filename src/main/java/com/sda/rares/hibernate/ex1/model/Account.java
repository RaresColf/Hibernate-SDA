package com.sda.rares.hibernate.ex1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "is_deactivated")
    private Boolean isDeactivated;
    @Column(name = "creation_date")
    private Date creationDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Account(){

    }

    public Account(String accountNumber, Boolean isDeactivated, Date creationDate) {
        this.accountNumber = accountNumber;
        this.isDeactivated = isDeactivated;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getDeactivated() {
        return isDeactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        isDeactivated = deactivated;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", isDeactivated=" + isDeactivated +
                ", creationDate=" + creationDate +
                '}';
    }
}
