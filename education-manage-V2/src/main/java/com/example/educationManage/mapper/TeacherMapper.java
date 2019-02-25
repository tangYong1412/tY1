package com.example.educationManage.mapper;

import com.example.educationManage.model.*;

import java.util.List;
import java.util.Map;

/**
 * mapper层，教师接口
 *
 * @author 唐勇
 */

public interface TeacherMapper {
    /**
     * 新增教师
     *
     * @param teacher
     *      教师类
     * @return 成功操作的数目
     */
    int insertTeacher(Teacher teacher);

    /**
     * 删除教师
     *
     * @param teacherId
     *      教师id
     * @return 成功操作的数目
     */
    int deleteTeacher(int teacherId);

    /**
     * 获取所有教师
     *
     * @return 所有教师
     */
    List<Teacher> selectAllTeacher();

    /**
     * 获取单个教师的登录信息
     *
     * @param teacherId
     *      教师id
     * @return 教师登录信息
     */
    TeacherLoginInfo selectTeacherLoginInfoById(int teacherId);

    /**
     * 获取某位教师所有开设课程
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设课程
     */
    List<Course> selectAllCourseOpenedById(int teacherId);

    /**
     * 获取某位教师开设的课程id、名称
     *
     * @param teacherId
     *      教师id
     * @return 教师所有已开设课程id、名称
     */
    List<CourseOpened> selectAllCourseIdById(int teacherId);

    /**
     * 获取某课程的所有学生成绩
     *
     * @param courseId
     *      课程id
     * @return 某课程的所有学生成绩
     */
    List<Student> selectAllGradeById(int courseId);

    /**
     * 获取教师姓名
     *
     * @param teacherId
     *      教师id
     * @return 教师姓名
     */
    String selectTeacherNameById(int teacherId);

    /**
     * 修改教师信息
     *
     * @param teacher
     *      教师类
     * @return 成功操作的数目
     */
    int updateTeacher(Teacher teacher);

    /**
     * 修改学生成绩
     *
     * @param courseChose
     *      已选课程类
     * @return 成功操作的数目
     */
    int updateGrade(CourseChose courseChose);

    /**
     * 新增作业
     *
     * @param job
     *      作业类
     * @return 成功操作的数目
     */
    int insertJob(Job job);
}
