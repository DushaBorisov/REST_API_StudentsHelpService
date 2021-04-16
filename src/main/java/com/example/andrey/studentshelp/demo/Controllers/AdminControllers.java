package com.example.andrey.studentshelp.demo.Controllers;

import com.example.andrey.studentshelp.demo.Services.UserService;
import com.example.andrey.studentshelp.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminControllers {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminControllers.class);

    private UserService userService;

    @Autowired
    public AdminControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> adminGetListOfUsers(){
        LOGGER.info("AdminControllers: adminGetListOfUsers() method started!!!");

        List<User> listOfUsers = userService.getAllUsers();

        return listOfUsers != null && !listOfUsers.isEmpty()
                ? new ResponseEntity<>(listOfUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/deleteUser/{userLogin}")
    public ResponseEntity deleteUserByLog(@PathVariable(name = "userLogin") String userLogin){
        LOGGER.info("AdminControllers: deleteUserByLog() method started with parameter: " + userLogin);
        return userService.deleteUserByLogin(userLogin) == true
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
