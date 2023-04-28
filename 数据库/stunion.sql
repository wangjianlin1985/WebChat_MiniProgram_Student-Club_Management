/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : stunion

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2022-03-09 19:50:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stunionId` int(11) DEFAULT '0' COMMENT '社团',
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '活动名称',
  `pic` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '封面',
  `hits` int(11) DEFAULT '0' COMMENT '点赞',
  `comments` int(11) DEFAULT '0' COMMENT '评论',
  `collects` int(11) DEFAULT '0' COMMENT '收藏',
  `content` longtext COLLATE utf8_bin COMMENT '活动明细',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社团活动表';

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '1', '街舞街舞、随时可舞、开始举行街舞大赛活动了', '/static/uploads/20220307/20220307224443.jpg', '4', '4', '1', '', '2022-03-07 22:44:47', null);
INSERT INTO `activity` VALUES ('2', '3', '2022开年举办跆拳道大赛活动', '/static/uploads/20220309/20220309154113.jpg', '3', '1', '1', 0x3C703E3C623EE6B4BBE58AA8E7AD96E588923C2F623E3C2F703E3C703EE6B4BBE58AA8E7AD96E58892E5A682E4B88BEFBC9A3C2F703E3C703E3C623EE6B4BBE58AA8E5B7A8E5B9953C2F623E3C2F703E3C703E3C623E3C62723E3C2F623E3C2F703E3C703E3C623EE6B4BBE58AA8E7BB86E588993C2F623E3C2F703E3C703E3C623E3C62723E3C2F623E3C2F703E3C703E3C623EE6B4BBE58AA8E5A596E593813C2F623E3C2F703E, '2022-03-09 15:42:02', null);

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `studentId` varchar(11) COLLATE utf8_bin DEFAULT '0' COMMENT '学生',
  `activityId` int(11) DEFAULT '0' COMMENT '活动',
  `reason` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '申请理由',
  `status` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `iscredit` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '是否打分',
  `credit` int(11) DEFAULT '0' COMMENT '学分',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '申请时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活动申请表';

-- ----------------------------
-- Records of activity_apply
-- ----------------------------
INSERT INTO `activity_apply` VALUES ('3', '3', '2', null, '1', '1', '1', '2022-03-09 15:43:53', '2022-03-09 15:47:09');
INSERT INTO `activity_apply` VALUES ('2', '2', '1', null, '1', '1', '2', '2022-03-09 00:06:33', '2022-03-09 15:13:45');

-- ----------------------------
-- Table structure for activity_collect
-- ----------------------------
DROP TABLE IF EXISTS `activity_collect`;
CREATE TABLE `activity_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `studentId` int(11) DEFAULT '0' COMMENT '学生',
  `activityId` int(11) DEFAULT '0' COMMENT '活动',
  `content` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '评论',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '收藏时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活动收藏表';

-- ----------------------------
-- Records of activity_collect
-- ----------------------------
INSERT INTO `activity_collect` VALUES ('7', '3', '1', null, '2022-03-09 15:45:58', null);
INSERT INTO `activity_collect` VALUES ('6', '3', '2', null, '2022-03-09 15:43:57', null);

