package com.example.andrey.studentshelp.demo.Services.ServicesImpl;


import com.example.andrey.studentshelp.demo.DAO.UserDAO;
import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.Email.SendEmailService;
import com.example.andrey.studentshelp.demo.Services.UserService;
import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private SendEmailService emailService;
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, SendEmailService sendEmailService) {
        this.userDAO = userDAO;
        this.emailService = sendEmailService;
    }

    @Override
    public User getUserByLogAndPass(String login, String password) {
        return userDAO.getUserByLogAndPas(login, password);
    }
    @Override
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User GetUserByLogin(String login){
        return userDAO.getUserByLogin(login);
    }

    @Override
    public boolean deleteUserByLogAndPass(String login, String password) {
        return userDAO.deleteUserByLogAndPass(login,password);
    }

    @Override
    public boolean addUser(User user) {
         if(userDAO.addUser(user)){
            emailService.sendEmailAfterRegister(user);
            return true;
        }
         return false;
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        return userDAO.deleteUserByLogin(login);
    }


}
