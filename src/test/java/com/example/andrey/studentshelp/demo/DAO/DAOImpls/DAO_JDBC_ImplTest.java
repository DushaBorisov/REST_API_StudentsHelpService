package com.example.andrey.studentshelp.demo.DAO.DAOImpls;

import com.example.andrey.studentshelp.demo.model.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DAO_JDBC_ImplTest {


  private static DAO_JDBC_Impl dao = new DAO_JDBC_Impl();



  @BeforeAll
  static void beforeAll() {
    User checkUser = dao.getUserByLogin("TestUser");
    System.out.println("Before");
    if (checkUser == null || !(checkUser.getName()).equals("TestUser")) {


      User newUser = new User();

      newUser.setName("TestUser");
      newUser.setSurname("TestUser");
      newUser.setLogin("login");
      newUser.setPassword("password");
      newUser.setAge(19);
      newUser.setMoney(200);
      newUser.setRole("ADMIN");

      dao.addUser(newUser);

    }
  }

  @AfterAll
  static void afterAll() {
    dao.deleteUserByLogAndPass("login","password");
  }

  @Test
    void getUserByLogAndPas_success_user_exists() {

      User user = dao.getUserByLogAndPas("login","password");
      assertEquals("TestUser", user.getName());

    }

  @Test
  void getUserByLoAndPass_success_user_does_not_exist() {

    User user =  dao.getUserByLogin("noSuchUser");
    assertEquals(null, user);
  }

    @Test
    void getUserByLogin_success_user_does_not_exist() {
      User user =  dao.getUserByLogin("NonexistingUser");
      assertEquals(null, user);
    }

  @Test
  void getUserByLogin_success_user_exist() {

    User user =  dao.getUserByLogin("login");
    assertEquals("TestUser", user.getName());
  }




    @Test
    void addUser_success() {
    User user = dao.getUserByLogAndPas("testLog", "testPass");

    if(user == null){
      User newUser = new User();
      newUser.setName("NewUser");
      newUser.setSurname("NewUser");
      newUser.setLogin("testLog");
      newUser.setPassword("testPass");
      newUser.setAge(19);
      newUser.setMoney(200);
      newUser.setRole("ADMIN");

      dao.addUser(newUser);

      assertEquals("NewUser",(dao.getUserByLogAndPas("testLog", "testPass")).getName());
      dao.deleteUserByLogAndPass("testLog", "testPass");
    }


    }



}