package com.example.educationManage.service.impl;

import com.example.educationManage.mapper.CourseMapper;
import com.example.educationManage.model.Course;
import com.example.educationManage.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现service层课程接口
 *
 * @author 唐勇
 */

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 新增课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    @Override
    public boolean addCourse(Course course) {
        course.choseNumber = 0;
        course.remainNumber = course.stuNumber;

        return 1 == courseMapper.insertCourse(course);
    }

    /**
     * 删除课程
     *
     * @param courseId
     *      课程id
     * @return boolean
     */
    @Override
    public boolean removeCourse(int courseId) {
        return 1 == courseMapper.deleteCourse(courseId);
    }

    /**
     * 获取所有课程
     *
     * @return 所有课程
     */
    @Override
    public List<Course> getAllCourse() {
        return courseMapper.selectAllCourse();
    }

    /**
     * 修改课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    @Override
    public boolean changeCourse(Course course) {
        return 1 == courseMapper.updateCourseById(course);
    }
}
