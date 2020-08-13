/*
 Navicat Premium Data Transfer

 Source Server         : CentOS-MySql
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 47.101.58.129:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 28/04/2020 15:25:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `content_id` int(64) NOT NULL COMMENT ' 订单内容id',
  `product_id` int(11) NOT NULL COMMENT '鲜花id',
  `count` int(11) NOT NULL COMMENT '鲜花数量'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1827648975, 1, 10);
INSERT INTO `content` VALUES (1827648975, 3, 10);
INSERT INTO `content` VALUES (1431428550, 1, 10);
INSERT INTO `content` VALUES (1431428550, 3, 10);
INSERT INTO `content` VALUES (595092594, 5, 10);
INSERT INTO `content` VALUES (595092594, 4, 10);
INSERT INTO `content` VALUES (1965446990, 1, 10);
INSERT INTO `content` VALUES (1965446990, 4, 10);

-- ----------------------------
-- Table structure for flower
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
) ENGINE = MyISAM AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO `product` VALUES (1, '百合', '想把世界最好的给你，却发现世上最好的是你', '11只红玫瑰，满天星，2只小熊，栀子叶搭配', '由于花材的季节原因和手工包扎，可能在花束的形式和搭配上与图片不完全一致，但我们保证鲜花的主花材品种、新鲜程度、数量、颜色与说明一致，谢谢。', '额外信息', 159.00, 0.90, 60, '亲情鲜花');
INSERT INTO `product` VALUES (3, '康乃馨', '想把世界最好的给你，却发现世上最好的是你', '11只红玫瑰，满天星，2只小熊，栀子叶搭配', '由于花材的季节原因和手工包扎，可能在花束的形式和搭配上与图片不完全一致，但我们保证鲜花的主花材品种、新鲜程度、数量、颜色与说明一致，谢谢。', '信息', 159.00, 0.90, 70, '亲情鲜花');
INSERT INTO `product` VALUES (4, '风信子', '想把世界最好的给你，却发现世上最好的是你', '11只红玫瑰，满天星，2只小熊，栀子叶搭配', '由于花材的季节原因和手工包扎，可能在花束的形式和搭配上与图片不完全一致，但我们保证鲜花的主花材品种、新鲜程度、数量、颜色与说明一致，谢谢。', '信息', 159.00, 0.90, 80, '友情鲜花');
INSERT INTO `product` VALUES (5, '栀子花', '想把世界最好的给你，却发现世上最好的是你', '11只红玫瑰，满天星，2只小熊，栀子叶搭配', '由于花材的季节原因和手工包扎，可能在花束的形式和搭配上与图片不完全一致，但我们保证鲜花的主花材品种、新鲜程度、数量、颜色与说明一致，谢谢。', '信息', 159.00, 0.90, 90, '友情鲜花');
INSERT INTO `product` VALUES (7, '丁香花', '新增花的花语', '新增花的简介', '新增花的描述', '新增花的额外信息', 159.00, 0.90, 100, '友情鲜花');
INSERT INTO `product` VALUES (8, '水仙花', '修改花的花语', '修改花的简介', '修改花的描述', '修改花的额外信息', 159.00, 0.90, 100, NULL);
INSERT INTO `product` VALUES (10, '玫瑰花', '花语', '简介', '描述', '信息', 22.00, 0.00, 22, '爱情鲜花');
INSERT INTO `product` VALUES (11, '紫罗兰', '花语', '简介', '描述', '信息', 11.00, 0.00, 11, NULL);
INSERT INTO `product` VALUES (12, '蔷薇花', '花语', '简介', '描述', '信息', 330.00, 0.10, 44, NULL);
INSERT INTO `product` VALUES (13, 'test', 'test', 'test', 'test', 'test extro', 159.00, 0.90, 60, 'test class');
INSERT INTO `product` VALUES (14, 'test', 'test', 'test', 'test', 'test', 10.00, 1.00, 99, 'test class modify');

-- ----------------------------
-- Table structure for goods_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img`  (
  `product_img_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '鲜花id',
  `product_img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`product_img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flower_img
-- ----------------------------
INSERT INTO `product_img` VALUES (2, 1, '127.0.0.1:8081/img/dc77d190-dc51-4035-a0ca-ad770a1a3cd7.png');
INSERT INTO `product_img` VALUES (5, 1, 'http://47.101.58.129:8282/imgs/3d3c412b-787b-4587-aa0c-189351e61bff.jpg');
INSERT INTO `product_img` VALUES (6, 1, 'http://47.101.58.129:8282/imgs/2eaec6f2-37c5-4e64-af77-c283b8827cd8.jpg');
INSERT INTO `product_img` VALUES (7, 2, 'http://47.101.58.129:8282/imgs/2df78495-9c27-4e8a-a28d-89523084beb6.jpg');
INSERT INTO `product_img` VALUES (8, 3, 'http://47.101.58.129:8282/imgs/9dd4830d-7402-41e0-8bff-18e9a9bbb1ad.jpg');
INSERT INTO `product_img` VALUES (9, 4, 'http://47.101.58.129:8282/imgs/43ddc610-3528-4830-bb0a-4323d8afe98d.png');
INSERT INTO `product_img` VALUES (10, 5, 'http://47.101.58.129:8282/imgs/2a8c465b-30e5-4534-8cbc-957dc1a9da79.jpg');
INSERT INTO `product_img` VALUES (11, 6, 'http://47.101.58.129:8282/imgs/ba298f56-f3c3-4d10-b8a9-ad333bee875f.jpg');
INSERT INTO `product_img` VALUES (12, 10, 'http://47.101.58.129:8282/imgs/b06c6207-5fdc-4fe8-99ef-b57d62d9e1c7.jpg');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (9, 1, 1827648975, 2862.00, 1);
INSERT INTO `order` VALUES (10, 2, 1431428550, 2862.00, 1);
INSERT INTO `order` VALUES (11, 2, 595092594, 2862.00, 1);
INSERT INTO `order` VALUES (12, 3, 1965446990, 2862.00, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhaiyue', '123456', 5717, 'zhaiyue', '18981325590');
INSERT INTO `user` VALUES (2, 'zhangsan', '123456', 10000, 'zhangsan', '1231231231');
INSERT INTO `user` VALUES (3, 'mary', '123456', 4193, '玛丽', '1231231231');
INSERT INTO `user` VALUES (4, 'wangwu', '123456', 100, '王五', '1231231231');
INSERT INTO `user` VALUES (23, 'test1', 'test modify', 5707, 'test', 'test');
INSERT INTO `user` VALUES (25, 'test3', 'test3 modify', 5707, '333', '1231231231');
INSERT INTO `user` VALUES (26, 'test4', 'test4', 5707, 'test', 'test');
INSERT INTO `user` VALUES (27, 'test', 'test', 0, 'test', 'test number');
INSERT INTO `user` VALUES (28, 'test2', 'test2', 0, NULL, 'test2 number');
INSERT INTO `user` VALUES (29, '111', '111', 0, NULL, '123');
INSERT INTO `user` VALUES (30, '222', '222', 0, NULL, '222');

SET FOREIGN_KEY_CHECKS = 1;
