package com.example.javamaildemo4.service;

import com.example.javamaildemo4.dao.ContactDAO;
import com.example.javamaildemo4.dao.MailDAO;
import com.example.javamaildemo4.dao.UserDAO;
import com.example.javamaildemo4.pojo.Contact;
import com.example.javamaildemo4.pojo.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactDAO contactDAO;
    public List<Contact> getContacts(String address){
        return contactDAO.findByHostAddress( address);
    }

}