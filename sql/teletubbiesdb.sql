/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : teletubbies

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 14/08/2020 00:13:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `content_id` int(64) NOT NULL COMMENT ' 订单内容id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `count` int(11) NOT NULL COMMENT '商品数量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of content
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content_id` int(64) NOT NULL COMMENT '订单内容id',
  `cost` decimal(10, 2) NOT NULL COMMENT '费用',
  `status_id` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 鲜花id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `product_langs` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '花语',
  `product_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简介',
  `product_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情',
  `product_extra_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '额外信息',
  `product_price` decimal(10, 2) NOT NULL COMMENT '单价',
  `product_discount` decimal(10, 2) NOT NULL COMMENT '折扣',
  `product_stock` int(10) NOT NULL COMMENT '库存',
  `product_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '鲜花分类',
  PRIMARY KEY (`product_id`) USING BTREE,
  FULLTEXT INDEX `search_name`(`product_name`, `product_langs`, `product_intro`, `product_details`, `product_extra_info`)
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '宝妈单肩包', '有用', '简介', '好', '额外信息', 79.00, 0.90, 1200, '妈妈专区');
INSERT INTO `product` VALUES (2, '多功能婴儿背带', '很棒', '简介', '好', '信息', 249.00, 1.00, 134, '妈妈专区');
INSERT INTO `product` VALUES (3, '宝妈衣服', '我喜欢', '简介', '好', '信息', 159.00, 0.89, 800, '妈妈专区');
INSERT INTO `product` VALUES (4, '宝妈双肩背包', '挺好', '简介', '好', '信息', 139.00, 0.90, 90, '妈妈专区');
INSERT INTO `product` VALUES (5, '婴儿抽纸', '给力', '简介', '好', '新增花的额外信息', 49.00, 0.90, 100, '宝宝专区');
INSERT INTO `product` VALUES (6, '婴儿尿不湿', 'nice', '简介', '好', '修改花的额外信息', 99.00, 0.90, 100, '宝宝专区');
INSERT INTO `product` VALUES (7, '婴儿摇摇椅', '挺好的', '简介', '好', '信息', 450.00, 0.98, 220, '宝宝专区');
INSERT INTO `product` VALUES (8, '婴儿床', '很给力', '简介', '好', '信息', 1200.00, 0.99, 1100, '宝宝专区');
INSERT INTO `product` VALUES (9, '婴儿洗衣机', '奥里给', '简介', '好', '信息', 1999.00, 0.99, 515, '宝宝专区');
INSERT INTO `product` VALUES (10, '奶粉', '营养搞，孩子爱喝', '婴幼儿奶粉', '奶粉，奶粉', '无', 240.00, 0.88, 999, '亲情鲜花');

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img`  (
  `product_img_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '鲜花id',
  `product_img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`product_img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES (1, 1, 'http://127.0.0.1:8282/imgs/121f0780-3b60-4cd4-b9b0-db24df44ea0c.jpg');
INSERT INTO `product_img` VALUES (2, 2, 'http://127.0.0.1:8282/imgs/49d95333-b1ba-43cc-9144-24dde3661cf5.jpg');
INSERT INTO `product_img` VALUES (3, 3, 'http://127.0.0.1:8282/imgs/0fb6e38e-29d7-4e2c-837a-74170aedbcf2.jpg');
INSERT INTO `product_img` VALUES (4, 4, 'http://127.0.0.1:8282/imgs/6b97f622-7a7e-418c-a60b-f8cdd37f22e9.jpg');
INSERT INTO `product_img` VALUES (5, 5, 'http://127.0.0.1:8282/imgs/b9bbaa28-5e68-4185-a0e0-9aa34a14d8a5.jpg');
INSERT INTO `product_img` VALUES (6, 6, 'http://127.0.0.1:8282/imgs/523867d6-14e1-4aa5-9e80-39988cfd904d.png');
INSERT INTO `product_img` VALUES (7, 7, 'http://127.0.0.1:8282/imgs/a94adc60-b932-406a-a793-45c495fe4f8b.png');
INSERT INTO `product_img` VALUES (8, 8, 'http://127.0.0.1:8282/imgs/2133fab1-64a0-45e7-b060-45d47555f7a6.png');
INSERT INTO `product_img` VALUES (9, 9, 'http://127.0.0.1:8282/imgs/b56e1ded-6021-4802-8f8b-9e0f98d2ca3d.png');
INSERT INTO `product_img` VALUES (10, 10, 'http://127.0.0.1:8282/imgs/4e0214a1-f5bf-45e0-ae41-21ba3dd8f057.jpg');
INSERT INTO `product_img` VALUES (11, 11, '');
INSERT INTO `product_img` VALUES (12, 12, '');
INSERT INTO `product_img` VALUES (13, 13, NULL);
INSERT INTO `product_img` VALUES (14, 14, NULL);
INSERT INTO `product_img` VALUES (15, 15, NULL);
INSERT INTO `product_img` VALUES (16, 16, NULL);

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status`  (
  `status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态id',
  `status_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态名称',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态描述',
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES (1, '已下单', '用户下单付费');
INSERT INTO `status` VALUES (2, '取消订单', '用户取消订单');
INSERT INTO `status` VALUES (3, '已接单', '商家接单');
INSERT INTO `status` VALUES (4, '配送中', '商品正在派送');
INSERT INTO `status` VALUES (5, '已收货', '用户确认收货');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `balance` decimal(10, 0) UNSIGNED NULL DEFAULT 0 COMMENT '余额',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`, `username`) USING BTREE,
  UNIQUE INDEX `u_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'zhangsan', '123456', 10000, 'zhangsan', '1231231231');
INSERT INTO `user` VALUES (3, 'mary', '123456', 4193, '玛丽', '1231231231');
INSERT INTO `user` VALUES (4, 'wangwu', '123456', 100, '王五', '1231231231');
INSERT INTO `user` VALUES (5, 'yizhaosan', '2316453754', 1200, '一朝散', '2316453754');

SET FOREIGN_KEY_CHECKS = 1;
