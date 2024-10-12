package com.rabbiter.market.person.service;

import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.person.qo.QueryEditPwd;
import com.rabbiter.market.person.qo.QueryEmp;
import com.rabbiter.market.person.vo.DetailEmpVo;
import com.rabbiter.market.person.vo.EditEmpVo;
import com.rabbiter.market.person.vo.InformationVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IEmployeeService extends IService<Employee> {


    /**
     * 修改个人密码业务
     *
     * @param editPwd
     * @param token
     */
    void edit_pwd(QueryEditPwd editPwd, String token);

    /**
     * 分页显示数据
     *
     * @param qo
     * @return
     */
    Page<Employee> pageByQo(QueryEmp qo);

    DetailEmpVo detail(Long uid);

    void saveEmp(Employee employee, String token);

    EditEmpVo editbtn(Long uid);

    void updateEmp(Employee employee, String token);

    void deactivate(Long id);

    /**
     * 重置密码
     *
     * @param eid
     * @param code
     */
    void resetPwd(Long eid, String code);

    InformationVo information(String token);
}
