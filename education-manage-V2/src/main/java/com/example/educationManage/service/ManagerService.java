package com.example.educationManage.service;

import com.example.educationManage.model.ManagerLoginInfo;

import java.util.List;

/**
 * service层，教务人员接口
 *
 * @author 唐勇
 */

public interface ManagerService {
    /**
     * 新增教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    boolean addManager(ManagerLoginInfo managerLoginInfo);

    /**
     * 删除教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    boolean removeManager(int managerId);

    /**
     * 获取单个教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    ManagerLoginInfo getManagerById(int managerId);

    /**
     * 获取所有教务人员
     *
     * @return 所有教务人员
     */
    List<ManagerLoginInfo> getAllManager();

    /**
     * 修改教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    boolean changeManager(ManagerLoginInfo managerLoginInfo);
}
