package com.tangyong.springbootmybatisdemo.mapper;

import com.tangyong.springbootmybatisdemo.model.Student;

import java.util.List;

public interface StudentMapper {
    //查看
    public List<Student> selectAll();

    //根据id查找
    public Student selectById(int stuId);

    //添加
    public boolean insert(Student student);

    //根据id删除
    public boolean deleteById(int stuId);

    //根据id更新
    public boolean updateById(Student student);
}
