package com.example.andrey.studentshelp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private int money;
    private int age;
    private String role;
    private String email;
}
