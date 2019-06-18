package com.experiment2.general_course_website.mapper;

import com.experiment2.gcw_common.gcw_common.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: TestMapper
 * @Description:
 * @Author: TangYong
 * @Date: 2019-06-18
 * @Version: 1.0
 */
@Mapper
public interface TestMapper {
    User test();
}
