package com.example.javamaildemo4.dao;

import com.example.javamaildemo4.pojo.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailDAO extends JpaRepository<Mail,Integer> {
    List<Mail> findAllById(int id);
    List<Mail> findAllByIdAndStatus(int id,int status);
    List<Mail> findAllByStatus(int status);
}