package com.rabbiter.market.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.person.domain.Dept;
import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.person.mapper.DeptMapper;
import com.rabbiter.market.person.qo.QueryDept;
import com.rabbiter.market.person.service.IEmployeeService;
import com.rabbiter.market.person.service.IDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public List<Dept> listByQo(QueryDept qo) {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<Dept>()
                .like(StringUtils.hasText(qo.getName()), Dept::getName, qo.getName())
                .eq(StringUtils.hasText(qo.getState()), Dept::getState, qo.getState());
        return super.list(wrapper);
    }

    @Transactional
    @Override
    public void forbiddenRole(Long id) {
        LambdaQueryWrapper<Employee> empWrapper = Wrappers.lambdaQuery(Employee.class)
                .eq(id != null, Employee::getDeptId, id);
        List<Employee> list = employeeService.list(empWrapper);
        if (list != null && list.size() > 0) {
            throw new BusinessException("操作失败，该部门正在使用");
        }
        LambdaUpdateWrapper<Dept> wrapper = Wrappers.lambdaUpdate(Dept.class)
                .set(Dept::getState, Dept.STATE_BAN)
                .eq(Dept::getId, id);
        super.update(wrapper);
    }

    @Transactional
    @Override
    public void saveDept(Dept dept) {
        //判断是否有被创建
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class)
                .eq(StringUtils.hasText(dept.getName()), Dept::getName, dept.getName());
        if (super.getOne(wrapper) != null) {
            throw new BusinessException("操作失败，该部门已存在");
        }
        dept.setState(Dept.STATE_NORMAL);
        super.save(dept);

    }

    @Transactional
    @Override
    public void updateDept(Dept dept) {
        if (Dept.STATE_BAN.equals(dept.getState())) {
            QueryWrapper<Employee> empWrapper = new QueryWrapper<Employee>().eq(dept.getId() != null, "deptId", dept.getId());
            List<Employee> list = employeeService.list(empWrapper);
            if (list != null && list.size() > 0) {
                throw new BusinessException("操作失败，该部门正在使用");
            }
        }
        super.updateById(dept);
    }
}
