package com.rabbiter.market.person.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.person.domain.Dept;
import com.rabbiter.market.person.qo.QueryDept;
import com.rabbiter.market.person.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 * @author gao
 */
@RestController
@Validated
@RequestMapping("/person/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    /*保存信息接口*/
    @HasPermission("person:dept:save")
    @PostMapping("/save")
    public JsonResult saveDept(@RequestBody Dept dept) {
        deptService.saveDept(dept);
        return JsonResult.success();
    }

    @HasPermission("person:dept:update")
    /*修改接口*/
    @PostMapping("/update")
    public JsonResult updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return JsonResult.success();
    }

    /*停用*/
    @HasPermission("person:dept:deactivate")
    @PostMapping("/deactivate")
    public JsonResult deactivate(@RequestParam Long id) {
        deptService.forbiddenRole(id);
        return JsonResult.success();
    }

    /*查询信息*/
    @GetMapping("/list")
    public JsonResult listByQo(QueryDept qo) {
        return JsonResult.success(deptService.listByQo(qo));
    }
}
