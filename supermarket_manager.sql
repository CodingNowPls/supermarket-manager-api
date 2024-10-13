/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_mysql5.7
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : supermarket_manager

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2024-10-13 12:12:29
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`  varchar(20) COLLATE utf8_bin  DEFAULT NULL COMMENT '部门名称',
    `info`  varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    `state` char(2) COLLATE utf8_bin      DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department`
VALUES ('8', '销售部', '销售部门', '0');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `phone`       varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
    `email`       varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
    `address`     varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '住址',
    `sex`         char(1) COLLATE utf8_bin      DEFAULT NULL COMMENT '性别',
    `password`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
    `nick_name`   varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
    `head_img`    longtext COLLATE utf8_bin COMMENT '头像',
    `state`       varchar(1) COLLATE utf8_bin   DEFAULT NULL COMMENT '状态 0：在职 1：离职',
    `isAdmin`     bit(1)                        DEFAULT NULL COMMENT '是否是超管 1:是 0不是',
    `info`        varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    `createby`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
    `create_time` datetime                      DEFAULT NULL COMMENT '创建时间',
    `age`         int(11) DEFAULT NULL COMMENT '年龄',
    `deptId`      bigint(20) DEFAULT NULL COMMENT '部门主键',
    `id_card`     varchar(18) COLLATE utf8_bin  DEFAULT NULL COMMENT '身份证号',
    `leave_time`  datetime                      DEFAULT NULL COMMENT '离职时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee`
VALUES ('1', '13333333333', '123@qq.com', '北京市海淀区', '0', '123456', '张三',
        0x2F66696C65732F313639343332343237383239335F66696C652E6A7067, '0', '', '系统管理员', '张三',
        '2024-10-12 03:56:22', '999', null, '411111199905089999', null);
INSERT INTO `t_employee`
VALUES ('15', '14788888888', null, '北京市海淀区', '1', '123456', '李四',
        0x2F66696C65732F313639343433343136323435375F30372E6A7067, '0', '\0', '销售人员', '张三', '2023-09-11 12:10:04',
        '18', '8', '411111199501019999', null);
INSERT INTO `t_employee`
VALUES ('16', '15455555555', null, null, '1', '123456', '叶子',
        0x2F66696C65732F313639343433343136323435375F30372E6A7067, '0', '\0', '仓库管理员', '张三',
        '2023-09-11 13:06:52', '18', '8', '511111199501015555', null);

