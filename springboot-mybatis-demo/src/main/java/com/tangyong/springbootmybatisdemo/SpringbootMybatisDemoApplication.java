package com.tangyong.springbootmybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//接口包
@MapperScan("com.tangyong.springbootmybatisdemo.mapper")
public class SpringbootMybatisDemoApplication{
    /**
     *测试
     */

// implements CommandLineRunner
//    @Autowired
//    private StudentMapper studentMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisDemoApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Student> studentList = studentMapper.selectAll();
//
//        for(Student student : studentList){
//            System.out.println("学号：" + student.getStuId() + " "
//                    + "姓名：" + student.getStuName() + " "
//                    + "性别：" + student.getStuSex() + " "
//                    + "年龄：" + student.getStuAge() + " "
//                    + "专业：" + student.getStuProfession());
//        }
//    }
}
