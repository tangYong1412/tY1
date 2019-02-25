package com.example.educationManage.mapper;

import com.example.educationManage.model.ManagerLoginInfo;

import java.util.List;

/**
 * mapper层，教务人员接口
 *
 * @author 唐勇
 */

public interface ManagerMapper {
    /**
     * 新增教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return 成功操作的数目
     */
    int insertManager(ManagerLoginInfo managerLoginInfo);

    /**
     * 删除单个教务人员
     *
     * @param managerId
     *      教务人员Id
     * @return 成功操作的数目
     */
    int deleteManager(int managerId);

    /**
     * 获取单个教务人员
     *
     * @param managerId
     *      教务人员id
     * @return 教务人员
     */
    ManagerLoginInfo selectManagerById(int managerId);

    /**
     * 获取所有的教务人员
     *
     * @return 所有的教务人员
     */
    List<ManagerLoginInfo> selectAllManager();

    /**
     * 修改教务人员信息
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return 成功操作的数目
     */
    int updateManager(ManagerLoginInfo managerLoginInfo);
}
