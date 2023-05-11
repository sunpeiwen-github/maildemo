package com.example.javamaildemo4.dao;

import com.example.javamaildemo4.pojo.Contact;
import com.example.javamaildemo4.pojo.Mail;
import com.example.javamaildemo4.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactDAO extends JpaRepository<Contact,Integer> {
    List<Contact> findByHostAddress(String address);
}