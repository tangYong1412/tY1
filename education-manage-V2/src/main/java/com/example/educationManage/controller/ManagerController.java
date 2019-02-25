package com.example.educationManage.controller;

import com.example.educationManage.model.ManagerLoginInfo;
import com.example.educationManage.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller层，教务人员接口
 *
 * @author 唐勇
 */

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    /**
     * 新增教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    @PostMapping("/addManager")
    public boolean addManager(@RequestBody ManagerLoginInfo managerLoginInfo) {
        if( managerLoginInfo == null ) {
            return false;
        }else{
            return managerService.addManager(managerLoginInfo);
        }
    }

    /**
     * 删除教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    @GetMapping("/removeManager/{managerId}")
    public boolean removeManager(@PathVariable("managerId") int managerId) {
        if(managerId == 0) {
            return false;
        }else{
            return managerService.removeManager(managerId);
        }
    }

    /**
     * 获取单个教务人员
     *
     * @param managerId
     *      教务人员id
     * @return boolean
     */
    @GetMapping("/getManager/{managerId}")
    public ManagerLoginInfo getManager(@PathVariable("managerId") int managerId) {
        if( managerId == 0 ) {
            return null;
        }else{
            return managerService.getManagerById(managerId);
        }
    }

    /**
     * 获取所有教务人员
     *
     * @return 所有教务人员
     */
    @GetMapping("/getAllManager")
    public List<ManagerLoginInfo> getAllManager() {
        return managerService.getAllManager();
    }

    /**
     * 修改教务人员
     *
     * @param managerLoginInfo
     *      教务人员类
     * @return boolean
     */
    @PostMapping("/updateManager")
    public boolean changeManager(@RequestBody ManagerLoginInfo managerLoginInfo) {
        if(managerLoginInfo == null) {
            return false;
        }else{
            return managerService.changeManager(managerLoginInfo);
        }
    }
}
