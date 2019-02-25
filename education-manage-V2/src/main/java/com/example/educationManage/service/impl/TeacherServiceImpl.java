package com.example.educationManage.service.impl;

import com.example.educationManage.mapper.StudentMapper;
import com.example.educationManage.mapper.TeacherMapper;
import com.example.educationManage.model.*;
import com.example.educationManage.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现service层教师接口
 *
 * @author 唐勇
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 新增教师
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    @Override
    public boolean addTeacher(Teacher teacher) {
        return 1 == teacherMapper.insertTeacher(teacher);
    }

    /**
     * 删除教师
     *
     * @param teacherId
     *      教师id
     * @return boolean
     */
    @Override
    public boolean removeTeacher(int teacherId) {
        return 1 == teacherMapper.deleteTeacher(teacherId);
    }

    /**
     * 获取所有教师
     *
     * @return 所有教师
     */
    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.selectAllTeacher();
    }

    /**
     * 获取单个教师的登录信息
     *
     * @param teacherId
     *      教师id
     * @return 教师登录信息
     */
    @Override
    public TeacherLoginInfo getAllTeacherLoginInfo(int teacherId) {
        return teacherMapper.selectTeacherLoginInfoById(teacherId);
    }

    /**
     * 获取某位教师所有已开设课程
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设课程
     */
    @Override
    public List<Course> getAllCourseOpenedById(int teacherId) {
        return teacherMapper.selectAllCourseOpenedById(teacherId);
    }

    /**
     * 获取某位教师开设的课程id、课程名称
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设的课程id、课程名称
     */
    @Override
    public List<CourseOpened> getAllCourseIdById(int teacherId) {
        return teacherMapper.selectAllCourseIdById(teacherId);
    }

    /**
     * 获取某课程的所有学生成绩
     *
     * @param courseId
     *      课程id
     * @return 该课程的所有学生成绩
     */
    @Override
    public List<Student> getAllGradeById(int courseId) {
        List<Student> studentList = teacherMapper.selectAllGradeById(courseId);

        int index = 0;
        Student studentGet;
        Student studentSet;
        while( index < studentList.size() ) {
            studentGet = studentList.get(index);
            studentSet = studentMapper.selectStudentById(studentGet.stuId);
            studentSet.grade = studentGet.grade;
            studentList.set(index++, studentSet);
        }

        return studentList;
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    @Override
    public boolean changeTeacher(Teacher teacher) {
        return 1 == teacherMapper.updateTeacher(teacher);
    }

    /**
     * 修改学生成绩
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @Override
    public boolean changeStuGrade(CourseChose courseChose) {
        return 1 == teacherMapper.updateGrade(courseChose);
    }

    /**
     * 新增作业
     *
     * @param job
     *      作业类
     * @return boolean
     */
    @Override
    public int addJob(Job job) {
        if( 1 == teacherMapper.insertJob(job) ) {
            return job.jobId;
        }else{
            return 0;
        }
    }
}
