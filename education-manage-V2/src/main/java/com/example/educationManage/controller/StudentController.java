package com.example.educationManage.controller;

import com.example.educationManage.model.*;
import com.example.educationManage.service.StudentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * controller层，学生接口
 *
 * @author 唐勇
 */

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 新增学生
     *
     * @param student
     *      学生类
     * @return boolean
     */
    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody Student student) {
        if(student == null) {
            return false;
        }else{
            return studentService.addStudent(student);
        }
    }

    /**
     * 删除学生
     *
     * @param stuId
     *      学生id
     * @return boolean
     */
    @GetMapping("/removeStudent/{stuId}")
    public boolean removeStudent(@PathVariable("stuId") long stuId) {
        if( stuId == 0 ) {
            return false;
        }else{
            return studentService.removeStudent(stuId);
        }
    }

    /**
     * 获取所有学生
     *
     * @return 所有学生
     */
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    /**
     * 获取单个学生的登录信息
     *
     * @param stuId
     *      学生id
     * @return 学生登录信息
     */
    @GetMapping("/getStuLoginInfo/{stuId}")
    public StudentLoginInfo getStuLoginInfo(@PathVariable("stuId") long stuId) {
        if( stuId == 0 ) {
            return null;
        }else{
            return studentService.getStuLoginInfoById(stuId);
        }
    }

    /**
     * 修改学生信息
     * @param student
     *      学生类
     * @return boolean
     */
    @PostMapping("/updateStudent")
    public boolean updateStudent(@RequestBody Student student) {
        if(student == null) {
            return false;
        }else{
            return studentService.changeStudent(student);
        }
    }

    /**
     * 选择课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @PostMapping("/selectCourse")
    public boolean selectCourse(@RequestBody CourseChose courseChose) {
        if( courseChose == null ) {
            return false;
        }else{
            return studentService.addCourseChose(courseChose);
        }
    }

    /**
     * 退选课程
     *
     * @param courseChose
     *      已选课程类
     * @return boolean
     */
    @PostMapping("/removeCourseChose")
    public boolean removeCourseChose(@RequestBody CourseChose courseChose) {
        if( courseChose == null ) {
            return false;
        }else{
            return studentService.removeCourseChose(courseChose);
        }
    }

    /**
     * 获取所有已选课程
     *
     * @param stuId
     *      学生id
     * @return 所有已选课程
     */
    @GetMapping("/getAllCourseChose/{stuId}")
    public List<Course> getAllCourseChose(@PathVariable("stuId") long stuId ) {
        if( stuId == 0 ) {
            return null;
        }else{
            return studentService.getAllCourseChoseById(stuId);
        }
    }

    /**
     * 获取某门课程所有作业
     *
     * @param courseId
     *      课程Id
     * @param stuId
     *      学生id
     * @return 该课程的所有作业
     */
    @GetMapping("/getAllJob")
    public List<Job> getAllJob(@RequestParam("courseId") int courseId, @RequestParam("stuId") long stuId) {
        if( courseId != 0 && stuId != 0 ) {
            return studentService.getAllJobById(courseId, stuId);
        }else{
            return null;
        }
    }

    /**
     * 下载作业附件
     *
     * @param fileName
     *      文件名
     */
    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        if( fileName != null ) {
            //文件存储路径
            String filePath = "E:/ideaProject/education-manage-v2-download/" + fileName;
            File file = new File(filePath);
            if( file.exists() ) {//如果文件存在
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

                byte[] bytes = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(bytes);
                    while( i != -1 ) {
                        os.write(bytes,0,i);
                        i = bis.read(bytes);
                    }

                    fis.close();
                    bis.close();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 提交作业
     *
     * @param jsonStr
     *      json字符串
     * @return boolean
     */
    @PostMapping("/upJob")
    public boolean upJob(@RequestParam("jsonStr") String jsonStr , @RequestParam("file") MultipartFile file) {
        if( file.isEmpty() ) {
            return false;
        }

        if( jsonStr == null || jsonStr.equals("") ) {
            return false;
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JobSubmitted jobSubmitted = new JobSubmitted();
        jobSubmitted.stuId = jsonObject.getLong("stuId");
        jobSubmitted.jobId = jsonObject.getInt("jobId");

        //获取文件名
        String fileName = file.getOriginalFilename();
        //设置文件存储路径
        String filePath = "E:/ideaProject/education-manage-v2-upload/";
        String path = filePath + fileName;
        File dest = new File(path);

        //检测是否存在目录
        if( !dest.getParentFile().exists() ) {
            //新建文件夹
            dest.getParentFile().mkdirs();
        }
        //写入文件
        try {
            file.transferTo(dest);
            jobSubmitted.upAddress = fileName;
            return studentService.upJob(jobSubmitted);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 申请假期
     * @param vocation
     *      假期类
     * @return boolean
     */
    @PostMapping("/applyVocation")
    public boolean applyVocation(@RequestBody Vocation vocation) {
        if( vocation == null ) {
            return false;
        }else{
            return studentService.addVocation(vocation);
        }
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
    @GetMapping("/getAllVocation")
    public List<Vocation> getAllVocation(@RequestParam("courseId") int courseId, @RequestParam("stuId") long stuId ) {
        if( courseId != 0 && stuId != 0 ) {
            return studentService.getAllVocation(courseId, stuId);
        }else{
            return null;
        }
    }
}
