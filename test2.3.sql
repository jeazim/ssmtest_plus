/*
 Navicat MySQL Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : test2.3

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 01/06/2023 08:49:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `hours` int NOT NULL,
  `schools` int NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `s_c`(`schools`) USING BTREE,
  CONSTRAINT `s_c` FOREIGN KEY (`schools`) REFERENCES `s_school` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES (1, 'C语⾔', 50, 2, 'b6ef6538-7994-4835-a61c-07b0b10fefb8.jpg');
INSERT INTO `c_course` VALUES (5, '软件工程', 30, 1, 'ce8860e1-71e9-426e-8d0a-2b7b1bde9a24.jpg');
INSERT INTO `c_course` VALUES (6, '数据库系统', 100, 1, '4a5798b2-2466-4522-9e7e-34997ebf65fc.png');
INSERT INTO `c_course` VALUES (7, '火车头仔', 100, 3, 'f7ebc1fb-fe49-4e59-9cb2-73cc16f65737.jpg');
INSERT INTO `c_course` VALUES (9, '高级Web技术', 64, 1, '39c9aa77-9afe-4abc-8bf1-748dfc314a67.jpg');
INSERT INTO `c_course` VALUES (11, 'sdfg', 34, 3, 'b4ab724d-7806-4dfd-9d0c-88b7f579c198.png');
INSERT INTO `c_course` VALUES (12, '操作系统', 72, 1, 'ce8860e1-71e9-426e-8d0a-2b7b1bde9a24.jpg');
INSERT INTO `c_course` VALUES (13, '123', 12, 3, '6340cb07-4078-42d2-9f51-9c14c13d24dd.jpg');
INSERT INTO `c_course` VALUES (14, 'jeazim', 100, 4, 'ce8860e1-71e9-426e-8d0a-2b7b1bde9a24.jpg');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for s_school
-- ----------------------------
DROP TABLE IF EXISTS `s_school`;
CREATE TABLE `s_school`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `school_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_school
-- ----------------------------
INSERT INTO `s_school` VALUES (1, '计算机学院');
INSERT INTO `s_school` VALUES (2, '土木学院');
INSERT INTO `s_school` VALUES (3, '二次元学院');
INSERT INTO `s_school` VALUES (4, '祝天下人人过上好日子');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(225) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(225) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pri` int NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456', 0);
INSERT INTO `user` VALUES ('11', '11', 0);
INSERT INTO `user` VALUES ('112', '11', 0);
INSERT INTO `user` VALUES ('123', '123', 0);
INSERT INTO `user` VALUES ('jeazim', '11', 1);

SET FOREIGN_KEY_CHECKS = 1;