-- ----------------------------
-- Table structure for activity_comment
-- ----------------------------
DROP TABLE IF EXISTS `activity_comment`;
CREATE TABLE `activity_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `studentId` int(11) DEFAULT '0' COMMENT '学生',
  `activityId` int(11) DEFAULT '0' COMMENT '活动',
  `content` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '评论',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '评论时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活动评论表';

-- ----------------------------
-- Records of activity_comment
-- ----------------------------
INSERT INTO `activity_comment` VALUES ('2', '3', '2', '大家赶紧参与吧，强身健体', '2022-03-09 15:44:17', null);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cate` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '类别',
  `title` longtext COLLATE utf8_bin COMMENT '标题',
  `content` longtext COLLATE utf8_bin COMMENT '内容',
  `pic` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '封面',
  `created_at` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updated_at` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文章';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', null, 0xE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918A, 0x3C703EE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918A3C2F703E3C703EE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918A3C2F703E3C703EE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918A3C2F703E3C703EE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918AE8BF99E698AFE7B3BBE7BB9FE58F91E5B883E79A84E7ACACE4B880E69DA1E585ACE5918A3C2F703E, null, null, null, '2022-03-08 22:13:23', null);
INSERT INTO `article` VALUES ('2', null, 0xE58F91E5B883E4B880E69DA1E585B3E4BA8EE585A8E4BD93E7A4BEE59BA2E585ACE5918AE8A5BFE4BFA1E681AF, 0x3C703EE58F91E5B883E4B880E69DA1E585B3E4BA8EE585A8E4BD93E7A4BEE59BA2E585ACE5918AE8A5BFE4BFA1E681AFE58F91E5B883E4B880E69DA1E585B3E4BA8EE585A8E4BD93E7A4BEE59BA2E585ACE5918AE8A5BFE4BFA1E681AF3C2F703E, null, null, null, '2022-03-09 15:39:31', null);

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `notes` longtext COLLATE utf8_bin COMMENT '备注',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='班级信息表';

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '数学学院18级01班', '', '2022-03-07 22:45:41', '2022-03-09 15:38:02');
INSERT INTO `clazz` VALUES ('2', '美术学院18级01班', '', '2022-03-07 22:45:52', '2022-03-09 15:37:48');
INSERT INTO `clazz` VALUES ('3', '体育学院18级03班', '', '2022-03-09 15:37:39', null);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `createdAt` text COLLATE utf8_bin COMMENT '创建时间',
  `updatedAt` longtext COLLATE utf8_bin COMMENT '更新时间',
  `code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `val` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '值',
  `txt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '显示',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', 0x323032322D30332D30362032323A33303A3538, null, 'user_role', 'admin', '管理员');
INSERT INTO `dict` VALUES ('2', 0x323032322D30332D30362032323A33313A3231, 0x323032322D30332D30372032323A32353A3131, 'user_role', 'leader', '社团部长');
INSERT INTO `dict` VALUES ('3', 0x323032322D30332D30372032323A32353A3531, null, 'stunion_apply_status', '0', '申请中');
INSERT INTO `dict` VALUES ('4', 0x323032322D30332D30372032323A32363A3035, null, 'stunion_apply_status', '1', '同意入团');
INSERT INTO `dict` VALUES ('5', 0x323032322D30332D30372032323A32363A3433, null, 'stunion_apply_status', '-1', '拒绝入团');
INSERT INTO `dict` VALUES ('6', 0x323032322D30332D30372032323A32353A3531, '', 'activity_apply_status', '0', '申请中');
INSERT INTO `dict` VALUES ('7', 0x323032322D30332D30372032323A32363A3035, '', 'activity_apply_status', '1', '同意入团');
INSERT INTO `dict` VALUES ('8', 0x323032322D30332D30372032323A32363A3433, '', 'activity_apply_status', '-1', '拒绝入团');
INSERT INTO `dict` VALUES ('9', null, null, 'is_credit', '0', '未打分');
INSERT INTO `dict` VALUES ('10', null, null, 'is_credit', '1', '已打分');

-- ----------------------------
-- Table structure for scenery
-- ----------------------------
DROP TABLE IF EXISTS `scenery`;
CREATE TABLE `scenery` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stunionId` int(11) DEFAULT '0' COMMENT '社团',
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `pic` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '封面',
  `content` longtext COLLATE utf8_bin COMMENT '内容',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社团风采表';

