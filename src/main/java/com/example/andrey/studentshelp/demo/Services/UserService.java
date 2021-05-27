package com.example.andrey.studentshelp.demo.Services;



import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UserService {

   public User getUserByLogAndPass(String login, String password);
   public List<User> getAllUsers();
   public boolean addUser(User user);
   public boolean deleteUserByLogin(String login);
   public User GetUserByLogin(String login);



}
