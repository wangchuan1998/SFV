/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : portal

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2013-10-03 10:21:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `photos`
-- ----------------------------
DROP TABLE IF EXISTS `photos`;
CREATE TABLE `photos` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `description` varchar(1000) default NULL,
  `create_time` datetime default NULL,
  `img_url` varchar(1000) default NULL,
  `img_url_s` varchar(1000) default NULL,
  `extend_name` varchar(10) default NULL,
  `userid` varchar(36) default NULL,
  `albumid` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photos
-- ----------------------------

-- ----------------------------
-- Table structure for `photo_albums`
-- ----------------------------
DROP TABLE IF EXISTS `photo_albums`;
CREATE TABLE `photo_albums` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `create_time` datetime default NULL,
  `userid` varchar(36) default NULL,
  `description` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo_albums
-- ----------------------------
INSERT INTO `photo_albums` VALUES ('26', '骑行都江堰', '2013-07-14 23:30:11', '8a0d69cf-d19e-426a-be65-f0c18a038436', '骑行都江堰');
INSERT INTO `photo_albums` VALUES ('29', '挑战世界之巅', '2013-07-15 22:31:34', '8a0d69cf-d19e-426a-be65-f0c18a038436', '');
INSERT INTO `photo_albums` VALUES ('30', '九寨沟之行', '2013-07-18 23:36:24', '8a0d69cf-d19e-426a-be65-f0c18a038436', '');
INSERT INTO `photo_albums` VALUES ('31', 'kkkk', '2013-09-20 20:41:48', '8a86abdc4139aebf014139af64940001', 'jljkl');
INSERT INTO `photo_albums` VALUES ('32', 'kkkk', '2013-09-20 20:41:49', '8a86abdc4139aebf014139af64940001', 'jljkl');
INSERT INTO `photo_albums` VALUES ('33', 'adasdas', '2013-09-20 20:45:05', '8a86abdc4139aebf014139af64940001', 'dasd');
INSERT INTO `photo_albums` VALUES ('34', '香港之行', '2013-09-20 20:47:26', '386900c6-c2a7-4e61-b14a-b809ae0ab0d9', '');
INSERT INTO `photo_albums` VALUES ('35', 'asda', '2013-09-20 20:48:57', '386900c6-c2a7-4e61-b14a-b809ae0ab0d9', 'asdasd');
INSERT INTO `photo_albums` VALUES ('36', 'asdf', '2013-09-20 20:50:05', '386900c6-c2a7-4e61-b14a-b809ae0ab0d9', 'sadfasdfsdf');
INSERT INTO `photo_albums` VALUES ('37', 'qweqwe', '2013-10-02 12:08:33', '386900c6-c2a7-4e61-b14a-b809ae0ab0d9', '');

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(10) NOT NULL auto_increment,
  `info` varchar(1000) default NULL,
  `userid` varchar(36) default NULL,
  `creat_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '今天又吃面', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-14 23:30:11');
INSERT INTO `sign` VALUES ('2', '中午去了趟市里，好热~~', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-14 23:30:11');
INSERT INTO `sign` VALUES ('3', '尼玛，又下雨了', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-14 23:30:11');
INSERT INTO `sign` VALUES ('4', '今年诸事不顺，要不要找个大师卜一卦。', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-14 23:30:11');
INSERT INTO `sign` VALUES ('5', '其实，阿尔萨斯这样的人物是鲜活的，主要是因为现实生活中这样的人确实存在，而且不在少数。那些从一生下来就被万众所瞩目，带着众多希望长起来的孩子，却多难成大气，原因就在这里。如此看来，这样的孩子，未必是幸福的，也未必是幸运的。\r\n					有人说，阿尔萨斯的堕落主要在于他自己刚愎自用的个性。但我想：历史上那么多成就大事的人物，他们是不是也很自信呢？“自信”是个褒义词，“刚愎自用”是个贬义词，两者表达的意思相近。不同在于，结果成功了，我们就说那人是“自信”的，不成功，就是“刚愎自用”了。这就是其中的差别了。', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-14 23:30:11');
INSERT INTO `sign` VALUES ('6', 'qwe', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-30 23:23:56');
INSERT INTO `sign` VALUES ('8', '发表一条状态，让朋友知道你~', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-31 21:31:19');
INSERT INTO `sign` VALUES ('9', '啊时代发生的', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-31 21:37:39');
INSERT INTO `sign` VALUES ('10', '发表一条状态，让朋友知道你~', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-07-31 23:05:06');
INSERT INTO `sign` VALUES ('11', 'aaaaaaaaaaaaaaa', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-08-04 19:07:59');
INSERT INTO `sign` VALUES ('12', '?????????????~', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-08-14 22:05:30');
INSERT INTO `sign` VALUES ('13', 'bmnbnm', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-08-14 22:30:57');
INSERT INTO `sign` VALUES ('14', 'vbnvnvn', '8a0d69cf-d19e-426a-be65-f0c18a038436', '2013-08-14 22:31:19');

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(36) NOT NULL,
  `realname` varchar(50) default NULL,
  `head_url` varchar(1000) default NULL,
  `email` varchar(40) default NULL,
  `password` varchar(100) NOT NULL default '',
  `grade` int(10) default NULL,
  `sex` char(1) default NULL,
  `birth_day` varchar(100) default NULL,
  `register_time` varchar(100) default NULL,
  `login_times` int(10) default NULL,
  `region` varchar(200) default NULL,
  `region_detail` varchar(1000) default NULL,
  `degree` varchar(100) default NULL,
  `height` int(3) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('386900c6-c2a7-4e61-b14a-b809ae0ab0d9', '乔峰', '/userfiles/photos/386900c6-c2a7-4e61-b14a-b809ae0ab0d9/1380706858880.jpg', 'duanbaiqiang@huawei.com', 'e6e061838856bf47e1de730719fb2609', '1', 'm', '', null, '291', '选择省份/选择城市/选择地区', null, '高中', '175');
INSERT INTO `system_user` VALUES ('8a0d69cf-d19e-426a-be65-f0c18a038436', '令狐冲', '/userfiles/photos/8a0d69cf-d19e-426a-be65-f0c18a038436/1380116266601.jpg', 'duanbaiqiang@126.com', 'e6e061838856bf47e1de730719fb2609', '1', 'm', '', null, '70', '甘肃省/白银市/会宁县', null, '高中', '187');
INSERT INTO `system_user` VALUES ('8a86abdc4139aebf014139af64940001', '少林寺', '/userfiles/photos/8a86abdc4139aebf014139af64940001/1379854917755.jpg', 'duanbaiqiang@qq.com', 'cbdcf5c49e6dae237884a36ec237cfe9', '1', 'f', '', '1379652297673', '22', '四川省/成都市/锦江区', null, '中专', '177');
