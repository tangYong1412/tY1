package com.experiment2.general_course_website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.experiment2.general_course_website.mapper")
public class GeneralCourseWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralCourseWebsiteApplication.class, args);
    }

}
