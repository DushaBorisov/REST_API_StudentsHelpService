package com.example.andrey.studentshelp.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOAddNewUser {
    private String name;
    private String surname;
    private String login;
    private String password;
    private int age;
    private String email;
}
