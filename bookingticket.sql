/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100427 (10.4.27-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : bookingticket

 Target Server Type    : MySQL
 Target Server Version : 100427 (10.4.27-MariaDB)
 File Encoding         : 65001

 Date: 22/04/2024 01:07:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'Đà Nẵng', 'blob:http://localhost:3000/c870099c-b1d6-41ec-a6a8-1f9dff0ec8e6', '2024-04-21 03:28:26.000000', '2024-04-21 03:28:26.000000');
INSERT INTO `city` VALUES (2, 'Sài Gòn', 'blob:http://localhost:3000/7b3be57b-2b4d-4696-be86-b281ebddb8fa', '2024-04-21 03:28:40.000000', '2024-04-21 03:28:40.000000');
INSERT INTO `city` VALUES (3, 'Đà Lạt', 'blob:http://localhost:3000/9fe8721a-232c-4847-b8e4-8fe57a0a2766', '2024-04-21 03:28:54.000000', '2024-04-21 03:28:54.000000');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES (1, 'Nguyễn Văn A', 'a@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');
INSERT INTO `driver` VALUES (2, 'Nguyễn Văn B', 'b@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');
INSERT INTO `driver` VALUES (3, 'Nguyễn Văn C', 'c@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');
INSERT INTO `driver` VALUES (4, 'Nguyễn Văn D', 'd@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');
INSERT INTO `driver` VALUES (5, 'Nguyễn Văn E', 'e@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');
INSERT INTO `driver` VALUES (6, 'Nguyễn Văn F', 'f@gmail.com', '12345678', 1, '2024-04-14 22:09:09.000000', '2024-04-14 22:09:13.000000');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3wxdofviqe2smmvh1w1yf98o1`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK3wxdofviqe2smmvh1w1yf98o1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for order_table
-- ----------------------------
DROP TABLE IF EXISTS `order_table`;
CREATE TABLE `order_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `ngaydat` datetime(6) NULL DEFAULT NULL,
  `tongtien` int NULL DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `trangthai` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKnmdjo6oaf01ow2reubtrhl6ev`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKnmdjo6oaf01ow2reubtrhl6ev` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_table
