package com.rabbiter.market.person.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.sercurity.annotation.NoRequireLogin;
import com.rabbiter.market.common.util.PathUtils;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.person.qo.QueryEmp;
import com.rabbiter.market.person.service.IEmployeeService;
import com.rabbiter.market.person.vo.DetailEmpVo;
import com.rabbiter.market.person.vo.EditEmpVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/person/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @HasPermission("person:employee:list")
    @PostMapping("/list")
    public JsonResult pageByQo(QueryEmp qo) {
        Page<Employee> page = employeeService.pageByQo(qo);
        return JsonResult.success(page);
    }

    @HasPermission("person:employee:list")
    @GetMapping("/detail")
    public JsonResult detail(Long uid) {
        DetailEmpVo vo = employeeService.detail(uid);
        return JsonResult.success(vo);
    }

    /**
     * 上传图片到阿里云oss
     * 返回网络图片地址,uploaded:1:成功 0:失败
     *
     * @param upload
     * @return
     */
    @NoRequireLogin
    @PostMapping("/uploadImg")
    public Map<String, Object> uploadImg(@RequestParam("file") MultipartFile upload) {
        Map<String, Object> map = new HashMap<>();
        if (upload != null && upload.getSize() > 0) {
            String path;
            try {
                path = PathUtils.upload(upload);
                map.put("uploaded", 1);  //成功
                map.put("url", path);  //成功
            } catch (Exception e) {
                e.printStackTrace();
                map.put("uploaded", 0);  //失败
                Map<String, Object> errorMap = new HashMap<>();
                errorMap.put("message", e.getMessage());
                map.put("error", errorMap);
            }
        } else {
            map.put("uploaded", 0);  //失败
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "上传失败，图片文件异常");
            map.put("error", errorMap);
        }
        return map;
    }

    /*保存*/
    @HasPermission("person:employee:save")
    @PostMapping("/save")
    public JsonResult saveEmp(Employee employee, HttpServletRequest request) {
        employeeService.saveEmp(employee, (String) request.getHeader("token"));
        return JsonResult.success();
    }

    /*修改按钮*/
    @HasPermission("person:employee:update")
    @GetMapping("/editbtn")
    public JsonResult editbtn(Long uid) {
        EditEmpVo vo = employeeService.editbtn(uid);
        return JsonResult.success(vo);
    }

    /*修改员工业务*/
    @HasPermission("person:employee:update")
    @PostMapping("/update")
    public JsonResult updateEmp(Employee employee, HttpServletRequest request) {
        employeeService.updateEmp(employee, (String) request.getHeader("token"));
        return JsonResult.success();
    }

    /*离职业务*/
    @HasPermission("person:employee:update")
    @PostMapping("/deactivate")
    public JsonResult deactivate(Long id) {
        employeeService.deactivate(id);
        return JsonResult.success();
    }

    /*更改密码*/
    @HasPermission("person:employee:resetPwd")
    @PostMapping("/resetPwd")
    public JsonResult resetPwd(Long eid, String code) {
        employeeService.resetPwd(eid, code);
        return JsonResult.success();
    }
}
