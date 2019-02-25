package com.example.educationManage.model;

/**
 * 课程类
 *
 * @author 唐勇
 */

public class Course {
    /**
     * 课程代码
     */
    public int courseId;

    /**
     * 课程名称
     */
    public String courseName;

    /**
     * 教师id
     */
    public int teacherId;

    /**
     * 教师姓名
     */
    public String teacherName;

    /**
     * 课程性质
     */
    public String courseType;

    /**
     * 学分
     */
    public double credit;

    /**
     * 周学时
     */
    public String learnTime;

    /**
     * 课程容量
     */
    public int stuNumber;

    /**
     * 已选课程数量
     */
    public int choseNumber;

    /**
     * 余量
     */
    public int remainNumber;

    /**
     * 是否预定教材
     */
    public String withBook;

    /**
     * 开设学院
     */
    public String faculty;

    /**
     * 先修课程
     */
    public String prepareCourse;

    /**
     * 课程简介
     */
    public String courseIntroduction;
}
