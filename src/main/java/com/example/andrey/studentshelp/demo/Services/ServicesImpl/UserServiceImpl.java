package com.example.andrey.studentshelp.demo.Services.ServicesImpl;


import com.example.andrey.studentshelp.demo.DAO.UserDAO;
import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.Services.UserService;
import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByLogAndPass(String login, String password) {
        return userDAO.getUserByLogAndPas(login, password);
    }

    @Override
    public User GetUserByLogin(String login){
        return userDAO.getUserByLogin(login);
    }
    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return  true;

    }
}
