package com.experiment2.general_course_website.controller;

import com.experiment2.gcw_common.gcw_common.controller.ITestController;
import com.experiment2.gcw_common.gcw_common.domain.User;
import com.experiment2.general_course_website.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: TangYong
 * @Date: 2019-06-18
 * @Version: 1.0
 */
@RestController
public class TestController implements ITestController {
    @Autowired
    ITestService service;

    @Override
    public User listUser() {
        return service.test();
    }
}
