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

 Date: 08/10/2021 21:05:32
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
  CONSTRAINT `historyrecord_foreignkey_seatid` FOREIGN KEY (`seatid`) REFERENCES `seatinfo` (`seatid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `historyrecord_foreignkey_userid` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of historyrecord
-- ----------------------------
INSERT INTO `historyrecord` VALUES (9, '1111', 1, '2021-10-07 22:29:44', '2021-10-07 23:02:13', '2021-10-07 23:22:58');
INSERT INTO `historyrecord` VALUES (10, '1111', 3, '2021-10-08 11:21:46', '2021-10-08 11:26:40', '2021-10-08 11:29:43');
INSERT INTO `historyrecord` VALUES (11, '1111', 3, '2021-10-08 11:35:23', '2021-10-08 11:46:04', '2021-10-08 11:46:04');
INSERT INTO `historyrecord` VALUES (12, '1111', 7, '2021-10-08 11:45:54', '2021-10-08 11:49:53', '2021-10-08 11:49:59');
INSERT INTO `historyrecord` VALUES (13, '1111', 10, '2021-10-08 11:55:03', '2021-10-08 11:55:09', '2021-10-08 12:00:25');
INSERT INTO `historyrecord` VALUES (14, '1111', 9, '2021-10-08 12:00:48', '2021-10-08 12:00:55', '2021-10-08 12:02:26');
INSERT INTO `historyrecord` VALUES (15, '1111', 13, '2021-10-08 12:03:07', '2021-10-08 12:03:11', '2021-10-08 12:03:15');
INSERT INTO `historyrecord` VALUES (16, '1111', 15, '2021-10-08 12:03:54', '2021-10-08 12:03:58', '2021-10-08 12:04:00');
INSERT INTO `historyrecord` VALUES (17, '3333', 2, '2021-10-08 12:00:48', '2021-10-08 12:00:55', '2021-10-08 12:11:26');
INSERT INTO `historyrecord` VALUES (18, '3333', 8, '2021-10-08 12:03:07', '2021-10-08 12:11:11', '2021-10-08 12:23:15');
INSERT INTO `historyrecord` VALUES (19, '3333', 11, '2021-10-08 12:03:54', '2021-10-08 12:33:58', '2021-10-08 12:44:00');
INSERT INTO `historyrecord` VALUES (20, '3334', 13, '2021-10-08 12:03:07', '2021-10-08 13:03:11', '2021-10-08 13:13:15');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签id，以逗号分隔',
  PRIMARY KEY (`seatid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seatinfo
-- ----------------------------
INSERT INTO `seatinfo` VALUES (1, '朝晖', 1, 2, 4, 6, '1,2,');
INSERT INTO `seatinfo` VALUES (2, '朝晖', 1, 7, 6, 6, '2,');
INSERT INTO `seatinfo` VALUES (3, '朝晖', 1, 8, 6, 1, '5,');
INSERT INTO `seatinfo` VALUES (4, '朝晖', 1, 3, 6, 2, '5,');
INSERT INTO `seatinfo` VALUES (5, '朝晖', 1, 4, 4, 0, '5,');
INSERT INTO `seatinfo` VALUES (6, '朝晖', 1, 5, 6, 6, '3,4,');
INSERT INTO `seatinfo` VALUES (7, '朝晖', 1, 6, 4, 3, '4,');
INSERT INTO `seatinfo` VALUES (8, '朝晖', 1, 1, 4, 4, '5,');
INSERT INTO `seatinfo` VALUES (9, '朝晖', 1, 9, 4, 3, '1,4,');
INSERT INTO `seatinfo` VALUES (10, '朝晖', 1, 10, 4, 2, '1,4,');
INSERT INTO `seatinfo` VALUES (11, '朝晖', 2, 7, 4, 2, '5,');
INSERT INTO `seatinfo` VALUES (12, '朝晖', 2, 10, 6, 0, '2,3,4,');
INSERT INTO `seatinfo` VALUES (13, '朝晖', 3, 9, 6, 4, '1,3,4,');
INSERT INTO `seatinfo` VALUES (14, '朝晖', 3, 3, 4, 3, '5,');
INSERT INTO `seatinfo` VALUES (15, '朝晖', 3, 6, 4, 1, '5,');
INSERT INTO `seatinfo` VALUES (16, '朝晖', 3, 7, 4, 2, '2,');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '靠窗', NULL, NULL);
INSERT INTO `tag` VALUES (2, '靠门', NULL, '吵闹');
INSERT INTO `tag` VALUES (3, '靠厕所', NULL, '吵闹');
INSERT INTO `tag` VALUES (4, '有电源', '计算机,信息,艺术', '比较抢手');
INSERT INTO `tag` VALUES (5, '普通', NULL, '一般必备标签');

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

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('0101', '王五', '123', 0, NULL, '屏峰', '计算机', NULL, NULL, NULL, 0, 0);
INSERT INTO `userinfo` VALUES ('1111', '张三', '123', 1, NULL, '屏峰', '教科', NULL, NULL, NULL, 0, 0);
INSERT INTO `userinfo` VALUES ('2222', '李四', '123', 1, NULL, '朝晖', '计算机', NULL, NULL, NULL, 0, 0);
INSERT INTO `userinfo` VALUES ('3333', '陈二', '123', 1, NULL, '莫干山', '教科', NULL, NULL, NULL, 0, 0);
INSERT INTO `userinfo` VALUES ('3334', '陈小', '123', 1, NULL, '屏峰', '教科', NULL, NULL, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
