package com.tangyong.springbootmybatisdemo.controller;

import com.tangyong.springbootmybatisdemo.model.Student;
import com.tangyong.springbootmybatisdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

//ResController返回内容
//返回视图
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    //查看所有学生
    //参数中的map在跳转的时候会默认传给页面
    @RequestMapping("/students")
    public String displayStudents(Map<String, Object> map){
        List<Student> studentList = studentService.findAll();
        map.put("studentList", studentList);

        //来到模板引擎页面
        return "table";
    }

    //查看单个学生
    @GetMapping("/student/{stuId}")
    public String checkStudent(@PathVariable("stuId") int id, Map<String, Object> map){
        Student student = studentService.findById(id);
        map.put("student", student);

        //来到模板引擎页面
        return "findOne";
    }

    //来到添加页面
    @GetMapping("/toAddOrUpdatePage")
    public String toAddPage(){
        //来到模板引擎页面
        return "addOrUpdate";
    }

    //添加
    //SpringMVC自动将请求参数和参数对象的属性一一绑定；
    //要求是请求参数的name和参数对象的成员变量的名称相同
    @PostMapping("/addOrUpdate")
    public String add(Student student){
        studentService.add(student);
        //重定向到一个地址，forward：表示转发到一个地址
        return "redirect:/students";
    }

    //删除
    @DeleteMapping("/delete/{stuId}")
    public String delete(@PathVariable("stuId") int id){
        studentService.deleteById(id);
        //重定向到一个地址，forward：表示转发到一个地址
        return "redirect:/students";
    }

    //来到更新界面
    @GetMapping("/toAddOrUpdatePage/{stuId}")
    public String changeInfo(@PathVariable("stuId") int id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);

        return "addOrUpdate";
    }

    //修改学生信息
    //SpringMVC自动将请求参数和参数对象的属性一一绑定；
    //要求是请求参数的name和参数对象的成员变量的名称相同
    @PutMapping("/addOrUpdate")
    public String changeStuInfo(Student student){
        studentService.changeById(student);
//        System.out.println(student.getStuId());
        return "redirect:/students";
    }
}
