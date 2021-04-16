package com.example.andrey.studentshelp.demo.DAO;


import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.model.User;

import java.util.ArrayList;

public interface UserDAO {

    public User getUserByLogAndPas(String login, String password);
    public User getUserByLogin(String login);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public ArrayList<User> getAllusers();

}
