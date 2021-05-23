/*
Navicat MySQL Data Transfer

Source Server         : mysql-124
Source Server Version : 80021
Source Host           : 192.168.1.201:3306
Source Database       : blog_auth

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-01-27 11:16:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(128) DEFAULT NULL,
  `client_secret` varchar(128) DEFAULT NULL,
  `scope` varchar(128) DEFAULT NULL,
  `authorized_grant_types` varchar(128) DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) DEFAULT NULL,
  `authorities` varchar(128) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('blog-security-app-client', 'article-service-resource,question-service-resource', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', 'all', 'password,refresh_token', '', 'all', null, null, null, 'false');
INSERT INTO `oauth_client_details` VALUES ('blog-security-desktopapp-client', 'article-service-resource', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', 'all', 'password,refresh_token', '', 'all', null, null, null, 'false');
INSERT INTO `oauth_client_details` VALUES ('blog-security-miniapp-client', 'question-service-resource', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', 'all', 'password,refresh_token', '', 'all', null, null, null, 'false');
INSERT INTO `oauth_client_details` VALUES ('blog-security-web-client', 'article-service-resource,question-service-resource,system-service-resource', '$2a$10$rRL4j/2qhDXHfHnlr5GOfu1y9rhpH584DKf4gB0xc0BcyLjYysayW', 'all', 'password,refresh_token', '', 'all', null, null, null, 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(128) DEFAULT NULL,
  `client_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