-- ----------------------------
-- Table structure for t_emp_role
-- ----------------------------
DROP TABLE IF EXISTS `t_emp_role`;
CREATE TABLE `t_emp_role`
(
    `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `eid` bigint(20) NOT NULL COMMENT '用户id',
    `rid` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_emp_role
-- ----------------------------
INSERT INTO `t_emp_role`
VALUES ('1', '1', '2');
INSERT INTO `t_emp_role`
VALUES ('20', '15', '3');
INSERT INTO `t_emp_role`
VALUES ('21', '16', '4');
INSERT INTO `t_emp_role`
VALUES ('22', '16', '5');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`          varchar(255) COLLATE utf8_bin                               DEFAULT NULL COMMENT '商品名',
    `createby`      varchar(20) COLLATE utf8_bin                                DEFAULT NULL COMMENT '创建者',
    `create_time`   datetime                                                    DEFAULT NULL COMMENT '创建时间',
    `category_id`   bigint(20) DEFAULT NULL COMMENT '商品分类id',
    `sell_price` double(10,2) DEFAULT NULL COMMENT '销售价格',
    `purchash_price` double(10,2) DEFAULT NULL COMMENT '进货价格',
    `update_time`   datetime                                                    DEFAULT NULL COMMENT '更改时间',
    `updateby`      varchar(255) COLLATE utf8_bin                               DEFAULT NULL COMMENT '更改者',
    `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_czech_ci DEFAULT NULL COMMENT '分类名',
    `cover_url`     varchar(255) COLLATE utf8_bin                               DEFAULT NULL COMMENT '商品封面',
  `state` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态',
    `residue_num`   bigint(20) DEFAULT NULL COMMENT '剩余数量',
    `info`          varchar(255) COLLATE utf8_bin                               DEFAULT NULL COMMENT '备注',
    `sales_volume`  bigint(20) DEFAULT NULL COMMENT '销量',
    `inventory`     bigint(20) DEFAULT NULL COMMENT '需库存量',
    `shelves`       bigint(20) DEFAULT NULL COMMENT '货架上需摆放的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods`
VALUES ('7', '运动鞋', '张三', '2023-09-11 12:20:02', '9', '150.00', '120.00', '2023-09-11 12:20:02', '张三', '服装',
        '/files/1694434783850_04.jpg', '0', '16', '适合打球跑步的运动鞋', '4', '100', '10');
INSERT INTO `t_goods`
VALUES ('8', '《PASLMS》英文版', '张三', '2023-09-11 12:21:11', '11', '55.00', '50.00', '2023-09-11 12:21:11', '张三',
        '书籍', '/files/1694434840737_05.jpg', '0', '59', '著名书籍《PASLMS》英文版', '1', '200', '50');
INSERT INTO `t_goods`
VALUES ('9', '桌椅套装', '张三', '2023-09-11 12:21:43', '13', '400.00', '350.00', '2023-09-11 12:22:00', '张三',
        '办公用具', '/files/1694434883855_03.jpg', '0', '0', '桌子加椅子', '10', '300', '20');
INSERT INTO `t_goods`
VALUES ('10', '短袖', '张三', '2023-09-11 12:22:50', '9', '60.00', '40.00', '2023-09-11 12:22:50', '张三', '服装',
        '/files/1694434945440_02.jpg', '0', '60', '夏季短袖', null, null, null);
INSERT INTO `t_goods`
VALUES ('11', '变形金刚玩具套装', '张三', '2023-09-11 12:30:45', '13', '165.00', '140.00', '2023-09-11 12:30:45',
        '张三', '办公用具', '/files/1694435421618_e04f06dc02b84d38bc4d1ba2b39add0d.jpg', '0', '19', '变形金刚玩具套装',
        '1', null, null);

-- ----------------------------
-- Table structure for t_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_category`;
CREATE TABLE `t_goods_category`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`  varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名',
    `info`  varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
    `state` char(2) COLLATE utf8_bin      DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_goods_category
-- ----------------------------
INSERT INTO `t_goods_category`
VALUES ('9', '服装', '衣服鞋袜', '0');
INSERT INTO `t_goods_category`
VALUES ('10', '零食', '饭后零食', '0');
INSERT INTO `t_goods_category`
VALUES ('11', '书籍', null, '0');
INSERT INTO `t_goods_category`
VALUES ('12', '饮料酒水', '饮料酒水类别', '0');
INSERT INTO `t_goods_category`
VALUES ('13', '办公用具', '办公用具', '0');

-- ----------------------------
-- Table structure for t_goods_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_stock`;
CREATE TABLE `t_goods_stock`
(
    `goods_id`    bigint(20) NOT NULL COMMENT '商品编号',
    `store_id`    bigint(20) NOT NULL COMMENT '仓库编号',
    `in_num`      bigint(20) NOT NULL COMMENT '入库数数量',
    `residue_num` bigint(20) NOT NULL COMMENT '剩余数量',
    `store_name`  varchar(255) COLLATE utf8_bin NOT NULL COMMENT '仓库名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_goods_stock
-- ----------------------------
INSERT INTO `t_goods_stock`
VALUES ('7', '5', '200', '180', '一号仓库');
INSERT INTO `t_goods_stock`
VALUES ('8', '5', '200', '140', '一号仓库');
INSERT INTO `t_goods_stock`
VALUES ('9', '5', '200', '190', '一号仓库');
INSERT INTO `t_goods_stock`
VALUES ('10', '5', '200', '140', '一号仓库');
INSERT INTO `t_goods_stock`
VALUES ('11', '5', '200', '180', '一号仓库');

-- ----------------------------
-- Table structure for t_goods_stock_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_stock_detail`;
CREATE TABLE `t_goods_stock_detail`
(
    `cn`            varchar(255) COLLATE utf8_bin NOT NULL,
    `goods_id`      bigint(20) NOT NULL,
    `goods_num`     int(11) DEFAULT NULL,
    `goods_name`    varchar(255) COLLATE utf8_bin NOT NULL,
    `goods_price` double DEFAULT NULL COMMENT '0：入库 1：出库',
    `type`          char(2) COLLATE utf8_bin      NOT NULL,
    `createid`      bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
    `state`         char(2) COLLATE utf8_bin      NOT NULL COMMENT '0：正常 1：过期 2：下架',
    `info`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `expiry_time`   datetime                      DEFAULT NULL COMMENT '过期时间',
    `createby`      varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `birth_time`    datetime                      DEFAULT NULL COMMENT '生产时间',
    `state1`        char(2) COLLATE utf8_bin      DEFAULT NULL COMMENT '0:正常 1：删除 2：待处理',
    `store_id`      bigint(20) DEFAULT NULL,
    `supplier_id`   bigint(20) DEFAULT NULL COMMENT '供货商编号',
    `supplier_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '供货商名称',
    `untreated_num` bigint(20) DEFAULT NULL COMMENT '待处理数量',
  PRIMARY KEY (`cn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_goods_stock_detail
-- ----------------------------
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212006578667522', '7', '200', '运动鞋', '120', '0', '1', '2023-09-11 12:32:04', '0', '运动鞋入库',
        '2029-06-25 16:00:00', '张三', '2023-09-10 16:00:00', '0', '5', '7', '阿里巴巴', null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212043736006658', '8', '200', '《PASLMS》英文版', '50', '0', '1', '2023-09-11 12:32:13', '0', '',
        '2029-06-25 16:00:00', '张三', '2023-09-10 16:00:00', '0', '5', '7', '阿里巴巴', null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212067161194498', '9', '200', '桌椅套装', '350', '0', '1', '2023-09-11 12:32:18', '0', '',
        '2029-06-25 16:00:00', '张三', '2023-09-10 16:00:00', '0', '5', '7', '阿里巴巴', null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212086803120129', '10', '200', '短袖', '40', '0', '1', '2023-09-11 12:32:23', '0', '',
        '2029-06-25 16:00:00', '张三', '2023-09-10 16:00:00', '0', '5', '7', '阿里巴巴', null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212102829555713', '11', '200', '变形金刚玩具套装', '140', '0', '1', '2023-09-11 12:32:27', '0', '',
        '2029-06-25 16:00:00', '张三', '2023-09-10 16:00:00', '0', '5', '7', '阿里巴巴', null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701212176804495361', '7', '20', '运动鞋', null, '1', '1', '2023-09-11 12:32:45', '0', '', null, '张三', null,
        '0', '5', null, null, null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701218295690104833', '8', '50', '《PASLMS》英文版', null, '1', '1', '2023-09-11 12:57:03', '0', '', null,
        '张三', null, '0', '5', null, null, null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701218352346763266', '9', '10', '桌椅套装', null, '1', '1', '2023-09-11 12:57:17', '0', '', null, '张三',
        null, '0', '5', null, null, null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701218386224156674', '10', '60', '短袖', null, '1', '1', '2023-09-11 12:57:25', '0', '', null, '张三', null,
        '0', '5', null, null, null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701218444214603778', '11', '20', '变形金刚玩具套装', null, '1', '1', '2023-09-11 12:57:39', '0', '', null,
        '张三', null, '0', '5', null, null, null);
INSERT INTO `t_goods_stock_detail`
VALUES ('1701219991275245570', '8', '10', '《PASLMS》英文版', null, '1', '1', '2023-09-11 13:03:48', '0', '', null,
        '张三', null, '0', '5', null, null, null);

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `name`     varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
    `phone`    varchar(11) COLLATE utf8_bin  NOT NULL COMMENT '手机号',
    `password` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '密码',
    `email`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
    `state`    char(2) COLLATE utf8_bin      NOT NULL COMMENT '状态',
    `info`     varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    `integral` bigint(20) DEFAULT NULL COMMENT '会员积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member`
VALUES ('5', '陈小明', '19955555555', '123456', '123@qq.com', '0', '新会员', '2422');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `label`        varchar(255) COLLATE utf8_bin NOT NULL COMMENT '名称',
    `purl`         varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
    `type`         varchar(255) COLLATE utf8_bin NOT NULL COMMENT '类型 0:目录 1 菜单 2 按钮',
    `parent_id`    bigint(20) DEFAULT NULL COMMENT '父id',
    `parent_label` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '父名称',
    `info`         varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    `state`        varchar(2) COLLATE utf8_bin   NOT NULL COMMENT '状态',
    `flag`         varchar(255) COLLATE utf8_bin NOT NULL COMMENT '权限的唯一标识',
    `icon`         varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
    `component`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '组件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu`
VALUES ('1', '销售管理', null, '0', null, null, null, '0', 'sale', 'iconfont icon-r-shield', null);
INSERT INTO `t_menu`
VALUES ('2', '人事管理', null, '0', null, null, null, '0', 'person', 'iconfont icon-r-team', null);
INSERT INTO `t_menu`
VALUES ('3', '个人中心', null, '0', null, null, null, '0', 'person', 'iconfont icon-r-user2', null);
INSERT INTO `t_menu`
VALUES ('4', '库存管理', null, '0', null, null, null, '0', 'inventory', 'iconfont icon-r-building', null);
INSERT INTO `t_menu`
VALUES ('5', '会员管理', null, '0', null, null, null, '0', 'member', 'iconfont icon-r-mark1', null);
INSERT INTO `t_menu`
VALUES ('6', '系统管理', null, '0', null, null, null, '0', 'system', 'iconfont icon-r-setting', null);
INSERT INTO `t_menu`
VALUES ('7', '密码修改', '/person/editPwd', '1', '3', '个人中心', null, '0', 'person:editPwd', 'iconfont icon-r-lock',
        'views/person/editPwd');
INSERT INTO `t_menu`
VALUES ('8', '角色管理', '/system/roleList', '1', '6', '系统管理', null, '0', 'system:role:list',
        'iconfont icon-r-user3', 'views/system/roleList');
INSERT INTO `t_menu`
VALUES ('9', '创建角色', null, '2', '8', '角色管理', null, '0', 'system:role:save', null, null);
INSERT INTO `t_menu`
VALUES ('10', '修改角色', null, '2', '8', '角色管理', null, '0', 'system:role:editRole', null, null);
INSERT INTO `t_menu`
VALUES ('11', '停用角色', null, '2', '8', '角色管理', null, '0', 'system:role:forbiddenRole', null, null);
INSERT INTO `t_menu`
VALUES ('12', '角色授权', null, '2', '8', '角色管理', null, '0', 'system:role:saveRolePermissions', null, null);
INSERT INTO `t_menu`
VALUES ('14', '菜单管理', '/system/menuList', '1', '6', '系统管理', null, '0', 'system:menu:list',
        'iconfont icon-r-list', 'views/system/menuList');
INSERT INTO `t_menu`
VALUES ('15', '仓库管理', '/inventory/warehouseList', '1', '4', '库存管理', null, '0', 'inventory:warehouse:list',
        'iconfont icon-r-building', 'views/inventory/warehouseList');
INSERT INTO `t_menu`
VALUES ('16', '新创仓库', '', '2', '15', '仓库管理', null, '0', 'inventory:warehouse:save', null, '');
INSERT INTO `t_menu`
VALUES ('17', '修改仓库', '', '2', '15', '仓库管理', null, '0', 'inventory:warehouse:update', null, '');
INSERT INTO `t_menu`
VALUES ('18', '停用仓库', '', '2', '15', '仓库管理', null, '0', 'inventory:warehouse:deactivate', null, '');
INSERT INTO `t_menu`
VALUES ('19', '商品管理', null, '0', null, null, null, '0', 'goods:category', 'iconfont icon-r-mark2', null);
INSERT INTO `t_menu`
VALUES ('20', '分类管理', '/goods/categoryList', '1', '19', '商品管理', null, '0', 'goods:category:list',
        'iconfont icon-r-list', 'views/goods/categoryList');
INSERT INTO `t_menu`
VALUES ('21', '创建商品分类', '', '2', '20', '商品分类管理', null, '0', 'goods:category:save', null, null);
INSERT INTO `t_menu`
VALUES ('22', '修改商品分类', '', '2', '20', '商品分类管理', null, '0', 'goods:category:update', null, null);
INSERT INTO `t_menu`
VALUES ('23', '停用商品分类', '', '2', '20', '商品分类管理', null, '0', 'goods:category:deactivate', null, null);
INSERT INTO `t_menu`
VALUES ('24', '部门管理', '/person/deptList', '1', '2', '人事管理', null, '0', 'person:dept:list',
        'iconfont icon-r-team', 'views/person/deptList');
INSERT INTO `t_menu`
VALUES ('25', '创建部门', null, '2', '24', '部门管理', null, '0', 'person:dept:save', null, null);
INSERT INTO `t_menu`
VALUES ('26', '修改部门', null, '2', '24', '部门管理', null, '0', 'person:dept:update', null, null);
INSERT INTO `t_menu`
VALUES ('27', '停用部门', null, '2', '24', '部门管理', null, '0', 'person:dept:deactivate', null, null);
INSERT INTO `t_menu`
VALUES ('28', '员工管理', '/person/employeeList', '1', '2', '人事管理', null, '0', 'person:employee:list',
        'iconfont icon-r-user2', 'views/person/employeeList');
INSERT INTO `t_menu`
VALUES ('29', '商品信息', '/goods/goodsList', '1', '19', '商品管理', null, '0', 'goods:goods:list',
        'iconfont icon-r-shield', 'views/goods/goodsList');
INSERT INTO `t_menu`
VALUES ('31', '会员信息', '/member/memberList', '1', '5', '会员管理', null, '0', 'member:member:list',
        'iconfont icon-r-mark1', 'views/member/memberList');
INSERT INTO `t_menu`
VALUES ('32', '个人资料', '/person/information', '1', '3', '个人中心', null, '0', 'person:employee:update',
        'iconfont icon-r-paper', 'views/person/information');
INSERT INTO `t_menu`
VALUES ('33', '出库明细', '/inventory/stockOutDetail', '1', '4', '库存管理', null, '0', 'inventory:stockOut:list',
        'iconfont icon-r-left', 'views/inventory/stockOutList');
INSERT INTO `t_menu`
VALUES ('34', '入库明细', '/inventory/stockInDetail', '1', '4', '库存管理', null, '0', 'inventor:stockIn:list',
        'iconfont icon-r-right', 'views/inventory/stockInList');
INSERT INTO `t_menu`
VALUES ('35', '销售主页', '/sale/saleMainList', '1', '1', '销售管理', null, '0', 'sale:saleMain:list',
        'iconfont icon-r-home', 'views/sale/saleMainList');
INSERT INTO `t_menu`
VALUES ('36', '销售记录', '/sale/saleRecordsList', '1', '1', '销售管理', null, '0', 'sale:saleRecords:list',
        'iconfont icon-r-paper', 'views/sale/saleRecordsList');
INSERT INTO `t_menu`
VALUES ('37', '供货商信息', '/inventory/supplierInfoList', '1', '4', '库存管理', null, '0', 'inventory:supplier:list',
        'iconfont icon-r-mark3', 'views/inventory/supplierList');
INSERT INTO `t_menu`
VALUES ('41', '积分商品', '/goods/pointGoodsList', '1', '19', '商品管理', null, '0', 'goods:pointGoods:list',
        'iconfont icon-r-mark1', 'views/goods/pointGoodsList');
INSERT INTO `t_menu`
VALUES ('42', '销售统计', '/goods/saleStatisticList', '1', '19', '商品管理', null, '0', 'goods:saleStatistic:list',
        'iconfont icon-r-add', 'views/goods/saleStatisticList');
INSERT INTO `t_menu`
VALUES ('43', '库存统计', '/inventory/stockStatisticList', '1', '4', '库存管理', null, '0',
        'inventory:stock:statistic:list', 'iconfont icon-r-add', 'views/inventory/stockStatisticList');
INSERT INTO `t_menu`
VALUES ('44', '积分兑换记录', '/sale/pointRedemption', '1', '1', '销售管理', null, '0', 'sale:pointRedemption:list',
        'iconfont icon-r-paper', 'views/sale/pointRedemption');
INSERT INTO `t_menu`
VALUES ('45', '员工创建', null, '2', '28', '员工管理', null, '0', 'person:employee:save', null, null);
INSERT INTO `t_menu`
VALUES ('46', '员工修改', null, '2', '28', '员工管理', null, '0', 'person:employee:update', null, null);
INSERT INTO `t_menu`
VALUES ('47', '员工分配职务', null, '2', '28', '员工管理', null, '0', 'person:employee:queryRoleIdsByEid', null, null);
INSERT INTO `t_menu`
VALUES ('48', '重置员工密码', null, '2', '28', '员工管理', null, '0', 'person:employee:resetPwd', null, null);
INSERT INTO `t_menu`
VALUES ('49', '商品入库', null, '2', '34', '入库明细', null, '0', 'inventory:stockIn:save', null, null);
INSERT INTO `t_menu`
VALUES ('50', '入库记录删除', null, '2', '34', '入库明细', null, '0', 'inventory:stockInDetail:del', null, null);
INSERT INTO `t_menu`
VALUES ('51', '商品出库', null, '2', '33', '出库明细', null, '0', 'inventory:stockOut:save', null, null);
INSERT INTO `t_menu`
VALUES ('52', '出库记录删除', null, '2', '33', '出库明细', null, '0', 'inventory:stockOutDetail:del', null, null);
INSERT INTO `t_menu`
VALUES ('53', '创建', null, '2', '37', '供货商信息', null, '0', 'inventory:supplier:save', null, null);
INSERT INTO `t_menu`
VALUES ('54', '修改', null, '2', '37', '供货商信息', null, '0', 'inventory:supplier:update', null, null);
INSERT INTO `t_menu`
VALUES ('55', '删除', null, '2', '37', '供货商信息', null, '0', 'inventory:supplier:deactivate', null, null);
INSERT INTO `t_menu`
VALUES ('56', '商品上架处理', null, '2', '40', '出库通知', null, '0', 'inventory:stockDetailOut:notice:saveOut_shelves',
        null, null);
INSERT INTO `t_menu`
VALUES ('57', '商品过期/下架处理', null, '2', '40', '出库通知', null, '0',
        'inventory:stockDetail:notice:resolveOutUntreatedForm', null, null);
INSERT INTO `t_menu`
VALUES ('58', '创建', null, '2', '31', '会员信息管理', null, '0', 'membe:member:save', null, null);
INSERT INTO `t_menu`
VALUES ('59', '兑换商品', null, '2', '31', '会员信息管理', null, '0',
        'sale:exchange_point_products_records:saveExchangePointProductRecords', null, null);
INSERT INTO `t_menu`
VALUES ('60', '修改', null, '2', '31', '会员信息管理', null, '0', 'member:member:update', null, null);
INSERT INTO `t_menu`
VALUES ('61', '删除', null, '2', '31', '会员信息管理', null, '0', 'membe:member:delMember', null, null);

-- ----------------------------
-- Table structure for t_point_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_point_goods`;
CREATE TABLE `t_point_goods`
(
    `goods_id`    bigint(20) DEFAULT NULL,
    `goods_name`  varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `integral`    bigint(20) DEFAULT NULL,
    `updateby`    varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `update_time` datetime                      DEFAULT NULL,
    `update_id`   bigint(20) DEFAULT NULL,
    `cover_url`   varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `state`       char(2) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_point_goods
-- ----------------------------
INSERT INTO `t_point_goods`
VALUES ('11', '变形金刚玩具套装', '50', '张三', '2023-09-11 12:59:48', '1',
        '/files/1694435421618_e04f06dc02b84d38bc4d1ba2b39add0d.jpg', '0');
INSERT INTO `t_point_goods`
VALUES ('10', '短袖', '60', '张三', '2023-09-11 12:59:57', '1', '/files/1694434945440_02.jpg', '0');
INSERT INTO `t_point_goods`
VALUES ('7', '运动鞋', '20', '张三', '2024-10-12 08:34:46', '1', '/files/1694434783850_04.jpg', '0');

-- ----------------------------
-- Table structure for t_point_redemption
-- ----------------------------
DROP TABLE IF EXISTS `t_point_redemption`;
CREATE TABLE `t_point_redemption`
(
    `cn`          varchar(255) COLLATE utf8_bin NOT NULL COMMENT '订单号',
    `goods_id`    bigint(20) DEFAULT NULL COMMENT '商品编号',
    `member_id`   bigint(20) DEFAULT NULL COMMENT '会员编号',
    `integral`    bigint(20) DEFAULT NULL COMMENT '积分',
    `update_time` datetime                      DEFAULT NULL COMMENT '最近操作时间',
    `updateby`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作者',
    `update_id`   bigint(20) DEFAULT NULL COMMENT '操作者编号',
    `state`       char(2) COLLATE utf8_bin      DEFAULT NULL COMMENT '状态',
    PRIMARY KEY (`cn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_point_redemption
-- ----------------------------
INSERT INTO `t_point_redemption`
VALUES ('1701220420541288450', '10', '5', '60', '2023-09-11 13:05:30', '张三', '1', '1');
INSERT INTO `t_point_redemption`
VALUES ('1843537181313560577', '11', '5', '50', '2024-10-08 06:21:30', '张三', '1', '1');
INSERT INTO `t_point_redemption`
VALUES ('1845019955126829057', '11', '5', '50', '2024-10-12 08:33:31', '张三', '1', '0');
INSERT INTO `t_point_redemption`
VALUES ('1845061153266819074', '11', '5', '50', '2024-10-12 11:17:13', '张三', '1', '0');
INSERT INTO `t_point_redemption`
VALUES ('1845309911476912130', '11', '5', '50', '2024-10-13 03:45:42', '李四', '15', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`  varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名',
    `info`  varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    `state` char(2) COLLATE utf8_bin      DEFAULT NULL COMMENT '状态 0： 正常 -1：停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role`
VALUES ('1', '系统管理员', '系统拥有者', '0');
INSERT INTO `t_role`
VALUES ('2', '超级系统拥有者', '超级系统拥有者', '0');
INSERT INTO `t_role`
VALUES ('3', '收银员', '负责销售收钱', '0');
INSERT INTO `t_role`
VALUES ('4', '仓库管理员', '负责管理库存', '0');
INSERT INTO `t_role`
VALUES ('5', '商品管理员', '负责商品货架的商品数量、通知仓库管理员入库和出库', '0');
INSERT INTO `t_role`
VALUES ('6', '人事主管', '负责管理部门信息和员工信息', '0');
INSERT INTO `t_role`
VALUES ('7', '员工', '超市工作人员', '0');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`
(
    `id`  int(11) NOT NULL AUTO_INCREMENT,
    `rid` bigint(20) NOT NULL COMMENT '角色id',
    `mid` bigint(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu`
VALUES ('64', '4', '32');
INSERT INTO `t_role_menu`
VALUES ('65', '4', '33');
INSERT INTO `t_role_menu`
VALUES ('66', '4', '34');
INSERT INTO `t_role_menu`
VALUES ('67', '4', '3');
INSERT INTO `t_role_menu`
VALUES ('68', '4', '4');
INSERT INTO `t_role_menu`
VALUES ('69', '4', '37');
INSERT INTO `t_role_menu`
VALUES ('70', '4', '7');
INSERT INTO `t_role_menu`
VALUES ('73', '4', '43');
INSERT INTO `t_role_menu`
VALUES ('74', '4', '15');
INSERT INTO `t_role_menu`
VALUES ('75', '4', '16');
INSERT INTO `t_role_menu`
VALUES ('76', '4', '17');
INSERT INTO `t_role_menu`
VALUES ('77', '4', '49');
INSERT INTO `t_role_menu`
VALUES ('78', '4', '18');
INSERT INTO `t_role_menu`
VALUES ('79', '4', '50');
INSERT INTO `t_role_menu`
VALUES ('80', '4', '51');
INSERT INTO `t_role_menu`
VALUES ('81', '4', '52');
INSERT INTO `t_role_menu`
VALUES ('82', '4', '53');
INSERT INTO `t_role_menu`
VALUES ('83', '4', '54');
INSERT INTO `t_role_menu`
VALUES ('84', '4', '55');
INSERT INTO `t_role_menu`
VALUES ('85', '4', '56');
INSERT INTO `t_role_menu`
VALUES ('86', '4', '57');
INSERT INTO `t_role_menu`
VALUES ('87', '5', '19');
INSERT INTO `t_role_menu`
VALUES ('88', '5', '20');
INSERT INTO `t_role_menu`
VALUES ('89', '5', '21');
INSERT INTO `t_role_menu`
VALUES ('90', '5', '22');
INSERT INTO `t_role_menu`
VALUES ('92', '5', '23');
INSERT INTO `t_role_menu`
VALUES ('93', '5', '41');
INSERT INTO `t_role_menu`
VALUES ('94', '5', '42');
INSERT INTO `t_role_menu`
VALUES ('95', '5', '29');
INSERT INTO `t_role_menu`
VALUES ('96', '6', '48');
INSERT INTO `t_role_menu`
VALUES ('97', '6', '2');
INSERT INTO `t_role_menu`
VALUES ('98', '6', '24');
INSERT INTO `t_role_menu`
VALUES ('99', '6', '25');
INSERT INTO `t_role_menu`
VALUES ('100', '6', '26');
INSERT INTO `t_role_menu`
VALUES ('101', '6', '27');
INSERT INTO `t_role_menu`
VALUES ('102', '6', '28');
INSERT INTO `t_role_menu`
VALUES ('103', '6', '45');
INSERT INTO `t_role_menu`
VALUES ('104', '6', '46');
INSERT INTO `t_role_menu`
VALUES ('105', '6', '47');
INSERT INTO `t_role_menu`
VALUES ('109', '7', '32');
INSERT INTO `t_role_menu`
VALUES ('110', '7', '3');
INSERT INTO `t_role_menu`
VALUES ('111', '7', '7');
INSERT INTO `t_role_menu`
VALUES ('121', '3', '32');
INSERT INTO `t_role_menu`
VALUES ('122', '3', '1');
INSERT INTO `t_role_menu`
VALUES ('123', '3', '3');
INSERT INTO `t_role_menu`
VALUES ('124', '3', '35');
INSERT INTO `t_role_menu`
VALUES ('125', '3', '36');
INSERT INTO `t_role_menu`
VALUES ('126', '3', '5');
INSERT INTO `t_role_menu`
VALUES ('127', '3', '7');
INSERT INTO `t_role_menu`
VALUES ('128', '3', '44');
INSERT INTO `t_role_menu`
VALUES ('129', '3', '58');
INSERT INTO `t_role_menu`
VALUES ('130', '3', '59');
INSERT INTO `t_role_menu`
VALUES ('131', '3', '60');
INSERT INTO `t_role_menu`
VALUES ('132', '3', '61');
INSERT INTO `t_role_menu`
VALUES ('133', '3', '31');

-- ----------------------------
-- Table structure for t_sale_record
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_record`;
CREATE TABLE `t_sale_record`
(
    `cn`           varchar(255) COLLATE utf8_bin NOT NULL,
    `eid`          bigint(20) NOT NULL,
    `sellway`      char(2) COLLATE utf8_bin      NOT NULL,
  `sell_time` datetime NOT NULL,
    `state`        char(2) COLLATE utf8_bin      NOT NULL COMMENT '0:正常 1：删除',
    `info`         varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
    `sellby`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `sell_total`   bigint(20) NOT NULL COMMENT '销售总数量',
  `sell_totalmoney` double NOT NULL COMMENT '销售总金额',
    `type`         char(1) COLLATE utf8_bin      NOT NULL COMMENT '0:非会员消费 1：会员消费',
    `member_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '顾客会员号码',
  PRIMARY KEY (`cn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_sale_record
-- ----------------------------
INSERT INTO `t_sale_record`
VALUES ('1701213155180429314', '15', '0', '2023-09-11 12:37:16', '0', '', '李四', '1', '120', '0', null);
INSERT INTO `t_sale_record`
VALUES ('1701220094014722049', '1', '1', '2023-09-11 13:04:45', '0', '', '张三', '1', '315', '1', '19955555555');
INSERT INTO `t_sale_record`
VALUES ('1843536791302008833', '1', '0', '2024-10-08 06:20:34', '0', '', '张三', '4', '500', '0', null);
INSERT INTO `t_sale_record`
VALUES ('1843537235738849282', '1', '1', '2024-10-08 06:22:03', '0', '', '张三', '10', '3200', '0', null);

-- ----------------------------
-- Table structure for t_sale_record_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_record_detail`;
CREATE TABLE `t_sale_record_detail`
(
    `sell_cn`    varchar(255) COLLATE utf8_bin NOT NULL COMMENT '销售订单号',
    `goods_id`   bigint(20) NOT NULL COMMENT '商品编号',
    `goods_num`  bigint(20) NOT NULL COMMENT '商品数量',
    `goods_price` double(10,2) NOT NULL COMMENT '销售单价',
    `goods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_sale_record_detail
-- ----------------------------
INSERT INTO `t_sale_record_detail`
VALUES ('1701213155180429314', '7', '1', '120.00', '运动鞋');
INSERT INTO `t_sale_record_detail`
VALUES ('1701220094014722049', '9', '1', '350.00', '桌椅套装');
INSERT INTO `t_sale_record_detail`
VALUES ('1843536791302008833', '11', '1', '140.00', '变形金刚玩具套装');
INSERT INTO `t_sale_record_detail`
VALUES ('1843536791302008833', '7', '3', '120.00', '运动鞋');
INSERT INTO `t_sale_record_detail`
VALUES ('1843537235738849282', '8', '1', '50.00', '《PASLMS》英文版');
INSERT INTO `t_sale_record_detail`
VALUES ('1843537235738849282', '9', '9', '350.00', '桌椅套装');

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier`
(
    `cn`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
    `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
    `tel`     varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
    `info`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
    `state`   char(2) COLLATE utf8_bin      DEFAULT NULL,
    PRIMARY KEY (`cn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------
INSERT INTO `t_supplier`
VALUES ('7', '阿里巴巴', '浙江省杭州市', '19955555555', '1688商家供货商', '0');

-- ----------------------------
-- Table structure for t_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse`;
CREATE TABLE `t_warehouse`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`    varchar(255) COLLATE utf8_bin NOT NULL COMMENT '仓库名称',
    `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '仓库地址',
    `state`   char(2) COLLATE utf8_bin      NOT NULL COMMENT '状态',
    `info`    varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_warehouse
-- ----------------------------
INSERT INTO `t_warehouse`
VALUES ('5', '一号仓库', '北京市海淀区', '0', '超市一号仓库');
