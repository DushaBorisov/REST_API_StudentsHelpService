package com.example.andrey.studentshelp.demo.DAO.DAOImpls;


import com.example.andrey.studentshelp.demo.DAO.UserDAO;
import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
//@PropertySource("application.properties")
public class DAO_JDBC_Impl implements UserDAO {



    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    Logger LOGGER = LoggerFactory.getLogger(DAO_JDBC_Impl.class);

   // @Value("${spring.datasource.url}")
    private static final   String URL = "jdbc:postgresql://localhost:5432/student_help_service_db";
    //@Value("${spring.datasource.username}")
    private static final  String username = "postgres";
   // @Value("${spring.datasource.password}")
    private static final  String password = "6146534";
    //@Value("${spring.datasource.driver-class-name}")
    private static final String driver = "org.postgresql.Driver";

    private static Connection connection;

    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUserByLogAndPas(String login, String password) {
        User user = getUserByLogin(login);
        if(user != null){
            if(passwordEncoder.matches(password, user.getPassword())) {
                LOGGER.info("DAO_JDBC_Impl: getUserByLogAndPas() method: password  match!!!");
                return user;
            }
            else {
                LOGGER.error("DAO_JDBC_Impl: getUserByLogAndPas() method: password don't match!!!");
                return null;
            }
        }else{
            LOGGER.error(" DAO_JDBC_Impl: getUserByLogAndPas() method: password don't match!!!");
            return null;
        }

    }

    @Override
    public User getUserByLogin(String login){
        User user;
        try {

            String sql= "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()) {
                LOGGER.info("DAO_JDBC_Impl: getUserByLogin()" +
                        "success query: SELECT USER FROM users " + "Logg: " + login );
                resultSet.next();
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                user.setMoney(resultSet.getInt("money"));
                user.setRole(resultSet.getString("role"));
                return user;
            }else {
                LOGGER.error("DAO_JDBC_Impl: getUserByLogin() method. Failed request: SELECT USER FROM users " + "Logg: ");
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {

        String sql = "SELECT * FROM users";
        User user;
        ArrayList<User> ListOfUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()) {
                LOGGER.info("DAO_JDBC_Impl: getAllUsers() method. Success query: SELECT * FROM users ");
                while (resultSet.next()){
                    user = new User();

                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setAge(resultSet.getInt("age"));
                    user.setMoney(resultSet.getInt("money"));
                    user.setRole(resultSet.getString("role"));

                    ListOfUsers.add(user);
                }
                return ListOfUsers;

            }else {
                LOGGER.error("DAO_JDBC_Impl: getAllUsers() method. Failed request: SELECT * FROM users ");
                return null;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean addUser(User user) {
        if (getUserByLogAndPas(user.getLogin(), user.getPassword()) == null) {

            String sql = "INSERT INTO users(name, surname, age, login, password, money,role) VALUES(?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5,passwordEncoder.encode(user.getPassword()));
                preparedStatement.setInt(6, 0);
                preparedStatement.setString(7,user.getRole());

                if (preparedStatement.executeUpdate() != 0) {
                    LOGGER.info("DAO_JDBC_Impl: addUser() method. User added!!!");
                    return true;
                }else LOGGER.error("DAO_JDBC_Impl: addUser() method. User didn't add!!!");
            } catch (SQLException throwables) {
                LOGGER.info("DAO_JDBC_Impl: addUser() method. Error adding user!!!");
                throwables.printStackTrace();
                return false;
            }

        }else{
            LOGGER.error("DAO_JDBC_Impl: addUser() method. The user with such data already exists!!!");
            return false;}
        return false;
    }



    @Override
    public boolean deleteUserByLogin(String login) {
        if(getUserByLogin(login) != null){
            String sql = "DELETE FROM users WHERE login = ?";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, login);

                int number = preparedStatement.executeUpdate();
                if (number != 0) {
                    LOGGER.info("DAO_JDBC_Impl: deleteUserByLogin() method. Number of deleted rows: " + number);
                    return true;
                }else LOGGER.error("DAO_JDBC_Impl: deleteUserByLogin() method. User is not deleted!!!");


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            LOGGER.info("DAO_JDBC_Impl: deleteUserByLogin() method. User with such login not found!!!");
            return false;
        }
        return false;
    }
}
