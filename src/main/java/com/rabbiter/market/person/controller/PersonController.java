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

@RestController
@Validated
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 修改个人的密码
     *
     * @param request
     * @param editPwd
     * @return
     */
    @HasPermission("person:edit_pwd")
    @PostMapping("/edit_pwd")
    public JsonResult edit_pwd(HttpServletRequest request, @RequestBody QueryEditPwd editPwd) {
        String token = request.getHeader("token");
        employeeService.edit_pwd(editPwd, token);
        return JsonResult.success();
    }

    @HasPermission("person:employee:update")
    @GetMapping("/information")
    public JsonResult information(HttpServletRequest request) {
        String token = request.getHeader("token");
        InformationVo vo = employeeService.information(token);
        return JsonResult.success(vo);
    }
}
