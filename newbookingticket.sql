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

 Date: 30/04/2024 14:11:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `user_id` int NULL DEFAULT NULL,
                            `booking_date` datetime(6) NULL DEFAULT NULL,
                            `total` int NULL DEFAULT NULL,
                            `payment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `is_paid` int NULL DEFAULT NULL,
                            `round_trip_ticket` int NULL DEFAULT NULL,
                            `created_at` datetime(6) NULL DEFAULT NULL,
                            `updated_at` datetime(6) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `FKkgseyy7t56x7lkjgu3wah5s3t`(`user_id` ASC) USING BTREE,
                            CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booking
-- ----------------------------

-- ----------------------------
-- Table structure for booking_detail
-- ----------------------------
DROP TABLE IF EXISTS `booking_detail`;
CREATE TABLE `booking_detail`  (
                                   `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                   `booking_id` int NULL DEFAULT NULL,
                                   `trip_id` int NULL DEFAULT NULL,
                                   `round_trip_ticket` int NULL DEFAULT NULL,
                                   `quantity` int NULL DEFAULT NULL,
                                   `seat_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `price` int NULL DEFAULT NULL,
                                   `noidon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `ghichu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `created_at` datetime(6) NULL DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `FK59941ondg9nwaifm2umnrduev`(`booking_id` ASC) USING BTREE,
                                   INDEX `FK2xohu6ps4mm6twrfw4xtacgp`(`trip_id` ASC) USING BTREE,
                                   CONSTRAINT `FK2xohu6ps4mm6twrfw4xtacgp` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                   CONSTRAINT `FK59941ondg9nwaifm2umnrduev` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booking_detail
-- ----------------------------

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
INSERT INTO `city` VALUES (1, 'Đà Nẵng', 'img/dn.png', '2024-04-21 03:28:26.000000', '2024-04-21 03:28:26.000000');
INSERT INTO `city` VALUES (2, 'Sài Gòn', 'img/sg.png', '2024-04-21 03:28:40.000000', '2024-04-21 03:28:40.000000');
INSERT INTO `city` VALUES (3, 'Đà Lạt', 'img/dl.png', '2024-04-21 03:28:54.000000', '2024-04-21 03:28:54.000000');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `created_at` datetime(6) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact
-- ----------------------------

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
-- Table structure for kindvehicle
-- ----------------------------
DROP TABLE IF EXISTS `kindvehicle`;
CREATE TABLE `kindvehicle`  (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `created_at` datetime(6) NULL DEFAULT NULL,
                                `updated_at` datetime(6) NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kindvehicle
-- ----------------------------
INSERT INTO `kindvehicle` VALUES (1, 'Giường nằm', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `kindvehicle` VALUES (2, 'Limousine', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `kindvehicle` VALUES (3, 'Ghế ngồi', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');

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
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion`  (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              `end_day` datetime(6) NULL DEFAULT NULL,
                              `start_day` datetime(6) NULL DEFAULT NULL,
                              `discount_percent` int NULL DEFAULT NULL,
                              `created_at` datetime(6) NULL DEFAULT NULL,
                              `updated_at` datetime(6) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotion
-- ----------------------------

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `trip_id` int NULL DEFAULT NULL,
                           `user_id` int NULL DEFAULT NULL,
                           `rating` int NULL DEFAULT NULL,
                           `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           `created_at` datetime(6) NULL DEFAULT NULL,
                           `updated_at` datetime(6) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `FKsdujhwxnw678xtqnvqre9gl3h`(`trip_id` ASC) USING BTREE,
                           INDEX `FKiyf57dy48lyiftdrf7y87rnxi`(`user_id` ASC) USING BTREE,
                           CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                           CONSTRAINT `FKsdujhwxnw678xtqnvqre9gl3h` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------

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
                         `loaixe_id` int NULL DEFAULT NULL,
                         `status` int NULL DEFAULT NULL,
                         `created_at` datetime(6) NULL DEFAULT NULL,
                         `updated_at` datetime(6) NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK9cr2xku9vc0n4xg2i3q6e5ekl`(`loaixe_id` ASC) USING BTREE,
                         CONSTRAINT `FK9cr2xku9vc0n4xg2i3q6e5ekl` FOREIGN KEY (`loaixe_id`) REFERENCES `kindvehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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

INSERT INTO `seat` VALUES (22, 'A01', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (23, 'A02', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (24, 'A03', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (25, 'A04', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (26, 'A05', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (27, 'A06', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (28, 'A07', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (29, 'A08', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (30, 'A09', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (31, 'A10', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `seat` VALUES (32, 'A11', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (33, 'A12', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (34, 'A13', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (35, 'A14', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (36, 'A15', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (37, 'A16', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (38, 'A17', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (39, 'A18', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (40, 'A19', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (41, 'A20', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (42, 'A21', 2, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');

INSERT INTO `seat` VALUES (43, 'A01', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (44, 'A02', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (45, 'A03', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (46, 'A04', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (47, 'A05', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 02:36:23.000000');
INSERT INTO `seat` VALUES (48, 'A06', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (49, 'A07', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (50, 'A08', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (51, 'A09', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 09:08:52.000000');
INSERT INTO `seat` VALUES (52, 'A10', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `seat` VALUES (53, 'A11', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (54, 'A12', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (55, 'A13', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (56, 'A14', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (57, 'A15', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (58, 'A16', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (59, 'A17', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (60, 'A18', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (61, 'A19', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (62, 'A20', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');
INSERT INTO `seat` VALUES (63, 'A21', 3, 0, '2024-04-14 22:55:07.000000', '2024-04-14 22:55:09.000000');

-- ----------------------------
-- Table structure for seat_reservsation
-- ----------------------------
DROP TABLE IF EXISTS `seat_reservsation`;
CREATE TABLE `seat_reservsation`  (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `booking_id` int NULL DEFAULT NULL,
                                      `trip_id` int NULL DEFAULT NULL,
                                      `ghe_id` int NULL DEFAULT NULL,
                                      `status` int NULL DEFAULT NULL,
                                      `created_at` datetime(6) NULL DEFAULT NULL,
                                      `updated_at` datetime(6) NULL DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `FK86bskijp07j65wdyb5u939d5h`(`booking_id` ASC) USING BTREE,
                                      INDEX `FKdogs4skrjosgkdt8yrxd1r264`(`ghe_id` ASC) USING BTREE,
                                      INDEX `FKc27yd48ljgw68twxfjbdoxdof`(`trip_id` ASC) USING BTREE,
                                      CONSTRAINT `FK86bskijp07j65wdyb5u939d5h` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                      CONSTRAINT `FKc27yd48ljgw68twxfjbdoxdof` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                      CONSTRAINT `FKdogs4skrjosgkdt8yrxd1r264` FOREIGN KEY (`ghe_id`) REFERENCES `seat` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seat_reservsation
-- ----------------------------

-- ----------------------------
-- Table structure for trip
-- ----------------------------
DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip`  (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `route_id` int NULL DEFAULT NULL,
                         `vehicle_id` int NULL DEFAULT NULL,
                         `ngaykhoihanh` date NULL DEFAULT NULL,
                         `giokhoihanh` time(6) NULL DEFAULT NULL,
                         `giave` int NULL DEFAULT NULL,
                         `taixe_id` int NULL DEFAULT NULL,
                         `controng` int NULL DEFAULT NULL,
                         `status` int NULL DEFAULT NULL,
                         `created_at` datetime(6) NULL DEFAULT NULL,
                         `updated_at` datetime(6) NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FKd2h5nli11j3hsr670tlswlnm7`(`taixe_id` ASC) USING BTREE,
                         INDEX `FKeva4adpyk6glllffnw5ypj20j`(`route_id` ASC) USING BTREE,
                         INDEX `FKrji8htecrp06ao6s7nfubswnr`(`vehicle_id` ASC) USING BTREE,
                         CONSTRAINT `FKd2h5nli11j3hsr670tlswlnm7` FOREIGN KEY (`taixe_id`) REFERENCES `driver` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                         CONSTRAINT `FKeva4adpyk6glllffnw5ypj20j` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                         CONSTRAINT `FKrji8htecrp06ao6s7nfubswnr` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- -------
-- ----------------------------
-- Records of trip
-- ----------------------------
INSERT INTO `trip` VALUES (1, 1, 1, '2024-05-11', '18:10:24.000000', 250000, 1, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (2, 1, 2, '2024-05-11', '19:10:24.000000', 300000, 2, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (3, 1, 3, '2024-05-11', '22:10:24.000000', 350000, 3, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (4, 2, 4, '2024-05-14', '18:10:24.000000', 250000, 4, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (5, 2, 5, '2024-05-14', '19:10:24.000000', 330000, 5, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (6, 2, 6, '2024-05-14', '22:10:24.000000', 350000, 6, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');
INSERT INTO `trip` VALUES (7, 2, 1, '2024-05-01', '22:10:24.000000', 300000, 1, 21, 1, '2024-04-16 18:10:33.000000', '2024-04-16 18:10:37.000000');

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
                         `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `confirm_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `created_at` datetime(6) NULL DEFAULT NULL,
                         `updated_at` datetime(6) NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Nguyễn Văn B', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abc@gmail.com', '12345678', 1, 1, "Đăng ký", "", '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (2, 'Nguyễn Văn C', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abcd@gmail.com', '12345678', 2, 1, "Google", "",  '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (3, 'Nguyễn Văn D', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abcde@gmail.com', '12345678', 3, 1, "Đăng ký", "",  '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `loaixe_id` int NULL DEFAULT NULL,
                            `bienso` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                            `succhua` int NULL DEFAULT NULL,
                            `trangthai` int NULL DEFAULT NULL,
                            `created_at` datetime(6) NULL DEFAULT NULL,
                            `updated_at` datetime(6) NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `FK8yvp5l18p5adpwmtarb8jpqc`(`loaixe_id` ASC) USING BTREE,
                            CONSTRAINT `FK8yvp5l18p5adpwmtarb8jpqc` FOREIGN KEY (`loaixe_id`) REFERENCES `kindvehicle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES (1, 'Vehicle001', 1, 'BS001', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `vehicle` VALUES (2, 'Vehicle002', 2, 'BS002', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (3, 'Vehicle003', 3, 'BS003', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (4, 'Vehicle004', 1, 'BS004', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `vehicle` VALUES (5, 'Vehicle005', 2, 'BS005', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (6, 'Vehicle006', 3, 'BS006', 21, 0, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');

-- ----------------------------
-- Table structure for waiting_seat
-- ----------------------------
DROP TABLE IF EXISTS `waiting_seat`;
CREATE TABLE `waiting_seat`  (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `trip_id` int NULL DEFAULT NULL,
                                 `ghe_id` int NULL DEFAULT NULL,
                                 `created_at` datetime(6) NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `FKkood0ttlamq7wvedqa6mnb0r9`(`ghe_id` ASC) USING BTREE,
                                 INDEX `FKdgkoylnn9b9db0g7dtruvuttw`(`trip_id` ASC) USING BTREE,
                                 CONSTRAINT `FKdgkoylnn9b9db0g7dtruvuttw` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `FKkood0ttlamq7wvedqa6mnb0r9` FOREIGN KEY (`ghe_id`) REFERENCES `seat` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of waiting_seat
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
