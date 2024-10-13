package com.rabbiter.market.person.controller;

import com.rabbiter.market.common.sercurity.annotation.NoRequireLogin;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.system.domain.Menu;
import com.rabbiter.market.person.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author gao
 */
@RestController
@Validated
public class LoginController {
    @Autowired
    private ILoginService loginService;

    /**
     * 登入功能
     *
     * @param username
     * @param password
     * @return
     */
    @NoRequireLogin
    @PostMapping("/login")
    public JsonResult login(@NotEmpty(message = "账号不能为空") String username, @NotEmpty(message = "密码不能为空") String password) {
        Map<String, Object> map = loginService.login(username, password);
        return JsonResult.success(map);

    }


    /**
     * 退出功能
     *
     * @return
     */
    @NoRequireLogin
    @GetMapping("/exit")
    public JsonResult exit() {
        loginService.exit();
        return JsonResult.success();
    }

    @PostMapping("/logout")
    public JsonResult logout(@NotEmpty(message = "内容不能为空") String content) {
        loginService.logout(content);
        return JsonResult.success();
    }

    /**
     * 查询登录者的拥有的菜单
     *
     * @return
     */
    @GetMapping("/empMenu")
    public JsonResult empMenu() {
        List<Menu> menus = loginService.empMenu();
        return JsonResult.success(menus);
    }

    @NoRequireLogin
    @GetMapping("/checkedToken")
    public JsonResult checkedToken(String token) {
        Map<String, Object> map = loginService.checkedToken(token);
        return JsonResult.success(map);
    }
}
