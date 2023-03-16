/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : library_seat_selection

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/03/2023 20:22:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for historyrecord
-- ----------------------------
DROP TABLE IF EXISTS `historyrecord`;
CREATE TABLE `historyrecord`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录条数',
  `userid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号/工号',
  `seatid` int NOT NULL COMMENT '座位号',
  `reservation` datetime NOT NULL COMMENT '预约时间',
  `reachtime` datetime NULL DEFAULT NULL COMMENT '签到时间',
  `leavetime` datetime NULL DEFAULT NULL COMMENT '签离时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `historyrecord_foreignkey_userid`(`userid`) USING BTREE,
  INDEX `historyrecord_foreignkey_seatid`(`seatid`) USING BTREE,
  CONSTRAINT `historyrecord_foreignkey_seatid` FOREIGN KEY (`seatid`) REFERENCES `seatinfo` (`seatid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `historyrecord_foreignkey_userid` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for layout
-- ----------------------------
DROP TABLE IF EXISTS `layout`;
CREATE TABLE `layout`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标识',
  `campus` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '隶属的图书馆',
  `floor` int NOT NULL COMMENT '隶属的楼层',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '布局内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for seatinfo
-- ----------------------------
DROP TABLE IF EXISTS `seatinfo`;
CREATE TABLE `seatinfo`  (
  `seatid` int NOT NULL AUTO_INCREMENT COMMENT '座位唯一标识',
  `campus` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '隶属的图书馆',
  `floor` int NULL DEFAULT NULL COMMENT '隶属的楼层',
  `deskno` int NULL DEFAULT NULL COMMENT '桌号',
  `desktype` int NULL DEFAULT NULL COMMENT '桌子类型；0表示不可用',
  `occupynum` int NOT NULL DEFAULT 0 COMMENT '目前桌子人数情况',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签id，以逗号分隔',
  PRIMARY KEY (`seatid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '座位特征标签名称',
  `faculty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认对应的学院',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号/工号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `identity` int NOT NULL COMMENT '身份，0-老师/1-学生',
  `gender` int NULL DEFAULT NULL COMMENT '性别，0-男/1-女',
  `campus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '校区',
  `faculty` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院',
  `liketag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '偏爱的tag id',
  `seatid` int NULL DEFAULT NULL COMMENT '占座桌号，未占座为null',
  `reservetime` datetime NULL DEFAULT NULL COMMENT '预约时间',
  `state` int NOT NULL DEFAULT 0 COMMENT '当前状态，0-未占座/1-已选座/2-暂离',
  `violate` int NOT NULL DEFAULT 0 COMMENT '违规记录次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userinfo_foreignkey_seatid`(`seatid`) USING BTREE,
  CONSTRAINT `userinfo_foreignkey_seatid` FOREIGN KEY (`seatid`) REFERENCES `seatinfo` (`seatid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
