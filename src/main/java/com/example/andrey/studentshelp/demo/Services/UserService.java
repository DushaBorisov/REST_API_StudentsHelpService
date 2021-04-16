package com.example.andrey.studentshelp.demo.Services;


import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface UserService {

   public User getUserByLogAndPass(String login, String password);
   public ArrayList<User> getAllUsers();
   public boolean addUser(User user);
   public boolean deleteUserByLogin(String login);
   public User GetUserByLogin(String login);


}
