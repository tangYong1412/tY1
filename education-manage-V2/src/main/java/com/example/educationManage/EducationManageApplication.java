package com.example.educationManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.educationManage.mapper")
public class EducationManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationManageApplication.class, args);
    }

}

