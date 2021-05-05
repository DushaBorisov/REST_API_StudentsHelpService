package com.example.andrey.studentshelp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    User customer;
    User performer;

    String description;
    List<String> picturesArr;
    Integer cost;
    String dateOrder;
    String DateExecution;
    int status;
    String subject;

}
