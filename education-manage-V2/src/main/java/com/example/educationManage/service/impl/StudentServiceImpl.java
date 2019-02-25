package com.example.educationManage.service.impl;

import com.example.educationManage.mapper.CourseMapper;
import com.example.educationManage.mapper.StudentMapper;
import com.example.educationManage.model.*;
import com.example.educationManage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现service层学生接口
 *
 * @author 唐勇
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 新增学生
     *
     * @param student
     *      学生类
     * @return boolean
     */
    @Override
    public boolean addStudent(Student student) {
        return 1 == studentMapper.insertStudent(student);
    }

    /**
     * 删除学生
     *
     * @param stuId
     *      学生id
     * @return boolean
     */
    @Override
    public boolean removeStudent(long stuId) {
        return 1 == studentMapper.deleteStudent(stuId);
    }

    /**
     * 获取所有学生
     *
     * @return 所有学生
     */
    @Override
    public List<Student> getAllStudent() {
        return studentMapper.selectAllStudent();
    }

    /**
     * 获取单个学生的登录信息
     *
     * @param stuId
     *      学生id
     * @return 学生登录信息
     */
    @Override
    public StudentLoginInfo getStuLoginInfoById(long stuId) {
        return studentMapper.selectStuLoginInfoById(stuId);
    }

    /**
     * 修改学生信息
     * @param student
     *      学生类
     * @return boolean
     */
    @Override
    public boolean changeStudent(Student student) {
        return 1 == studentMapper.updateStudent(student);
    }

    /**
     * 选择课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @Override
    public boolean addCourseChose(CourseChose courseChose) {
        if( studentMapper.selectRemain(courseChose.courseId) > 0 ) {//课程余量大于0
            if( studentMapper.insertCourseChose(courseChose) == 1 ) {
                return 1 == studentMapper.updateChose(courseChose.courseId);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 退选课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @Override
    public boolean removeCourseChose(CourseChose courseChose) {
        if( studentMapper.selectChose(courseChose.courseId) > 0 ) {//课程已选量大于0
            if( studentMapper.deleteCourseChose(courseChose) == 1 ) {
                return 1 == studentMapper.updateRemain(courseChose.courseId);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 获取所有已选课程
     *
     * @param stuId
     *      学生id
     * @return 所有已选课程
     */
    @Override
    public List<Course> getAllCourseChoseById(long stuId) {
        List<CourseChose> courseChoseList = studentMapper.selectCourseChoseById(stuId);
        List<Course> courseList = new ArrayList<>();

        Course course;
        for (CourseChose courseChose :
                courseChoseList) {
            course = courseMapper.selectCourseById(courseChose.courseId);
            course.withBook = courseChose.withBook;
            courseList.add(course);
        }

        return courseList;
    }

    /**
     * 获取某门课程所有作业
     *
     * @param courseId
     *      课程Id
     * @return 该课程的所有作业
     */
    @Override
    public List<Job> getAllJobById(int courseId, long stuId) {
        List<Job> jobList = studentMapper.selectAllJobById(courseId);
        Object time;
        Map<String, Object> map = new HashMap<>();
        map.put("stuId", stuId);

        //获取提交次数
        int index = 0;
        Job job;
        while( index < jobList.size() ) {
            job = jobList.get(index);
            map.put("jobId", job.jobId);
            time = studentMapper.selectSubmitTimeById(map);
            if( time != null ) {
                job.submitTimes = (int)time;
            }else{
                job.submitTimes = 0;
            }

            jobList.set(index++, job);
        }
        return jobList;
    }

    /**
     * 提交作业
     *
     * @param jobSubmitted
     *      作业提交类
     * @return boolean
     */
    @Override
    public boolean upJob(JobSubmitted jobSubmitted) {
        Map<String, Object> map = new HashMap<>();
        map.put("stuId", jobSubmitted.stuId);
        map.put("jobId", jobSubmitted.jobId);
        Object time = studentMapper.selectSubmitTimeById(map);

        if( time != null ) {
            jobSubmitted.submitTimes = (int)time + 1;
            return 1 == studentMapper.updateJob(jobSubmitted);
        }else{
            jobSubmitted.submitTimes = 0;
            return 1 == studentMapper.insertJob(jobSubmitted);
        }
    }

    /**
     * 申请假期
     * @param vocation
     *      假期类
     * @return boolean
     */
    @Override
    public boolean addVocation(Vocation vocation) {
        return 1 == studentMapper.insertVocation(vocation);
    }

    /**
     * 获取所有假期
     *
     * @param courseId
     *      课程id
     * @param stuId
     *      学生id
     * @return 所有假期
     */
    @Override
    public List<Vocation> getAllVocation(int courseId, long stuId) {
        Map<String, Object> map = new HashMap<>();
        map.put("courseId", courseId);
        map.put("stuId", stuId);
        List<Vocation> vocationList = studentMapper.selectAllVocation(map);

        int index = 0;
        Student student;
        Vocation vocation;
        while( index < vocationList.size() ) {
            vocation = vocationList.get(index);
            student = studentMapper.selectStudentById(vocation.stuId);
            vocation.stuName = student.stuName;
            vocation.stuSex = student.stuSex;
            vocation.faculty = student.faculty;

            vocationList.set(index++, vocation);
        }

        return vocationList;
    }
}
