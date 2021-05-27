package com.example.andrey.studentshelp.demo.DAO.rowMappers;

import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {


        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setAge(resultSet.getInt("age"));
        user.setMoney(resultSet.getInt("money"));
        user.setRole(resultSet.getString("role"));
        user.setEmail(resultSet.getString("email"));

        return user;

    }
}
