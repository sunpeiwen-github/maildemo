package com.example.javamaildemo4.dao;


import com.example.javamaildemo4.pojo.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByAddress(String address);

    User getByAddressAndPassword(String address,String password);
    //没有实验过
    @Query("SELECT u.password FROM User u WHERE u.address = :address")
    String getPasswordByAddress(@Param("address") String address);


    @Query("SELECT u.id FROM User u WHERE u.address = :address")
    int getIdByAddress(@Param("address") String address);
}
