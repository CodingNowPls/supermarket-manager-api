package com.rabbiter.market.person.service;

import com.rabbiter.market.person.domain.Employee;
import com.rabbiter.market.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 员工登录业务接口
 */
public interface ILoginService extends IService<Employee> {
    /**
     * 处理员工登录业务
     *
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> login(String username, String password);

    /**
     * 退出功能
     *
     */
    void exit();

    /**
     * 注销账户功能
     *
     * @param content
     */
    void logout(String content);

    /**
     * 登录者拥有的菜单信息
     *
     * @return
     */
    List<Menu> empMenu();

    Map<String, Object> checkedToken(String token);
}
