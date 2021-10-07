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

 Date: 03/10/2021 23:01:19
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
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1010', '赵六', '123');

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
  CONSTRAINT `historyrecord_foreignkey_userid` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `historyrecord_foreignkey_seatid` FOREIGN KEY (`seatid`) REFERENCES `seatinfo` (`seatid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of historyrecord
-- ----------------------------

-- ----------------------------
-- Table structure for layout
-- ----------------------------
DROP TABLE IF EXISTS `layout`;
CREATE TABLE `layout`  (
  `id` int NOT NULL COMMENT '标识',
  `campus` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '隶属的图书馆',
  `floor` int NOT NULL COMMENT '隶属的楼层',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '布局内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of layout
-- ----------------------------
INSERT INTO `layout` VALUES (1, '朝晖', 1, '\'e_ff_e\',\'effffe\',\'e_ff_e\',\'f_ff_f\'');

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
  PRIMARY KEY (`seatid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seatinfo
-- ----------------------------
INSERT INTO `seatinfo` VALUES (1, '朝晖', 1, 2, 4, 0);
INSERT INTO `seatinfo` VALUES (2, '朝晖', 1, 7, 6, 6);
INSERT INTO `seatinfo` VALUES (3, '朝晖', 1, 8, 6, 0);
INSERT INTO `seatinfo` VALUES (4, '朝晖', 1, 3, 6, 2);
INSERT INTO `seatinfo` VALUES (5, '朝晖', 1, 4, 4, 0);
INSERT INTO `seatinfo` VALUES (6, '朝晖', 1, 5, 6, 6);
INSERT INTO `seatinfo` VALUES (7, '朝晖', 1, 6, 4, 3);
INSERT INTO `seatinfo` VALUES (8, '朝晖', 1, 1, 4, 4);
INSERT INTO `seatinfo` VALUES (9, '朝晖', 1, 9, 4, 3);
INSERT INTO `seatinfo` VALUES (10, '朝晖', 1, 10, 4, 2);

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
     `seatid` int NULL DEFAULT NULL COMMENT '占座桌号，未占座为null',
     `reservetime` datetime NULL DEFAULT NULL COMMENT '预约时间',
     `state` int NOT NULL DEFAULT 0 COMMENT '当前状态，0-未占座/1-已选座/2-暂离',
     `violate` int NOT NULL DEFAULT 0 COMMENT '违规记录次数',
     PRIMARY KEY (`id`) USING BTREE,
     INDEX `userinfo_foreignkey_seatid`(`seatid`) USING BTREE,
     CONSTRAINT `userinfo_foreignkey_seatid` FOREIGN KEY (`seatid`) REFERENCES `seatinfo` (`seatid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('0101', '王五', '123', 0, NULL, '屏峰', NULL, NUll, 0, 0);
INSERT INTO `userinfo` VALUES ('1111', '张三', '123', 1, NULL, '屏峰', NULL, NUll, 0, 0);
INSERT INTO `userinfo` VALUES ('2222', '李四', '123', 1, NULL, '朝晖', NULL, NUll, 0, 0);
INSERT INTO `userinfo` VALUES ('3333', '陈二', '123', 1, NULL, '莫干山', NULL, NUll, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
