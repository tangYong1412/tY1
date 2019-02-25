package com.example.educationManage.controller;

import com.example.educationManage.model.*;
import com.example.educationManage.service.TeacherService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * controller层，教师接口
 *
 * @author 唐勇
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 新增教师
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    @PostMapping("/addTeacher")
    public boolean addTeacher(@RequestBody Teacher teacher) {
        if( teacher == null ) {
            return false;
        }else{
            return teacherService.addTeacher(teacher);
        }
    }

    /**
     * 删除教师
     *
     * @param teacherId
     *      教师id
     * @return boolean
     */
    @GetMapping("/removeTeacher/{teacherId}")
    public boolean removeTeacher(@PathVariable("teacherId") int teacherId) {
        if( teacherId == 0 ) {
            return false;
        }else{
            return teacherService.removeTeacher(teacherId);
        }
    }

    /**
     * 获取所有教师
     *
     * @return 所有教师
     */
    @GetMapping("/getAllTeacher")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    /**
     * 获取单个教师的登录信息
     *
     * @param teacherId
     *      教师id
     * @return 教师登录信息
     */
    @GetMapping("/getTeacherLoginInfo/{teacherId}")
    public TeacherLoginInfo getTeacherLoginInfo(@PathVariable("teacherId") int teacherId) {
        if( teacherId == 0) {
            return null;
        }else{
            return teacherService.getAllTeacherLoginInfo(teacherId);
        }
    }

    /**
     * 获取某位教师所有已开设课程
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设课程
     */
    @GetMapping("/getAllCourseOpened/{teacherId}")
    public List<Course> getAllCourseOpened(@PathVariable("teacherId") int teacherId) {
        if(teacherId == 0) {
            return null;
        }else{
            return teacherService.getAllCourseOpenedById(teacherId);
        }
    }

    /**
     * 获取某位教师开设的课程id、课程名称
     *
     * @param teacherId
     *      教师id
     * @return 该教师所有已开设的课程id、课程名称
     */
    @GetMapping("/getCourseIdAndName/{teacherId}")
    public List<CourseOpened> getCourseIdAndName(@PathVariable("teacherId") int teacherId ) {
        if( teacherId == 0 ) {
            return null;
        }else{
            return teacherService.getAllCourseIdById(teacherId);
        }
    }

    /**
     * 获取某课程的所有学生成绩
     *
     * @param courseId
     *      课程id
     * @return 该课程的所有学生成绩
     */
    @GetMapping("/getAllGrade/{courseId}")
    public List<Student> getAllGrade(@PathVariable("courseId") int courseId) {
        if( courseId == 0 ) {
            return null;
        }else{
            return teacherService.getAllGradeById(courseId);
        }
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     *      教师类
     * @return boolean
     */
    @PostMapping("/updateTeacher")
    public boolean changeTeacher(@RequestBody Teacher teacher) {
        if( teacher == null ){
            return false;
        }else{
            return teacherService.changeTeacher(teacher);
        }
    }

    /**
     * 修改学生成绩
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @PostMapping("/updateStuGrade")
    public boolean changeStuGrade(@RequestBody CourseChose courseChose) {
        if( courseChose == null ) {
            return false;
        }else{
            return teacherService.changeStuGrade(courseChose);
        }
    }

    /**
     * 新增作业
     *
     * @param jobStr
     *      作业类
     * @return boolean
     */
    @PostMapping("/addJob")
    public int addJob(@RequestParam("jobStr") String jobStr, @RequestParam("file") MultipartFile file) {
        if( jobStr == null || jobStr.equals("") ) {
            return 0;
        }

        JSONObject jsonObject = JSONObject.fromObject(jobStr);
        //自动注入获取不到key
        //Job job1 = (Job) JSONObject.toBean(jsonObject, Job.class);
        //手动注入
        Job job = new Job();
        job.courseId = jsonObject.getInt("courseId");
        job.jobTitle = jsonObject.getString("jobTitle");
        job.upTime = jsonObject.getString("upTime");
        job.endTime = jsonObject.getString("endTime");
        job.content = jsonObject.getString("content");

        if( file.isEmpty() ) {//没有上传文件
            job.annex = "";
            return teacherService.addJob(job);
        }else{
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //设置文件存储路径
            String filePath = "E:/ideaProject/education-manage-v2-download/" + fileName;
            File dest = new File(filePath);

            //检测目录是否存在
            if( !dest.getParentFile().exists() ) {
                dest.getParentFile().mkdirs();
            }
            //写入文件
            try {
                file.transferTo(dest);
                job.annex = fileName;
                return teacherService.addJob(job);
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
