package com.example.educationManage.mapper;

import com.example.educationManage.model.*;

import java.util.List;
import java.util.Map;

/**
 * mapper层，学生接口
 *
 * @author 唐勇
 */

public interface StudentMapper {
    /**
     * 新增学生
     *
     * @param student
     *      学生类
     * @return 成功操作的数目
     */
    int insertStudent(Student student);

    /**
     * 删除学生
     *
     * @param stuId
     *      学生id
     * @return 成功操作的数目
     */
    int deleteStudent(long stuId);

    /**
     * 获取所有学生
     *
     * @return 所有学生
     */
    List<Student> selectAllStudent();

    /**
     * 获取单个学生
     *
     * @param stuId
     *      学生id
     * @return 单个学生
     */
    Student selectStudentById(long stuId);

    /**
     * 获取单个学生的登录信息
     *
     * @param stuId
     *      学生id
     * @return 学生登录信息
     */
    StudentLoginInfo selectStuLoginInfoById(long stuId);

    /**
     * 修改学生信息
     *
     * @param student
     *      学生类
     * @return 成功操作的数目
     */
    int updateStudent(Student student);

    /**
     * 选择课程
     *
     * @param courseChose
     *      已选课程类
     * @return 成功操作的数目
     */
    int insertCourseChose(CourseChose courseChose);

    /**
     * 获取课程余量
     *
     * @param courseId
     *      课程id
     * @return 课程余量
     */
    int selectRemain(int courseId);

    /**
     * 课程已选量加一
     *
     * @param courseId
     *      课程id
     * @return 成功操作的数目
     */
    int updateChose(int courseId);

    /**
     * 退选课程
     *
     * @param courseChose
     *      已选课程类
     * @return 成功操作的数目
     */
    int deleteCourseChose(CourseChose courseChose);

    /**
     * 获取课程已选量
     *
     * @param courseId
     *      课程id
     * @return 课程已选量
     */
    int selectChose(int courseId);

    /**
     * 课程剩余量加一
     *
     * @param courseId
     *      课程id
     * @return 成功操作的数目
     */
    int updateRemain(int courseId);

    /**
     * 获取所有已选课程
     *
     * @param stuId
     *      学生id
     * @return 所有已选课程
     */
    List<CourseChose> selectCourseChoseById(long stuId);

    /**
     * 获取某门课程的所有作业
     *
     * @param courseId
     *      课程id
     * @return 该课程的所有作业
     */
    List<Job> selectAllJobById(int courseId);

    /**
     * 获取作业提交次数
     *
     * @param params
     *      学生id、作业id
     * @return 提交次数
     */
    Object selectSubmitTimeById(Map<String, Object> params);

    /**
     * 第一次提交作业
     *
     * @param jobSubmitted
     *      作业提交类
     * @return 成功操作的数目
     */
    int insertJob(JobSubmitted jobSubmitted);

    /**
     * 重新提交作业
     *
     * @param jobSubmitted
     *      作业提交类
     * @return 成功操作的数目
     */
    int updateJob(JobSubmitted jobSubmitted);

    /**
     * 申请假期
     *
     * @param vocation
     *      假期类
     * @return 成功操作的数目
     */
    int insertVocation(Vocation vocation);

    /**
     * 获取所有假期
     *
     * @param params
     *      课程id、学生id
     * @return 所有假期
     */
    List<Vocation> selectAllVocation(Map<String, Object> params);
}
