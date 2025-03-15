package com.zeeshan.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String customerName;
    private String customerEmail;
    private String password;
    private Long contact;

    public Integer getCid() {
        return cid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Customer() {
    }

    public Customer(Integer cid, String customerName, String customerEmail, String password, Long contact) {
        this.cid = cid;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.password = password;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                '}';
    }
}
