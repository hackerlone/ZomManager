/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : rtv

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-08-23 10:27:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rtv_charge
-- ----------------------------
DROP TABLE IF EXISTS `rtv_charge`;
CREATE TABLE `rtv_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crop_id` int(11) DEFAULT NULL,
  `money` decimal(11,2) DEFAULT '0.00',
  `charge_time` datetime DEFAULT NULL,
  `charge_note` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rtv_charge
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_consoler_user_mapping
-- ----------------------------
DROP TABLE IF EXISTS `rtv_consoler_user_mapping`;
CREATE TABLE `rtv_consoler_user_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `console_display_name` varchar(128) NOT NULL,
  `console_id` bigint(20) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_consoler_user_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_console_cop_mapping
-- ----------------------------
DROP TABLE IF EXISTS `rtv_console_cop_mapping`;
CREATE TABLE `rtv_console_cop_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `corp_id` int(11) NOT NULL,
  `console_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `console_id` (`console_id`),
  KEY `console_cop_id` (`console_id`,`corp_id`),
  KEY `corp_id` (`corp_id`),
  CONSTRAINT `rtv_console_cop_mapping_ibfk_1` FOREIGN KEY (`corp_id`) REFERENCES `rtv_corp_table` (`id`),
  CONSTRAINT `rtv_console_cop_mapping_ibfk_2` FOREIGN KEY (`console_id`) REFERENCES `rtv_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_console_cop_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_corp_table
-- ----------------------------
DROP TABLE IF EXISTS `rtv_corp_table`;
CREATE TABLE `rtv_corp_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `corp_name` varchar(64) DEFAULT NULL,
  `corp_password` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `register_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `expire_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `last_logon_date` datetime DEFAULT '1900-01-01 00:00:00',
  `last_logon_ip` varchar(64) DEFAULT NULL,
  `permission_level` int(11) DEFAULT '0',
  `priority_level` int(11) DEFAULT '0',
  `max_user` int(11) DEFAULT '100',
  `max_group` int(11) DEFAULT '10',
  `max_console` int(11) DEFAULT '2',
  `max_user_group` int(11) DEFAULT '50',
  `status` int(11) DEFAULT '1',
  `zone_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_corp_table
-- ----------------------------
INSERT INTO `rtv_corp_table` VALUES ('1', 'admin', 'zom', '21b95a0f90138767b0fd324e6be3457b', 'williamwang@163.com', '18615707686', '2014-01-12 13:36:19', '2016-04-20 13:36:19', '2016-03-20 13:36:19', '182.160.45.22', '2', '1', '100', '10', '2', '100', '0', '1');
INSERT INTO `rtv_corp_table` VALUES ('29', '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', '1', '2016-08-22 10:09:51', '2016-09-02 10:09:42', '1900-01-01 00:00:00', null, '0', '0', '10', '10', '10', '0', '1', '2');

