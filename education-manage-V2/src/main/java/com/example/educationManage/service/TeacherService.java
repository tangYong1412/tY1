package com.example.educationManage.service;

import com.example.educationManage.model.*;

import java.util.List;

/**
 * service层，教师接口
 *
 * @author 唐勇
 */

public interface TeacherService {
    /**
     * 新增教师
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    boolean addTeacher(Teacher teacher);

    /**
     * 删除教师
     *
     * @param teacherId
     *      教师id
     * @return boolean
     */
    boolean removeTeacher(int teacherId);

    /**
     * 获取所有教师
     *
     * @return 所有教师
     */
    List<Teacher> getAllTeacher();

    /**
     * 获取单个教师的登录信息
     *
     * @param teacherId
     *      教师id
     * @return 教师登录信息
     */
    TeacherLoginInfo getAllTeacherLoginInfo(int teacherId);

    /**
     * 获取某位教师所有已开设课程
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设课程
     */
    List<Course> getAllCourseOpenedById(int teacherId);

    /**
     * 获取某位教师开设的课程id、课程名称
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设的课程id、课程名称
     */
    List<CourseOpened> getAllCourseIdById(int teacherId);

    /**
     * 获取某课程的所有学生成绩
     *
     * @param courseId
     *      课程id
     * @return 该课程的所有学生成绩
     */
    List<Student> getAllGradeById(int courseId);

    /**
     * 修改教师信息
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    boolean changeTeacher(Teacher teacher);

    /**
     * 修改学生成绩
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    boolean changeStuGrade(CourseChose courseChose);

    /**
     * 新增作业
     *
     * @param job
     *      作业类
     * @return boolean
     */
    int addJob(Job job);
}
