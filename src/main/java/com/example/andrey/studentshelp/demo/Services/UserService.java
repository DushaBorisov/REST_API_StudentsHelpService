package com.example.andrey.studentshelp.demo.Services;


import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

   public User getUserByLogAndPass(String login, String password);
   public boolean addUser(User user);
   public boolean updateUser(User user);
   public User GetUserByLogin(String login);


}
