/*
Navicat MySQL Data Transfer

Source Server         : mysql-124
Source Server Version : 80021
Source Host           : 192.168.1.201:3306
Source Database       : blog_article

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-01-27 11:16:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_advert
-- ----------------------------
DROP TABLE IF EXISTS `b_advert`;
CREATE TABLE `b_advert` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '广告标题',
  `image_url` varchar(255) DEFAULT NULL COMMENT '广告图片',
  `advert_url` varchar(255) DEFAULT NULL COMMENT '广告链接',
  `advert_target` varchar(255) DEFAULT NULL COMMENT '广告跳转方式（_blank：新窗口打开，_self：当前窗口打开）',
  `position` tinyint DEFAULT '1' COMMENT '广告位置(1:首页轮播)',
  `status` tinyint DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='广告信息表';

-- ----------------------------
-- Records of b_advert
-- ----------------------------
INSERT INTO `b_advert` VALUES ('1246008989731442689', '梦学谷', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/ac91105eef9040a883279318f0f2b769.png', 'https://ke.qq.com/course/470253?tuin=2be5e632', '_blank', '1', '1', '2', '2020-04-03 17:37:47', '2020-04-03 17:37:47');
INSERT INTO `b_advert` VALUES ('1263472520185675778', 'nuxt.js广告', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/f52b4ca9eac14656aedbd65a5f661b6d.jpg', 'https://ke.qq.com/course/2803878?tuin=2be5e632', '_blank', '1', '1', '1', '2020-05-21 22:11:37', '2020-05-21 22:11:37');
INSERT INTO `b_advert` VALUES ('1288294369008484354', '前端vue.js全家桶前后端分离项目实战', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/9d83082760e84c15a685d6e61338174a.png', 'https://ke.qq.com/course/466900?tuin=2be5e632', '_blank', '2', '1', '1', '2020-07-29 10:04:47', '2020-07-29 10:04:47');
INSERT INTO `b_advert` VALUES ('1288294797553106945', 'springsecurity oauth2全家桶', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/5a4f0b1a93e54657b50a93786082e4b7.jpg', 'https://ke.qq.com/course/483312?tuin=2be5e632', '_blank', '1', '1', '2', '2020-07-29 10:06:29', '2020-07-29 10:06:29');
INSERT INTO `b_advert` VALUES ('1288295465659596801', 'SpringCloud分布式微服务架构+Docker容器化部署', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/38d4f0fefe5b4fb49e4d04f75d07b142.jpg', 'https://ke.qq.com/course/470275?tuin=2be5e632', '_blank', '2', '1', '2', '2020-07-29 10:09:09', '2020-07-29 10:09:09');
INSERT INTO `b_advert` VALUES ('1288295465659596802', 'SpringCloud分布式微服务架构+Docker容器化部署', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/38d4f0fefe5b4fb49e4d04f75d07b142.jpg', 'https://ke.qq.com/course/470275?tuin=2be5e632', '_blank', '2', '1', '2', '2020-07-29 10:09:09', '2020-07-29 10:09:09');
INSERT INTO `b_advert` VALUES ('1351166406087000066', 'string11', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/38d4f0fefe5b4fb49e4d04f75d07b142.jpg', 'https://ke.qq.com/course/470275?tuin=2be5e632', '_blank', '100', '0', '110', '2021-01-18 21:55:40', '2021-01-18 21:56:35');

-- ----------------------------
-- Table structure for b_article
-- ----------------------------
DROP TABLE IF EXISTS `b_article`;
CREATE TABLE `b_article` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `user_id` varchar(40) DEFAULT NULL COMMENT '发布者用户id',
  `nick_name` varchar(40) DEFAULT NULL COMMENT '发布者用户昵称',
  `user_image` varchar(255) DEFAULT NULL COMMENT '发布者头像url',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `summary` text COMMENT '文章简介',
  `image_url` varchar(255) DEFAULT NULL COMMENT '文章主图地址',
  `md_content` longtext COMMENT 'md主体内容',
  `html_content` longtext COMMENT 'html主体内容',
  `view_count` int DEFAULT '0' COMMENT '浏览次数',
  `thumhup` int DEFAULT '0' COMMENT '点赞数',
  `status` tinyint DEFAULT '1' COMMENT '0: 已删除, 1:未审核，2:审核通过，3：审核未通过',
  `ispublic` tinyint DEFAULT '1' COMMENT '0：不公开，1：公开',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章信息表';

-- ----------------------------
-- Records of b_article
-- ----------------------------
INSERT INTO `b_article` VALUES ('1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '更新2-博客系统采用 Vue.js + ElementUI 来搭建博客后台管理系统。', '使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/ea90bff14995488e9c2791a8694df38c.png', '# vue-element-admin 概述\n![aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg](https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/4a8e837091584673a4c3fd92647a4f7a.jpeg)\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 是一个后台前端解决方案，它基于 [vue](https://github.com/vuejs/vue) 和 [element-ui](https://github.com/ElemeFE/element)实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。\n\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。\n\n官方还提供了一套基础模板 [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) ，我们基于它进行二次开发，想要什么功能或者组件就去 `vue-element-admin` 那里复制过来。\n\n\n\n## 参考：\n\n- 官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/\n\n- [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-element-admin\n\n  `gitee`  ：https://gitee.com/mirrors/vue-element-admin\n\n  `github` ：https://github.com/PanJiaChen/vue-element-admin\n\n- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-admin-template\n\n  `github` 源码：https://github.com/PanJiaChen/vue-admin-template\n\n', '<h1><a id=\"vueelementadmin__0\"></a>vue-element-admin 概述</h1>\n<p><img src=\"https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/4a8e837091584673a4c3fd92647a4f7a.jpeg\" alt=\"aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg\" /><br />\n<a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 是一个后台前端解决方案，它基于 <a href=\"https://github.com/vuejs/vue\" target=\"_blank\">vue</a> 和 <a href=\"https://github.com/ElemeFE/element\" target=\"_blank\">element-ui</a>实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。</p>\n<p><a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。</p>\n<p>官方还提供了一套基础模板 <a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> ，我们基于它进行二次开发，想要什么功能或者组件就去 <code>vue-element-admin</code> 那里复制过来。</p>\n<h2><a id=\"_10\"></a>参考：</h2>\n<ul>\n<li>\n<p>官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-element-admin</p>\n<p><code>gitee</code>  ：https://gitee.com/mirrors/vue-element-admin</p>\n<p><code>github</code> ：https://github.com/PanJiaChen/vue-element-admin</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-admin-template</p>\n<p><code>github</code> 源码：https://github.com/PanJiaChen/vue-admin-template</p>\n</li>\n</ul>\n', '225', '2', '1', '1', '2020-01-07 10:38:07', '2020-12-13 20:10:54');
INSERT INTO `b_article` VALUES ('1244940138650423298', '1', null, null, '修改标题xxxxx', '122', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/bbd8b38ba5e0444fa2075ef28dfeec75.png', '1222', '<p>1222</p>\n', '0', '1', '2', '1', '2020-02-07 10:38:07', '2020-04-16 21:09:14');
INSERT INTO `b_article` VALUES ('1245884663208275970', '2', null, null, 'test', '123', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200403/d8cdd8ae405f47f3baadcd6c852dd6b1.png', '123', '<p>123</p>\n', '0', '2', '2', '1', '2020-02-26 10:38:07', '2020-04-17 16:58:21');
INSERT INTO `b_article` VALUES ('1245894712647413762', '2', null, null, '图片', '123', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200403/37cd9bca2f2e40bdabfd25ac16b35257.png', '1', '<p>1</p>\n', '0', '2', '2', '1', '2020-03-07 10:38:07', '2020-04-16 21:13:30');
INSERT INTO `b_article` VALUES ('1246718260580331521', '2', null, null, 'gtest', '123', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/8207df8964c44b47850fc4135042bce7.png', '123', '<p>123</p>\n', '1', '2', '2', '1', '2020-04-07 10:38:07', '2020-04-16 17:09:05');
INSERT INTO `b_article` VALUES ('1256827283824635906', 'string', '又测试啦', 'http://www.baidu.com/112233.png', 'string', 'string', 'string', 'string', 'string', '0', '0', '0', '1', '2020-05-03 14:04:05', '2020-07-25 16:02:25');
INSERT INTO `b_article` VALUES ('1263079731933392898', null, null, null, 'test', '1231', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/e34b0c224b6249c997a852d6def27c5f.jpeg', '1231231', '<p>1231231</p>\n', '0', '0', '2', '1', '2020-05-20 20:10:49', '2020-05-20 20:10:49');
INSERT INTO `b_article` VALUES ('1263079788816543745', null, null, null, 'test', '1231', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/e34b0c224b6249c997a852d6def27c5f.jpeg', '1231231', '<p>1231231</p>\n', '0', '0', '2', '1', '2020-05-20 20:11:03', '2020-05-20 20:11:03');
INSERT INTO `b_article` VALUES ('1263079980475265025', null, null, null, 'test', '1231', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/e34b0c224b6249c997a852d6def27c5f.jpeg', '1231231', '<p>1231231</p>\n', '0', '0', '2', '1', '2020-05-20 20:11:48', '2020-05-20 20:11:48');
INSERT INTO `b_article` VALUES ('1263080137249959937', null, null, null, 'test', '1231', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/e34b0c224b6249c997a852d6def27c5f.jpeg', '1231231', '<p>1231231</p>\n', '0', '0', '2', '1', '2020-05-20 20:12:26', '2020-05-20 20:12:26');
INSERT INTO `b_article` VALUES ('1263080212978118658', null, null, null, 'test', '1231', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/e34b0c224b6249c997a852d6def27c5f.jpeg', '1231231', '<p>1231231</p>\n', '0', '0', '2', '1', '2020-05-20 20:12:44', '2020-05-20 20:12:44');
INSERT INTO `b_article` VALUES ('1263080327776219138', null, null, null, '123123', '123123', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/d5bbc19724314f018e1a4e792715dfbf.jpeg', 'afdasdfsdf', '<p>afdasdfsdf</p>\n', '8', '0', '2', '1', '2020-07-20 20:13:11', '2020-05-20 20:14:41');
INSERT INTO `b_article` VALUES ('1263081450994053121', null, null, null, '123', 'sdfsdf', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/9a315121b8bc40cb895dc2d064ff5536.jpeg', 'sdfsdf', '<p>sdfsdf</p>\n', '4', '3', '2', '1', '2020-05-20 20:17:39', '2020-05-20 20:18:09');
INSERT INTO `b_article` VALUES ('1263091654519336962', null, '梦学谷', null, 'asdfsdfsdfgdfg', 'asdsdfsdfgdg', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/ab53fbaabc5a49d585adb5db8f0e390f.jpeg', 'dddddd', '<p>dddddd</p>\n', '3', '5', '2', '1', '2020-07-20 20:58:12', '2020-05-20 20:58:12');
INSERT INTO `b_article` VALUES ('1263092250819342337', null, '梦学谷', null, 'wwwwww', 'wwwwwww', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/6a14d3e018494d63a1b7c1203c119029.jpeg', 'wwwwww', '<p>wwwwww</p>\n', '39', '11', '2', '1', '2020-05-20 21:00:34', '2020-05-20 21:00:34');
INSERT INTO `b_article` VALUES ('1263092800898117633', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', 'xxxxxx', 'asdfsdf', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/0e77c5e95b644295ab01bf3e28980d9e.jpeg', 'asdfsdfd', '<p>asdfsdfd</p>\n', '1', '0', '1', '1', '2020-05-20 21:02:45', '2020-05-20 21:02:45');
INSERT INTO `b_article` VALUES ('1263095988200058881', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '测试', '测试', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/20e5749dcca04789ab290220ed62563b.png', '# 一级标题**测试**', '<h1><a id=\"_0\"></a>一级标题<strong>测试</strong></h1>\n', '0', '0', '1', '1', '2020-05-20 21:15:25', '2020-05-20 21:15:25');
INSERT INTO `b_article` VALUES ('1263098950418870273', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '梦学谷', '梦学谷梦学谷梦学谷梦学谷', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/9b96373ea7e141438655ca28f480b862.jpeg', '梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷', '<p>梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷梦学谷</p>\n', '11', '0', '1', '1', '2020-05-20 21:27:11', '2020-05-21 12:12:42');
INSERT INTO `b_article` VALUES ('1263442805739651073', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '梦学---xxxx', 'asdfsd', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200521/9ebdf5e241d047a9afcab4af7ec5ed39.jpeg', 'sdfsdfdfd', '<p>sdfsdfdfd</p>\n', '3', '2', '2', '0', '2020-05-21 20:13:33', '2020-05-22 17:09:34');
INSERT INTO `b_article` VALUES ('1288293481762193409', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', 'vue-element-admin 是一个后台前端解决方案，它基于 vue 和 element-ui', 'vue-element-admin 是一个后台前端解决方案，它基于 vue 和 element-ui实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/803246133a69413485b122a51a980aea.jpg', '# vue-element-admin 概述\n\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 是一个后台前端解决方案，它基于 [vue](https://github.com/vuejs/vue) 和 [element-ui](https://github.com/ElemeFE/element)实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。\n\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。\n\n官方还提供了一套基础模板 [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) ，我们基于它进行二次开发，想要什么功能或者组件就去 `vue-element-admin` 那里复制过来。\n\n\n\n**参考：**\n\n- 官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/\n\n- [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-element-admin\n\n  `gitee`  ：https://gitee.com/mirrors/vue-element-admin\n\n  `github` ：https://github.com/PanJiaChen/vue-element-admin\n\n- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-admin-template\n\n  `github` 源码：https://github.com/PanJiaChen/vue-admin-template\n\n# 克隆与运行项目\n\n为了项目版本保持一致，大家全部使用老师在网盘中提供好的模板项目。因为可能你用的版本和老师的不一致，而出现一些未知的问题，解决起来很棘手；并且老师已经把项目依赖都已经下载好了，节省你的下载时间。\n\n所以强烈建议你用网盘提供好的。\n\n> `vue-element-admin` 模板项目位于：03-配套资料/01-前端模板代码.zip\n\n\n\n1. **克隆 `vue-element-admin` 项目与安装依赖**\n\n   ```sh\n   # 克隆项目\n   git clone https://github.com/PanJiaChen/vue-element-admin.git\n   \n   # 进入项目目录\n   cd vue-element-admin\n   \n   # 强烈建议不要用 cnpm 安装 会有各种诡异的bug 可以通过如下操作解决 npm 下载速度慢的问题\n   npm install --registry=https://registry.npm.taobao.org\n   \n   # 安装依赖，如果报错，则参见下文·报错声明·\n   npm install\n   \n   # 本地开发 启动项目\n   npm run dev\n   ```\n\n   >**报错声明：**\n   >\n   >**拉取项目后，当 `run install` 时，如果提示 `node-sass` 下载失败，则在项目根目录创建一个 `.npmrc` 文件 ,**\n   >\n   >**文件内容是把 `node-sass` 的路径修改成淘宝的 `npm`，就很顺利的可以在国内的网络环境下载了**\n   >\n   >```properties\n   >sass_binary_site=https://npm.taobao.org/mirrors/node-sass/\n   >registry=https://registry.npm.taobao.org\n   >```\n\n   \n\n', '<h1><a id=\"vueelementadmin__0\"></a>vue-element-admin 概述</h1>\n<p><a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 是一个后台前端解决方案，它基于 <a href=\"https://github.com/vuejs/vue\" target=\"_blank\">vue</a> 和 <a href=\"https://github.com/ElemeFE/element\" target=\"_blank\">element-ui</a>实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。</p>\n<p><a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。</p>\n<p>官方还提供了一套基础模板 <a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> ，我们基于它进行二次开发，想要什么功能或者组件就去 <code>vue-element-admin</code> 那里复制过来。</p>\n<p><strong>参考：</strong></p>\n<ul>\n<li>\n<p>官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-element-admin</p>\n<p><code>gitee</code>  ：https://gitee.com/mirrors/vue-element-admin</p>\n<p><code>github</code> ：https://github.com/PanJiaChen/vue-element-admin</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-admin-template</p>\n<p><code>github</code> 源码：https://github.com/PanJiaChen/vue-admin-template</p>\n</li>\n</ul>\n<h1><a id=\"_28\"></a>克隆与运行项目</h1>\n<p>为了项目版本保持一致，大家全部使用老师在网盘中提供好的模板项目。因为可能你用的版本和老师的不一致，而出现一些未知的问题，解决起来很棘手；并且老师已经把项目依赖都已经下载好了，节省你的下载时间。</p>\n<p>所以强烈建议你用网盘提供好的。</p>\n<blockquote>\n<p><code>vue-element-admin</code> 模板项目位于：03-配套资料/01-前端模板代码.zip</p>\n</blockquote>\n<ol>\n<li>\n<p><strong>克隆 <code>vue-element-admin</code> 项目与安装依赖</strong></p>\n<pre><code class=\"lang-sh\"># 克隆项目\ngit clone https://github.com/PanJiaChen/vue-element-admin.git\n\n# 进入项目目录\ncd vue-element-admin\n\n# 强烈建议不要用 cnpm 安装 会有各种诡异的bug 可以通过如下操作解决 npm 下载速度慢的问题\nnpm install --registry=https://registry.npm.taobao.org\n\n# 安装依赖，如果报错，则参见下文·报错声明·\nnpm install\n\n# 本地开发 启动项目\nnpm run dev\n</code></pre>\n<blockquote>\n<p><strong>报错声明：</strong></p>\n<p><strong>拉取项目后，当 <code>run install</code> 时，如果提示 <code>node-sass</code> 下载失败，则在项目根目录创建一个 <code>.npmrc</code> 文件 ,</strong></p>\n<p><strong>文件内容是把 <code>node-sass</code> 的路径修改成淘宝的 <code>npm</code>，就很顺利的可以在国内的网络环境下载了</strong></p>\n<pre><code class=\"lang-properties\">sass_binary_site=https://npm.taobao.org/mirrors/node-sass/\nregistry=https://registry.npm.taobao.org\n</code></pre>\n</blockquote>\n</li>\n</ol>\n', '2', '1', '2', '1', '2020-07-29 10:01:16', '2020-07-29 10:01:33');
INSERT INTO `b_article` VALUES ('1288296750278443009', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '第二章 初始化博客社区门户项目', 'ESLint 是一个用来按照规则给出报告的代码检测工具，使用它可以避免低级错误和统一代码的风格，也保证了代码的可读性。\n', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/6f71c3c1fd8a49f19b0f7f4056a9b99b.png', '# 第二章 初始化项目\n![image20200509092757510.png](https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/4882eecb1bb44802b2752e74eef0e7a9.png)\n## 重命名项目\n\n1. 将目录名 `vue-admin-template-master` 重命名为 `mengxuegu-blog-admin` \n\n2. 将  `mengxuegu-blog-admin/package.json` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"description\": \"mengxuegu blog\",\n     \"author\": \"mengxuegu.com\",\n     // ...\n   }\n   ```\n\n3. 将  `mengxuegu-blog-admin/package-lock.jsom` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"lockfileVersion\": 1,\n   ```\n\n## Eslint 语法规范检查\n\nESLint 是一个用来按照规则给出报告的代码检测工具，使用它可以避免低级错误和统一代码的风格，也保证了代码的可读性。\n\n### 取消 ESLint 校验\n\n在工程根目录下的 `vue.config.js` 中将 lintOnSave 指定为 false 即可。\n\n```js\nlintOnSave: false, // process.env.NODE_ENV === \'development\',\n```\n', '<h1><a id=\"__0\"></a>第二章 初始化项目</h1>\n<p><img src=\"https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200729/4882eecb1bb44802b2752e74eef0e7a9.png\" alt=\"image20200509092757510.png\" /></p>\n<h2><a id=\"_2\"></a>重命名项目</h2>\n<ol>\n<li>\n<p>将目录名 <code>vue-admin-template-master</code> 重命名为 <code>mengxuegu-blog-admin</code></p>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package.json</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"description\"</span>: <span class=\"hljs-string\">\"mengxuegu blog\"</span>,\n  <span class=\"hljs-attr\">\"author\"</span>: <span class=\"hljs-string\">\"mengxuegu.com\"</span>,\n  // ...\n}\n</code></div></pre>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package-lock.jsom</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"lockfileVersion\"</span>: <span class=\"hljs-number\">1</span>,\n</code></div></pre>\n</li>\n</ol>\n<h2><a id=\"Eslint__27\"></a>Eslint 语法规范检查</h2>\n<p>ESLint 是一个用来按照规则给出报告的代码检测工具，使用它可以避免低级错误和统一代码的风格，也保证了代码的可读性。</p>\n<h3><a id=\"_ESLint__31\"></a>取消 ESLint 校验</h3>\n<p>在工程根目录下的 <code>vue.config.js</code> 中将 lintOnSave 指定为 false 即可。</p>\n<pre><div class=\"hljs\"><code class=\"lang-js\">lintOnSave: <span class=\"hljs-literal\">false</span>, <span class=\"hljs-comment\">// process.env.NODE_ENV === \'development\',</span>\n</code></div></pre>\n', '1', '0', '2', '1', '2020-07-29 10:14:15', '2020-07-29 10:14:31');
INSERT INTO `b_article` VALUES ('1288310670351335425', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '博客管理系统后台微服务架构技术栈', '门户包括: 博客文章、问答管理、评论管理等                         |\n系统管理中心：分类管理、标签管理、文章审核、广告管理、问答管理、系统权限管理等功能。', null, '# 项目架构\n\n## 项目功能\n\n| 模块名称     | 功能说明                                                     |\n| ------------ | ------------------------------------------------------------ |\n| 门户         | 包括: 博客文章、问答管理、评论管理等                         |\n| 系统管理中心 | 分类管理、标签管理、文章审核、广告管理、问答管理、系统权限管理等功能。 |\n\n\n## 后台技术栈\n\n| 技术名                          | 版本          |\n| ------------------------------- | ------------- |\n| JDK                             | 1.8+          |\n| Maven                           | 3.3.9         |\n| Spring Boot                     | 2.2.5.RELEASE |\n| Spring Cloud                    | Hoxton.SR3    |\n| Spring Security OAuth2          | 5.3.0         |\n| Mybatis                         |               |\n| Mybatis-Plus                    | 3.2.0         |\n| Alibaba Nacos 注册中心&配置中心 |               |\n| druid 连接池                    | 1.1.21        |\n| Swagger-ui                      |               |\n\n', '<h1><a id=\"_0\"></a>项目架构</h1>\n<h2><a id=\"_2\"></a>项目功能</h2>\n<table>\n<thead>\n<tr>\n<th>模块名称</th>\n<th>功能说明</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>门户</td>\n<td>包括: 博客文章、问答管理、评论管理等</td>\n</tr>\n<tr>\n<td>系统管理中心</td>\n<td>分类管理、标签管理、文章审核、广告管理、问答管理、系统权限管理等功能。</td>\n</tr>\n</tbody>\n</table>\n<h2><a id=\"_10\"></a>后台技术栈</h2>\n<table>\n<thead>\n<tr>\n<th>技术名</th>\n<th>版本</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>JDK</td>\n<td>1.8+</td>\n</tr>\n<tr>\n<td>Maven</td>\n<td>3.3.9</td>\n</tr>\n<tr>\n<td>Spring Boot</td>\n<td>2.2.5.RELEASE</td>\n</tr>\n<tr>\n<td>Spring Cloud</td>\n<td>Hoxton.SR3</td>\n</tr>\n<tr>\n<td>Spring Security OAuth2</td>\n<td>5.3.0</td>\n</tr>\n<tr>\n<td>Mybatis</td>\n<td></td>\n</tr>\n<tr>\n<td>Mybatis-Plus</td>\n<td>3.2.0</td>\n</tr>\n<tr>\n<td>Alibaba Nacos 注册中心&amp;配置中心</td>\n<td></td>\n</tr>\n<tr>\n<td>druid 连接池</td>\n<td>1.1.21</td>\n</tr>\n<tr>\n<td>Swagger-ui</td>\n<td></td>\n</tr>\n</tbody>\n</table>\n', '1', '0', '2', '1', '2020-07-29 11:09:34', '2020-07-29 11:10:47');
INSERT INTO `b_article` VALUES ('1338095426062077954', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '更新2-博客系统采用 Vue.js + ElementUI 来搭建博客后台管理系统。', '使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/ea90bff14995488e9c2791a8694df38c.png', '# vue-element-admin 概述\n![aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg](https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/4a8e837091584673a4c3fd92647a4f7a.jpeg)\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 是一个后台前端解决方案，它基于 [vue](https://github.com/vuejs/vue) 和 [element-ui](https://github.com/ElemeFE/element)实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。\n\n[vue-element-admin](https://panjiachen.github.io/vue-element-admin) 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。\n\n官方还提供了一套基础模板 [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) ，我们基于它进行二次开发，想要什么功能或者组件就去 `vue-element-admin` 那里复制过来。\n\n\n\n## 参考：\n\n- 官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/\n\n- [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-element-admin\n\n  `gitee`  ：https://gitee.com/mirrors/vue-element-admin\n\n  `github` ：https://github.com/PanJiaChen/vue-element-admin\n\n- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template) 脚手架: \n\n  在线预览：https://panjiachen.gitee.io/vue-admin-template\n\n  `github` 源码：https://github.com/PanJiaChen/vue-admin-template\n\n', '<h1><a id=\"vueelementadmin__0\"></a>vue-element-admin 概述</h1>\n<p><img src=\"https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200520/4a8e837091584673a4c3fd92647a4f7a.jpeg\" alt=\"aHR0cHM6Ly91cGxvYWQtaW1hZ2VzLmppYW5zaHUuaW8vdXBsb2FkX2ltYWdlcy81NTMxMjExLWQxYTNlNWIzNmVlMDNmMDgucG5nP2ltYWdlTW9ncjIvYXV0by1vcmllbnQvc3RyaXAlN0NpbWFnZVZpZXcyLzIvdy80NjAvZm9ybWF0L3dlYnA.jpeg\" /><br />\n<a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 是一个后台前端解决方案，它基于 <a href=\"https://github.com/vuejs/vue\" target=\"_blank\">vue</a> 和 <a href=\"https://github.com/ElemeFE/element\" target=\"_blank\">element-ui</a>实现。它使用了最新的前端技术栈，内置了 i18n 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。</p>\n<p><a href=\"https://panjiachen.github.io/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 定位是后台集成方案，不太适合当基础模板来进行二次开发，因为本项目集成了很多你可能用不到的功能，会造成不少的代码冗余。</p>\n<p>官方还提供了一套基础模板 <a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> ，我们基于它进行二次开发，想要什么功能或者组件就去 <code>vue-element-admin</code> 那里复制过来。</p>\n<h2><a id=\"_10\"></a>参考：</h2>\n<ul>\n<li>\n<p>官方文档 ：https://panjiachen.gitee.io/vue-element-admin-site/zh/</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-element-admin\" target=\"_blank\">vue-element-admin</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-element-admin</p>\n<p><code>gitee</code>  ：https://gitee.com/mirrors/vue-element-admin</p>\n<p><code>github</code> ：https://github.com/PanJiaChen/vue-element-admin</p>\n</li>\n<li>\n<p><a href=\"https://github.com/PanJiaChen/vue-admin-template\" target=\"_blank\">vue-admin-template</a> 脚手架:</p>\n<p>在线预览：https://panjiachen.gitee.io/vue-admin-template</p>\n<p><code>github</code> 源码：https://github.com/PanJiaChen/vue-admin-template</p>\n</li>\n</ul>\n', '224', '1', '3', '1', '2020-01-07 10:38:07', '2020-12-14 18:21:00');
INSERT INTO `b_article` VALUES ('2', '1', null, null, '深入学习解析HTTP请求', '这篇文章我们就按照这几种常见的服务端对 HTTP请求的操作来说一下服务器应用程序如何通过 Request对象解析请求头和请求体。篇幅有点长，不过整体不难懂，而且也可以下载程序中的源码自己运行调试，动手实践一下更有助于理解吸收。', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/461d3f8f4a0f4a4a9186f1adab4c3c46.png', '1. 将目录名 `vue-admin-template` 重命名为 `mengxuegu-blog-admin` \n\n2. 将  `mengxuegu-blog-admin/package.jsom` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"description\": \"mengxuegu blog\",\n     \"author\": \"mengxuegu.com\",\n     // ...\n   }\n   ```\n\n3. 将  `mengxuegu-blog-admin/package-lock.jsom` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"lockfileVersion\": 1,\n   ```\n\n   效果图：', '<ol>\n<li>\n<p>将目录名 <code>vue-admin-template</code> 重命名为 <code>mengxuegu-blog-admin</code></p>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package.jsom</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"description\"</span>: <span class=\"hljs-string\">\"mengxuegu blog\"</span>,\n  <span class=\"hljs-attr\">\"author\"</span>: <span class=\"hljs-string\">\"mengxuegu.com\"</span>,\n  // ...\n}\n</code></div></pre>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package-lock.jsom</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"lockfileVersion\"</span>: <span class=\"hljs-number\">1</span>,\n</code></div></pre>\n<p>效果图：</p>\n</li>\n</ol>\n', '10', '2', '2', '1', '2020-04-07 10:38:07', '2020-04-16 21:37:42');
INSERT INTO `b_article` VALUES ('3', '2', null, null, '页面 Markdown 编辑器', 'Vue中比较流行的 Markdown 插件有： mavon-editor 、marked + highlight.js 、tui-editor ……\n因为 mavon-editor 体验更加的好，基于Vue的markdown编辑器，并支持 Nuxt.js , 兼容性好，使用文档有中文的，功能较全。所以我们项目采用 mavon-editor ，\nmavon-editor网址：https://github.com/hinesboy/mavonEditor\n', 'https://mengxuegu.oss-cn-shenzhen.aliyuncs.com/article/20200405/48df8ff1ba55428294c75b8b6751fa3d.png', '1. 将目录名 `vue-admin-template` 重命名为 `mengxuegu-blog-admin` \n\n2. 将  `mengxuegu-blog-admin/package.jsom` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"description\": \"mengxuegu blog\",\n     \"author\": \"mengxuegu.com\",\n     // ...\n   }\n   ```\n\n3. 将  `mengxuegu-blog-admin/package-lock.jsom` 中的 `name` 值改为 `mengxuegu-blog-admin` \n\n   ```json\n   {\n     \"name\": \"mengxuegu-blog-admin\",\n     \"version\": \"1.0.1\",\n     \"lockfileVersion\": 1,\n   ```\n\n   效果图：', '<ol>\n<li>\n<p>将目录名 <code>vue-admin-template</code> 重命名为 <code>mengxuegu-blog-admin</code></p>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package.jsom</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"description\"</span>: <span class=\"hljs-string\">\"mengxuegu blog\"</span>,\n  <span class=\"hljs-attr\">\"author\"</span>: <span class=\"hljs-string\">\"mengxuegu.com\"</span>,\n  // ...\n}\n</code></div></pre>\n</li>\n<li>\n<p>将  <code>mengxuegu-blog-admin/package-lock.jsom</code> 中的 <code>name</code> 值改为 <code>mengxuegu-blog-admin</code></p>\n<pre><div class=\"hljs\"><code class=\"lang-json\">{\n  <span class=\"hljs-attr\">\"name\"</span>: <span class=\"hljs-string\">\"mengxuegu-blog-admin\"</span>,\n  <span class=\"hljs-attr\">\"version\"</span>: <span class=\"hljs-string\">\"1.0.1\"</span>,\n  <span class=\"hljs-attr\">\"lockfileVersion\"</span>: <span class=\"hljs-number\">1</span>,\n</code></div></pre>\n<p>效果图：</p>\n</li>\n</ol>\n', '100', '1', '2', '1', '2020-04-07 10:38:07', '2020-04-16 21:08:59');
INSERT INTO `b_article` VALUES ('string', 'string', '又测试啦', 'http://www.baidu.com/112233.png', 'string', 'string', 'string', 'string', 'string', '0', '0', '1', '1', '2020-05-03 14:04:05', '2020-05-03 14:05:03');
INSERT INTO `b_article` VALUES ('string444', 'string', '又测试啦', 'http://www.baidu.com/112233.png', '大数据高级开发工程师', 'string', '44string', 'string', '44string', '0', '0', '0', '2', '2020-12-15 21:10:22', '2020-12-15 21:14:39');

-- ----------------------------
-- Table structure for b_article_label
-- ----------------------------
DROP TABLE IF EXISTS `b_article_label`;
CREATE TABLE `b_article_label` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `article_id` varchar(40) DEFAULT NULL COMMENT '文章 id',
  `label_id` varchar(40) DEFAULT NULL COMMENT '标签id',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签中间表';

-- ----------------------------
-- Records of b_article_label
-- ----------------------------
INSERT INTO `b_article_label` VALUES ('1245982620028936194', '1245884663208275970', '11', '2020-04-03 15:53:00');
INSERT INTO `b_article_label` VALUES ('1245982620028936195', '1245884663208275970', '8', '2020-04-03 15:53:00');
INSERT INTO `b_article_label` VALUES ('1245982620028936196', '1245884663208275970', '10', '2020-04-03 15:53:00');
INSERT INTO `b_article_label` VALUES ('1246608348114264066', '2', '15', '2020-04-05 09:19:25');
INSERT INTO `b_article_label` VALUES ('1246608348114264067', '2', '18', '2020-04-05 09:19:25');
INSERT INTO `b_article_label` VALUES ('1246608626444083202', '3', '19', '2020-04-05 09:20:32');
INSERT INTO `b_article_label` VALUES ('1246608626444083203', '3', '18', '2020-04-05 09:20:32');
INSERT INTO `b_article_label` VALUES ('1246608626444083204', '3', '20', '2020-04-05 09:20:32');
INSERT INTO `b_article_label` VALUES ('1246608626444083205', '3', '17', '2020-04-05 09:20:32');
INSERT INTO `b_article_label` VALUES ('1246608697378152450', '1245894712647413762', '14', '2020-04-05 09:20:48');
INSERT INTO `b_article_label` VALUES ('1246608697378152451', '1245894712647413762', '13', '2020-04-05 09:20:48');
INSERT INTO `b_article_label` VALUES ('1246608697378152452', '1245894712647413762', '15', '2020-04-05 09:20:48');
INSERT INTO `b_article_label` VALUES ('1246718260588720130', '1246718260580331521', '12', '2020-04-05 16:36:10');
INSERT INTO `b_article_label` VALUES ('1250731885750067202', '1244940138650423298', '1', '2020-04-16 18:24:53');
INSERT INTO `b_article_label` VALUES ('1250731885750067203', '1244940138650423298', '3', '2020-04-16 18:24:53');
INSERT INTO `b_article_label` VALUES ('1256827086478438402', 'string', 'string', '2020-05-03 14:05:02');
INSERT INTO `b_article_label` VALUES ('1256827283837218817', '1256827283824635906', 'string', '2020-05-03 14:05:49');
INSERT INTO `b_article_label` VALUES ('1263079731945975810', '1263079731933392898', '1244923532817915906', '2020-05-20 20:10:49');
INSERT INTO `b_article_label` VALUES ('1263079731945975811', '1263079731933392898', '3', '2020-05-20 20:10:49');
INSERT INTO `b_article_label` VALUES ('1263079731945975812', '1263079731933392898', '6', '2020-05-20 20:10:49');
INSERT INTO `b_article_label` VALUES ('1263079731945975813', '1263079731933392898', '5', '2020-05-20 20:10:49');
INSERT INTO `b_article_label` VALUES ('1263079788824932354', '1263079788816543745', '1244923532817915906', '2020-05-20 20:11:03');
INSERT INTO `b_article_label` VALUES ('1263079788824932355', '1263079788816543745', '3', '2020-05-20 20:11:03');
INSERT INTO `b_article_label` VALUES ('1263079788824932356', '1263079788816543745', '6', '2020-05-20 20:11:03');
INSERT INTO `b_article_label` VALUES ('1263079788824932357', '1263079788816543745', '5', '2020-05-20 20:11:03');
INSERT INTO `b_article_label` VALUES ('1263079980487847938', '1263079980475265025', '1244923532817915906', '2020-05-20 20:11:48');
INSERT INTO `b_article_label` VALUES ('1263079980487847939', '1263079980475265025', '3', '2020-05-20 20:11:48');
INSERT INTO `b_article_label` VALUES ('1263079980487847940', '1263079980475265025', '6', '2020-05-20 20:11:48');
INSERT INTO `b_article_label` VALUES ('1263079980492042242', '1263079980475265025', '5', '2020-05-20 20:11:48');
INSERT INTO `b_article_label` VALUES ('1263080137262542850', '1263080137249959937', '1244923532817915906', '2020-05-20 20:12:26');
INSERT INTO `b_article_label` VALUES ('1263080137262542851', '1263080137249959937', '3', '2020-05-20 20:12:26');
INSERT INTO `b_article_label` VALUES ('1263080137262542852', '1263080137249959937', '6', '2020-05-20 20:12:26');
INSERT INTO `b_article_label` VALUES ('1263080137262542853', '1263080137249959937', '5', '2020-05-20 20:12:26');
INSERT INTO `b_article_label` VALUES ('1263080212982312962', '1263080212978118658', '1244923532817915906', '2020-05-20 20:12:44');
INSERT INTO `b_article_label` VALUES ('1263080212982312963', '1263080212978118658', '3', '2020-05-20 20:12:44');
INSERT INTO `b_article_label` VALUES ('1263080212982312964', '1263080212978118658', '6', '2020-05-20 20:12:44');
INSERT INTO `b_article_label` VALUES ('1263080212982312965', '1263080212978118658', '5', '2020-05-20 20:12:44');
INSERT INTO `b_article_label` VALUES ('1263080327784607745', '1263080327776219138', '8', '2020-05-20 20:13:11');
INSERT INTO `b_article_label` VALUES ('1263080327784607746', '1263080327776219138', '12', '2020-05-20 20:13:11');
INSERT INTO `b_article_label` VALUES ('1263080327784607747', '1263080327776219138', '15', '2020-05-20 20:13:11');
INSERT INTO `b_article_label` VALUES ('1263080327784607748', '1263080327776219138', '14', '2020-05-20 20:13:11');
INSERT INTO `b_article_label` VALUES ('1263080327784607749', '1263080327776219138', '13', '2020-05-20 20:13:11');
INSERT INTO `b_article_label` VALUES ('1263081451006636034', '1263081450994053121', '7', '2020-05-20 20:17:39');
INSERT INTO `b_article_label` VALUES ('1263081451006636035', '1263081450994053121', '11', '2020-05-20 20:17:39');
INSERT INTO `b_article_label` VALUES ('1263081451006636036', '1263081450994053121', '9', '2020-05-20 20:17:39');
INSERT INTO `b_article_label` VALUES ('1263081451006636037', '1263081450994053121', '10', '2020-05-20 20:17:39');
INSERT INTO `b_article_label` VALUES ('1263091654527725569', '1263091654519336962', '2', '2020-05-20 20:58:12');
INSERT INTO `b_article_label` VALUES ('1263091654527725570', '1263091654519336962', '1', '2020-05-20 20:58:12');
INSERT INTO `b_article_label` VALUES ('1263092250827730945', '1263092250819342337', '19', '2020-05-20 21:00:34');
INSERT INTO `b_article_label` VALUES ('1263092250827730946', '1263092250819342337', '18', '2020-05-20 21:00:34');
INSERT INTO `b_article_label` VALUES ('1263092250827730947', '1263092250819342337', '20', '2020-05-20 21:00:34');
INSERT INTO `b_article_label` VALUES ('1263092250827730948', '1263092250819342337', '17', '2020-05-20 21:00:34');
INSERT INTO `b_article_label` VALUES ('1263092800906506241', '1263092800898117633', '19', '2020-05-20 21:02:45');
INSERT INTO `b_article_label` VALUES ('1263092800906506242', '1263092800898117633', '18', '2020-05-20 21:02:45');
INSERT INTO `b_article_label` VALUES ('1263092800906506243', '1263092800898117633', '20', '2020-05-20 21:02:45');
INSERT INTO `b_article_label` VALUES ('1263092800906506244', '1263092800898117633', '17', '2020-05-20 21:02:45');
INSERT INTO `b_article_label` VALUES ('1263095988208447489', '1263095988200058881', '1244923532817915906', '2020-05-20 21:15:25');
INSERT INTO `b_article_label` VALUES ('1263095988208447490', '1263095988200058881', '3', '2020-05-20 21:15:25');
INSERT INTO `b_article_label` VALUES ('1263095988208447491', '1263095988200058881', '6', '2020-05-20 21:15:25');
INSERT INTO `b_article_label` VALUES ('1263095988208447492', '1263095988200058881', '5', '2020-05-20 21:15:25');
INSERT INTO `b_article_label` VALUES ('1263321794348220418', '1263098950418870273', '15', '2020-05-21 12:12:41');
INSERT INTO `b_article_label` VALUES ('1263321794348220419', '1263098950418870273', '14', '2020-05-21 12:12:41');
INSERT INTO `b_article_label` VALUES ('1263321794348220420', '1263098950418870273', '13', '2020-05-21 12:12:41');
INSERT INTO `b_article_label` VALUES ('1263758893237071873', '1263442805739651073', '15', '2020-05-22 17:09:34');
INSERT INTO `b_article_label` VALUES ('1263758893237071874', '1263442805739651073', '14', '2020-05-22 17:09:34');
INSERT INTO `b_article_label` VALUES ('1263758893237071875', '1263442805739651073', '13', '2020-05-22 17:09:34');
INSERT INTO `b_article_label` VALUES ('1288293481808330754', '1288293481762193409', '4', '2020-07-29 10:01:16');
INSERT INTO `b_article_label` VALUES ('1288296750295220225', '1288296750278443009', '1244923532817915906', '2020-07-29 10:14:15');
INSERT INTO `b_article_label` VALUES ('1288296750295220226', '1288296750278443009', '4', '2020-07-29 10:14:15');
INSERT INTO `b_article_label` VALUES ('1288310670363918338', '1288310670351335425', '12', '2020-07-29 11:09:34');
INSERT INTO `b_article_label` VALUES ('1288310670363918339', '1288310670351335425', '9', '2020-07-29 11:09:34');
INSERT INTO `b_article_label` VALUES ('1288310670363918340', '1288310670351335425', '10', '2020-07-29 11:09:34');
INSERT INTO `b_article_label` VALUES ('1338094040616378369', '1', '8', '2020-12-13 20:10:54');
INSERT INTO `b_article_label` VALUES ('1338094040616378370', '1', '12', '2020-12-13 20:10:54');
INSERT INTO `b_article_label` VALUES ('1338094040616378371', '1', '10', '2020-12-13 20:10:54');
INSERT INTO `b_article_label` VALUES ('1338095426385039361', '1338095426062077954', '8', '2020-12-13 20:16:24');
INSERT INTO `b_article_label` VALUES ('1338095426385039362', '1338095426062077954', '12', '2020-12-13 20:16:24');
INSERT INTO `b_article_label` VALUES ('1338095426385039363', '1338095426062077954', '10', '2020-12-13 20:16:24');

-- ----------------------------
-- Table structure for b_category
-- ----------------------------
DROP TABLE IF EXISTS `b_category`;
CREATE TABLE `b_category` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `sort` int DEFAULT '1' COMMENT '排序',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章分类表';

-- ----------------------------
-- Records of b_category
-- ----------------------------
INSERT INTO `b_category` VALUES ('1', '大数据架构师', 'string', '0', '0', '2020-12-11 22:15:48', '2020-12-12 21:59:43');
INSERT INTO `b_category` VALUES ('1250639404517728258', '大数据', '大数据热门技术', '0', '1', '2020-04-16 12:17:24', '2020-04-16 12:17:24');
INSERT INTO `b_category` VALUES ('2', 'Java', 'java系列', '1', '3', '2024-03-10 12:15:00', '2024-03-10 12:15:00');
INSERT INTO `b_category` VALUES ('3', '移动端', '如android、ios', '1', '4', '2024-03-10 12:15:07', '2024-03-10 12:15:07');
INSERT INTO `b_category` VALUES ('4', '人工智能', '人工智能相关', '1', '6', '2024-03-10 12:16:59', '2024-03-10 12:16:59');
INSERT INTO `b_category` VALUES ('7', '区块链', '区块链相关', '1', '8', '2024-03-10 12:17:11', '2024-03-10 12:17:11');
INSERT INTO `b_category` VALUES ('string', 'string', 'string', '0', '0', '2020-01-01 00:00:00', '2020-01-01 00:00:00');

-- ----------------------------
-- Table structure for b_comment
-- ----------------------------
DROP TABLE IF EXISTS `b_comment`;
CREATE TABLE `b_comment` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `parent_id` varchar(40) DEFAULT '-1' COMMENT '-1表示正常回复，其他值表示是评论的回复',
  `user_id` varchar(40) DEFAULT NULL COMMENT '评论者用户id',
  `nick_name` varchar(40) DEFAULT NULL COMMENT '评论者用户昵称',
  `user_image` varchar(255) DEFAULT NULL COMMENT '评论者头像url',
  `article_id` varchar(40) DEFAULT NULL COMMENT '文章id',
  `content` text COMMENT '评论内容',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息表';

-- ----------------------------
-- Records of b_comment
-- ----------------------------
INSERT INTO `b_comment` VALUES ('1', '-1', '1', '梦学谷', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '11', '很好', '2024-03-10 12:54:23');
INSERT INTO `b_comment` VALUES ('1258611426935758849', '-1', null, null, null, '1', '123', '2020-05-08 12:15:22');
INSERT INTO `b_comment` VALUES ('1258611504450691074', '-1', null, null, null, '1', '[微笑]', '2020-05-08 12:15:41');
INSERT INTO `b_comment` VALUES ('1262688535096463361', '-1', null, null, null, '1', '[嘻嘻]', '2020-05-19 18:16:21');
INSERT INTO `b_comment` VALUES ('1262959723207110658', '-1', null, null, null, '1', null, '2020-05-20 12:13:57');
INSERT INTO `b_comment` VALUES ('1263074396929699841', '-1', null, null, null, '1', '123123', '2020-05-20 19:49:37');
INSERT INTO `b_comment` VALUES ('1263074421692870658', '-1', null, null, null, '1', '[微笑][嘻嘻]', '2020-05-20 19:49:43');
INSERT INTO `b_comment` VALUES ('1263080690940030977', '-1', null, null, null, '1263080327776219138', 'asdasd', '2020-05-20 20:14:38');
INSERT INTO `b_comment` VALUES ('1263090501379338242', '-1', null, null, null, '1263081450994053121', 'sdfsdf', '2020-05-20 20:53:37');
INSERT INTO `b_comment` VALUES ('1263091006138658818', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263081450994053121', 'asdfs[微笑]', '2020-05-20 20:55:37');
INSERT INTO `b_comment` VALUES ('1263092866807410690', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263092800898117633', 'asdfsdf', '2020-05-20 21:03:01');
INSERT INTO `b_comment` VALUES ('1263093784584040449', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263092800898117633', 'asdfsdf', '2020-05-20 21:06:40');
INSERT INTO `b_comment` VALUES ('1263093816875986945', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263092800898117633', 'asdfsdfasssss', '2020-05-20 21:06:47');
INSERT INTO `b_comment` VALUES ('1263093962808406017', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263092800898117633', 'asdfsdfd', '2020-05-20 21:07:22');
INSERT INTO `b_comment` VALUES ('1263094145537454081', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263092800898117633', 'asdfsdf', '2020-05-20 21:08:06');
INSERT INTO `b_comment` VALUES ('1263094170220933121', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263092800898117633', 'xxxx', '2020-05-20 21:08:11');
INSERT INTO `b_comment` VALUES ('1263095719471001602', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263092800898117633', '12123123', '2020-05-20 21:14:21');
INSERT INTO `b_comment` VALUES ('1263096008185917442', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263095988200058881', 'asdf', '2020-05-20 21:15:30');
INSERT INTO `b_comment` VALUES ('1263096359475654657', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263095988200058881', 'zcvzxcvxcv[嘻嘻]', '2020-05-20 21:16:53');
INSERT INTO `b_comment` VALUES ('1263113534814990338', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '[微笑]', '2020-05-20 22:25:08');
INSERT INTO `b_comment` VALUES ('1263114298618720258', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '[嘻嘻]', '2020-05-20 22:28:10');
INSERT INTO `b_comment` VALUES ('1263114358337220610', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263098950418870273', '我来啦', '2020-05-20 22:28:25');
INSERT INTO `b_comment` VALUES ('1263116104207872001', '1263114298618720258', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1263098950418870273', '[微笑]', '2020-05-20 22:35:21');
INSERT INTO `b_comment` VALUES ('1263121543188361217', '-1', '10', '测试', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '3', '[微笑]', '2020-05-20 22:56:58');
INSERT INTO `b_comment` VALUES ('1263349594908164097', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '[微笑]', '2020-05-21 14:03:09');
INSERT INTO `b_comment` VALUES ('1263374776322392066', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263092250819342337', '[喔克]', '2020-05-21 15:43:13');
INSERT INTO `b_comment` VALUES ('1263442939550531586', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263442805739651073', 'asdf', '2020-05-21 20:14:05');
INSERT INTO `b_comment` VALUES ('1288296942201405441', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1288296750278443009', '[微笑]', '2020-07-29 10:15:01');
INSERT INTO `b_comment` VALUES ('1288311127144595457', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1288310670351335425', '[棒棒]', '2020-07-29 11:11:23');
INSERT INTO `b_comment` VALUES ('1350449017967919105', '-1', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '有错别字啊，不行啊这编辑的水平。。。', '2021-01-16 22:25:03');
INSERT INTO `b_comment` VALUES ('1350449549591756801', '1350449017967919105', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '读得懂就行，错别字有什么', '2021-01-16 22:27:10');
INSERT INTO `b_comment` VALUES ('1350449816206884865', '1350449549591756801', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '专业编辑不能犯这种低级错误，还读得懂就行，什么玩意。', '2021-01-16 22:28:13');
INSERT INTO `b_comment` VALUES ('1350450019710320642', '1350449549591756801', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '别吵了，再吵老子删评论了', '2021-01-16 22:29:02');
INSERT INTO `b_comment` VALUES ('1350450135770906625', '1350449549591756801', '9', '巅峰键盘侠1', 'https://avatar.csdnimg.cn/2/8/E/3_zwzw1219-1.jpg', '1263098950418870273', '槽，玩不起别玩啊', '2021-01-16 22:29:30');
INSERT INTO `b_comment` VALUES ('2', '-1', '1', '小亭', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '10', '很好22222', '2024-03-10 12:54:23');
INSERT INTO `b_comment` VALUES ('3', '-1', '2', '小学', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '10', '66666', '2024-03-10 12:54:23');
INSERT INTO `b_comment` VALUES ('4', '3', '3', '小谷', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '10', 'yes ', '2024-03-10 12:54:23');
INSERT INTO `b_comment` VALUES ('5', '4', '4', '涛姐', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '10', '合胃口', '2024-03-10 12:54:23');
INSERT INTO `b_comment` VALUES ('string', '-1', 'string', '又测试啦', 'http://www.baidu.com/112233.png', 'string', 'string', '2020-05-02 21:07:40');

-- ----------------------------
-- Table structure for b_label
-- ----------------------------
DROP TABLE IF EXISTS `b_label`;
CREATE TABLE `b_label` (
  `id` varchar(40) NOT NULL COMMENT '主键',
  `category_id` varchar(40) DEFAULT NULL COMMENT '分类id',
  `name` varchar(40) DEFAULT NULL COMMENT '标签名称',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';

-- ----------------------------
-- Records of b_label
-- ----------------------------
INSERT INTO `b_label` VALUES ('1', '1', 'html5', '2024-03-10 12:23:47', '2020-12-13 00:09:11');
INSERT INTO `b_label` VALUES ('10', '2', 'mybatis', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('11', '2', 'springmvc', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('12', '2', 'springsecurity', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('1244923532817915906', '1', 'npm', '2020-03-31 17:44:34', '2020-03-31 17:44:34');
INSERT INTO `b_label` VALUES ('1250704765871144961', null, null, '2020-04-16 16:37:07', '2020-04-16 16:37:07');
INSERT INTO `b_label` VALUES ('13', '3', 'ios', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('14', '3', 'android', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('15', '3', '小程序', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('17', '4', '人脸识别', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('18', '4', '神经网络', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('19', '4', '深度学习', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('2', '1', 'javascript', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('20', '4', '算法', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('3', '1', 'css', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('4', '1', 'vue.js', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('5', '1', 'node.js', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('6', '1', 'jquery', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('7', '2', 'javase', '2024-03-10 12:23:47', '2024-03-10 12:23:47');
INSERT INTO `b_label` VALUES ('8', '2', 'servlet', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('9', '2', 'spring', '2024-03-10 12:26:22', '2024-03-10 12:26:22');
INSERT INTO `b_label` VALUES ('string111', '1', 'string', '2020-12-13 11:09:44', '2020-12-13 11:09:44');
INSERT INTO `b_label` VALUES ('string222', '1', 'html5', '2024-03-10 12:23:47', '2020-12-13 00:09:11');
INSERT INTO `b_label` VALUES ('string333', '1', 'string', '2020-01-01 00:00:00', '2020-01-01 00:00:00');

-- ----------------------------
-- View structure for v_category_article_count
-- ----------------------------
DROP VIEW IF EXISTS `v_category_article_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_category_article_count` AS select `b1`.`name` AS `name`,ifnull(sum(`b3`.`total`),0) AS `num` from ((`b_category` `b1` left join `b_label` `b2` on((`b1`.`id` = `b2`.`category_id`))) left join (select `t2`.`label_id` AS `label_id`,count(`t1`.`id`) AS `total` from (`b_article` `t1` join `b_article_label` `t2` on((`t1`.`id` = `t2`.`article_id`))) where ((`t1`.`status` = 2) and (`t1`.`ispublic` = 1)) group by `t2`.`label_id`) `b3` on((`b2`.`id` = `b3`.`label_id`))) group by `b1`.`name` ;

-- ----------------------------
-- View structure for v_six_month_aritcle_count
-- ----------------------------
DROP VIEW IF EXISTS `v_six_month_aritcle_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_six_month_aritcle_count` AS select `t1`.`year_month` AS `year_month`,count(`t2`.`id`) AS `total` from ((select date_format(curdate(),'%Y-%m') AS `year_month` union select date_format((curdate() - interval 1 month),'%Y-%m') AS `year_month` union select date_format((curdate() - interval 2 month),'%Y-%m') AS `year_month` union select date_format((curdate() - interval 3 month),'%Y-%m') AS `year_month` union select date_format((curdate() - interval 4 month),'%Y-%m') AS `year_month` union select date_format((curdate() - interval 5 month),'%Y-%m') AS `year_month`) `t1` left join `b_article` `t2` on(((`t1`.`year_month` = date_format(`t2`.`create_date`,'%Y-%m')) and (`t2`.`status` = 2) and (`t2`.`ispublic` = 1)))) group by `t1`.`year_month` ;
