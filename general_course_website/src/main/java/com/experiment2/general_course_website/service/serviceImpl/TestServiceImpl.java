package com.experiment2.general_course_website.service.serviceImpl;

import com.experiment2.gcw_common.gcw_common.domain.User;
import com.experiment2.general_course_website.mapper.TestMapper;
import com.experiment2.general_course_website.service.ITestService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TestServiceImpl
 * @Description:
 * @Author: TangYong
 * @Date: 2019-06-18
 * @Version: 1.0
 */

@Service
@Log4j
public class TestServiceImpl implements ITestService {
    @Autowired
    TestMapper testMapper;

    public User test() {
        return testMapper.test();
    }
}
