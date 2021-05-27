package com.example.andrey.studentshelp.demo.DAO;



import com.example.andrey.studentshelp.demo.model.User;


import java.util.List;

public interface UserDAO {

    public User getUserByLogAndPas(String login, String password);
    public User getUserByLogin(String login);
    public List<User> getAllUsers();
    public boolean addUser(User user);
    public boolean deleteUserByLogin(String login);

}
