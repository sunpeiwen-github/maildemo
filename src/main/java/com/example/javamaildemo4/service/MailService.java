package com.example.javamaildemo4.service;

import com.example.javamaildemo4.dao.MailDAO;
import com.example.javamaildemo4.dao.UserDAO;
import com.example.javamaildemo4.pojo.Mail;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    MailDAO mailDAO;
    @Autowired
    UserDAO userDAO;

    public List<Mail> list() {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        return mailDAO.findAll(sort);
        return mailDAO.findAll();
    }

    public void addOrUpdate(Mail mail) {
        mailDAO.save(mail);
    }

    public void deleteById(int id) {
        mailDAO.deleteById(id);
    }

    public List<Mail> listByCategory(int status) {
        return mailDAO.findAllByStatus(status);
    }

    public void sendMail(Mail mail) throws Exception {
        String from = mail.getFromAddress();
        String to = mail.getRecipientAddress();
        String subject = mail.getSubject();
        String body = mail.getContent();
        int atIndex = from.indexOf('@');
        String domain = "";
        if (atIndex != -1) {
            domain = from.substring(atIndex + 1);
            System.out.println(domain);

        }
        String smtpHost = "smtp." + domain;
//        System.out.println(smtpHost);
        System.out.println(mail);

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", smtpHost); // 发件人的邮箱的 SMTP服务器地址
        props.setProperty("mail.smtp.auth", "true"); // 请求认证，参数名称与具体实现有关

        // 创建Session实例对象
        Session session = Session.getDefaultInstance(props);
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置收件人
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        // 设置发送日期
        message.setSentDate(new Date());
        // 设置邮件主题
        message.setSubject(subject);
        // 设置纯文本内容的邮件正文
        message.setText(body);
        // 保存并生成最终的邮件内容
        message.saveChanges();
        // 设置为debug模式, 可以查看详细的发送 log
//            session.setDebug(true);
        session.setDebug(false);
        // 获取Transport对象
        Transport transport = session.getTransport("smtp");
        // 第2个参数需要填写的是QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
        transport.connect(from, userDAO.getPasswordByAddress(from));
        // 发送，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public List<Mail> getInboxMessages(String address) throws Exception {

        String password = userDAO.getPasswordByAddress(address); // 邮箱的密码 授权码

        int atIndex = address.indexOf('@');
        String domain = "";
        if (atIndex != -1) {
            domain = address.substring(atIndex + 1);
//            System.out.println(domain);
        }
        String imapHost = "imap." + domain;
        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", imapHost);
        prop.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(prop);

        // 使用imap会话机制，连接服务器
        int total = 0;
        IMAPStore store = (IMAPStore) session.getStore("imap");
        store.connect(address, password);

        List<Mail> mails = getMailsFromServer(store);


//        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
//        folder.open(Folder.READ_WRITE);
        // 获取总邮件数
//        total = folder.getMessageCount();
//        System.out.println("-----------------共有邮件：" + total + " 封--------------");

        // 得到收件箱文件夹信息，获取邮件列表
//        System.out.println("未读邮件数：" + folder.getUnreadMessageCount());
//        System.out.println("已删除邮件数"+folder.getDeletedMessageCount());
//        System.out.println("新邮件数"+folder.getNewMessageCount());
//        Message[] messages = folder.getMessages();
//
//
//
//        Mail[] mails=new Mail[messages.length];
//        for (int i = 0; i < messages.length; i++) {
//            mails[i]=new Mail();
//            mails[i].setId(i+1);
////获得from地址
//            String from=((InternetAddress)messages[i].getFrom()[0]).getAddress();
//            System.out.println(from);
//            mails[i].setFromAddress(from);
//            mails[i].setFromName(from);
////获得收件人
//            String recipient=((InternetAddress)messages[i].getAllRecipients()[0]).getAddress();
//            mails[i].setRecipientAddress(recipient);
//            mails[i].setRecipientName(recipient);
//            mails[i].setContent(messages[i].getContent().toString());
//            mails[i].setSubject(messages[i].getSubject());
//            mails[i].setSendtime(messages[i].getSentDate());
//            Flags flags = messages[i].getFlags();
//            int status=0;
//            if (flags.contains(Flags.Flag.SEEN)) {
////                System.out.println("这是一封已读邮件");
//                status=1;
//            } else {
////                System.out.println("这是未读邮件");
//                status=0;
//            }
//            mails[i].setStatus(status);
//            mails[i].setMailsize(messages[i].getSize());
//        }
//        int messageNumber = 0;
//        for (Message message : messages) {
//            System.out.println("第"+(messageNumber+1)+"封");
//            System.out.println("发送时间：" + message.getSentDate());
//            System.out.println("主题：" + message.getSubject());
//            System.out.println("内容：" + message.getContent());
//            Flags flags = message.getFlags();
//
//            if (flags.contains(Flags.Flag.SEEN)) {
//                System.out.println("这是一封已读邮件");
//            } else {
//                System.out.println("这是未读邮件");
//            }
//
//            //每封邮件都有一个MessageNumber，可以通过邮件的MessageNumber在收件箱里面取得该邮件
//            messageNumber = message.getMessageNumber();
//        }
//        Message message = folder.getMessage(messageNumber);
//        System.out.println(message.getContent() + message.getContentType());
//        // 释放资源
//        if (folder != null) {
//            folder.close(true);
//        }

        if (store != null) {
            store.close();
        }
        return mails;
    }

    public List<Mail> getMailsFromServer(Store store) throws Exception {
        List<Mail> inbox = getMailsByBox(store, "Inbox");
        List<Mail> sent = getMailsByBox(store, "Sent");
        List<Mail> trash = getMailsByBox(store, "Trash");
        List<Mail> drafts = getMailsByBox(store, "Drafts");


        List<Mail> boxList = new ArrayList<>();
        boxList.addAll(inbox);
        boxList.addAll(sent);
        boxList.addAll(trash);
        boxList.addAll(drafts);

        return boxList;
    }

    public List<Mail> getMailsByBox(Store store, String boxName) throws Exception {


        System.out.println(boxName);
        IMAPFolder folder = (IMAPFolder) store.getFolder(boxName); // 收件箱
        folder.open(Folder.READ_WRITE);
        Message[] messages = folder.getMessages();

        //创建链表List mails
        Mail[] mails = new Mail[messages.length];
        for (int i = 0; i < messages.length; i++) {
            mails[i] = new Mail();
            mails[i].setId(messages[i].getMessageNumber());
            //虽然这里设置了 但是写入数据库中id还是会变
            System.out.println("id:" + messages[i].getMessageNumber());
//获得from地址
            String from = ((InternetAddress) messages[i].getFrom()[0]).getAddress();
            System.out.println(from);
            mails[i].setFromAddress(from);
            mails[i].setFromName(from);
//获得收件人
            String recipient = ((InternetAddress) messages[i].getAllRecipients()[0]).getAddress();
            mails[i].setRecipientAddress(recipient);
            mails[i].setRecipientName(recipient);
            mails[i].setContent(messages[i].getContent().toString());
            mails[i].setSubject(messages[i].getSubject());
            mails[i].setSendtime(messages[i].getSentDate());
            Flags flags = messages[i].getFlags();
            int status = 0;

            if (boxName.equals("Sent")) {
                status = 2;
            } else if (boxName.equals("Trash")) {
                status = 3;
            } else if (boxName.equals("Drafts")) {
                status = 4;
            } else if (flags.contains(Flags.Flag.SEEN)) {
//                System.out.println("这是一封已读邮件");
                status = 1;
            } else {
//                System.out.println("这是未读邮件");
                status = 0;
            }


            mails[i].setStatus(status);
            mails[i].setMailsize(messages[i].getSize());
        }

        List<Mail> mailList = new ArrayList<>(Arrays.asList(mails));

        // 释放资源
        if (folder != null) {
            folder.close(true);
        }

        return mailList;
    }

    public void mailInDatabase(Mail mail) {
        mailDAO.save(mail);
    }

    public void mailsInDatabase(List<Mail> mails) {
        for (Mail mail : mails) {
            mailInDatabase(mail);
        }
    }

    public void cleanMails() {
        mailDAO.deleteAllMails();
    }

    public void deleteMails(String address, List<Mail> mailList) throws Exception {
        //数据库操作
        for (Mail mail : mailList) {
            mailDAO.deleteById(mail.getId());
        }
        //同步到邮件服务器

        String password = userDAO.getPasswordByAddress(address); // 邮箱的密码 授权码

        int atIndex = address.indexOf('@');
        String domain = "";
        if (atIndex != -1) {
            domain = address.substring(atIndex + 1);
//            System.out.println(domain);
        }
        String imapHost = "imap." + domain;
        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");
        prop.put("mail.imap.host", imapHost);
        prop.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(prop);

        // 使用imap会话机制，连接服务器
        int total = 0;
        IMAPStore store = (IMAPStore) session.getStore("imap");
        store.connect(address, password);
        IMAPFolder folder = (IMAPFolder) store.getFolder("Inbox"); // 收件箱
        folder.open(Folder.READ_WRITE);
        Message[] messages = folder.getMessages();


        for (int i = 0; i < messages.length; i++) {
            for (Mail mail : mailList) {
                Date mailTime = mail.getSendtime();
//判断时间相同则删除邮件
                if (mailTime.equals(messages[i].getSentDate())) {
                    messages[i].setFlag(Flags.Flag.DELETED, true);
                    System.out.println("删除");
                    System.out.println(messages[i].getSubject());
                }
            }

        }

        // 释放资源
        if (folder != null) {
            folder.close(true);
        }
        if (store != null) {
            store.close();
        }
    }

    public boolean isEmpty() {
        return mailDAO.findAll().isEmpty();
    }
}
