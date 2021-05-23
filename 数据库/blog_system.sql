/*
Navicat MySQL Data Transfer

Source Server         : mysql-124
Source Server Version : 80021
Source Host           : 192.168.1.201:3306
Source Database       : blog_system

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-01-27 11:16:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(40) NOT NULL COMMENT '菜单 ID',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父菜单 ID (0为顶级菜单)',
  `name` varchar(60) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `type` int DEFAULT '1' COMMENT '类型(1目录，2菜单，3按钮)',
  `code` varchar(60) DEFAULT NULL COMMENT '授权标识符',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `sort` int DEFAULT '1' COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('11', '0', '首页', '/dashboard', '1', 'index', 'el-icon-s-home', '1', '', '2023-08-08 11:11:11', '2020-04-24 22:28:38');
INSERT INTO `sys_menu` VALUES ('1251060960026705921', '0', '博客管理', '/blog', '1', 'blog', 'el-icon-notebook-2', '2', null, '2020-04-17 16:12:31', '2020-12-23 11:27:20');
INSERT INTO `sys_menu` VALUES ('1251061016268128258', '1251060960026705921', '标签管理', '/blog/label', '2', 'label', 'el-icon-collection-tag\nel-icon-collection-tag\nel-icon-collection-tag\nel-icon-collection-tagel-icon-collection-tag', '3', null, '2020-04-17 16:12:44', '2020-04-24 10:33:28');
INSERT INTO `sys_menu` VALUES ('1251061181913776129', '1251060960026705921', '分类管理', '/blog/category', '2', 'category', 'el-icon-s-order', '2', null, '2020-04-17 16:13:24', '2020-04-24 10:33:14');
INSERT INTO `sys_menu` VALUES ('1251061228965478402', '1251060960026705921', '文章管理', '/blog/article', '2', 'article', 'el-icon-notebook-1\nel-icon-notebook-1', '1', null, '2020-04-17 16:13:35', '2020-04-24 15:40:53');
INSERT INTO `sys_menu` VALUES ('1251061280744161281', '1251061228965478402', '查询', '', '3', 'article:search', '', '1', null, '2020-04-17 16:13:47', '2020-07-10 16:19:54');
INSERT INTO `sys_menu` VALUES ('1251061397639413762', '1251061228965478402', '删除', '', '3', 'article:delete', '', '4', null, '2020-04-17 16:14:15', '2020-07-10 16:24:52');
INSERT INTO `sys_menu` VALUES ('1251061460868546562', '1251061228965478402', '查看', '', '3', 'article:view', '', '3', null, '2020-04-17 16:14:30', '2020-07-30 15:26:04');
INSERT INTO `sys_menu` VALUES ('1251070494405349377', '1251061181913776129', '查询', '', '3', 'category:search', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251070561216417794', '1251061181913776129', '新增', '', '3', 'category:add', '', '2', null, '2020-04-17 16:50:40', '2020-07-10 16:24:30');
INSERT INTO `sys_menu` VALUES ('1251070617537531905', '1251061181913776129', '编辑', '', '3', 'category:edit', '', '3', null, '2020-04-17 16:50:53', '2020-07-10 16:24:37');
INSERT INTO `sys_menu` VALUES ('1251070746856312833', '1251061181913776129', '删除', '', '3', 'category:detele', '', '4', null, '2020-04-17 16:51:24', '2020-07-10 16:24:20');
INSERT INTO `sys_menu` VALUES ('1251071087492517890', '1251061016268128258', '查询', '', '3', 'label:search', '', '1', null, '2020-04-17 16:52:45', '2020-07-10 16:23:26');
INSERT INTO `sys_menu` VALUES ('1251071167758913538', '1251061016268128258', '新增', '', '3', 'label:add', '', '2', null, '2020-04-17 16:53:04', '2020-07-10 16:23:17');
INSERT INTO `sys_menu` VALUES ('1251071220363874305', '1251061016268128258', '编辑', '', '3', 'label:edit', '', '4', null, '2020-04-17 16:53:17', '2020-07-10 16:22:53');
INSERT INTO `sys_menu` VALUES ('1251071270213177345', '1251061016268128258', '删除', '', '3', 'label:delete', '', '4', null, '2020-04-17 16:53:29', '2020-07-10 16:23:03');
INSERT INTO `sys_menu` VALUES ('1251071340815896577', '18', '设置角色', '', '3', 'user:role', '', '5', null, '2020-04-17 16:53:46', '2020-07-10 16:27:47');
INSERT INTO `sys_menu` VALUES ('1251071383350333442', '18', '密码修改', '', '3', 'user:password', '', '6', null, '2020-04-17 16:53:56', '2020-07-10 16:27:52');
INSERT INTO `sys_menu` VALUES ('1251071493878632450', '0', '广告管理', '/advert/index', '2', 'adver', 'el-icon-picture-outline-round', '3', null, '2020-04-17 16:54:22', '2020-04-24 22:20:46');
INSERT INTO `sys_menu` VALUES ('1253512582655021057', '1251061228965478402', '审核', '', '3', 'article:audit', '', '2', null, '2020-04-24 10:34:23', '2020-07-10 16:25:27');
INSERT INTO `sys_menu` VALUES ('1253513166296616961', '0', '梦学谷官网', 'http://www.mengxuegu.com', '2', 'public', 'el-icon-link', '5', null, '2020-04-24 10:36:42', '2020-04-24 15:58:34');
INSERT INTO `sys_menu` VALUES ('1253592898971275265', '23', '分配权限', '', '3', 'role:permission', '', '5', null, '2020-04-24 15:53:32', '2020-07-10 16:30:22');
INSERT INTO `sys_menu` VALUES ('1281490789589049345', '1251071493878632450', '新增', '', '3', 'advert:add', '', '2', '新增广告按钮', '2020-07-10 15:29:47', '2020-07-10 16:24:01');
INSERT INTO `sys_menu` VALUES ('1281503969723801601', '1251071493878632450', '删除', '', '3', 'advert:delete', '', '4', null, '2020-07-10 16:22:10', '2020-07-10 16:22:10');
INSERT INTO `sys_menu` VALUES ('1281504074870808577', '1251071493878632450', '编辑', '', '3', 'advert:edit', '', '3', null, '2020-07-10 16:22:35', '2020-07-10 16:22:35');
INSERT INTO `sys_menu` VALUES ('1281504383378644993', '1251071493878632450', '查询', '', '3', 'advert:search', '', '1', null, '2020-07-10 16:23:48', '2020-07-10 16:23:48');
INSERT INTO `sys_menu` VALUES ('17', '0', '系统管理', '/system', '1', 'system', 'el-icon-setting', '4', null, '2023-08-08 11:11:11', '2020-04-24 22:28:56');
INSERT INTO `sys_menu` VALUES ('18', '17', '用户管理', '/system/user', '2', 'user', 'el-icon-user-solid', '1', null, '2023-08-08 11:11:11', '2020-07-10 16:28:26');
INSERT INTO `sys_menu` VALUES ('19', '18', '查询', '', '3', 'user:search', '', '1', '员工列表', '2023-08-08 11:11:11', '2020-07-10 16:26:49');
INSERT INTO `sys_menu` VALUES ('20', '18', '新增', '', '3', 'user:add', '', '2', '新增用户', '2023-08-08 11:11:11', '2020-07-10 16:26:15');
INSERT INTO `sys_menu` VALUES ('21', '18', '编辑', '', '3', 'user:edit', '', '3', '修改用户', '2023-08-08 11:11:11', '2020-07-10 16:27:38');
INSERT INTO `sys_menu` VALUES ('22', '18', '删除', '', '3', 'user:delete', '', '4', '删除用户', '2023-08-08 11:11:11', '2020-07-10 16:26:24');
INSERT INTO `sys_menu` VALUES ('23', '17', '角色管理', '/system/role', '2', 'role', 'el-icon-coin', '2', null, '2023-08-08 11:11:11', '2020-07-10 16:28:36');
INSERT INTO `sys_menu` VALUES ('24', '23', '查询', '', '3', 'role:search', '', '1', '角色列表', '2023-08-08 11:11:11', '2020-07-10 16:29:05');
INSERT INTO `sys_menu` VALUES ('25', '23', '新增', '', '3', 'role:add', '', '2', '新增角色', '2023-08-08 11:11:11', '2020-07-10 16:29:17');
INSERT INTO `sys_menu` VALUES ('26', '23', '修改', '', '3', 'role:edit', '', '3', '修改角色', '2023-08-08 11:11:11', '2020-07-10 16:29:53');
INSERT INTO `sys_menu` VALUES ('27', '23', '删除', '', '3', 'role:delete', '', '4', '删除角色', '2023-08-08 11:11:11', '2020-07-10 16:30:16');
INSERT INTO `sys_menu` VALUES ('28', '17', '菜单管理', '/system/menu', '2', 'menu', 'el-icon-menu', '3', null, '2023-08-08 11:11:11', '2020-07-10 16:28:43');
INSERT INTO `sys_menu` VALUES ('29', '28', '查询', '', '3', 'menu:search', '', '1', '权限列表', '2023-08-08 11:11:11', '2020-07-10 16:31:01');
INSERT INTO `sys_menu` VALUES ('30', '28', '新增', '', '3', 'menu:add', '', '2', '新增权限', '2023-08-08 11:11:11', '2020-07-10 16:31:12');
INSERT INTO `sys_menu` VALUES ('31', '28', '修改', '', '3', 'menu:edit', '', '3', '修改权限', '2023-08-08 11:11:11', '2020-07-10 16:31:29');
INSERT INTO `sys_menu` VALUES ('32', '28', '删除', '', '3', 'menu:delete', '', '4', '删除权限', '2023-08-08 11:11:11', '2020-07-10 16:31:21');
INSERT INTO `sys_menu` VALUES ('string11', 'string11', 'string11', 'string11', '1', 'string11', 'string11', '11', 'string11', '2020-12-23 11:35:42', '2020-12-23 11:35:42');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(40) NOT NULL COMMENT '角色 ID',
  `name` varchar(60) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '角色说明',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '普通管理员', '拥有查看权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_role` VALUES ('9', '超级管理员', '拥有所有的权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '主键 ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色 ID',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1287965925087313921', '9', '11');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313922', '9', '1251060960026705921');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313923', '9', '1251061228965478402');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313924', '9', '1251061280744161281');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508225', '9', '1253512582655021057');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508226', '9', '1251061460868546562');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508227', '9', '1251061397639413762');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508228', '9', '1251061181913776129');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508229', '9', '1251070494405349377');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508230', '9', '1251070561216417794');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508231', '9', '1251070617537531905');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508232', '9', '1251070746856312833');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508233', '9', '1251061016268128258');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508234', '9', '1251071087492517890');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508235', '9', '1251071167758913538');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508236', '9', '1251071270213177345');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508237', '9', '1251071220363874305');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508238', '9', '1251071493878632450');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508239', '9', '1281504383378644993');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508240', '9', '1281490789589049345');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508241', '9', '1281504074870808577');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508242', '9', '1281503969723801601');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508243', '9', '17');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508244', '9', '18');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508245', '9', '19');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508246', '9', '20');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508247', '9', '21');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508248', '9', '22');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508249', '9', '1251071340815896577');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702529', '9', '1251071383350333442');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702530', '9', '23');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702531', '9', '24');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702532', '9', '25');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702533', '9', '26');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702534', '9', '27');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702535', '9', '1253592898971275265');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702536', '9', '28');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702537', '9', '29');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702538', '9', '30');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702539', '9', '31');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702540', '9', '32');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702541', '9', '1253513166296616961');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942914', '10', '11');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942915', '10', '17');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942916', '10', '1251060960026705921');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(40) NOT NULL COMMENT '用户 ID',
  `username` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码，加密存储, admin/1234',
  `is_account_non_expired` int DEFAULT '1' COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int DEFAULT '1' COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int DEFAULT '1' COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int DEFAULT '1' COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(60) DEFAULT NULL COMMENT '昵称',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像url',
  `mobile` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `pwd_update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '密码更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', 'editor', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '1', '1', '1', '1', '编辑', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '16888886666', 'test1@qq.com', '2023-08-08 11:11:11', '2020-12-28 10:51:52', '2020-04-10 09:41:51');
INSERT INTO `sys_user` VALUES ('11', 'kefu', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '0', '1', '0', '0', '客服', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '13579246810', 'string', '2020-04-11 21:37:25', '2020-04-17 16:43:19', '2020-04-11 21:37:25');
INSERT INTO `sys_user` VALUES ('1253583309139775489', 'root', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '0', '1', '1', '1', '系统根用户', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '15888888888', null, '2020-04-24 15:15:26', '2020-07-25 09:16:36', '2020-04-24 15:15:26');
INSERT INTO `sys_user` VALUES ('1345372454662520834', 'string-001', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '1', '1', '1', '1', 'string-001', null, null, null, '2021-01-02 22:12:43', '2021-01-02 22:12:43', '2021-01-02 22:12:43');
INSERT INTO `sys_user` VALUES ('1345672843605573633', '1234', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '1', '1', '1', '1', 'test-001', 'test-001', 'test-001', 'test-001', '2021-01-03 18:06:20', '2021-01-03 18:06:20', '2021-01-03 18:06:20');
INSERT INTO `sys_user` VALUES ('1345735001223790594', '123456', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '1', '1', '1', '1', '123456-001', '123456-001', '123456-001', '123456-001', '2021-01-03 22:13:20', '2021-01-03 22:13:20', '2021-01-03 22:13:20');
INSERT INTO `sys_user` VALUES ('9', 'admin', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', '1', '1', '1', '1', '平台超管', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200522/8665d73ae2484bd28bc2ff4103538385.png', '18888888888', 'mengxuegu888@163.com', '2023-08-08 11:11:11', '2020-05-22 22:30:14', '2020-04-10 09:41:51');
INSERT INTO `sys_user` VALUES ('string', 'string', 'string', '0', '0', '0', '0', '又测试啦', 'http://www.baidu.com/112233.png', 'string', '112233@qq.com', '2020-12-28 11:30:58', '2021-01-17 14:31:58', '2020-12-28 13:06:22');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(40) NOT NULL COMMENT '主键 ID',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户 ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1287965845408120834', '9', '9');
INSERT INTO `sys_user_role` VALUES ('1343385360494813186', '10', '3');
INSERT INTO `sys_user_role` VALUES ('1343385360494813187', '10', '5');
INSERT INTO `sys_user_role` VALUES ('1343385360494813188', '10', '13');
