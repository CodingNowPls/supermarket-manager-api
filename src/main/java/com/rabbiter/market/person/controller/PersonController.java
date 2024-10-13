package com.rabbiter.market.person.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.person.qo.QueryEditPwd;
import com.rabbiter.market.person.service.IEmployeeService;
import com.rabbiter.market.person.vo.InformationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人信息
 *
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 修改个人的密码
     *
     * @param editPwd
     * @return
     */
    @HasPermission("person:edit_pwd")
    @PostMapping("/edit_pwd")
    public JsonResult edit_pwd(@RequestBody QueryEditPwd editPwd) {
        employeeService.edit_pwd(editPwd);
        return JsonResult.success();
    }

    @HasPermission("person:employee:update")
    @GetMapping("/information")
    public JsonResult information() {
        InformationVo vo = employeeService.information();
        return JsonResult.success(vo);
    }
}
