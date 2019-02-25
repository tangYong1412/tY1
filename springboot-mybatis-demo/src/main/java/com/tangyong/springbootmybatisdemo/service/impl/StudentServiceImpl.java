package com.tangyong.springbootmybatisdemo.service.impl;


import com.tangyong.springbootmybatisdemo.mapper.StudentMapper;
import com.tangyong.springbootmybatisdemo.model.Student;
import com.tangyong.springbootmybatisdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    @Override
    public Student findById(int stuId) {
        return studentMapper.selectById(stuId);
    }

    @Override
    public boolean add(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public boolean deleteById(int stuId) {
        return studentMapper.deleteById(stuId);
    }

    @Override
    public boolean changeById(Student student) {
        return studentMapper.updateById(student);
    }
}
