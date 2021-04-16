package com.example.andrey.studentshelp.demo.Controllers;


import com.example.andrey.studentshelp.demo.DTO.AuthResponse;
import com.example.andrey.studentshelp.demo.DTO.DTOAddNewUser;
import com.example.andrey.studentshelp.demo.DTO.DTOUserToServer;
import com.example.andrey.studentshelp.demo.Security.JWT.JwtProvider;
import com.example.andrey.studentshelp.demo.Services.UserService;
import com.example.andrey.studentshelp.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private static Logger LOGGER = LoggerFactory.getLogger(UserControllers.class);

    private UserService userService;

    private JwtProvider jwtProvider;



    @Autowired
    public UserControllers(UserService userService, JwtProvider jwtProvider) {

        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/userByLogAndPass")
    public ResponseEntity<User> findUserByLogAndPass(@RequestBody DTOUserToServer dtoUserToServer){
        LOGGER.info("findUserByLogAndPass " + "Log: ," + dtoUserToServer.getLogin() + " Pass: " + dtoUserToServer.getPassword());
        final User userHelp = userService.getUserByLogAndPass(dtoUserToServer.getLogin(), dtoUserToServer.getPassword());


        return userHelp != null
                ? new ResponseEntity<>(userHelp, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/register")
    public ResponseEntity addUser(@RequestBody DTOAddNewUser user){

        User innerUser = new User();

        innerUser.setRole("ROLE_USER");
        innerUser.setLogin(user.getLogin());
        innerUser.setPassword(user.getPassword());
        innerUser.setName(user.getName());
        innerUser.setSurname(user.getSurname());
        innerUser.setAge(user.getAge());
        innerUser.setMoney(0);



      return (userService.addUser(innerUser) == true)
              ? new ResponseEntity(HttpStatus.CREATED)
              : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody DTOUserToServer dtoUserToServer){

        User user = userService.getUserByLogAndPass(dtoUserToServer.getLogin(),dtoUserToServer.getPassword());
        if(user == null){
            LOGGER.error("Generate token exception");
            return new AuthResponse("error");
        }
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }

    @GetMapping("/user")
    public String user(){
        return "This is user!!!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is admin!!!";
    }
}
