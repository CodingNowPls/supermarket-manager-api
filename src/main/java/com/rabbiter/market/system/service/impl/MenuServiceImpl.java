package com.rabbiter.market.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.system.domain.Menu;
import com.rabbiter.market.system.mapper.MenuMapper;
import com.rabbiter.market.system.qo.MenuQuery;
import com.rabbiter.market.system.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAll() {
        // 查询目录菜单
        // select * from t_menu where type='0' and state='0'
        LambdaQueryWrapper<Menu> wrapper1 = Wrappers.lambdaQuery(Menu.class)
                .eq(Menu::getType, Menu.TYPE_CATALOGUE)
                .eq(Menu::getState, Menu.STATE_NORMAL);
        List<Menu> catalogs = super.list(wrapper1);
        if (catalogs.isEmpty()) {
            return null;
        }

        // 得到目录下的菜单信息
        for (Menu catalog : catalogs) {
            // select * from t_menu where type='1' and parent_id=#{id} and state='0'
            LambdaQueryWrapper<Menu> wrapper2 = Wrappers.lambdaQuery(Menu.class)
                    .eq(Menu::getType, Menu.TYPE_MENU)
                    .eq(Menu::getState, Menu.STATE_NORMAL)
                    .eq(Menu::getParentId, catalog.getId());
            List<Menu> menus = super.list(wrapper2);

            // 获取菜单下的按钮
            for (Menu menu : menus) {
                // select * from t_menu where type='2' and parent_id=#{id} and state='0'
                LambdaQueryWrapper<Menu> wrapper3 = Wrappers.lambdaQuery(Menu.class)
                        .eq(Menu::getType, Menu.TYPE_BUTTON)
                        .eq(Menu::getState, Menu.STATE_NORMAL)
                        .eq(Menu::getParentId, menu.getId());
                List<Menu> buttons = super.list(wrapper3);
                if (!buttons.isEmpty()) {
                    menu.setChildren(buttons);
                }
            }
            catalog.setChildren(menus);
        }
        return catalogs;

    }

    @Override
    public List<Menu> queryByRids(Set<Long> rids) {
        List<Menu> result = menuMapper.queryByRids(rids);
        if (result.isEmpty()) {
            return null;
        }
        //目录
        Set<Menu> catalogs = new HashSet<>();
        //菜单
        Set<Menu> menus = new HashSet<>();
        //按钮
        Set<Menu> buttons = new HashSet<>();

        Iterator<Menu> iterator1 = result.iterator();
        while (iterator1.hasNext()) {
            Menu item = iterator1.next();
            switch (item.getType()) {
                case Menu.TYPE_CATALOGUE:
                    catalogs.add(item);
                    break;
                case Menu.TYPE_MENU:
                    menus.add(item);
                    break;
                case Menu.TYPE_BUTTON:
                    buttons.add(item);
                    break;
            }
            iterator1.remove();
        }

        for (Menu catalog : catalogs) {
            catalog.setChildren(new ArrayList<>());
            for (Menu menu : menus) {
                menu.setChildren(new ArrayList<>());
                for (Menu button : buttons) {
                    //将按钮分配到对应的菜单下
                    if (button.getParentId() == menu.getId()) {
                        menu.getChildren().add(button);
                    }
                }
                //将菜单分配到对应的目录下
                if (menu.getParentId() == catalog.getId()) {
                    catalog.getChildren().add(menu);
                }
            }

        }
        List<Menu> catalogues = new ArrayList<>();
        //去重
        for (Menu catalog : catalogs) {
            catalogues.add(catalog);
        }
        return catalogues;
    }

    @Override
    public Page<Menu> queryPageByQo(MenuQuery qo) {
        Page<Menu> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        //查询目录
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery(Menu.class)
                .eq(Menu::getType, Menu.TYPE_CATALOGUE)
                .like(StringUtils.hasText(qo.getName()), Menu::getLabel, qo.getName());
        Page<Menu> page1 = super.page(page, wrapper);
        //补全目录的子集
        List<Menu> catalogs = page1.getRecords();
        if (catalogs == null || catalogs.isEmpty()) {
            return page1;
        }
        /*补全目录下的菜单*/
        for (Menu catalog : catalogs) {
            LambdaQueryWrapper<Menu> wrapper_menu = Wrappers.lambdaQuery(Menu.class)
                    .eq(Menu::getType, Menu.TYPE_MENU)
                    .eq(Menu::getParentId, catalog.getId());
            List<Menu> menus = super.list(wrapper_menu);
            if (menus == null || menus.isEmpty()) {
                continue;
            }
            /*补全菜单下的按钮*/
            for (Menu menu : menus) {
                LambdaQueryWrapper<Menu> wrapper_button = Wrappers.lambdaQuery(Menu.class)
                        .eq(Menu::getState, Menu.TYPE_BUTTON)
                        .eq(Menu::getParentId, menu.getId());
                List<Menu> buttons = super.list(wrapper_button);
                if (buttons == null || buttons.isEmpty()) {
                    continue;
                }
                menu.setChildren(buttons);
            }
            catalog.setChildren(menus);
        }
        return page1;
    }

    @Override
    public List<Long> listParentByIds(List<Long> ids) {
        List<Long> parentIds = new ArrayList<>();
        ArrayList<Long> btnIds = new ArrayList<>();
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery(Menu.class)
                .in(Menu::getId, ids);
        List<Menu> list = super.list(wrapper);

        if (list == null) {
            return parentIds;
        }
        for (Menu menu : list) {
            if (Menu.TYPE_MENU.equals(menu.getType())) {
                //菜单
                parentIds.add(menu.getParentId());
            } else {
                //按钮
                parentIds.add(menu.getParentId());
                btnIds.add(menu.getId());
            }
        }
        return parentIds;
    }
}
