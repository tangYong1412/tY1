package com.example.educationManage.service;

import com.example.educationManage.model.Course;

import java.util.List;

/**
 * service层，课程接口
 *
 * @author 唐勇
 */

public interface CourseService {
    /**
     * 新增课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    boolean addCourse(Course course);

    /**
     * 删除课程
     *
     * @param courseId
     *      课程id
     * @return boolean
     */
    boolean removeCourse(int courseId);

    /**
     * 获取所有课程
     *
     * @return 所有课程
     */
    List<Course> getAllCourse();

    /**
     * 修改课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    boolean changeCourse(Course course);
}
