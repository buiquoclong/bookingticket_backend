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

 Date: 27/06/2024 16:36:35
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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of booking
-- ----------------------------
INSERT INTO `booking` VALUES (1, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-20 14:37:42.000000', 360000, 'Thanh toán trả sau', 0, 0, '2024-06-20 14:37:42.000000', '2024-06-20 14:37:42.000000');
INSERT INTO `booking` VALUES (2, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-20 14:40:04.000000', 120000, 'Thanh toán bằng VNPay', 1, 0, '2024-06-20 14:40:04.000000', '2024-06-20 14:40:04.000000');
INSERT INTO `booking` VALUES (3, 'Nguyễn Văn D (Admin)', 'abcde@gmail.com', '12345678', 3, '2024-06-20 14:46:52.000000', 363000, 'Thanh toán tiền mặt', 1, 0, '2024-06-20 14:46:52.000000', '2024-06-20 14:46:52.000000');
INSERT INTO `booking` VALUES (4, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-20 15:05:57.000000', 363000, 'Thanh toán trả sau', 0, 0, '2024-06-20 15:05:57.000000', '2024-06-20 15:05:57.000000');
INSERT INTO `booking` VALUES (5, 'Nguyễn Văn D (Admin)', 'abcde@gmail.com', '12345678', 3, '2024-06-20 17:22:41.000000', 121000, 'Thanh toán tiền mặt', 1, 0, '2024-06-20 17:22:41.000000', '2024-06-20 17:22:41.000000');
INSERT INTO `booking` VALUES (6, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-21 10:17:17.000000', 217800, 'Thanh toán khi lên xe', 0, 0, '2024-06-21 10:17:17.000000', '2024-06-21 10:17:17.000000');
INSERT INTO `booking` VALUES (7, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-21 10:21:31.000000', 481000, 'Thanh toán bằng VNPay', 1, 1, '2024-06-21 10:21:31.000000', '2024-06-21 10:21:31.000000');
INSERT INTO `booking` VALUES (8, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345678', 4, '2024-06-21 10:30:04.000000', 481000, 'Thanh toán bằng VNPay', 1, 1, '2024-06-21 10:30:04.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `booking` VALUES (9, 'Bùi Quốc Long', '', '0123456789', 3, '2024-06-21 10:50:01.000000', 560000, 'Thanh toán tiền mặt', 1, 1, '2024-06-21 10:50:01.000000', '2024-06-21 10:50:01.000000');
INSERT INTO `booking` VALUES (10, 'Bùi Quốc Long', 'buiquoclongkt123@gmail.com', '12345', 4, '2024-06-27 15:54:24.000000', 240000, 'Thanh toán khi lên xe', 0, 0, '2024-06-27 15:54:24.000000', '2024-06-27 15:54:24.000000');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of booking_detail
-- ----------------------------
INSERT INTO `booking_detail` VALUES ('209rHdtA', 10, 2, 0, 2, 'A10, A11', 240000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-27 15:54:24.000000');
INSERT INTO `booking_detail` VALUES ('cZpQRZap', 5, 3, 0, 1, 'A07', 121000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-20 17:22:41.000000');
INSERT INTO `booking_detail` VALUES ('dzSaWhyN', 1, 1, 0, 3, 'A01, A02, A03', 360000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-20 14:37:42.000000');
INSERT INTO `booking_detail` VALUES ('fE3ErGF0', 8, 3, 0, 1, 'A11', 121000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:30:04.000000');
INSERT INTO `booking_detail` VALUES ('HPuic9nX', 9, 2, 1, 3, 'A07, A08, A09', 360000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:50:01.000000');
INSERT INTO `booking_detail` VALUES ('MiYE88ZT', 2, 1, 0, 1, 'A04', 120000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-20 14:40:04.000000');
INSERT INTO `booking_detail` VALUES ('OpmSkmDF', 7, 2, 1, 3, 'A01, A02, A03', 360000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:21:31.000000');
INSERT INTO `booking_detail` VALUES ('rcIjPr35', 9, 4, 0, 1, 'A12', 200000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:50:01.000000');
INSERT INTO `booking_detail` VALUES ('wcNXziv4', 6, 3, 0, 2, 'A08, A09', 242000, 'Bến Xe An Sương, Quốc Lộ 22, Ấp Đông Lân, Bà Điểm, Hóc Môn, TP Hồ Chí Minh', 'Mang đồ nặng', '2024-06-21 10:17:17.000000');
INSERT INTO `booking_detail` VALUES ('wJWbvXmH', 8, 2, 1, 3, 'A04, A05, A06', 360000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:30:04.000000');
INSERT INTO `booking_detail` VALUES ('xMMVGK7p', 3, 3, 0, 3, 'A01, A02, A03', 363000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-20 14:46:52.000000');
INSERT INTO `booking_detail` VALUES ('XQClACSb', 7, 3, 0, 1, 'A10', 121000, 'Tại nhà xe', 'Không có ghi chú', '2024-06-21 10:21:31.000000');
INSERT INTO `booking_detail` VALUES ('YPF2cfUO', 4, 3, 0, 3, 'A04, A05, A06', 363000, 'Bến Xe An Sương, Quốc Lộ 22, Ấp Đông Lân, Bà Điểm, Hóc Môn, TP Hồ Chí Minh', 'abc', '2024-06-20 15:05:57.000000');

-- ----------------------------
-- Table structure for catch_point
-- ----------------------------
DROP TABLE IF EXISTS `catch_point`;
CREATE TABLE `catch_point`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `route_id` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  `updated_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKm58dq6gy4x1ht7yyeubl8fg05`(`route_id` ASC) USING BTREE,
  CONSTRAINT `FKm58dq6gy4x1ht7yyeubl8fg05` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of catch_point
-- ----------------------------
INSERT INTO `catch_point` VALUES (1, 30, 'Bến xe An Sương', 'Bến Xe An Sương, Quốc Lộ 22, Ấp Đông Lân, Bà Điểm, Hóc Môn, TP Hồ Chí Minh', '2024-06-20 14:14:24.000000', '2024-06-20 14:14:24.000000');
INSERT INTO `catch_point` VALUES (2, 30, 'Bến Xe Miền Tây', ' BX Miền Tây: 395 Kinh Dương Vương , P.An Lạc , Q.Bình Tân , TP.HCM', '2024-06-20 14:15:24.000000', '2024-06-20 14:15:24.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'Bà Rịa - Vũng tàu', 'https://drive.google.com/thumbnail?id=1hvDCq1x-g9vmx7235RlW8uCv_u-QW8o9', '2024-06-20 13:26:07.000000', '2024-06-27 15:24:18.000000');
INSERT INTO `city` VALUES (2, 'Bình Dương', 'https://drive.google.com/thumbnail?id=1XpLJGuViEo2mt_VMhl5_UxmlAtf1fG2A', '2024-06-20 13:26:25.000000', '2024-06-27 15:24:34.000000');
INSERT INTO `city` VALUES (3, 'Bình Phước', 'https://drive.google.com/thumbnail?id=1VUqPM7yoLXn8Tcz_gs_aR2haMMP3tyIM', '2024-06-20 13:26:43.000000', '2024-06-27 15:24:48.000000');
INSERT INTO `city` VALUES (4, 'Đồng Nai', 'https://drive.google.com/thumbnail?id=160o0mrhn71evwKVfmob98qnkATonu4Er', '2024-06-20 13:27:07.000000', '2024-06-27 15:25:02.000000');
INSERT INTO `city` VALUES (5, 'Tây Ninh', 'https://drive.google.com/thumbnail?id=1mbuhUkur3BqaB5Ybkecpxn7P0hSvJTBl', '2024-06-20 13:31:54.000000', '2024-06-27 15:25:18.000000');
INSERT INTO `city` VALUES (6, 'TP. Hồ Chí Minh', 'https://drive.google.com/thumbnail?id=1jjBTnl34L0hxcNQGGkGq8Oq14w-Nu87c', '2024-06-20 13:33:01.000000', '2024-06-27 15:25:34.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES (1, 'Cần liên hệ', 'nguyenvana@gmail.com', 'Nguyễn Văn A', 'Cần liên hệ', '2024-06-27 06:58:34.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of kindvehicle
-- ----------------------------
INSERT INTO `kindvehicle` VALUES (1, 'Giường nằm', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `kindvehicle` VALUES (2, 'Limousine', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `kindvehicle` VALUES (3, 'Ghế ngồi', '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `kindvehicle` VALUES (4, 'một ', '2024-06-27 04:03:40.000000', '2024-06-27 04:03:40.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Bình Dương', 1, '2024-06-20 13:38:23.000000');
INSERT INTO `log` VALUES (2, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Bình Phước', 1, '2024-06-20 13:38:55.000000');
INSERT INTO `log` VALUES (3, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Đồng Nai', 1, '2024-06-20 13:39:16.000000');
INSERT INTO `log` VALUES (4, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Tây Ninh', 1, '2024-06-20 13:39:44.000000');
INSERT INTO `log` VALUES (5, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:40:09.000000');
INSERT INTO `log` VALUES (6, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Bình Dương', 1, '2024-06-20 13:41:31.000000');
INSERT INTO `log` VALUES (7, 3, 'Tạo tuyến tên: Bình Dương - Bà Rịa - Vũng tàu', 1, '2024-06-20 13:41:45.000000');
INSERT INTO `log` VALUES (8, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Bình Phước', 1, '2024-06-20 13:42:01.000000');
INSERT INTO `log` VALUES (9, 3, 'Tạo tuyến tên: Bình Phước - Bà Rịa - Vũng tàu', 1, '2024-06-20 13:42:16.000000');
INSERT INTO `log` VALUES (10, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Đồng Nai', 1, '2024-06-20 13:42:31.000000');
INSERT INTO `log` VALUES (11, 3, 'Tạo tuyến tên: Đồng Nai - Bà Rịa - Vũng tàu', 1, '2024-06-20 13:43:00.000000');
INSERT INTO `log` VALUES (12, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Tây Ninh', 1, '2024-06-20 13:43:23.000000');
INSERT INTO `log` VALUES (13, 3, 'Tạo tuyến tên: Tây Ninh - Bà Rịa - Vũng tàu', 1, '2024-06-20 13:44:41.000000');
INSERT INTO `log` VALUES (14, 3, 'Tạo tuyến tên: Bà Rịa - Vũng tàu - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:44:56.000000');
INSERT INTO `log` VALUES (15, 3, 'Tạo tuyến tên: Thành phố Hồ Chí Minh - Bà Rịa - Vũng tàu', 1, '2024-06-20 13:45:16.000000');
INSERT INTO `log` VALUES (16, 3, 'Tạo tuyến tên: Bình Dương - Bình Phước', 1, '2024-06-20 13:45:42.000000');
INSERT INTO `log` VALUES (17, 3, 'Tạo tuyến tên: Bình Phước - Bình Dương', 1, '2024-06-20 13:46:01.000000');
INSERT INTO `log` VALUES (18, 3, 'Tạo tuyến tên: Bình Dương - Đồng Nai', 1, '2024-06-20 13:46:30.000000');
INSERT INTO `log` VALUES (19, 3, 'Tạo tuyến tên: Đồng Nai - Bình Dương', 1, '2024-06-20 13:47:00.000000');
INSERT INTO `log` VALUES (20, 3, 'Tạo tuyến tên: Bình Dương - Tây Ninh', 1, '2024-06-20 13:47:18.000000');
INSERT INTO `log` VALUES (21, 3, 'Tạo tuyến tên: Tây Ninh - Bình Dương', 1, '2024-06-20 13:47:37.000000');
INSERT INTO `log` VALUES (22, 3, 'Tạo tuyến tên: Bình Dương - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:48:04.000000');
INSERT INTO `log` VALUES (23, 3, 'Tạo tuyến tên: Thành phố Hồ Chí Minh - Bình Dương', 1, '2024-06-20 13:49:19.000000');
INSERT INTO `log` VALUES (24, 3, 'Tạo tuyến tên: Bình Phước - Đồng Nai', 1, '2024-06-20 13:49:59.000000');
INSERT INTO `log` VALUES (25, 3, 'Tạo tuyến tên: Đồng Nai - Bình Phước', 1, '2024-06-20 13:50:31.000000');
INSERT INTO `log` VALUES (26, 3, 'Tạo tuyến tên: Bình Phước - Tây Ninh', 1, '2024-06-20 13:50:56.000000');
INSERT INTO `log` VALUES (27, 3, 'Tạo tuyến tên: Tây Ninh - Bình Phước', 1, '2024-06-20 13:51:12.000000');
INSERT INTO `log` VALUES (28, 3, 'Tạo tuyến tên: Bình Phước - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:51:43.000000');
INSERT INTO `log` VALUES (29, 3, 'Tạo tuyến tên: Thành phố Hồ Chí Minh - Bình Phước', 1, '2024-06-20 13:52:10.000000');
INSERT INTO `log` VALUES (30, 3, 'Tạo tuyến tên: Đồng Nai - Tây Ninh', 1, '2024-06-20 13:52:31.000000');
INSERT INTO `log` VALUES (31, 3, 'Tạo tuyến tên: Tây Ninh - Đồng Nai', 1, '2024-06-20 13:52:45.000000');
INSERT INTO `log` VALUES (32, 3, 'Tạo tuyến tên: Đồng Nai - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:53:02.000000');
INSERT INTO `log` VALUES (33, 3, 'Tạo tuyến tên: Thành phố Hồ Chí Minh - Đồng Nai', 1, '2024-06-20 13:53:49.000000');
INSERT INTO `log` VALUES (34, 3, 'Tạo tuyến tên: Tây Ninh - Thành phố Hồ Chí Minh', 1, '2024-06-20 13:54:00.000000');
INSERT INTO `log` VALUES (35, 3, 'Tạo tuyến tên: Thành phố Hồ Chí Minh - Tây Ninh', 1, '2024-06-20 13:54:09.000000');
INSERT INTO `log` VALUES (36, 3, 'Tạo chuyến đi ', 1, '2024-06-20 14:09:19.000000');
INSERT INTO `log` VALUES (37, 3, 'Tạo chuyến đi ', 1, '2024-06-20 14:09:54.000000');
INSERT INTO `log` VALUES (38, 3, 'Tạo chuyến đi ', 1, '2024-06-20 14:10:36.000000');
INSERT INTO `log` VALUES (39, 3, 'Tạo chuyến đi ', 1, '2024-06-20 14:11:13.000000');
INSERT INTO `log` VALUES (40, 3, 'Tạo điểm đón của tuyến: 30', 1, '2024-06-20 14:14:24.000000');
INSERT INTO `log` VALUES (41, 3, 'Tạo điểm đón của tuyến: 30', 1, '2024-06-20 14:15:24.000000');
INSERT INTO `log` VALUES (42, 3, 'Cập nhật chuyến đi Id: 2', 2, '2024-06-20 14:23:46.000000');
INSERT INTO `log` VALUES (43, 3, 'Cập nhật Thành phố Id: 6', 2, '2024-06-20 14:26:01.000000');
INSERT INTO `log` VALUES (44, 3, 'Cập nhật chuyến đi Id: 1', 2, '2024-06-20 14:28:02.000000');
INSERT INTO `log` VALUES (45, 3, 'Cập nhật chuyến đi Id: 1', 2, '2024-06-20 14:28:17.000000');
INSERT INTO `log` VALUES (46, 3, 'Cập nhật tuyến Id: 23', 2, '2024-06-20 14:29:05.000000');
INSERT INTO `log` VALUES (47, 3, 'Cập nhật tuyến Id: 24', 2, '2024-06-20 14:29:11.000000');
INSERT INTO `log` VALUES (48, 3, 'Cập nhật tuyến Id: 27', 2, '2024-06-20 14:29:19.000000');
INSERT INTO `log` VALUES (49, 3, 'Cập nhật tuyến Id: 28', 2, '2024-06-20 14:29:25.000000');
INSERT INTO `log` VALUES (50, 3, 'Cập nhật tuyến Id: 29', 2, '2024-06-20 14:29:31.000000');
INSERT INTO `log` VALUES (51, 3, 'Cập nhật tuyến Id: 30', 2, '2024-06-20 14:29:37.000000');
INSERT INTO `log` VALUES (52, 3, 'Cập nhật tuyến Id: 17', 2, '2024-06-20 14:29:53.000000');
INSERT INTO `log` VALUES (53, 3, 'Cập nhật tuyến Id: 18', 2, '2024-06-20 14:29:58.000000');
INSERT INTO `log` VALUES (54, 3, 'Cập nhật tuyến Id: 9', 2, '2024-06-20 14:30:17.000000');
INSERT INTO `log` VALUES (55, 3, 'Cập nhật tuyến Id: 10', 2, '2024-06-20 14:30:25.000000');
INSERT INTO `log` VALUES (56, 3, 'Cập nhật tuyến Id: 17', 2, '2024-06-20 14:30:48.000000');
INSERT INTO `log` VALUES (57, 3, 'Cập nhật tuyến Id: 18', 2, '2024-06-20 14:31:02.000000');
INSERT INTO `log` VALUES (58, 3, 'Cập nhật tuyến Id: 23', 2, '2024-06-20 14:31:13.000000');
INSERT INTO `log` VALUES (59, 3, 'Cập nhật tuyến Id: 24', 2, '2024-06-20 14:31:19.000000');
INSERT INTO `log` VALUES (60, 3, 'Cập nhật tuyến Id: 27', 2, '2024-06-20 14:31:25.000000');
INSERT INTO `log` VALUES (61, 3, 'Cập nhật tuyến Id: 28', 2, '2024-06-20 14:31:32.000000');
INSERT INTO `log` VALUES (62, 3, 'Cập nhật tuyến Id: 29', 2, '2024-06-20 14:31:37.000000');
INSERT INTO `log` VALUES (63, 3, 'Cập nhật tuyến Id: 30', 2, '2024-06-20 14:31:43.000000');
INSERT INTO `log` VALUES (64, 3, 'Tạo mã giảm giá cho: Giảm giá cho thành viên mới', 1, '2024-06-20 14:35:52.000000');
INSERT INTO `log` VALUES (65, 3, 'Cập nhật chuyến đi Id: 1', 2, '2024-06-20 14:42:30.000000');
INSERT INTO `log` VALUES (66, 4, 'Tạo đánh giá ', 1, '2024-06-20 14:43:40.000000');
INSERT INTO `log` VALUES (67, 3, 'Đặt vé', 1, '2024-06-20 14:46:52.000000');
INSERT INTO `log` VALUES (68, 3, 'Đặt vé', 1, '2024-06-20 17:22:41.000000');
INSERT INTO `log` VALUES (69, 4, 'Cập nhật đánh giá Id: 1', 2, '2024-06-21 10:37:23.000000');
INSERT INTO `log` VALUES (70, 4, 'Cập nhật đánh giá Id: 1', 2, '2024-06-21 10:38:46.000000');
INSERT INTO `log` VALUES (71, 3, 'Đặt vé', 1, '2024-06-21 10:50:01.000000');
INSERT INTO `log` VALUES (72, 3, 'Tạo tài xế tên: a', 1, '2024-06-22 10:10:11.000000');
INSERT INTO `log` VALUES (73, 3, 'Xóa tài xế Id: 7', 2, '2024-06-22 10:15:14.000000');
INSERT INTO `log` VALUES (74, 3, 'Tạo loại xe tên: một ', 1, '2024-06-27 04:03:40.000000');
INSERT INTO `log` VALUES (75, 3, 'Tạo mã giảm giá cho: xzdsa', 1, '2024-06-27 04:04:01.000000');
INSERT INTO `log` VALUES (76, 3, 'Tạo thành phố tên: hà nội', 1, '2024-06-27 04:36:11.000000');
INSERT INTO `log` VALUES (77, 3, 'Xóa thành phố Id: 7', 2, '2024-06-27 04:37:44.000000');
INSERT INTO `log` VALUES (78, 3, 'Tạo thành phố tên: Hà Nội', 1, '2024-06-27 04:38:00.000000');
INSERT INTO `log` VALUES (79, 3, 'Xóa thành phố Id: 8', 2, '2024-06-27 04:42:04.000000');
INSERT INTO `log` VALUES (80, 3, 'Tạo thành phố tên: Hà Nội ', 1, '2024-06-27 04:42:21.000000');
INSERT INTO `log` VALUES (81, 3, 'Xóa thành phố Id: 9', 2, '2024-06-27 04:46:19.000000');
INSERT INTO `log` VALUES (82, 3, 'Xóa mã giảm giá Id: 2', 2, '2024-06-27 06:57:01.000000');
INSERT INTO `log` VALUES (83, 3, 'Tạo thành phố tên: abc', 1, '2024-06-27 07:02:02.000000');
INSERT INTO `log` VALUES (84, 3, 'Cập nhật Thành phố Id: 10', 2, '2024-06-27 07:02:22.000000');
INSERT INTO `log` VALUES (85, 3, 'Cập nhật Thành phố Id: 10', 2, '2024-06-27 07:02:27.000000');
INSERT INTO `log` VALUES (86, 3, 'Xóa thành phố Id: 10', 2, '2024-06-27 07:02:46.000000');
INSERT INTO `log` VALUES (87, 3, 'Cập nhật Thành phố Id: 1', 2, '2024-06-27 15:24:22.000000');
INSERT INTO `log` VALUES (88, 3, 'Cập nhật Thành phố Id: 2', 2, '2024-06-27 15:24:37.000000');
INSERT INTO `log` VALUES (89, 3, 'Cập nhật Thành phố Id: 3', 2, '2024-06-27 15:24:50.000000');
INSERT INTO `log` VALUES (90, 3, 'Cập nhật Thành phố Id: 4', 2, '2024-06-27 15:25:05.000000');
INSERT INTO `log` VALUES (91, 3, 'Cập nhật Thành phố Id: 5', 2, '2024-06-27 15:25:20.000000');
INSERT INTO `log` VALUES (92, 3, 'Cập nhật Thành phố Id: 6', 2, '2024-06-27 15:25:37.000000');
INSERT INTO `log` VALUES (93, 3, 'Cập nhật chuyến đi Id: 1', 2, '2024-06-27 15:41:11.000000');
INSERT INTO `log` VALUES (94, 3, 'Cập nhật chuyến đi Id: 1', 2, '2024-06-27 15:41:51.000000');
INSERT INTO `log` VALUES (95, 3, 'Cập nhật chuyến đi Id: 2', 2, '2024-06-27 15:49:20.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES (1, 'XY4D5CU0', 'Giảm giá cho thành viên mới', '2024-06-28 14:35:00.000000', '2024-06-20 14:35:00.000000', 10, '2024-06-20 14:35:52.000000', '2024-06-20 14:35:52.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, 1, 4, 5, 'Trải nghiệm tốt  ', '2024-06-20 14:43:40.000000', '2024-06-21 10:38:46.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, 'Bà Rịa - Vũng tàu - Bình Dương', 1, 2, '90', '2', 1, '2024-06-20 13:41:31.000000', '2024-06-20 13:41:31.000000');
INSERT INTO `route` VALUES (2, 'Bình Dương - Bà Rịa - Vũng tàu', 2, 1, '90', '2', 1, '2024-06-20 13:41:45.000000', '2024-06-20 13:41:45.000000');
INSERT INTO `route` VALUES (3, 'Bà Rịa - Vũng tàu - Bình Phước', 1, 3, '140', '3', 1, '2024-06-20 13:42:01.000000', '2024-06-20 13:42:01.000000');
INSERT INTO `route` VALUES (4, 'Bình Phước - Bà Rịa - Vũng tàu', 3, 1, '140', '3', 1, '2024-06-20 13:42:16.000000', '2024-06-20 13:42:16.000000');
INSERT INTO `route` VALUES (5, 'Bà Rịa - Vũng tàu - Đồng Nai', 1, 4, '60', '1.5', 1, '2024-06-20 13:42:31.000000', '2024-06-20 13:42:31.000000');
INSERT INTO `route` VALUES (6, 'Đồng Nai - Bà Rịa - Vũng tàu', 4, 1, '60', '1.5', 1, '2024-06-20 13:43:00.000000', '2024-06-20 13:43:00.000000');
INSERT INTO `route` VALUES (7, 'Bà Rịa - Vũng tàu - Tây Ninh', 1, 5, '180', '3.5', 1, '2024-06-20 13:43:23.000000', '2024-06-20 13:43:23.000000');
INSERT INTO `route` VALUES (8, 'Tây Ninh - Bà Rịa - Vũng tàu', 5, 1, '180', '3.5', 1, '2024-06-20 13:44:41.000000', '2024-06-20 13:44:41.000000');
INSERT INTO `route` VALUES (9, 'Bà Rịa - Vũng tàu - TP. Hồ Chí Minh', 1, 6, '100', '2', 1, '2024-06-20 13:44:56.000000', '2024-06-20 14:30:17.000000');
INSERT INTO `route` VALUES (10, 'TP. Hồ Chí Minh - Bà Rịa - Vũng tàu', 6, 1, '100', '2', 1, '2024-06-20 13:45:16.000000', '2024-06-20 14:30:25.000000');
INSERT INTO `route` VALUES (11, 'Bình Dương - Bình Phước', 2, 3, '90', '2', 1, '2024-06-20 13:45:42.000000', '2024-06-20 13:45:42.000000');
INSERT INTO `route` VALUES (12, 'Bình Phước - Bình Dương', 3, 2, '90', '2', 1, '2024-06-20 13:46:00.000000', '2024-06-20 13:46:00.000000');
INSERT INTO `route` VALUES (13, 'Bình Dương - Đồng Nai', 2, 4, '60', '1.5', 1, '2024-06-20 13:46:30.000000', '2024-06-20 13:46:30.000000');
INSERT INTO `route` VALUES (14, 'Đồng Nai - Bình Dương', 4, 2, '60', '1.5', 1, '2024-06-20 13:47:00.000000', '2024-06-20 13:47:00.000000');
INSERT INTO `route` VALUES (15, 'Bình Dương - Tây Ninh', 2, 5, '100', '2', 1, '2024-06-20 13:47:18.000000', '2024-06-20 13:47:18.000000');
INSERT INTO `route` VALUES (16, 'Tây Ninh - Bình Dương', 5, 2, '100', '2', 1, '2024-06-20 13:47:37.000000', '2024-06-20 13:47:37.000000');
INSERT INTO `route` VALUES (17, 'Bình Dương - TP. Hồ Chí Minh', 2, 6, '30', '1', 1, '2024-06-20 13:48:04.000000', '2024-06-20 14:30:48.000000');
INSERT INTO `route` VALUES (18, 'TP. Hồ Chí Minh - Bình Dương', 6, 2, '30', '1', 1, '2024-06-20 13:49:19.000000', '2024-06-20 14:31:02.000000');
INSERT INTO `route` VALUES (19, 'Bình Phước - Đồng Nai', 3, 4, '120', '2.5', 1, '2024-06-20 13:49:59.000000', '2024-06-20 13:49:59.000000');
INSERT INTO `route` VALUES (20, 'Đồng Nai - Bình Phước', 4, 3, '120', '2.5', 1, '2024-06-20 13:50:31.000000', '2024-06-20 13:50:31.000000');
INSERT INTO `route` VALUES (21, 'Bình Phước - Tây Ninh', 3, 5, '150', '3', 1, '2024-06-20 13:50:55.000000', '2024-06-20 13:50:55.000000');
INSERT INTO `route` VALUES (22, 'Tây Ninh - Bình Phước', 5, 3, '150', '3', 1, '2024-06-20 13:51:12.000000', '2024-06-20 13:51:12.000000');
INSERT INTO `route` VALUES (23, 'Bình Phước - TP. Hồ Chí Minh', 3, 6, '110', '2.5', 1, '2024-06-20 13:51:43.000000', '2024-06-20 14:31:13.000000');
INSERT INTO `route` VALUES (24, 'TP. Hồ Chí Minh - Bình Phước', 6, 3, '110', '2.5', 1, '2024-06-20 13:52:10.000000', '2024-06-20 14:31:19.000000');
INSERT INTO `route` VALUES (25, 'Đồng Nai - Tây Ninh', 4, 5, '150', '3', 1, '2024-06-20 13:52:31.000000', '2024-06-20 13:52:31.000000');
INSERT INTO `route` VALUES (26, 'Tây Ninh - Đồng Nai', 5, 4, '150', '3', 1, '2024-06-20 13:52:45.000000', '2024-06-20 13:52:45.000000');
INSERT INTO `route` VALUES (27, 'Đồng Nai - TP. Hồ Chí Minh', 4, 6, '60', '1.5', 1, '2024-06-20 13:53:02.000000', '2024-06-20 14:31:25.000000');
INSERT INTO `route` VALUES (28, 'TP. Hồ Chí Minh - Đồng Nai', 6, 4, '60', '1.5', 1, '2024-06-20 13:53:49.000000', '2024-06-20 14:31:32.000000');
INSERT INTO `route` VALUES (29, 'Tây Ninh - TP. Hồ Chí Minh', 5, 6, '100', '2', 1, '2024-06-20 13:54:00.000000', '2024-06-20 14:31:37.000000');
INSERT INTO `route` VALUES (30, 'TP. Hồ Chí Minh - Tây Ninh', 6, 5, '100', '2', 1, '2024-06-20 13:54:09.000000', '2024-06-20 14:31:43.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seat_reservsation
-- ----------------------------
INSERT INTO `seat_reservsation` VALUES (1, 1, 1, 1, 1, '2024-06-20 14:37:42.000000', '2024-06-20 14:37:42.000000');
INSERT INTO `seat_reservsation` VALUES (2, 1, 1, 2, 1, '2024-06-20 14:37:42.000000', '2024-06-20 14:37:42.000000');
INSERT INTO `seat_reservsation` VALUES (3, 1, 1, 3, 1, '2024-06-20 14:37:42.000000', '2024-06-20 14:37:42.000000');
INSERT INTO `seat_reservsation` VALUES (4, 2, 1, 4, 1, '2024-06-20 14:40:04.000000', '2024-06-20 14:40:04.000000');
INSERT INTO `seat_reservsation` VALUES (5, 3, 3, 43, 1, '2024-06-20 14:46:52.000000', '2024-06-20 14:46:52.000000');
INSERT INTO `seat_reservsation` VALUES (6, 3, 3, 44, 1, '2024-06-20 14:46:52.000000', '2024-06-20 14:46:52.000000');
INSERT INTO `seat_reservsation` VALUES (7, 3, 3, 45, 1, '2024-06-20 14:46:52.000000', '2024-06-20 14:46:52.000000');
INSERT INTO `seat_reservsation` VALUES (8, 4, 3, 46, 1, '2024-06-20 15:05:57.000000', '2024-06-20 15:05:57.000000');
INSERT INTO `seat_reservsation` VALUES (9, 4, 3, 47, 1, '2024-06-20 15:05:57.000000', '2024-06-20 15:05:57.000000');
INSERT INTO `seat_reservsation` VALUES (10, 4, 3, 48, 1, '2024-06-20 15:05:57.000000', '2024-06-20 15:05:57.000000');
INSERT INTO `seat_reservsation` VALUES (11, 5, 3, 49, 1, '2024-06-20 17:22:41.000000', '2024-06-20 17:22:41.000000');
INSERT INTO `seat_reservsation` VALUES (12, 6, 3, 50, 1, '2024-06-21 10:17:17.000000', '2024-06-21 10:17:17.000000');
INSERT INTO `seat_reservsation` VALUES (13, 6, 3, 51, 1, '2024-06-21 10:17:17.000000', '2024-06-21 10:17:17.000000');
INSERT INTO `seat_reservsation` VALUES (14, 7, 3, 52, 1, '2024-06-21 10:21:31.000000', '2024-06-21 10:21:31.000000');
INSERT INTO `seat_reservsation` VALUES (15, 7, 2, 22, 1, '2024-06-21 10:21:31.000000', '2024-06-21 10:21:31.000000');
INSERT INTO `seat_reservsation` VALUES (16, 7, 2, 23, 1, '2024-06-21 10:21:31.000000', '2024-06-21 10:21:31.000000');
INSERT INTO `seat_reservsation` VALUES (17, 7, 2, 24, 1, '2024-06-21 10:21:31.000000', '2024-06-21 10:21:31.000000');
INSERT INTO `seat_reservsation` VALUES (18, 8, 3, 53, 1, '2024-06-21 10:30:04.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `seat_reservsation` VALUES (19, 8, 2, 25, 1, '2024-06-21 10:30:04.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `seat_reservsation` VALUES (20, 8, 2, 26, 1, '2024-06-21 10:30:04.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `seat_reservsation` VALUES (21, 8, 2, 27, 1, '2024-06-21 10:30:04.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `seat_reservsation` VALUES (22, 9, 4, 54, 1, '2024-06-21 10:50:01.000000', '2024-06-21 10:50:01.000000');
INSERT INTO `seat_reservsation` VALUES (23, 9, 2, 28, 1, '2024-06-21 10:50:01.000000', '2024-06-21 10:50:01.000000');
INSERT INTO `seat_reservsation` VALUES (24, 9, 2, 29, 1, '2024-06-21 10:50:01.000000', '2024-06-21 10:50:01.000000');
INSERT INTO `seat_reservsation` VALUES (25, 9, 2, 30, 1, '2024-06-21 10:50:01.000000', '2024-06-21 10:50:01.000000');
INSERT INTO `seat_reservsation` VALUES (26, 10, 2, 31, 1, '2024-06-27 15:54:24.000000', '2024-06-27 15:54:24.000000');
INSERT INTO `seat_reservsation` VALUES (27, 10, 2, 32, 1, '2024-06-27 15:54:24.000000', '2024-06-27 15:54:24.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trip
-- ----------------------------
INSERT INTO `trip` VALUES (1, 30, 1, '2024-07-01', '15:09:00.000000', 120000, 1, 17, 1, '2024-06-20 14:09:18.000000', '2024-06-27 16:33:20.000000');
INSERT INTO `trip` VALUES (2, 29, 2, '2024-07-01', '14:10:00.000000', 120000, 2, 10, 1, '2024-06-20 14:09:54.000000', '2024-06-27 16:33:20.000000');
INSERT INTO `trip` VALUES (3, 30, 3, '2024-06-21', '17:10:00.000000', 121000, 3, 10, 1, '2024-06-20 14:10:36.000000', '2024-06-21 10:30:04.000000');
INSERT INTO `trip` VALUES (4, 30, 2, '2024-06-21', '18:11:00.000000', 200000, 4, 20, 1, '2024-06-20 14:11:13.000000', '2024-06-21 10:50:01.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Nguyễn Văn B', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abc@gmail.com', '1234567', 1, 2, 'Đăng ký', '', '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (2, 'Nguyễn Văn C', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abcd@gmail.com', '12345679', 2, 2, 'Google', '', '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (3, 'Nguyễn Văn D', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'abcde@gmail.com', '123456780', 3, 2, 'Đăng ký', '', '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');
INSERT INTO `user` VALUES (4, 'Bùi Quốc Long', '$2a$10$gabxjiwA.veuqREmm4KPK.vaqg59LAIX5BN8KYlOJDFGyiYCGrwBG', 'buiquoclongkt123@gmail.com', '12345', 1, 2, 'Đăng ký', '', '2024-04-23 22:52:54.000000', '2024-04-23 22:52:59.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES (1, 'Vehicle001', 1, 'BS001', 21, 1, '2024-06-20 14:09:18.000000', '2024-06-20 14:09:18.000000');
INSERT INTO `vehicle` VALUES (2, 'Vehicle002', 2, 'BS002', 21, 1, '2024-06-20 14:11:13.000000', '2024-06-20 14:11:13.000000');
INSERT INTO `vehicle` VALUES (3, 'Vehicle003', 3, 'BS003', 21, 1, '2024-06-20 14:10:36.000000', '2024-06-20 14:10:36.000000');
INSERT INTO `vehicle` VALUES (4, 'Vehicle004', 1, 'BS004', 21, 1, '2024-04-14 22:53:40.000000', '2024-04-21 21:24:15.000000');
INSERT INTO `vehicle` VALUES (5, 'Vehicle005', 2, 'BS005', 21, 1, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');
INSERT INTO `vehicle` VALUES (6, 'Vehicle006', 3, 'BS006', 21, 1, '2024-04-14 22:53:40.000000', '2024-04-14 22:53:44.000000');

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of waiting_seat
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
