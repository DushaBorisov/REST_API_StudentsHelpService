package com.example.andrey.studentshelp.demo.Email;

import com.example.andrey.studentshelp.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);

    private JavaMailSender javaMailSender;

    @Autowired
    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    public void sendEmailAfterRegister( User user){

        LOGGER.info("SendEmailService: sendEmailAfterRegister() method started with param:  " + "to: " + user.getEmail());

        String body = "Hi " + user.getName() + "!!!" + " Registration on the resourse of the StudentHelpService has been successfully!!!" +
                " Now you can use all the possibilities of our service!!!";
        String topic = "Registration";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("students.help.service.andrey@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);



    }
}
