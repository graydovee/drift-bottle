/*
Navicat MySQL Data Transfer

Source Server         : 腾讯数据库
Source Server Version : 50718
Source Database       : java

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-01-08 18:12:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bottle`
-- ----------------------------
DROP TABLE IF EXISTS `bottle`;
CREATE TABLE `bottle` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `message` text,
  `kinds` int(2) NOT NULL DEFAULT '0',
  `state` int(2) NOT NULL DEFAULT '0',
  `create_time` date NOT NULL,
  `uid` int(10) NOT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `sex` int(2) NOT NULL,
  `birth` date DEFAULT NULL,
  `pick_up_times` int(3) NOT NULL DEFAULT '3',
  `throw_times` int(3) NOT NULL DEFAULT '6',
  PRIMARY KEY (`uid`,`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
