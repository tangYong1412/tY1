package com.example.educationManage.service.impl;

import com.example.educationManage.mapper.ManagerMapper;
import com.example.educationManage.model.ManagerLoginInfo;
import com.example.educationManage.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现service层教务人员接口
 *
 * @author 唐勇
 */

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 新增教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    @Override
    public boolean addManager(ManagerLoginInfo managerLoginInfo) {
        return 1 == managerMapper.insertManager(managerLoginInfo);
    }

    /**
     * 删除教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    @Override
    public boolean removeManager(int managerId) {
        return 1 == managerMapper.deleteManager(managerId);
    }

    /**
     * 获取单个教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    @Override
    public ManagerLoginInfo getManagerById(int managerId) {
        return managerMapper.selectManagerById(managerId);
    }

    /**
     * 获取所有教务人员
     *
     * @return 所有教务人员
     */
    @Override
    public List<ManagerLoginInfo> getAllManager() {
        return managerMapper.selectAllManager();
    }

    /**
     * 修改教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    @Override
    public boolean changeManager(ManagerLoginInfo managerLoginInfo) {
        return 1 == managerMapper.updateManager(managerLoginInfo);
    }
}
