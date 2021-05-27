package com.example.andrey.studentshelp.demo.DAO.DAOImpls;


import com.example.andrey.studentshelp.demo.DAO.UserDAO;
import com.example.andrey.studentshelp.demo.DAO.rowMappers.UserRowMapper;
import com.example.andrey.studentshelp.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO_JDBC_Impl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Logger LOGGER = LoggerFactory.getLogger(UserDAO_JDBC_Impl.class);

    @Autowired
    public UserDAO_JDBC_Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        String sql= "SELECT * FROM users WHERE login = ?";
        try {
             user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), login);
        }catch (EmptyResultDataAccessException e){
            user =null;
        }
        if(user == null) LOGGER.error("DAO_JDBC_Impl: getUserByLogin() method. User with login: " + login + " not found");
            else LOGGER.info("DAO_JDBC_Impl: getUserByLogin() method. User discovered: " + user.toString());
        return user;

    }

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";
        List<User> list = jdbcTemplate.query(sql,new UserRowMapper());
        if(list.isEmpty() || list == null)  LOGGER.info("DAO_JDBC_Impl: getAllUsers() method. There are no users in the database!");
        return list;

    }

    @Override
    public boolean addUser(User user) {
        if (getUserByLogAndPas(user.getLogin(), user.getPassword()) == null) {
            String sql = "INSERT INTO users(name, surname, age, login, password, money,role,email) VALUES(?, ?, ?, ?, ?, ?,?,?)";
            int number = jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getLogin(), passwordEncoder.encode(user.getPassword()), 0, user.getRole(), user.getEmail());
            if ((number == 0)) {
                LOGGER.error("DAO_JDBC_Impl: addUser() method. User didn't add!!!");
                return false;
            } else {
                LOGGER.info("DAO_JDBC_Impl: addUser() method. User  added!!!");
                return true;
            }

        }else{
            LOGGER.error("DAO_JDBC_Impl: addUser() method. User with such data already exists!!!");
            return false;
        }
    }



    @Override
    public boolean deleteUserByLogin(String login) {
        if(getUserByLogin(login) != null){
            String sql = "DELETE FROM users WHERE login = ?";
            int number = jdbcTemplate.update(sql,login);
            if(number == 0){
                LOGGER.error("DAO_JDBC_Impl: deleteUserByLogin() method. User is not deleted!!!");
                return false;
            }else{
                LOGGER.info("DAO_JDBC_Impl: deleteUserByLogin() method. User deleted!");
                return true;
            }

        }else{
            LOGGER.info("DAO_JDBC_Impl: deleteUserByLogin() method. User with such login not found!!!");
            return false;
        }

    }

}
