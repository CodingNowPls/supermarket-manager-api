package com.rabbiter.market.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.common.redis.constants.RedisKeys;
import com.rabbiter.market.common.redis.service.RedisTemplateService;
import com.rabbiter.market.person.domain.Dept;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.system.domain.Role;
import com.rabbiter.market.person.mapper.EmployeeMapper;
import com.rabbiter.market.person.qo.QueryEditPwd;
import com.rabbiter.market.person.qo.QueryDept;
import com.rabbiter.market.person.qo.QueryEmp;
import com.rabbiter.market.person.service.IEmployeeService;
import com.rabbiter.market.person.service.IDeptService;
import com.rabbiter.market.system.service.IRoleService;
import com.rabbiter.market.person.vo.DetailEmpVo;
import com.rabbiter.market.person.vo.EditEmpVo;
import com.rabbiter.market.person.vo.InformationVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.util.HttpRequestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IRoleService roleService;

    @Override
    public void editPwd(QueryEditPwd editPwd) {
        //获取缓存中的登录员工信息
        String token = HttpRequestUtil.getToken();
        String str = redisTemplateService.getCacheObject(token);
        Employee employee = JSONObject.parseObject(str, Employee.class);
        if (employee.getId() == 1L) {
            throw new BusinessException("该账户不能被修改");
        }
        //比对用户名是否一致
        if (!employee.getUsername().equals(editPwd.getUsername())) {
            //不一致
            throw new BusinessException("没有权限修改其他人的密码");
        }
        //比对旧密码是否输入正确
        if (!employee.getPassword().equals(editPwd.getOldPwd())) {
            throw new BusinessException("原密码输入有误");
        }
        //比对新密码和旧密码是否一致
        if (editPwd.getOldPwd().equals(editPwd.getNewPwd())) {
            throw new BusinessException("新密码和旧密码一致");
        }
        LambdaUpdateWrapper<Employee> wrapper = Wrappers.lambdaUpdate(Employee.class)
                .set(Employee::getPassword, editPwd.getNewPwd())
                .eq(Employee::getUsername, editPwd.getUsername());

        super.update(wrapper);
    }

    @Transactional
    @Override
    public Page<Employee> pageByQo(QueryEmp qo) {
        Page<Employee> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<Employee> wrapper = Wrappers.lambdaQuery(Employee.class);
        if (StringUtils.hasText(qo.getUsername())) {
            wrapper.like(Employee::getUsername, qo.getUsername());
        }
        if (StringUtils.hasText(qo.getAge())) {
            wrapper.eq(Employee::getAge, qo.getAge());
        }
        if (StringUtils.hasText(qo.getNickName())) {
            wrapper.like(Employee::getNickName, qo.getNickName());
        }
        if (StringUtils.hasText(qo.getAddress())) {
            wrapper.like(Employee::getAddress, qo.getAddress());
        }
        if (StringUtils.hasText(qo.getSex())) {
            wrapper.eq(Employee::getSex, qo.getSex());
        }
        wrapper.ne(Employee::getId, 1L);
        if (qo.getDeptId() != null) {
            wrapper.eq(Employee::getDeptId, qo.getDeptId());
        }
        super.page(page, wrapper);
        //补全部门信息
        List<Dept> depts = deptService.listByQo(new QueryDept());
        List<Employee> records = page.getRecords();
        if (records != null) {
            for (Employee record : records) {
                if (depts != null) {
                    for (Dept dept : depts) {
                        if (dept.getId().equals(record.getDeptId())) {
                            record.setDeptName(dept.getName());
                            break;
                        }
                    }
                }
            }
        }
        return page;
    }

    @Override
    public DetailEmpVo detail(Long uid) {
        DetailEmpVo vo = new DetailEmpVo();
        //查询员工信息
        Employee employee = super.getById(uid);
        BeanUtils.copyProperties(employee, vo);

        //补全角色信息
        Set<String> roleNames = new HashSet<>();
        if (employee.getIsAdmin() == true) {
            //查询所有角色
            List<Role> list = roleService.list();
            for (Role role : list) {
                roleNames.add(role.getName());

            }
        } else {
            Set<Role> roles = roleService.queryByEid(uid);
            for (Role role : roles) {
                roleNames.add(role.getName());
            }
        }
        vo.setRoleNames(roleNames);
        return vo;
    }

    @Override
    public void saveEmp(Employee employee) {
        String token = HttpRequestUtil.getToken();
        //校验参数是否有误
        if (!StringUtils.hasLength(employee.getPassword())) {
            employee.setPassword(Employee.DEFAULT_PWD);
        }
        if (!StringUtils.hasLength(employee.getUsername())) {
            throw new BusinessException("手机号不能为空");
        }
        if (!StringUtils.hasLength(employee.getIdCard())) {
            throw new BusinessException("身份证号不能为空");
        }
        if (employee.getAge() == null) {
            employee.setAge(18);
        } else if (employee.getAge() < 0 || employee.getAge() > 120) {
            throw new BusinessException("年龄值有误");
        }
        //校验用户是否已注册
        LambdaQueryWrapper<Employee> wrapper = Wrappers.lambdaQuery(Employee.class)
                .eq(Employee::getUsername, employee.getUsername());
        Employee one = super.getOne(wrapper);
        if (one != null) {
            throw new BusinessException("系统中已存在该账户");
        }
        employee.setState(Employee.STATE_NORMAL);
        if (!StringUtils.hasText(employee.getSex())) {
            employee.setSex(Employee.SEX_MEN);
        }
        if (!StringUtils.hasText(employee.getHeadImg())) {
            employee.setHeadImg(Employee.DEFAULT_HEAD_IMG);
        }
        employee.setCreateTime(new Date());
        employee.setIsAdmin(false);
        String nickName = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class).getNickName();
        employee.setCreateby(nickName);
        super.save(employee);
    }

    @Override
    public EditEmpVo editBtn(Long uid) {
        EditEmpVo vo = new EditEmpVo();
        Employee employee = super.getById(uid);
        BeanUtils.copyProperties(employee, vo);
        return vo;
    }

    @Override
    public void updateEmp(Employee employee) {
        String token = HttpRequestUtil.getToken();
        if (Employee.STATE_DEL.equals(employee.getState())) {
            if (employee.getIsAdmin()) {
                throw new BusinessException("不可以给系统管理者办理离职");
            }
        }
        LambdaQueryWrapper<Employee> wrapper = Wrappers.lambdaQuery(Employee.class);
        wrapper.ne(Employee::getId, employee.getId());
        if (StringUtils.hasText(employee.getUsername())) {
            wrapper.eq(Employee::getUsername, employee.getUsername());
        }
        if (StringUtils.hasText(employee.getIdCard())) {
            wrapper.or().eq(Employee::getIdCard, employee.getIdCard());
        }
        List<Employee> list = super.list(wrapper);
        if (list != null && list.size() > 0) {
            throw new BusinessException("系统已存在相同的用户名或身份证号");
        }

        if (Employee.STATE_DEL.equals(employee.getState())) {
            employee.setLeaveTime(new Date());
        } else {
            employee.setCreateTime(new Date());
            String nickName = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class).getNickName();
            employee.setCreateby(nickName);
        }
        super.updateById(employee);
    }

    @Override
    public void deactivate(Long id) {
        Employee employee = super.getById(id);
        if (employee.getIsAdmin()) {
            throw new BusinessException("不可以给系统管理者办理离职");
        }
        if (Employee.STATE_DEL.equals(employee.getState())) {
            throw new BusinessException("已是离职状态");
        }
        LambdaUpdateWrapper<Employee> wrapper = Wrappers.lambdaUpdate(Employee.class)
                .set(Employee::getState, Employee.STATE_DEL)
                .eq(Employee::getId, id);
        super.update(wrapper);
    }

    @Transactional
    @Override
    public void resetPwd(Long eid, String code) {
        Employee employee = super.getById(eid);
        if (employee.getId() == 1L) {
            throw new BusinessException("该账户不可被修改");
        }
        if (employee.getIsAdmin()) {
            if (code.equals("123456")) {
                LambdaUpdateWrapper<Employee> wrapper = Wrappers.lambdaUpdate(Employee.class)
                        .set(Employee::getPassword, Employee.DEFAULT_PWD)
                        .eq(Employee::getId, eid);
                super.update(wrapper);
            } else {
                throw new BusinessException("密钥错误");
            }
        } else {
            if (code.equals("456789")) {
                LambdaUpdateWrapper<Employee> wrapper = Wrappers.lambdaUpdate(Employee.class)
                        .set(Employee::getPassword, Employee.DEFAULT_PWD)
                        .eq(Employee::getId, eid);
                super.update(wrapper);
            } else {
                throw new BusinessException("密钥错误");
            }
        }
        redisTemplateService.deleteObject(RedisKeys.LOGIN_USER.join(employee.getUsername()));
        redisTemplateService.deleteObject(RedisKeys.DISABLEUSER.join(employee.getUsername()));
        redisTemplateService.deleteObject(RedisKeys.LOGIN_ERRO_PWDNUM.join(employee.getUsername()));
    }

    @Override
    public InformationVo information() {
        String token = HttpRequestUtil.getToken();
        Employee employee = JSONObject.parseObject(redisTemplateService.getCacheObject(token), Employee.class);
        DetailEmpVo detail = detail(employee.getId());
        InformationVo vo = new InformationVo();
        BeanUtils.copyProperties(detail, vo);
        return vo;

    }
}