-- ----------------------------

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_id` int NULL DEFAULT NULL,
  `chuyendi_id` int NULL DEFAULT NULL,
  `soghe` int NULL DEFAULT NULL,
  `tenghe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gia` int NULL DEFAULT NULL,
  `tongtien` int NULL DEFAULT NULL,
  `noidon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ghichu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK770ub42casdaky1wg07x5xu0b`(`order_id` ASC) USING BTREE,
  INDEX `FKt3f6dof8m5ejeqokttwfeje7i`(`chuyendi_id` ASC) USING BTREE,
  CONSTRAINT `FK770ub42casdaky1wg07x5xu0b` FOREIGN KEY (`order_id`) REFERENCES `order_table` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt3f6dof8m5ejeqokttwfeje7i` FOREIGN KEY (`chuyendi_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'User', '2024-04-14 22:49:04.000000', '2024-04-14 22:49:10.000000');
INSERT INTO `role` VALUES (2, 'Employee', '2024-04-16 00:34:06.000000', '2024-04-16 00:34:10.000000');
INSERT INTO `role` VALUES (3, 'Admin', '2024-04-16 00:34:27.000000', '2024-04-16 00:34:30.000000');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_route` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `diemdi` int NULL DEFAULT NULL,
  `diemden` int NULL DEFAULT NULL,
  `khoangcach` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `thoigiandi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `trangthai` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6ngj368ymedbl29qg2exi7cxk`(`diemden` ASC) USING BTREE,
  INDEX `FKou0ir92ph8xo13qulrcsrcsmq`(`diemdi` ASC) USING BTREE,
  CONSTRAINT `FK6ngj368ymedbl29qg2exi7cxk` FOREIGN KEY (`diemden`) REFERENCES `city` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKou0ir92ph8xo13qulrcsrcsmq` FOREIGN KEY (`diemdi`) REFERENCES `city` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, 'Đà Nẵng - Sài Gòn', 1, 2, '500km', '12', 1, '2024-04-14 22:51:06.000000', '2024-04-14 22:51:11.000000');
INSERT INTO `route` VALUES (2, 'Sài Gòn - Đà Nẵng', 2, 1, '500km', '12', 1, '2024-04-14 22:51:06.000000', '2024-04-14 22:51:11.000000');

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenghe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phuongtien_id` int NULL DEFAULT NULL,
  `trangthai` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKbfth6tqvdq12fx8s0oo1gu34u`(`phuongtien_id` ASC) USING BTREE,
  CONSTRAINT `FKbfth6tqvdq12fx8s0oo1gu34u` FOREIGN KEY (`phuongtien_id`) REFERENCES `vehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seat
-- ----------------------------
INSERT INTO `seat` VALUES (1, 'A01', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (2, 'A02', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (3, 'A03', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (4, 'A04', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (5, 'A05', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (6, 'A06', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (7, 'A07', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (8, 'A08', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (9, 'A09', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (10, 'A10', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `seat` VALUES (11, 'A11', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (12, 'A12', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (13, 'A13', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (14, 'A14', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (15, 'A15', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (16, 'A16', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (17, 'A17', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (18, 'A18', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (19, 'A19', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (20, 'A20', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (21, 'A21', 1, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (24, 'A01', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (25, 'A02', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (26, 'A03', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (27, 'A04', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (28, 'A05', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (29, 'A06', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (30, 'A07', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (31, 'A08', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (32, 'A09', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (33, 'A10', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (34, 'A11', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (35, 'A12', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (36, 'A13', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (37, 'A14', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (38, 'A15', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (39, 'A16', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (40, 'A17', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (41, 'A18', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (42, 'A19', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (45, 'A20', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (46, 'A21', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (47, 'A01', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (48, 'A02', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (49, 'A03', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (50, 'A04', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (51, 'A05', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (52, 'A06', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (53, 'A07', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (54, 'A08', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (55, 'A09', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (56, 'A10', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (57, 'A11', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (58, 'A12', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (59, 'A13', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (60, 'A14', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (61, 'A15', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (62, 'A16', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (63, 'A17', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (64, 'A18', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (67, 'A19', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (68, 'A20', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (69, 'A21', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (71, 'A01', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `seat` VALUES (72, 'A02', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-20 11:11:14.000000');
INSERT INTO `seat` VALUES (73, 'A03', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-20 11:11:14.000000');
INSERT INTO `seat` VALUES (74, 'A04', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-20 11:11:14.000000');
INSERT INTO `seat` VALUES (75, 'A05', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-20 11:11:14.000000');
INSERT INTO `seat` VALUES (76, 'A06', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-20 11:11:14.000000');
INSERT INTO `seat` VALUES (77, 'A07', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (78, 'A08', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (79, 'A09', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (80, 'A10', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (81, 'A11', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (82, 'A12', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (83, 'A13', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (84, 'A14', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (85, 'A15', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (86, 'A16', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (87, 'A17', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (88, 'A18', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (89, 'A19', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (90, 'A20', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (91, 'A21', 4, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (94, 'A01', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (95, 'A02', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (96, 'A03', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (97, 'A04', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (98, 'A05', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (99, 'A06', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (100, 'A07', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (101, 'A08', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (102, 'A09', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (103, 'A10', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (104, 'A11', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (105, 'A12', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (106, 'A13', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (107, 'A14', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (108, 'A15', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (109, 'A16', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (110, 'A17', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (111, 'A18', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (112, 'A19', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (113, 'A20', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (114, 'A21', 5, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (115, 'A01', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (116, 'A02', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (117, 'A03', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (118, 'A04', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (119, 'A05', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (120, 'A06', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (123, 'A07', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (124, 'A08', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (125, 'A09', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (126, 'A10', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (127, 'A11', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (128, 'A12', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (129, 'A13', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (130, 'A14', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (131, 'A15', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (132, 'A16', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (133, 'A17', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (134, 'A18', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (137, 'A19', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (138, 'A20', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (139, 'A21', 6, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');

-- ----------------------------
-- Table structure for seatbooked
-- ----------------------------
DROP TABLE IF EXISTS `seatbooked`;
CREATE TABLE `seatbooked`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghe_id` int NULL DEFAULT NULL,
  `trip_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_4wwageq4iyn7cj7h0ojmhcydf`(`ghe_id` ASC) USING BTREE,
  INDEX `FKpgsnsg3rl0dn2njqmi60uspme`(`trip_id` ASC) USING BTREE,
  INDEX `FK1xs4825y6013tdxcbqmywnn6h`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FK1xs4825y6013tdxcbqmywnn6h` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKe5o43m54gj43hcp2wx6nitun` FOREIGN KEY (`ghe_id`) REFERENCES `seat` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpgsnsg3rl0dn2njqmi60uspme` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seatbooked
-- ----------------------------

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES (1, 'Đã xác thực', '2024-04-14 22:51:36.000000', '2024-04-14 22:51:39.000000');

-- ----------------------------
-- Table structure for trip
-- ----------------------------
DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tuyen_id` int NULL DEFAULT NULL,
  `phuongtien_id` int NULL DEFAULT NULL,
  `ngaykhoihanh` date NULL DEFAULT NULL,
  `giokhoihanh` time(6) NULL DEFAULT NULL,
  `giave` int NULL DEFAULT NULL,
  `taixe_id` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_tp9035ijs36h4ymxj5e8uxv52`(`phuongtien_id` ASC) USING BTREE,
  INDEX `FKd2h5nli11j3hsr670tlswlnm7`(`taixe_id` ASC) USING BTREE,
  INDEX `FKlshljerimsxuyoe8mr4itq8e9`(`tuyen_id` ASC) USING BTREE,
  CONSTRAINT `FKd2h5nli11j3hsr670tlswlnm7` FOREIGN KEY (`taixe_id`) REFERENCES `driver` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjunw79myiodxv5jat10fdqjsl` FOREIGN KEY (`phuongtien_id`) REFERENCES `vehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKlshljerimsxuyoe8mr4itq8e9` FOREIGN KEY (`tuyen_id`) REFERENCES `route` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trip
-- ----------------------------
INSERT INTO `trip` VALUES (1, 1, 1, '2024-04-25', '18:10:24.000000', 300000, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (2, 1, 2, '2024-04-25', '19:10:24.000000', 300000, 2, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (3, 1, 3, '2024-04-25', '22:10:24.000000', 300000, 3, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (4, 2, 4, '2024-04-28', '18:10:24.000000', 300000, 4, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (5, 2, 5, '2024-04-28', '19:10:24.000000', 300000, 5, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (6, 2, 6, '2024-04-28', '22:10:24.000000', 300000, 6, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 'Đăng ký', '2024-04-14 22:52:06.000000', '2024-04-14 22:52:09.000000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoten` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_dl7g53f7lpmorjc24kx74apx8`(`role` ASC) USING BTREE,
  UNIQUE INDEX `UK_su93soui1ledea731cucockr8`(`status` ASC) USING BTREE,
  UNIQUE INDEX `UK_q2o1n3cbq93x0kx00vpaf03s2`(`type` ASC) USING BTREE,
  CONSTRAINT `FK8tmbsnntxtkc6kg0xjai9tv9w` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKl5alypubd40lwejc45vl35wjb` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrtjrhf3d468vp6enm8n798amu` FOREIGN KEY (`type`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Nguyễn Văn B', '12345678', 'abc@gmail.com', '12345678', 1, 1, 1, '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (2, NULL, '1234567812345678fdsfsd12345678fdsfsd12345678fdsfsd', 'ababdabdabdc@gmail.com', NULL, NULL, NULL, NULL, '2024-04-15 20:56:58.000000', NULL);
INSERT INTO `user` VALUES (3, NULL, '12345678', 'ababdabdabdc@gmail.com', NULL, NULL, NULL, NULL, '2024-04-15 20:58:21.000000', NULL);

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `loaixe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bienso` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `succhua` int NULL DEFAULT NULL,
  `controng` int NULL DEFAULT NULL,
  `trangthai` int NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES (1, 'Giường nằm', 'BS001', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `vehicle` VALUES (2, 'Limousine', 'BS002', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (3, 'Ghế ngồi', 'BS003', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (4, 'Giường nằm', 'BS001', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `vehicle` VALUES (5, 'Limousine', 'BS002', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (6, 'Ghế ngồi', 'BS003', 21, 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');

SET FOREIGN_KEY_CHECKS = 1;
