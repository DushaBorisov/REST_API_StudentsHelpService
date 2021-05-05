package com.example.andrey.studentshelp.demo.DAO;

import com.example.andrey.studentshelp.demo.model.Order;

import java.util.List;

public interface MakeOrderDAO {

    List<Order> getAllUserOrdersByLogAndPass(String login, String password);
    boolean makeOrder(Order order);



}
