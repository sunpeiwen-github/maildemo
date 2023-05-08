package com.example.javamaildemo4.controller;

import com.example.javamaildemo4.pojo.Mail;
import com.example.javamaildemo4.service.MailService;
import com.example.javamaildemo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.Flags;
import javax.mail.Message;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MailController {
    @Autowired
    MailService mailService;


    @CrossOrigin
    @GetMapping("/api/mails")
    public List<Mail> list() throws Exception {
//        System.out.println("list");
        return mailService.list();
    }
    @CrossOrigin
    @PostMapping("/api/mails")
    public Mail addOrUpdate(@RequestBody Mail mail) throws Exception {
        mailService.addOrUpdate(mail);
        return mail;
    }
    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Mail mail) throws Exception {
        mailService.deleteById(mail.getId());
    }

    @CrossOrigin
    @GetMapping("/api/categories/{status}/mails")
    public List<Mail> listByCategory(@PathVariable("status") int status) throws Exception {

        if (0 != status) {

//            System.out.println(mailService.listByCategory(status));
            return mailService.listByCategory(status);
        } else {
            return list();
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/send")
    public void sendMail(@RequestBody Mail mail) throws Exception {
       System.out.println("send");
        mailService.sendMail(mail);
        //发送后写入数据库
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/inbox")
    public List<Mail> InboxMail(@RequestBody String address) throws Exception {
        System.out.println("receive");

        Mail[] mails=mailService.getInboxMessages(address);

       List<Mail> mailList=new ArrayList<>(Arrays.asList(mails));

//        for (int i = 0; i < mailList.size(); i++) {
//            System.out.println(mailList.get(i));
//        }
        //写入数据库
        mailService.mailsInDatabase(mailList);
       return mailList;

    }
}
