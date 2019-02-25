package com.example.educationManage.model;

/**
 * 作业类
 *
 * @author 唐勇
 */

public class Job {
    /**
     * 作业id
     */
    public int jobId;

    /**
     * 课程id
     */
    public int courseId;

    /**
     * 作业标题
     */
    public String jobTitle;

    /**
     * 提交时间
     */
    public String upTime;

    /**
     * 截止时间
     */
    public String endTime;

    /**
     * 作业介绍
     */
    public String content;

    /**
     * 附件
     */
    public String annex;

    /**
     * 提交次数
     */
    public int submitTimes;
}
