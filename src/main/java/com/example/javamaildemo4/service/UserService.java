package com.example.javamaildemo4.service;


import com.example.javamaildemo4.dao.UserDAO;
import com.example.javamaildemo4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String address) {
        User user = getByName(address);
        return null!=user;
    }

    public User getByName(String address) {
        return userDAO.findByAddress(address);
    }

    public User get(String address, String password){
        return userDAO.getByAddressAndPassword(address, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
