package com.example.educationManage.service;

import com.example.educationManage.model.*;

import java.util.List;

/**
 * service层，学生接口
 *
 * @author 唐勇
 */

public interface StudentService {
    /**
     * 新增学生
     *
     * @param student
     *      学生类
     * @return boolean
     */
    boolean addStudent(Student student);

    /**
     * 删除学生
     *
     * @param stuId
     *      学生id
     * @return boolean
     */
    boolean removeStudent(long stuId);

    /**
     * 获取所有学生
     *
     * @return 所有学生
     */
    List<Student> getAllStudent();

    /**
     * 获取单个学生的登录信息
     *
     * @param stuId
     *      学生id
     * @return 学生登录信息
     */
    StudentLoginInfo getStuLoginInfoById(long stuId);

    /**
     * 修改学生信息
     * @param student
     *      学生类
     * @return boolean
     */
    boolean changeStudent(Student student);

    /**
     * 选择课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    boolean addCourseChose(CourseChose courseChose);

    /**
     * 退选课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    boolean removeCourseChose(CourseChose courseChose);

    /**
     * 获取所有已选课程
     *
     * @param stuId
     *      学生id
     * @return 所有已选课程
     */
    List<Course> getAllCourseChoseById(long stuId);

    /**
     * 获取某门课程所有作业
     *
     * @param courseId
     *      课程Id
     * @param stuId
     *      课程id
     * @return 该课程的所有作业
     */
    List<Job> getAllJobById(int courseId, long stuId);

    /**
     * 提交作业
     *
     * @param jobSubmitted
     *      作业提交类
     * @return boolean
     */
    boolean upJob(JobSubmitted jobSubmitted);

    /**
     * 申请假期
     * @param vocation
     *      假期类
     * @return boolean
     */
    boolean addVocation(Vocation vocation);

    /**
     * 获取所有假期
     *
     * @param courseId
     *      课程id
     * @param stuId
     *      学生id
     * @return 所有假期
     */
    List<Vocation> getAllVocation(int courseId, long stuId);
}
