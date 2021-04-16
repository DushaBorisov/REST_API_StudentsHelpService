package com.example.andrey.studentshelp.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOUserToServer {
    private String login;
    private String password;
}