-- ----------------------------
-- Table structure for rtv_feedback
-- ----------------------------
DROP TABLE IF EXISTS `rtv_feedback`;
CREATE TABLE `rtv_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `type` bigint(20) NOT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `status` enum('open','closed','transacting') NOT NULL DEFAULT 'open',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_group
-- ----------------------------
DROP TABLE IF EXISTS `rtv_group`;
CREATE TABLE `rtv_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_ts` bigint(20) DEFAULT '0',
  `owner_id` bigint(20) NOT NULL,
  `group_name` varchar(100) NOT NULL DEFAULT '',
  `pttid` bigint(20) NOT NULL,
  `ptt_time` datetime NOT NULL DEFAULT '2015-01-01 00:00:00',
  `group_name_easemob` varchar(100) NOT NULL DEFAULT '',
  `create_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `corp_id` int(11) NOT NULL DEFAULT '0',
  `rank` int(11) DEFAULT '0',
  `dcg` int(11) DEFAULT '0',
  `preconfig` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `zone_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73737 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_group
-- ----------------------------
INSERT INTO `rtv_group` VALUES ('65565', '9', '65565', 'temp', '0', '2016-08-20 10:59:52', 'test', '2016-08-20 10:59:52', '1', '0', '1', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('65566', '37', '65566', 'temp', '0', '2016-08-20 10:59:52', 'test', '2016-08-20 10:59:52', '1', '1', '1', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('65567', '5', '65567', 'temp', '0', '2016-08-20 10:59:52', 'test', '2016-08-20 10:59:52', '1', '1', '1', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('65568', '6', '65568', 'temp', '0', '2016-08-20 10:59:52', 'test', '2016-08-20 10:59:52', '1', '2', '1', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('73733', '7', '65565', 'temp', '0', '2016-08-20 10:59:52', 'test', '2016-08-20 10:59:52', '1', '0', '0', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('73734', '5', '65566', 'temp', '0', '2016-08-20 11:00:12', 'test', '2016-08-20 11:00:12', '1', '0', '0', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('73735', '10', '65565', 'temp', '0', '2016-08-20 11:00:17', 'test', '2016-08-20 11:00:17', '1', '0', '0', '1', '1', '1');
INSERT INTO `rtv_group` VALUES ('73736', '14', '65565', 'temp', '0', '2016-08-20 11:00:18', 'test', '2016-08-20 11:00:18', '1', '0', '0', '1', '1', '1');

-- ----------------------------
-- Table structure for rtv_group_admin_mapping
-- ----------------------------
DROP TABLE IF EXISTS `rtv_group_admin_mapping`;
CREATE TABLE `rtv_group_admin_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_admin_id` (`group_id`,`admin_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `rtv_group_admin_mapping_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `rtv_group` (`id`),
  CONSTRAINT `rtv_group_admin_mapping_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `rtv_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_group_admin_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_group_picture_map
-- ----------------------------
DROP TABLE IF EXISTS `rtv_group_picture_map`;
CREATE TABLE `rtv_group_picture_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture_id` bigint(20) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `url` varchar(5000) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_group_picture_map
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_password_reset_code
-- ----------------------------
DROP TABLE IF EXISTS `rtv_password_reset_code`;
CREATE TABLE `rtv_password_reset_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `code` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `due_date` datetime NOT NULL,
  `req_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_password_reset_code
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_picture_map
-- ----------------------------
DROP TABLE IF EXISTS `rtv_picture_map`;
CREATE TABLE `rtv_picture_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture_id` bigint(20) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `url` varchar(5000) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_picture_map
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_token
-- ----------------------------
DROP TABLE IF EXISTS `rtv_token`;
CREATE TABLE `rtv_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(128) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `request_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `refresh_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `overdue_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_token
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_user
-- ----------------------------
DROP TABLE IF EXISTS `rtv_user`;
CREATE TABLE `rtv_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(64) DEFAULT NULL,
  `user_password` varchar(255) NOT NULL DEFAULT '',
  `client_version` varchar(64) DEFAULT NULL,
  `device` enum('android','ios','pc','web') NOT NULL DEFAULT 'android',
  `push_id` varchar(64) DEFAULT NULL,
  `push_password` varchar(255) DEFAULT NULL,
  `img_url` varchar(1024) DEFAULT NULL,
  `phone` varchar(20) NOT NULL DEFAULT '',
  `register_date` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `last_logon_date` datetime DEFAULT '1900-01-01 00:00:00',
  `last_access_date` datetime DEFAULT '1900-01-01 00:00:00',
  `last_logon_ip` varchar(255) DEFAULT NULL,
  `logon_state` enum('offline','online') NOT NULL DEFAULT 'offline',
  `salt` varchar(64) NOT NULL,
  `ts_profile` bigint(20) DEFAULT '0',
  `ts_group` bigint(20) DEFAULT '0',
  `rank` int(11) DEFAULT '0',
  `admin_id` bigint(20) DEFAULT '0',
  `dcg_id` bigint(20) NOT NULL DEFAULT '0',
  `corp_id` int(11) NOT NULL DEFAULT '0',
  `logon_name` varchar(255) DEFAULT '',
  `priority` int(11) DEFAULT '1',
  `status` int(11) DEFAULT '1',
  `preconfig` int(11) DEFAULT '0',
  `default_grp` int(11) DEFAULT '0',
  `gps_report` int(11) DEFAULT '1',
  `gps_interval` int(11) DEFAULT '30',
  `adm_ts` int(11) DEFAULT '0',
  `zone_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=65569 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_user
-- ----------------------------
INSERT INTO `rtv_user` VALUES ('65565', '最新用户', '8ae98ce88cea8fea8eb8daef8bbddfe8d0e082b58deb89efd7b5d0b6d2b6d4b2d6b3d6b0d4e287b3d7e183b68eb8daef8bbbdeee8abbdeea8ebbdeea8ebadfee', null, 'web', 'u65565', 'u65565', null, '18702827609', '2016-08-20 10:59:52', '2016-08-20 10:59:52', null, null, 'offline', 'c0aa3d7ac03eaedf', '1', '10', '0', null, '65565', '1', 'u1', '1', '1', '1', '0', '1', '30', '0', '1');
INSERT INTO `rtv_user` VALUES ('65566', '调度台1', 'd6b7d5b6d2eb89ea8eb6d3b68eec89ecd4b5d7b4d0e98be8', null, 'web', 'u65566', 'u65566', null, '18702827608', '2016-08-20 10:59:52', '2016-08-20 10:59:52', null, null, 'offline', '61263fe3e6e99cfa', '1', '9', '1', null, '65566', '1', 'diaodutai1', '11', '1', '1', '0', '1', '30', '0', '1');
INSERT INTO `rtv_user` VALUES ('65567', '调度台3', 'd6b7d5b6d2eb89ea8eb6d3b68eec89ecd4b5d7b4d0e98be8', null, 'web', 'u65567', 'u65567', null, '18702827606', '2016-08-20 10:59:52', '2016-08-20 10:59:52', null, null, 'offline', '83d6bc4ed9c8f3ec', '1', '6', '0', null, '65567', '1', 'diaodutai3', '11', '1', '1', '0', '1', '30', '0', '1');
INSERT INTO `rtv_user` VALUES ('65568', '调度台2', 'd6b7d5b6d2eb89ea8eb6d3b68eec89ecd4b5d7b4d0e98be8', null, 'web', 'u65568', 'u65568', null, '18702827607', '2016-08-20 10:59:52', '2016-08-20 10:59:52', null, null, 'offline', '6b37acf2ab969d94', '1', '6', '2', null, '65568', '1', 'diaodutai2', '11', '1', '1', '0', '1', '30', '0', '1');

-- ----------------------------
-- Table structure for rtv_user_admin_mapping
-- ----------------------------
DROP TABLE IF EXISTS `rtv_user_admin_mapping`;
CREATE TABLE `rtv_user_admin_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `console_cop_id` (`user_id`,`admin_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `rtv_user_admin_mapping_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `rtv_user` (`id`),
  CONSTRAINT `rtv_user_admin_mapping_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `rtv_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_user_admin_mapping
-- ----------------------------
INSERT INTO `rtv_user_admin_mapping` VALUES ('62', '65566', '65568');

-- ----------------------------
-- Table structure for rtv_user_group_map
-- ----------------------------
DROP TABLE IF EXISTS `rtv_user_group_map`;
CREATE TABLE `rtv_user_group_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `group_right` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `group_user_id` (`group_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `rtv_user_group_map_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `rtv_group` (`id`),
  CONSTRAINT `rtv_user_group_map_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `rtv_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_user_group_map
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_user_group_map_bak
-- ----------------------------
DROP TABLE IF EXISTS `rtv_user_group_map_bak`;
CREATE TABLE `rtv_user_group_map_bak` (
  `id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `group_right` bigint(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_user_group_map_bak
-- ----------------------------

-- ----------------------------
-- Table structure for rtv_version_map
-- ----------------------------
DROP TABLE IF EXISTS `rtv_version_map`;
CREATE TABLE `rtv_version_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `server_version` varchar(500) DEFAULT NULL,
  `client_os_type` varchar(500) DEFAULT NULL,
  `client_version` varchar(500) DEFAULT NULL,
  `client_url` varchar(5000) DEFAULT NULL,
  `latest_server` bigint(20) NOT NULL DEFAULT '0',
  `latest_client` bigint(20) NOT NULL DEFAULT '0',
  `client_description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtv_version_map
-- ----------------------------
INSERT INTO `rtv_version_map` VALUES ('1', '123', '123', '123', null, '0', '0', null);

-- ----------------------------
-- Table structure for zone_id_assign
-- ----------------------------
DROP TABLE IF EXISTS `zone_id_assign`;
CREATE TABLE `zone_id_assign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `max_uid` bigint(20) NOT NULL,
  `max_tgid` bigint(20) NOT NULL,
  `cur_uid` bigint(20) NOT NULL,
  `cur_tgid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zone_id_assign
-- ----------------------------
INSERT INTO `zone_id_assign` VALUES ('1', 'zone 1', '73728', '131071', '65568', '73736');
INSERT INTO `zone_id_assign` VALUES ('2', 'zone 2', '1221123', '123231', '213112', '1232123');