-- ----------------------------
-- Records of scenery
-- ----------------------------
INSERT INTO `scenery` VALUES ('1', '1', '1111111111111111111', '/static/uploads/20220309/20220309140718.jpg', 0x3131, '2022-03-09 14:07:19', null);
INSERT INTO `scenery` VALUES ('2', '3', '跆拳道日常训练', '/static/uploads/20220309/20220309154519.jpg', 0x3C703EE8B786E68BB3E98193E697A5E5B8B8E8AEADE7BB833C2F703E3C703EE8B786E68BB3E98193E697A5E5B8B8E8AEADE7BB833C2F703E3C703EE8B786E68BB3E98193E697A5E5B8B8E8AEADE7BB833C2F703E3C703EE8B786E68BB3E98193E697A5E5B8B8E8AEADE7BB833C2F703E3C703E3C62723E3C2F703E, '2022-03-09 15:45:23', null);

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sno` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `clazzId` int(11) DEFAULT '0' COMMENT '班级',
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `idcard` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `credit` int(11) DEFAULT '0' COMMENT '学分',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '注册时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生信息表';

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1', '壹诺', '280101', '1', '123456', null, null, null, '0', null, null);
INSERT INTO `students` VALUES ('2', '武磊', '180101', '1', '123456', '1', '2', '3', '2', '2022-03-08 23:02:02', '2022-03-09 14:33:07');
INSERT INTO `students` VALUES ('3', '吴菲菲', '1800301', '3', '123456', '18812346789', '', '', '1', '2022-03-09 15:43:08', null);

-- ----------------------------
-- Table structure for study
-- ----------------------------
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `pic` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '封面',
  `content` longtext COLLATE utf8_bin COMMENT '内容',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学习园地表';

-- ----------------------------
-- Records of study
-- ----------------------------
INSERT INTO `study` VALUES ('1', '阿斯顿法国红酒看来', '/static/uploads/20220309/20220309131636.jpg', 0x3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E3C703EE998BFE696AFE9A1BFE6B395E59BBDE7BAA2E98592E79C8BE69DA53C2F703E, '2022-03-09 13:16:37', '2022-03-09 13:21:59');

-- ----------------------------
-- Table structure for stunion
-- ----------------------------
DROP TABLE IF EXISTS `stunion`;
CREATE TABLE `stunion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `logo` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'logo',
  `userId` int(11) DEFAULT '0' COMMENT '部长',
  `tel` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `slogan` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '口号',
  `content` longtext COLLATE utf8_bin COMMENT '简介',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社团信息表';

-- ----------------------------
-- Records of stunion
-- ----------------------------
INSERT INTO `stunion` VALUES ('1', '街舞社团', '/static/uploads/20220307/20220307224341.png', '3', '18812345555', '街舞街舞、随时可舞', 0x3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E3C703EE8A197E8889EE8A197E8889EE38081E99A8FE697B6E58FAFE8889E3C2F703E, '2022-03-07 22:44:13', '2022-03-07 22:46:31');
INSERT INTO `stunion` VALUES ('2', '双截棍社团', '/static/uploads/20220308/20220308201920.jpg', '4', '', '哼哼哈嘿', '', '2022-03-08 20:19:33', null);
INSERT INTO `stunion` VALUES ('3', '跆拳道社团', '/static/uploads/20220309/20220309154018.jpg', '5', '18812345555', '跆拳道呀，真厉害啊', 0x3C703EE8B786E68BB3E98193E7A4BEE59BA23C2F703E3C703EE8B786E68BB3E98193E59180EFBC8CE79C9FE58E89E5AEB3E5958A3C2F703E, '2022-03-09 15:40:43', null);

-- ----------------------------
-- Table structure for stunion_apply
-- ----------------------------
DROP TABLE IF EXISTS `stunion_apply`;
CREATE TABLE `stunion_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `studentId` int(11) DEFAULT '0' COMMENT '学生',
  `stunionId` int(11) DEFAULT '0' COMMENT '社团',
  `reason` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '申请理由',
  `status` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '申请时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='社团申请表';

-- ----------------------------
-- Records of stunion_apply
-- ----------------------------
INSERT INTO `stunion_apply` VALUES ('1', '2', '1', null, null, '2022-03-08 23:22:14', null);
INSERT INTO `stunion_apply` VALUES ('4', '3', '3', null, '1', '2022-03-09 15:43:43', '2022-03-09 15:44:43');
INSERT INTO `stunion_apply` VALUES ('3', '2', '1', null, '1', '2022-03-08 23:22:58', null);
INSERT INTO `stunion_apply` VALUES ('5', '3', '2', null, '0', '2022-03-09 15:46:09', null);

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` longtext COLLATE utf8_bin COMMENT '内容',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='新闻信息表';

-- ----------------------------
-- Records of t_article
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `role` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '角色',
  `idcard` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `address` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `notes` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `createdAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updatedAt` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '管理员', 'admin', '123456', 'admin', '101010198801011234', '18812345555', '230085568@qq.com', '北京望京', '', null, '2022-03-06 22:35:19');
INSERT INTO `users` VALUES ('3', '吴迪', 'wudi', '123456', 'leader', '', '', '', '', '', '2022-03-07 22:46:14', null);
INSERT INTO `users` VALUES ('4', '严琦', 'yanqi', '123456', 'leader', '', '', '', '', '', '2022-03-07 22:46:25', null);
INSERT INTO `users` VALUES ('5', '王五', 'wangwu', '123456', 'leader', '101010198812345555', '18812345555', 'wangwu@163.com', '北京朝阳区', '', '2022-03-09 15:38:49', null);
