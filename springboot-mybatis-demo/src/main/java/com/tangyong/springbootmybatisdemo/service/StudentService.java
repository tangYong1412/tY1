package com.tangyong.springbootmybatisdemo.service;

import com.tangyong.springbootmybatisdemo.model.Student;

import java.util.List;

public interface StudentService {
    //查看
    public List<Student> findAll();

    //根据id查找
    public Student findById(int stuId);

    //添加
    public boolean add(Student student);

    //删除
    public boolean deleteById(int stuId);

    //更新
    public boolean changeById(Student student);
}