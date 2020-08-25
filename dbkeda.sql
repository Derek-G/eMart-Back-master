/*
 Navicat Premium Data Transfer

 Source Server         : mysql本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : dbkeda

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 05/08/2019 15:42:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'Seafood', 29.99, '');
INSERT INTO `category` VALUES (2, 'ZEK Nori', 8.90, 'ZEK Nori Details');
INSERT INTO `category` VALUES (3, 'Sala 1+1Chicken Leg', 29.99, 'Sala 1+1Chicken Leg Details');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datetime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `summoney` decimal(10,2) DEFAULT NULL,
  `state` tinyint(4) NOT NULL COMMENT '订单状态',
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (9, '2018-01-03 16:59:50', 8.90, 0, 2);
INSERT INTO `order` VALUES (10, '2018-01-04 10:07:43', 8.90, 0, 2);
INSERT INTO `order` VALUES (11, '2019-08-05 11:31:10', 10.00, 0, 2);
INSERT INTO `order` VALUES (12, '2019-08-05 11:31:40', 10.00, 0, 2);
COMMIT;

-- ----------------------------
-- Table structure for order_category
-- ----------------------------
DROP TABLE IF EXISTS `order_category`;
CREATE TABLE `order_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `num` int(11) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_category
-- ----------------------------
BEGIN;
INSERT INTO `order_category` VALUES (5, 9, 2, 1, '2018-01-03 14:32:32');
INSERT INTO `order_category` VALUES (6, 10, 2, 1, '2018-01-04 10:07:43');
INSERT INTO `order_category` VALUES (7, 11, 1, 1, '2019-08-05 11:31:11');
INSERT INTO `order_category` VALUES (8, 12, 1, 1, '2019-08-05 11:31:40');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (2, 'admin', '123456', '18361248888', 'BeiJing');
INSERT INTO `user` VALUES (3, 'test', '123456', '18361222222', 'BeiJing');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
