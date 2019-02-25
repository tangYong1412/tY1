package com.example.educationManage.mapper;

import com.example.educationManage.model.Course;

import java.util.List;

/**
 * mapper层，课程接口
 *
 * @author 唐勇
 */

public interface CourseMapper {
    /**
     * 新增课程
     *
     * @param course
     *      课程类
     * @return 成功操作的数目
     */
    int insertCourse(Course course);

    /**
     * 删除课程
     *
     * @param courseId
     *      课程id
     * @return 成功操作的数目
     */
    int deleteCourse(int courseId);

    /**
     * 获取所有课程
     *
     * @return 所有课程
     */
    List<Course> selectAllCourse();

    /**
     * 获取单个课程
     *
     * @param courseId
     *      课程id
     * @return 课程
     */
    Course selectCourseById(int courseId);

    /**
     * 修改课程
     *
     * @param course
     *      课程类
     * @return 成功操作的数目
     */
    int updateCourseById(Course course);
}
