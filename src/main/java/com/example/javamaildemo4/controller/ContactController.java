package com.example.javamaildemo4.controller;

import com.example.javamaildemo4.pojo.Contact;
import com.example.javamaildemo4.pojo.Mail;
import com.example.javamaildemo4.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/contact")
    public List<Contact> contactList(@RequestBody String address) throws Exception {
        System.out.println("contact 地址");
        //去除地址后的 等号
        address = URLDecoder.decode(address, "UTF-8");
        address = address.substring(0,address.length()-1);

        return contactService.getContacts(address);
    }
}
