package com.example.javamaildemo4.dao;

import com.example.javamaildemo4.pojo.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MailDAO extends JpaRepository<Mail,Integer> {
    List<Mail> findAllById(int id);
    List<Mail> findAllByIdAndStatus(int id,int status);
    List<Mail> findAllByStatus(int status);

    @Modifying
    @Transactional
    @Query("DELETE FROM Mail")
    void deleteAllMails();




}