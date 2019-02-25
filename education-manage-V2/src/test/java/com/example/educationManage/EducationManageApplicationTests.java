package com.example.educationManage;

import com.example.educationManage.mapper.CourseMapper;
import com.example.educationManage.mapper.ManagerMapper;
import com.example.educationManage.mapper.StudentMapper;
import com.example.educationManage.mapper.TeacherMapper;
import com.example.educationManage.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationManageApplicationTests {
//    //--------------------教师接口测试---------------------------------------------
//    @Autowired
//    private TeacherMapper teacherMapper;
//
//    /**
//     * mapper层，新增教师接口测试
//     */
//    @Test
//    public void teacherMapperInsertTea() {
//        Teacher teacher = new Teacher();
//        teacher.teacherId = 102;
//        teacher.password = "12345";
//        teacher.teacherName = "李丽君";
//        teacher.teacherSex = "女";
//        teacher.educationBg = "硕士";
//
//        System.out.println(teacherMapper.insertTeacher(teacher));
//    }
//
//    /**
//     * mapper层，删除教师接口测试
//     */
//    @Test
//    public void teacherMapperDeleteTea() {
//        System.out.println(teacherMapper.deleteTeacher(102));
//    }
//
//    /**
//     * mapper层，获取所有教师接口测试
//     */
//    @Test
//    public void teacherMapperSelectAllTea() {
//        List<Teacher> teacherList = teacherMapper.selectAllTeacher();
//
//        for (Teacher t :
//                teacherList) {
//            System.out.println(t.teacherId + ", " + t.teacherName);
//        }
//    }
//
//    /**
//     * mapper层，获取单个教师的登录信息接口测试
//     */
//    @Test
//    public void teacherMapperSelectTeacherLoginInfo() {
//        TeacherLoginInfo teacherLoginInfo = teacherMapper.selectTeacherLoginInfoById(101);
//
//        System.out.println(teacherLoginInfo.teacherId + ", " + teacherLoginInfo.password);
//    }
//
//    /**
//     * mapper层，获取某位老师所有已开设课程接口测试
//     */
//    @Test
//    public void teacherMapperSelectAllCourseOpened() {
//        List<Course> courseList = teacherMapper.selectAllCourseOpenedById(102);
//
//        for (Course c :
//                courseList) {
//            System.out.println(c.courseId + ", " + c.courseName);
//        }
//    }
//
//    /**
//     * mapper层，获取某位教师开设的课程id、名称接口测试
//     */
//    @Test
//    public void teacherMapperSelectAllCourseId() {
//        List<CourseOpened> courseOpenedList = teacherMapper.selectAllCourseIdById(101);
//        for (CourseOpened c :
//                courseOpenedList) {
//            System.out.println(c.courseId + ", " + c.courseName);
//        }
//    }
//
//    /**
//     * mapper层，获取某课程的所有学生成绩接口测试
//     */
//    @Test
//    public void teacherMapperSelectAllGradeById() {
//        List<Student> studentList = teacherMapper.selectAllGradeById(11005);
//
//        for (Student s :
//                studentList) {
//            System.out.println(s.stuId + ", " + s.grade);
//        }
//    }
//
//    /**
//     * mapper层，获取教师姓名接口测试
//     */
//    @Test
//    public void teacherMapperSelectTeacherNameById() {
//        System.out.println(teacherMapper.selectTeacherNameById(101));
//    }
//
//    /**
//     * mapper层，修改教师信息接口测试
//     */
//    @Test
//    public void teacherMapperUpdateTeacher() {
//        Teacher teacher = new Teacher();
//        teacher.teacherId = 102;
//        teacher.password = "12345";
//        teacher.teacherName = "李立军";
//        teacher.teacherSex = "男";
//        teacher.educationBg = "硕士";
//
//        System.out.println(teacherMapper.updateTeacher(teacher));
//    }
//
//    /**
//     * mapper层，修改学生成绩接口测试
//     */
//    @Test
//    public void teacherMapperUpdateGrade() {
//        Map<String, Object> map =  new HashMap<>();
//        map.put("courseId", 11001);
//        map.put("stuId", 11703080409L);
//        map.put("grade", 84.5);
//
//        System.out.println(teacherMapper.updateGrade(map));
//    }
//
//    /**
//     * mapper层，新增作业接口测试
//     */
//    @Test
//    public void teacherMapperInsertJob() {
//        Job job = new Job();
//        job.courseId = 11001;
//        job.jobTitle = "Lack of Face-to-Face";
//        job.upTime = "2018-10-3";
//        job.endTime = "2018-10-5";
//        job.content = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
//        job.annex = "xxxxxxxxxxxxxxxxxx";
//
//        System.out.println(teacherMapper.insertJob(job));
//        System.out.println(job.jobId);
//    }

//    //--------------------学生接口测试---------------------------------------------
//    @Autowired
//    private StudentMapper studentMapper;
//
//    /**
//     * mapper层，新增学生接口测试
//     */
//    @Test
//    public void studentMapperInsertStu() {
//        Student student = new Student();
//        student.stuId = 11703080410L;
//        student.password = "12345";
//        student.stuName = "李四";
//        student.stuSex = "男";
//        student.faculty = "两江人工智能学院";
//
//        System.out.println(studentMapper.insertStudent(student));
//    }
//
//    /**
//     * mapper层，删除学生接口测试
//     */
//    @Test
//    public void studentMapperDeleteStu() {
//        System.out.println(studentMapper.deleteStudent(11703080409L));
//    }
//
//    /**
//     * mapper层，获取所有学生接口测试
//     */
//    @Test
//    public void studentMapperSelectAllStu() {
//        List<Student> studentList = studentMapper.selectAllStudent();
//
//        for (Student s :
//                studentList) {
//            System.out.println(s.stuId + ", " + s.stuName);
//        }
//    }
//
//    /**
//     * mapper层，获取单个学生接口测试
//     */
//    @Test
//    public void studentMapperSelctStu() {
//        Student student = studentMapper.selectStudentById(11703080409L);
//
//        System.out.println(student.stuName);
//    }
//
//    /**
//     * mapper层，获取学生登录信息接口测试
//     */
//    @Test
//    public void studentMapperSelectStuLoginInfo() {
//        StudentLoginInfo studentLoginInfo = studentMapper.selectStuLoginInfoById(11703080409L);
//
//        System.out.println(studentLoginInfo.stuId + ", " + studentLoginInfo.password);
//    }
//
//    /**
//     * mapper层，修改学生接口测试
//     */
//    @Test
//    public void studentMapperUpdateStu() {
//        Student student = new Student();
//        student.stuId = 11703080409L;
//        student.password = "12345";
//        student.stuName = "王五";
//        student.stuSex = "男";
//        student.faculty = "计算机科学与工程学院";
//
//        System.out.println(studentMapper.updateStudent(student));
//    }
//
//    /**
//     * mapper层，选择课程接口测试
//     */
//    @Test
//    public void studentMapperInsertCourseChose() {
//        CourseChose courseChose = new CourseChose();
//        courseChose.courseId = 11005;
//        courseChose.stuId = 11703080409L;
//        courseChose.withBook = "y";
//
//        System.out.println(studentMapper.insertCourseChose(courseChose));
//    }
//
//    /**
//     * mapper层，退选课程接口测试
//     */
//    @Test
//    public void studentMapperDeleteCourseChose() {
//        CourseChose courseChose = new CourseChose();
//        courseChose.courseId = 11001;
//        courseChose.stuId = 11703080409L;
//
//        System.out.println(studentMapper.deleteCourseChose(courseChose));
//    }
//
//    /**
//     * mapper层，获取所有已选课程接口测试
//     */
//    @Test
//    public void studentMapperSelectAllCourseChose() {
//        List<CourseChose> courseChoseList = studentMapper.selectCourseChoseById(11703080409L);
//
//        for (CourseChose c :
//                courseChoseList) {
//            System.out.println(c.courseId + ", " +c.withBook);
//        }
//    }
//    /**
//     * mapper层，获取某门课程所有作业接口测试
//     */
//    @Test
//    public void studentMapperSelectAllJob() {
//        List<Job> jobList = studentMapper.selectAllJobById(11001);
//
//        for (Job j :
//                jobList) {
//            System.out.println(j.jobId + ", " +j.jobTitle);
//        }
//    }
//
//    /**
//     * mapper层，获取作业提交次数接口测试
//     */
//    @Test
//    public void studentMapperSelectSubmitTime() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("jobId", 1);
//        map.put("stuId", 11703080409L);
//
//        System.out.println(studentMapper.selectSubmitTimeById(map));
//    }
//
//    /**
//     * mapper层，第一次提交作业接口测试
//     */
//    @Test
//    public void studentMapperUpdateJob() {
//        JobSubmitted jobSubmitted = new JobSubmitted();
//        jobSubmitted.jobId = 1;
//        jobSubmitted.stuId = 11703080409L;
//        jobSubmitted.submitTimes = 0;
//        jobSubmitted.upAddress = "xxxxxxxxxxxx";
//
//        System.out.println(studentMapper.insertJob(jobSubmitted));
//    }
//
//    /**
//     * mapper层，重新提交作业接口测试
//     */
//    @Test
//    public void studentMapperInsertJob() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("jobId", 1);
//        map.put("stuId", 11703080409L);
//
//        int time = studentMapper.selectSubmitTimeById(map);
//
//        JobSubmitted jobSubmitted = new JobSubmitted();
//        jobSubmitted.jobId = 1;
//        jobSubmitted.stuId = 11703080409L;
//        jobSubmitted.submitTimes = time + 1;
//        jobSubmitted.upAddress = "xxxxxxxxxxxxxx";
//
//        System.out.println(studentMapper.updateJobById(jobSubmitted));
//    }
//
//    /**
//     * mapper层，申请假期接口测试
//     */
//    @Test
//    public void studentMapperInsertVocation() {
//        Vocation vocation = new Vocation();
//        vocation.courseId = 11001;
//        vocation.stuId = 11703080409L;
//        vocation.beginTime = "2018-10-12";
//        vocation.endTime = "2018-10-15";
//        vocation.reason = "xxxxxxxxx";
//
//        System.out.println(studentMapper.insertVocation(vocation));;
//    }
//
//    /**
//     * mapper层，获取所有假期
//     */
//    @Test
//    public void studentMapperSelectAllVocation() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("stuId", 11703080409L);
//        map.put("courseId", 11001);
//        List<Vocation> vocationList = studentMapper.selectAllVocation(map);
//
//        for (Vocation v:
//             vocationList) {
//            System.out.println(v.vocationId + ", " + v.beginTime + ", " + v.endTime);
//        }
//    }

//    //--------------------课程接口测试---------------------------------------------
//    @Autowired
//    private CourseMapper courseMapper;
//
//    /**
//     * mapper层，新增课程接口测试
//     */
//    @Test
//    public void courseMapperInsert() {
//        Course course = new Course();
//        course.courseId = 11003;
//        course.teacherId = 101;
//        course.courseName = "中国电影鉴赏";
//        course.courseType = "通识教育";
//        course.credit = 2.0;
//        course.learnTime = "4.0-0.0";
//        course.stuNumber = 42;
//        course.choseNumber = 0;
//        course.remainNumber = course.stuNumber - course.choseNumber;
//        course.faculty = "xxx学院";
//        course.prepareCourse = "xxx";
//        course.courseIntroduction = "xxxxxxxxxxxxxxxxxxxxxxxxxxx";
//
//        System.out.println(courseMapper.insertCourse(course));
//    }
//
//    /**
//     * mapper层，删除课程接口测试
//     */
//    @Test
//    public void courseMapperDelete() {
//        System.out.println(courseMapper.deleteCourse(11005));
//    }
//
//    /**
//     * mapper层，获取所有课程接口测试
//     */
//    @Test
//    public void courseMapperSelectAll() {
//        List<Course> courseList = courseMapper.selectAllCourse();
//
//        for (Course c:courseList) {
//            System.out.println(c.courseId + ", " + c.courseName);
//        }
//    }
//
//    /**
//     * mapper层，修改课程接口测试
//     */
//    @Test
//    public void courseMapperUpdate() {
//        Course course = new Course();
//        course.courseId = 11001;
//        course.teacherId = 101;
//        course.courseName = "漫威发展历程";
//        course.courseType = "通识教育";
//        course.credit = 2.0;
//        course.learnTime = "4.0-0.0";
//        course.stuNumber = 42;
//        course.choseNumber = 0;
//        course.remainNumber = course.stuNumber - course.choseNumber;
//        course.faculty = "xxx学院";
//        course.prepareCourse = "xxx";
//        course.courseIntroduction = "xxxxxxxxxxxxxxxxxxxxxxxxxxx";
//
//        System.out.println(courseMapper.updateCourseById(course));
//    }

//    //--------------------教务人员接口测试---------------------------------------------
//    @Autowired
//    private ManagerMapper managerMapper;
//
//    /**
//     * mapper层，新增教务人员接口测试
//     */
//    @Test
//    public void managerMapperInsertTest() {
//        ManagerLoginInfo managerLoginInfo = new ManagerLoginInfo();
//        managerLoginInfo.managerId = 102;
//        managerLoginInfo.password = "1234";
//
//        int id = managerMapper.insertManager(managerLoginInfo);
//        System.out.println(id);
//    }
//
//    /**
//     * mapper层，删除教务人员接口测试
//     */
//    @Test
//    public void managerMapperDeleteTest() {
//        System.out.println(managerMapper.deleteManager(101));
//    }
//
//    /**
//     * mapper层，获取单个教务人员接口测试
//     */
//    @Test
//    public void managerMapperSelectById() {
//        ManagerLoginInfo managerLoginInfo = managerMapper.selectManagerById(101);
//
//        System.out.println(managerLoginInfo.managerId + ", " + managerLoginInfo.password);
//    }
//
//    /**
//     * mapper层，获取所有教务人员接口测试
//     */
//    @Test
//    public void managerMapperSelectAll() {
//        List<ManagerLoginInfo> managerLoginInfoList = managerMapper.selectAllManager();
//
//        for (ManagerLoginInfo m:
//             managerLoginInfoList ) {
//            System.out.println(m.managerId + ", " + m.password);
//        }
//    }
//
//    /**
//     * mapper层，修改教务人员信息接口测试
//     */
//    @Test
//    public void managerMapperUpdate(){
//        ManagerLoginInfo managerLoginInfo = new ManagerLoginInfo();
//        managerLoginInfo.managerId = 101;
//        managerLoginInfo.password = "akmm1412";
//
//        System.out.println(managerMapper.updateManager(managerLoginInfo));
//    }
}
