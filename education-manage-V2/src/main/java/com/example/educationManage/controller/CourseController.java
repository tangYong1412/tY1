package com.example.educationManage.controller;

import com.example.educationManage.model.Course;
import com.example.educationManage.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller层，课程接口
 *
 * @author 唐勇
 */

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 新增课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    @PostMapping("/addCourse")
    public boolean addCourse(@RequestBody Course course) {
        if(course == null) {
            return false;
        }else{
            return courseService.addCourse(course);
        }
    }

    /**
     * 删除课程
     *
     * @param courseId
     *      课程id
     * @return boolean
     */
    @GetMapping("/removeCourse/{courseId}")
    public boolean removeCourse(@PathVariable("courseId") int courseId) {
        if(courseId == 0) {
            return false;
        }else{
             return courseService.removeCourse(courseId);
        }
    }

    /**
     * 获取所有课程
     *
     * @return 所有课程
     */
    @GetMapping("/getAllCourse")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    /**
     * 修改课程
     *
     * @param course
     *      课程类
     * @return boolean
     */
    @PostMapping("/updateCourse")
    public boolean changeCourse(@RequestBody Course course) {
        if( course == null ) {
            return false;
        }else{
            return courseService.changeCourse(course);
        }
    }
}
