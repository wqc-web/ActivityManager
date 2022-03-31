/*
 Navicat Premium Data Transfer

 Source Server         : 20
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.0.20:3306
 Source Schema         : example

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 25/11/2020 16:57:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址url',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简述',
  `type` bigint(20) NULL DEFAULT NULL COMMENT '附件类型',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件类型名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `source_id` bigint(20) NULL DEFAULT NULL COMMENT '来源ID',
  `version` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_SOURCE_ID`(`source_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES (1285101742937092097, 'timg.jpg', '/upload/20200720/18278db8349641ceab8aa891c6def09a.jpg', NULL, 1283569008632770562, '图标', '2020-07-20 14:38:26', 1, 1283586752459608065, 1);
INSERT INTO `t_attachment` VALUES (1285102358849662978, 'timg.jpg', '/upload/20200720/8a65f83b52dc44179a4186a5958c4968.jpg', NULL, 1283569008632770562, '图标', '2020-07-20 14:40:53', 1, 7, 1);
INSERT INTO `t_attachment` VALUES (1285500764323586049, 'timg.jpg', '/upload/20200721/2c310404220d4df2ba4545e52edc983e.jpg', NULL, 1283569008632770562, '图标', '2020-07-21 17:04:00', 1, 2, 1);

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` bigint(20) NOT NULL COMMENT '主键，部门编号',
  `dep_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位部门名称',
  `dep_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位部门编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `delete_flag` int(1) NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '中州科技', NULL, '中州科技', 0, '2020-07-01 09:19:12', 1, NULL, NULL, 0, 0);
INSERT INTO `t_department` VALUES (2, '研发部', NULL, '中州科技研发部', 1, '2020-07-01 09:19:37', 1, NULL, NULL, 0, 0);
INSERT INTO `t_department` VALUES (1278590282614448129, '1', NULL, '1', 0, '2020-07-02 15:24:13', 1, NULL, NULL, 1, 0);
INSERT INTO `t_department` VALUES (1278592332471476225, '2', NULL, '222', 2, '2020-07-02 15:32:22', 1, '2020-07-02 16:27:09', 1, 1, 0);
INSERT INTO `t_department` VALUES (1278592777998835713, '3', NULL, '3', 2, '2020-07-02 15:34:08', 1, NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary`  (
  `id` bigint(20) NOT NULL COMMENT '数据字典ID',
  `dic_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典值',
  `dic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典名称',
  `dic_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据字典编码',
  `type` bigint(20) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '数据字典类型',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `delete_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES (1280387705410203650, '0', '男', NULL, 01280381305585971201, '性别', 'sex', '性别：男', '2020-07-07 14:26:32', 1, '2020-07-10 14:35:46', 1, 0, 0);
INSERT INTO `t_dictionary` VALUES (1280387774683328514, '1', '女', NULL, 01280381305585971201, '性别', 'sex', '性别：女', '2020-07-07 14:26:49', 1, '2020-07-10 14:35:53', 1, 0, 0);
INSERT INTO `t_dictionary` VALUES (1281476930893254657, 'wxHeadImg', '微信头像', NULL, 01281475719016230914, '附件类型', 'attachmentType', '', '2020-07-10 14:34:44', 1, '2020-07-10 14:35:18', 1, 0, 0);
INSERT INTO `t_dictionary` VALUES (1283569008632770562, '1', '图标', 'icon', 01281475719016230914, '附件类型', 'attachmentType', '', '2020-07-16 09:07:54', 1, '2020-07-16 09:30:05', 1, 0, 0);

-- ----------------------------
-- Table structure for t_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary_type`;
CREATE TABLE `t_dictionary_type`  (
  `id` bigint(20) NOT NULL,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典类型名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `delete_flag` int(1) NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dictionary_type
-- ----------------------------
INSERT INTO `t_dictionary_type` VALUES (1280381305585971201, '性别', 'sex', '性别，分为：男、女', '2020-07-07 14:01:06', 1, '2020-07-10 14:35:38', 1, 0, 0);
INSERT INTO `t_dictionary_type` VALUES (1280382230262231041, 'test', NULL, 'ttest', '2020-07-07 14:04:47', 1, NULL, NULL, 1, 0);
INSERT INTO `t_dictionary_type` VALUES (1280660721012670465, '1', NULL, '1', '2020-07-08 08:31:24', 1, NULL, NULL, 0, 0);
INSERT INTO `t_dictionary_type` VALUES (1281429207548702721, '附件类型', NULL, '附件类型', '2020-07-10 11:25:06', 1, '2020-07-10 14:28:17', 1, 1, 0);
INSERT INTO `t_dictionary_type` VALUES (1281475719016230914, '附件类型', 'attachmentType', '附件类型', '2020-07-10 14:29:55', 1, '2020-07-10 14:33:14', 1, 0, 0);

-- ----------------------------
-- Table structure for t_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url',
  `parameters` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `ip_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人IP地址',
  `operation_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人名称',
  `operation_user_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人ID',
  `operation_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_OPEARTION_USER_NAME`(`operation_user_name`) USING BTREE,
  INDEX `IDX_NAME`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_operation_log
-- ----------------------------
INSERT INTO `t_operation_log` VALUES (1279952189720764418, '获取登录用户信息', '/api/getLoginInfo', NULL, '114.238.51.181', 'admin', 1, '2020-07-06 09:35:57', 0);
INSERT INTO `t_operation_log` VALUES (1279952203989786626, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-06 09:36:00', 0);
INSERT INTO `t_operation_log` VALUES (1279952416225763329, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10&name=&ipAddr=&operationUserName=test', '114.238.51.181', 'admin', 1, '2020-07-06 09:36:51', 0);
INSERT INTO `t_operation_log` VALUES (1279952458131054593, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10&name=&ipAddr=&operationUserName=', '114.238.51.181', 'admin', 1, '2020-07-06 09:37:01', 0);
INSERT INTO `t_operation_log` VALUES (1279952505367306242, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10&name=%E7%94%A8%E6%88%B7&ipAddr=&operationUserName=', '114.238.51.181', 'admin', 1, '2020-07-06 09:37:12', 0);
INSERT INTO `t_operation_log` VALUES (1279952519049125889, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-06 09:37:16', 0);
INSERT INTO `t_operation_log` VALUES (1279961493361717250, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:12:55', 0);
INSERT INTO `t_operation_log` VALUES (1279961497237254146, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:12:56', 0);
INSERT INTO `t_operation_log` VALUES (1279961528929415170, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:13:04', 0);
INSERT INTO `t_operation_log` VALUES (1279961795825561601, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:14:07', 0);
INSERT INTO `t_operation_log` VALUES (1279961801273962498, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:14:09', 0);
INSERT INTO `t_operation_log` VALUES (1279961958455504897, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:14:46', 0);
INSERT INTO `t_operation_log` VALUES (1279961973668245505, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:14:50', 0);
INSERT INTO `t_operation_log` VALUES (1279962009290469378, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:14:58', 0);
INSERT INTO `t_operation_log` VALUES (1279962062407135233, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:15:11', 0);
INSERT INTO `t_operation_log` VALUES (1279962089326178305, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:15:17', 0);
INSERT INTO `t_operation_log` VALUES (1279963196106534913, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:19:41', 0);
INSERT INTO `t_operation_log` VALUES (1279963199617167361, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:19:42', 0);
INSERT INTO `t_operation_log` VALUES (1279963273717936129, '编辑权限菜单', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:20:00', 0);
INSERT INTO `t_operation_log` VALUES (1279963279136976898, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:20:01', 0);
INSERT INTO `t_operation_log` VALUES (1279963309893808129, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:20:08', 0);
INSERT INTO `t_operation_log` VALUES (1279963706393948161, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:21:43', 0);
INSERT INTO `t_operation_log` VALUES (1279963714782556162, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:21:45', 0);
INSERT INTO `t_operation_log` VALUES (1279963723531874305, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:21:47', 0);
INSERT INTO `t_operation_log` VALUES (1279963735934431234, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:21:50', 0);
INSERT INTO `t_operation_log` VALUES (1279963915882655746, '编辑权限菜单', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:22:33', 0);
INSERT INTO `t_operation_log` VALUES (1279963921704349698, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:22:34', 0);
INSERT INTO `t_operation_log` VALUES (1279963932408213505, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:22:37', 0);
INSERT INTO `t_operation_log` VALUES (1279963940910067713, '查看权限菜单详情', '/api/permission/detail/1279963706653995010', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:22:39', 0);
INSERT INTO `t_operation_log` VALUES (1279963956294774786, '编辑权限菜单', '/api/permission/edit/1279963706653995010', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:22:42', 0);
INSERT INTO `t_operation_log` VALUES (1279963961848033281, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:22:44', 0);
INSERT INTO `t_operation_log` VALUES (1279963972556091394, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:22:46', 0);
INSERT INTO `t_operation_log` VALUES (1279963972811943938, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:22:46', 0);
INSERT INTO `t_operation_log` VALUES (1279963998892126209, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:22:53', 0);
INSERT INTO `t_operation_log` VALUES (1279964028986257410, '角色管理', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:23:00', 0);
INSERT INTO `t_operation_log` VALUES (1279964077841510402, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:11', 0);
INSERT INTO `t_operation_log` VALUES (1279964088826392578, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:23:14', 0);
INSERT INTO `t_operation_log` VALUES (1279964089111605249, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:14', 0);
INSERT INTO `t_operation_log` VALUES (1279964098196467713, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:16', 0);
INSERT INTO `t_operation_log` VALUES (1279964147685060610, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:28', 0);
INSERT INTO `t_operation_log` VALUES (1279964155469688834, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:30', 0);
INSERT INTO `t_operation_log` VALUES (1279964195575623682, '菜单管理', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:23:39', 0);
INSERT INTO `t_operation_log` VALUES (1279964212206039042, '菜单管理', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:23:43', 0);
INSERT INTO `t_operation_log` VALUES (1279964217474084865, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:45', 0);
INSERT INTO `t_operation_log` VALUES (1279964254715310082, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:54', 0);
INSERT INTO `t_operation_log` VALUES (1279964261220675585, '菜单管理', '/api/permission/detail/1279963706653995010', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:23:55', 0);
INSERT INTO `t_operation_log` VALUES (1279964270016131074, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:23:57', 0);
INSERT INTO `t_operation_log` VALUES (1279964279927271426, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:24:00', 0);
INSERT INTO `t_operation_log` VALUES (1279964289599336450, '菜单管理', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:24:02', 0);
INSERT INTO `t_operation_log` VALUES (1279964311783010306, '菜单管理', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:24:07', 0);
INSERT INTO `t_operation_log` VALUES (1279964316174446594, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:24:08', 0);
INSERT INTO `t_operation_log` VALUES (1279964398567354370, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:24:28', 0);
INSERT INTO `t_operation_log` VALUES (1279964418268000257, '菜单管理', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:24:33', 0);
INSERT INTO `t_operation_log` VALUES (1279964662259052546, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:25:31', 0);
INSERT INTO `t_operation_log` VALUES (1279964667615178753, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:25:32', 0);
INSERT INTO `t_operation_log` VALUES (1279964676372885506, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:25:34', 0);
INSERT INTO `t_operation_log` VALUES (1279964694387421185, '菜单管理', '/api/permission/detail/1279963706653995010', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:25:38', 0);
INSERT INTO `t_operation_log` VALUES (1279964706311827458, '菜单管理', '/api/permission/edit/1279963706653995010', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:25:41', 0);
INSERT INTO `t_operation_log` VALUES (1279964710048952321, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:25:42', 0);
INSERT INTO `t_operation_log` VALUES (1279964720731844609, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:25:45', 0);
INSERT INTO `t_operation_log` VALUES (1279964728533250050, '菜单管理', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:25:47', 0);
INSERT INTO `t_operation_log` VALUES (1279965014588977153, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:26:55', 0);
INSERT INTO `t_operation_log` VALUES (1279965019655696385, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:26:56', 0);
INSERT INTO `t_operation_log` VALUES (1279965164476624897, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:27:30', 0);
INSERT INTO `t_operation_log` VALUES (1279965170424147969, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:27:32', 0);
INSERT INTO `t_operation_log` VALUES (1279965476704808961, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:28:45', 0);
INSERT INTO `t_operation_log` VALUES (1279965483256311810, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:28:46', 0);
INSERT INTO `t_operation_log` VALUES (1279965496782942209, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:28:50', 0);
INSERT INTO `t_operation_log` VALUES (1279965497634385922, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:28:50', 0);
INSERT INTO `t_operation_log` VALUES (1279965682817101825, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:29:34', 0);
INSERT INTO `t_operation_log` VALUES (1279965701137821697, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:29:38', 0);
INSERT INTO `t_operation_log` VALUES (1279965871443341313, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:30:19', 0);
INSERT INTO `t_operation_log` VALUES (1279965876828827649, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:30:20', 0);
INSERT INTO `t_operation_log` VALUES (1279965898299469825, '菜单管理', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:30:25', 0);
INSERT INTO `t_operation_log` VALUES (1279965905228460034, '菜单管理', '/api/permission/detail/1279965871720165378', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:30:27', 0);
INSERT INTO `t_operation_log` VALUES (1279965939319762946, '菜单管理', '/api/permission/edit/1279965871720165378', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:30:35', 0);
INSERT INTO `t_operation_log` VALUES (1279965944327761921, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:30:36', 0);
INSERT INTO `t_operation_log` VALUES (1279966073424244737, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:31:07', 0);
INSERT INTO `t_operation_log` VALUES (1279966077991841793, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:31:08', 0);
INSERT INTO `t_operation_log` VALUES (1279966216412262402, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:31:41', 0);
INSERT INTO `t_operation_log` VALUES (1279966221025996802, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:31:42', 0);
INSERT INTO `t_operation_log` VALUES (1279966228298919938, '菜单管理', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:31:44', 0);
INSERT INTO `t_operation_log` VALUES (1279966242093985794, '菜单管理', '/api/permission/detail/1279965871720165378', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:31:47', 0);
INSERT INTO `t_operation_log` VALUES (1279966265699528705, '菜单管理', '/api/permission/edit/1279965871720165378', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:31:53', 0);
INSERT INTO `t_operation_log` VALUES (1279966269759614978, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:31:54', 0);
INSERT INTO `t_operation_log` VALUES (1279966286327115777, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:31:58', 0);
INSERT INTO `t_operation_log` VALUES (1279966294275321857, '菜单管理', '/api/permission/detail/1279965683060371458', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:32:00', 0);
INSERT INTO `t_operation_log` VALUES (1279966315091652609, '菜单管理', '/api/permission/edit/1279965683060371458', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:32:05', 0);
INSERT INTO `t_operation_log` VALUES (1279966319365648386, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:32:06', 0);
INSERT INTO `t_operation_log` VALUES (1279966335857651714, '菜单管理', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:32:10', 0);
INSERT INTO `t_operation_log` VALUES (1279966467667849218, '菜单管理', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:32:41', 0);
INSERT INTO `t_operation_log` VALUES (1279966472533241858, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:32:42', 0);
INSERT INTO `t_operation_log` VALUES (1279966494691749889, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:32:48', 0);
INSERT INTO `t_operation_log` VALUES (1279966531622596609, '角色管理', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-06 10:32:56', 0);
INSERT INTO `t_operation_log` VALUES (1279966539134595074, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-06 10:32:58', 0);
INSERT INTO `t_operation_log` VALUES (1280302104564527106, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 08:46:23', 0);
INSERT INTO `t_operation_log` VALUES (1280302105747320833, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 08:46:24', 0);
INSERT INTO `t_operation_log` VALUES (1280302128446894081, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:46:29', 0);
INSERT INTO `t_operation_log` VALUES (1280302158243229698, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:46:36', 0);
INSERT INTO `t_operation_log` VALUES (1280302165952360449, '菜单管理', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:46:38', 0);
INSERT INTO `t_operation_log` VALUES (1280302176245182465, '菜单管理', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:46:40', 0);
INSERT INTO `t_operation_log` VALUES (1280302270755434497, '菜单管理', '/api/permission/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:47:03', 0);
INSERT INTO `t_operation_log` VALUES (1280302310760706049, '菜单管理', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:47:12', 0);
INSERT INTO `t_operation_log` VALUES (1280302326401265666, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:47:16', 0);
INSERT INTO `t_operation_log` VALUES (1280302384920195074, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 08:47:30', 0);
INSERT INTO `t_operation_log` VALUES (1280302385113133058, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:47:30', 0);
INSERT INTO `t_operation_log` VALUES (1280302705918668802, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:48:47', 0);
INSERT INTO `t_operation_log` VALUES (1280302705918668803, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 08:48:47', 0);
INSERT INTO `t_operation_log` VALUES (1280303400394747906, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:51:32', 0);
INSERT INTO `t_operation_log` VALUES (1280303400440885250, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 08:51:32', 0);
INSERT INTO `t_operation_log` VALUES (1280303446406262786, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 08:51:43', 0);
INSERT INTO `t_operation_log` VALUES (1280305781090086913, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 09:01:00', 0);
INSERT INTO `t_operation_log` VALUES (1280305785229864962, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 09:01:01', 0);
INSERT INTO `t_operation_log` VALUES (1280305871640915969, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 09:01:21', 0);
INSERT INTO `t_operation_log` VALUES (1280305871972265986, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 09:01:21', 0);
INSERT INTO `t_operation_log` VALUES (1280306189950840834, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 09:02:37', 0);
INSERT INTO `t_operation_log` VALUES (1280306189950840835, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 09:02:37', 0);
INSERT INTO `t_operation_log` VALUES (1280306425163214850, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 09:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1280306425171603457, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 09:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1280334980353884161, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 10:57:01', 0);
INSERT INTO `t_operation_log` VALUES (1280334981545066498, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 10:57:02', 0);
INSERT INTO `t_operation_log` VALUES (1280337503047434241, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:07:03', 0);
INSERT INTO `t_operation_log` VALUES (1280337504754515970, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:07:03', 0);
INSERT INTO `t_operation_log` VALUES (1280338616576425986, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:11:28', 0);
INSERT INTO `t_operation_log` VALUES (1280338618212204545, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:11:29', 0);
INSERT INTO `t_operation_log` VALUES (1280338621647339522, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:11:30', 0);
INSERT INTO `t_operation_log` VALUES (1280339449321889793, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:14:47', 0);
INSERT INTO `t_operation_log` VALUES (1280339449321889794, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:14:47', 0);
INSERT INTO `t_operation_log` VALUES (1280339683468910594, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:15:43', 0);
INSERT INTO `t_operation_log` VALUES (1280339800024424449, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:16:11', 0);
INSERT INTO `t_operation_log` VALUES (1280341851534020610, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:24:20', 0);
INSERT INTO `t_operation_log` VALUES (1280341924623962113, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:24:37', 0);
INSERT INTO `t_operation_log` VALUES (1280341928705019905, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 11:24:38', 0);
INSERT INTO `t_operation_log` VALUES (1280341928969261057, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:24:38', 0);
INSERT INTO `t_operation_log` VALUES (1280342069847543809, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:25:12', 0);
INSERT INTO `t_operation_log` VALUES (1280342079574134786, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:25:14', 0);
INSERT INTO `t_operation_log` VALUES (1280342083734884353, '日志管理', '/api/operationLog/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:25:15', 0);
INSERT INTO `t_operation_log` VALUES (1280343175419940865, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:29:35', 0);
INSERT INTO `t_operation_log` VALUES (1280343849675280385, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 11:32:16', 0);
INSERT INTO `t_operation_log` VALUES (1280378673572106241, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 13:50:39', 0);
INSERT INTO `t_operation_log` VALUES (1280378675509874690, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 13:50:39', 0);
INSERT INTO `t_operation_log` VALUES (1280378694459740162, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 13:50:44', 0);
INSERT INTO `t_operation_log` VALUES (1280379183914070017, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 13:52:40', 0);
INSERT INTO `t_operation_log` VALUES (1280380492008435713, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 13:57:52', 0);
INSERT INTO `t_operation_log` VALUES (1280380746355224578, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 13:58:53', 0);
INSERT INTO `t_operation_log` VALUES (1280380747089227777, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 13:58:53', 0);
INSERT INTO `t_operation_log` VALUES (1280380806258274306, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 13:59:07', 0);
INSERT INTO `t_operation_log` VALUES (1280381304004718593, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:01:06', 0);
INSERT INTO `t_operation_log` VALUES (1280381311466385410, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:01:08', 0);
INSERT INTO `t_operation_log` VALUES (1280381432291700738, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:01:36', 0);
INSERT INTO `t_operation_log` VALUES (1280381847896895489, '数据字典管理', '/api/dictionaryType/detail/1280381305585971201', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:03:16', 0);
INSERT INTO `t_operation_log` VALUES (1280381916234690561, '数据字典管理', '/api/dictionaryType/edit/1280381305585971201', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:03:32', 0);
INSERT INTO `t_operation_log` VALUES (1280381922496786433, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1280382074007629825, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:04:09', 0);
INSERT INTO `t_operation_log` VALUES (1280382229901520898, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:04:47', 0);
INSERT INTO `t_operation_log` VALUES (1280382238365626369, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:04:49', 0);
INSERT INTO `t_operation_log` VALUES (1280382261321052162, '数据字典管理', '/api/dictionaryType/delete/1280382230262231041', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:04:54', 0);
INSERT INTO `t_operation_log` VALUES (1280382266496823297, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:04:55', 0);
INSERT INTO `t_operation_log` VALUES (1280382360768000001, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:05:18', 0);
INSERT INTO `t_operation_log` VALUES (1280382401767321601, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:05:28', 0);
INSERT INTO `t_operation_log` VALUES (1280382405819019266, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:05:29', 0);
INSERT INTO `t_operation_log` VALUES (1280383310635249665, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:09:04', 0);
INSERT INTO `t_operation_log` VALUES (1280385058061987841, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:16:01', 0);
INSERT INTO `t_operation_log` VALUES (1280385587739668481, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:18:07', 0);
INSERT INTO `t_operation_log` VALUES (1280385749740466178, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1280385765980811266, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:18:50', 0);
INSERT INTO `t_operation_log` VALUES (1280385781642342401, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:18:53', 0);
INSERT INTO `t_operation_log` VALUES (1280385804643905538, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:18:59', 0);
INSERT INTO `t_operation_log` VALUES (1280385814739595265, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:01', 0);
INSERT INTO `t_operation_log` VALUES (1280385955097784321, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:35', 0);
INSERT INTO `t_operation_log` VALUES (1280386013474107393, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:19:49', 0);
INSERT INTO `t_operation_log` VALUES (1280386018071064577, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:50', 0);
INSERT INTO `t_operation_log` VALUES (1280386026300289026, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:19:52', 0);
INSERT INTO `t_operation_log` VALUES (1280386036416950273, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:54', 0);
INSERT INTO `t_operation_log` VALUES (1280386047347306497, '数据字典管理', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:57', 0);
INSERT INTO `t_operation_log` VALUES (1280386054049804290, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:19:58', 0);
INSERT INTO `t_operation_log` VALUES (1280386059137495042, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:20:00', 0);
INSERT INTO `t_operation_log` VALUES (1280386074098577410, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:20:03', 0);
INSERT INTO `t_operation_log` VALUES (1280386298284126210, '数据字典管理', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:20:57', 0);
INSERT INTO `t_operation_log` VALUES (1280386433718202370, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:21:29', 0);
INSERT INTO `t_operation_log` VALUES (1280386433881780226, '数据字典管理', '/api/dictionary/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:21:29', 0);
INSERT INTO `t_operation_log` VALUES (1280386494518833154, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:21:43', 0);
INSERT INTO `t_operation_log` VALUES (1280386508452311042, '数据字典管理', '/api/dictionary/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:21:47', 0);
INSERT INTO `t_operation_log` VALUES (1280386535727869953, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:21:53', 0);
INSERT INTO `t_operation_log` VALUES (1280387341462056962, '数据字典管理', '/api/dictionary/detail/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:05', 0);
INSERT INTO `t_operation_log` VALUES (1280387341554331650, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:05', 0);
INSERT INTO `t_operation_log` VALUES (1280387355437477890, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:09', 0);
INSERT INTO `t_operation_log` VALUES (1280387355512975362, '数据字典管理', '/api/dictionary/detail/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:09', 0);
INSERT INTO `t_operation_log` VALUES (1280387365839351809, '数据字典管理', '/api/dictionary/detail/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:11', 0);
INSERT INTO `t_operation_log` VALUES (1280387365927432193, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:11', 0);
INSERT INTO `t_operation_log` VALUES (1280387406545072130, '数据字典管理', '/api/dictionary/detail/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:21', 0);
INSERT INTO `t_operation_log` VALUES (1280387406553460738, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:21', 0);
INSERT INTO `t_operation_log` VALUES (1280387562338299905, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:58', 0);
INSERT INTO `t_operation_log` VALUES (1280387566683598849, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:59', 0);
INSERT INTO `t_operation_log` VALUES (1280387566863953921, '数据字典管理', '/api/dictionary/detail/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:25:59', 0);
INSERT INTO `t_operation_log` VALUES (1280387578096300034, '数据字典管理', '/api/dictionary/edit/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:02', 0);
INSERT INTO `t_operation_log` VALUES (1280387582617759745, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:26:03', 0);
INSERT INTO `t_operation_log` VALUES (1280387604776267777, '数据字典管理', '/api/dictionary/delete/1280386527028883457', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:08', 0);
INSERT INTO `t_operation_log` VALUES (1280387608643416065, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:26:09', 0);
INSERT INTO `t_operation_log` VALUES (1280387617963159554, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:11', 0);
INSERT INTO `t_operation_log` VALUES (1280387705150156801, '数据字典管理', '/api/dictionary/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:32', 0);
INSERT INTO `t_operation_log` VALUES (1280387710325927938, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:26:33', 0);
INSERT INTO `t_operation_log` VALUES (1280387715333926914, '数据字典管理', '/api/dictionaryType/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:34', 0);
INSERT INTO `t_operation_log` VALUES (1280387774427475969, '数据字典管理', '/api/dictionary/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:26:49', 0);
INSERT INTO `t_operation_log` VALUES (1280387778986684417, '数据字典管理', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:26:50', 0);
INSERT INTO `t_operation_log` VALUES (1280387901162565633, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:19', 0);
INSERT INTO `t_operation_log` VALUES (1280387902131449857, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:19', 0);
INSERT INTO `t_operation_log` VALUES (1280387911023374338, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:21', 0);
INSERT INTO `t_operation_log` VALUES (1280387922687733762, '部门管理', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:24', 0);
INSERT INTO `t_operation_log` VALUES (1280387926903009281, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:25', 0);
INSERT INTO `t_operation_log` VALUES (1280387931407691778, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:26', 0);
INSERT INTO `t_operation_log` VALUES (1280387942254161922, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:29', 0);
INSERT INTO `t_operation_log` VALUES (1280387947492847617, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:30', 0);
INSERT INTO `t_operation_log` VALUES (1280387961875116034, '角色管理', '/api/role/list', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:33', 0);
INSERT INTO `t_operation_log` VALUES (1280387976307716098, '部门管理', '/api/department/findDepTree', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:37', 0);
INSERT INTO `t_operation_log` VALUES (1280387995416965121, '部门管理', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:41', 0);
INSERT INTO `t_operation_log` VALUES (1280388034386243585, '部门管理', '/api/department/delete/1278592777998835713', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:27:51', 0);
INSERT INTO `t_operation_log` VALUES (1280388038186283009, '部门管理', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:27:51', 0);
INSERT INTO `t_operation_log` VALUES (1280388067231838210, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:27:58', 0);
INSERT INTO `t_operation_log` VALUES (1280388089033830402, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:28:04', 0);
INSERT INTO `t_operation_log` VALUES (1280388112240914434, '角色管理', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:28:09', 0);
INSERT INTO `t_operation_log` VALUES (1280388476075814913, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:29:36', 0);
INSERT INTO `t_operation_log` VALUES (1280388680204201986, '菜单管理', '/api/permission/detail/3', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:30:24', 0);
INSERT INTO `t_operation_log` VALUES (1280388737712304130, '菜单管理', '/api/permission/edit/3', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:30:38', 0);
INSERT INTO `t_operation_log` VALUES (1280388741852082178, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:30:39', 0);
INSERT INTO `t_operation_log` VALUES (1280388755701673985, '菜单管理', '/api/permission/detail/4', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:30:42', 0);
INSERT INTO `t_operation_log` VALUES (1280388766736887810, '菜单管理', '/api/permission/edit/4', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:30:45', 0);
INSERT INTO `t_operation_log` VALUES (1280388770671144961, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:30:46', 0);
INSERT INTO `t_operation_log` VALUES (1280388990050021378, '菜单管理', '/api/permission/detail/5', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:38', 0);
INSERT INTO `t_operation_log` VALUES (1280388999151661058, '菜单管理', '/api/permission/edit/5', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:41', 0);
INSERT INTO `t_operation_log` VALUES (1280389005568946178, '菜单管理', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:31:42', 0);
INSERT INTO `t_operation_log` VALUES (1280389021503107073, '菜单管理', '/api/permission/detail/6', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:46', 0);
INSERT INTO `t_operation_log` VALUES (1280389029744914433, '菜单管理', '/api/permission/edit/6', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:48', 0);
INSERT INTO `t_operation_log` VALUES (1280389034786467842, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:31:49', 0);
INSERT INTO `t_operation_log` VALUES (1280389045758767105, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:52', 0);
INSERT INTO `t_operation_log` VALUES (1280389057637036034, '编辑权限菜单', '/api/permission/edit/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:31:54', 0);
INSERT INTO `t_operation_log` VALUES (1280389063668445185, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:31:56', 0);
INSERT INTO `t_operation_log` VALUES (1280389101861777410, '查看权限菜单详情', '/api/permission/detail/3', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:05', 0);
INSERT INTO `t_operation_log` VALUES (1280389115904307202, '编辑权限菜单', '/api/permission/edit/3', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:08', 0);
INSERT INTO `t_operation_log` VALUES (1280389121176547330, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:10', 0);
INSERT INTO `t_operation_log` VALUES (1280389131389677570, '查看权限菜单详情', '/api/permission/detail/4', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:12', 0);
INSERT INTO `t_operation_log` VALUES (1280389143901286402, '编辑权限菜单', '/api/permission/edit/4', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:15', 0);
INSERT INTO `t_operation_log` VALUES (1280389149412601858, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:16', 0);
INSERT INTO `t_operation_log` VALUES (1280389161412505602, '查看权限菜单详情', '/api/permission/detail/5', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:19', 0);
INSERT INTO `t_operation_log` VALUES (1280389174620368897, '编辑权限菜单', '/api/permission/edit/5', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:22', 0);
INSERT INTO `t_operation_log` VALUES (1280389179410264066, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:24', 0);
INSERT INTO `t_operation_log` VALUES (1280389200528584706, '查看权限菜单详情', '/api/permission/detail/6', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:29', 0);
INSERT INTO `t_operation_log` VALUES (1280389214088769538, '编辑权限菜单', '/api/permission/edit/6', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:32', 0);
INSERT INTO `t_operation_log` VALUES (1280389219772051457, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:33', 0);
INSERT INTO `t_operation_log` VALUES (1280389237400711170, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:37', 0);
INSERT INTO `t_operation_log` VALUES (1280389256962945025, '编辑权限菜单', '/api/permission/edit/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:32:42', 0);
INSERT INTO `t_operation_log` VALUES (1280389261429878785, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:43', 0);
INSERT INTO `t_operation_log` VALUES (1280389288684466177, '查看权限菜单分页列表', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:50', 0);
INSERT INTO `t_operation_log` VALUES (1280389314965975041, '查看权限菜单分页列表', '/api/permission/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:56', 0);
INSERT INTO `t_operation_log` VALUES (1280389328056397825, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:32:59', 0);
INSERT INTO `t_operation_log` VALUES (1280389353687789570, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:33:05', 0);
INSERT INTO `t_operation_log` VALUES (1280389368019726338, '编辑权限菜单', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:33:08', 0);
INSERT INTO `t_operation_log` VALUES (1280389371849125889, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:09', 0);
INSERT INTO `t_operation_log` VALUES (1280389383974858753, '查看权限菜单分页列表', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:12', 0);
INSERT INTO `t_operation_log` VALUES (1280389386843762689, '查看权限菜单分页列表', '/api/permission/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:13', 0);
INSERT INTO `t_operation_log` VALUES (1280389390673162242, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:14', 0);
INSERT INTO `t_operation_log` VALUES (1280389483149176833, '查看权限菜单详情', '/api/permission/detail/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:33:36', 0);
INSERT INTO `t_operation_log` VALUES (1280389549003943937, '编辑权限菜单', '/api/permission/edit/1279961796995772418', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:33:52', 0);
INSERT INTO `t_operation_log` VALUES (1280389552942395393, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:53', 0);
INSERT INTO `t_operation_log` VALUES (1280389564313153538, '查看权限菜单分页列表', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:55', 0);
INSERT INTO `t_operation_log` VALUES (1280389566200590338, '查看权限菜单分页列表', '/api/permission/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:33:56', 0);
INSERT INTO `t_operation_log` VALUES (1280389582570958849, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:34:00', 0);
INSERT INTO `t_operation_log` VALUES (1280389645732982786, '查看权限菜单详情', '/api/permission/detail/1279965476973244417', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:34:15', 0);
INSERT INTO `t_operation_log` VALUES (1280389663353253890, '编辑权限菜单', '/api/permission/edit/1279965476973244417', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 14:34:19', 0);
INSERT INTO `t_operation_log` VALUES (1280389667337842689, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 14:34:20', 0);
INSERT INTO `t_operation_log` VALUES (1280390091004489729, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:36:01', 0);
INSERT INTO `t_operation_log` VALUES (1280390110138904577, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:36:05', 0);
INSERT INTO `t_operation_log` VALUES (1280390300996513793, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:36:51', 0);
INSERT INTO `t_operation_log` VALUES (1280390547017609217, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:37:50', 0);
INSERT INTO `t_operation_log` VALUES (1280390802178129922, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:38:50', 0);
INSERT INTO `t_operation_log` VALUES (1280390861997293570, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:39:05', 0);
INSERT INTO `t_operation_log` VALUES (1280392390162595842, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:45:09', 0);
INSERT INTO `t_operation_log` VALUES (1280392390254870530, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:45:09', 0);
INSERT INTO `t_operation_log` VALUES (1280393989316493314, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:51:30', 0);
INSERT INTO `t_operation_log` VALUES (1280394346390175745, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:52:55', 0);
INSERT INTO `t_operation_log` VALUES (1280394346717331457, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:52:55', 0);
INSERT INTO `t_operation_log` VALUES (1280394355827359745, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:52:58', 0);
INSERT INTO `t_operation_log` VALUES (1280394356129349633, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:52:58', 0);
INSERT INTO `t_operation_log` VALUES (1280394371946070017, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:53:02', 0);
INSERT INTO `t_operation_log` VALUES (1280395338871554049, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:56:52', 0);
INSERT INTO `t_operation_log` VALUES (1280395382446178305, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:57:02', 0);
INSERT INTO `t_operation_log` VALUES (1280395658200694786, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:58:08', 0);
INSERT INTO `t_operation_log` VALUES (1280395658292969474, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'test', 2, '2020-07-07 14:58:08', 0);
INSERT INTO `t_operation_log` VALUES (1280395666002100225, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:58:10', 0);
INSERT INTO `t_operation_log` VALUES (1280395669688893442, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'test', 2, '2020-07-07 14:58:11', 0);
INSERT INTO `t_operation_log` VALUES (1280397043390562306, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:03:38', 0);
INSERT INTO `t_operation_log` VALUES (1280397044422361090, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:03:39', 0);
INSERT INTO `t_operation_log` VALUES (1280398211101573122, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:17', 0);
INSERT INTO `t_operation_log` VALUES (1280398212196286465, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:17', 0);
INSERT INTO `t_operation_log` VALUES (1280398240998572034, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:24', 0);
INSERT INTO `t_operation_log` VALUES (1280398242005204994, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:24', 0);
INSERT INTO `t_operation_log` VALUES (1280398246631522305, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:25', 0);
INSERT INTO `t_operation_log` VALUES (1280398247453605889, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:26', 0);
INSERT INTO `t_operation_log` VALUES (1280398250427367426, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:26', 0);
INSERT INTO `t_operation_log` VALUES (1280398251283005442, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:08:26', 0);
INSERT INTO `t_operation_log` VALUES (1280398283998576642, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:08:34', 0);
INSERT INTO `t_operation_log` VALUES (1280400320203145218, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:16:40', 0);
INSERT INTO `t_operation_log` VALUES (1280400321222361089, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:16:40', 0);
INSERT INTO `t_operation_log` VALUES (1280400771162128385, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:18:27', 0);
INSERT INTO `t_operation_log` VALUES (1280400772030349314, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:18:27', 0);
INSERT INTO `t_operation_log` VALUES (1280400776535031810, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:18:28', 0);
INSERT INTO `t_operation_log` VALUES (1280402686021287937, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:26:04', 0);
INSERT INTO `t_operation_log` VALUES (1280402687480905730, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:26:04', 0);
INSERT INTO `t_operation_log` VALUES (1280403322150404097, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:35', 0);
INSERT INTO `t_operation_log` VALUES (1280403343415525377, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:40', 0);
INSERT INTO `t_operation_log` VALUES (1280403349916696577, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:42', 0);
INSERT INTO `t_operation_log` VALUES (1280403362944204802, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:45', 0);
INSERT INTO `t_operation_log` VALUES (1280403367268532225, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:46', 0);
INSERT INTO `t_operation_log` VALUES (1280403373132169218, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:48', 0);
INSERT INTO `t_operation_log` VALUES (1280403386381975554, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:28:51', 0);
INSERT INTO `t_operation_log` VALUES (1280408340995788802, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:48:32', 0);
INSERT INTO `t_operation_log` VALUES (1280408342140833793, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 15:48:32', 0);
INSERT INTO `t_operation_log` VALUES (1280408346561630209, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:48:33', 0);
INSERT INTO `t_operation_log` VALUES (1280408460860608514, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:49:01', 0);
INSERT INTO `t_operation_log` VALUES (1280408705136873474, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 15:49:59', 0);
INSERT INTO `t_operation_log` VALUES (1280415651978117122, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:35', 0);
INSERT INTO `t_operation_log` VALUES (1280415666989531138, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:39', 0);
INSERT INTO `t_operation_log` VALUES (1280415678653890561, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:41', 0);
INSERT INTO `t_operation_log` VALUES (1280415717719638017, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:51', 0);
INSERT INTO `t_operation_log` VALUES (1280415735839031298, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:55', 0);
INSERT INTO `t_operation_log` VALUES (1280415742570889218, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:17:57', 0);
INSERT INTO `t_operation_log` VALUES (1280415855766765569, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:18:24', 0);
INSERT INTO `t_operation_log` VALUES (1280417487413936130, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 16:24:53', 0);
INSERT INTO `t_operation_log` VALUES (1280417488303128578, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 16:24:53', 0);
INSERT INTO `t_operation_log` VALUES (1280417495181787138, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:24:55', 0);
INSERT INTO `t_operation_log` VALUES (1280417506204418049, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:24:57', 0);
INSERT INTO `t_operation_log` VALUES (1280417516904087554, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:25:00', 0);
INSERT INTO `t_operation_log` VALUES (1280417533081518081, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 16:25:04', 0);
INSERT INTO `t_operation_log` VALUES (1280443428408229890, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 18:07:57', 0);
INSERT INTO `t_operation_log` VALUES (1280443429419057154, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 18:07:58', 0);
INSERT INTO `t_operation_log` VALUES (1280443440844341250, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:08:00', 0);
INSERT INTO `t_operation_log` VALUES (1280446773512470529, '查看日志分页列表', '/api/operationLog/page', 'current=34&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:21:15', 0);
INSERT INTO `t_operation_log` VALUES (1280446783465553922, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:21:17', 0);
INSERT INTO `t_operation_log` VALUES (1280446797059293186, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:21:21', 0);
INSERT INTO `t_operation_log` VALUES (1280446926436794369, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 18:21:51', 0);
INSERT INTO `t_operation_log` VALUES (1280446927309209601, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-07 18:21:52', 0);
INSERT INTO `t_operation_log` VALUES (1280446936259854337, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:21:54', 0);
INSERT INTO `t_operation_log` VALUES (1280446952252735489, '查看日志分页列表', '/api/operationLog/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-07 18:21:58', 0);
INSERT INTO `t_operation_log` VALUES (1280660685939900417, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-08 08:31:16', 0);
INSERT INTO `t_operation_log` VALUES (1280660687353380865, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-08 08:31:16', 0);
INSERT INTO `t_operation_log` VALUES (1280660691224723458, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:17', 0);
INSERT INTO `t_operation_log` VALUES (1280660699244232706, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:19', 0);
INSERT INTO `t_operation_log` VALUES (1280660718391230466, '添加数据字典类型', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-08 08:31:23', 0);
INSERT INTO `t_operation_log` VALUES (1280660725899034625, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:25', 0);
INSERT INTO `t_operation_log` VALUES (1280660746765697025, '添加数据字典类型', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-08 08:31:30', 0);
INSERT INTO `t_operation_log` VALUES (1280660814273019906, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:46', 0);
INSERT INTO `t_operation_log` VALUES (1280660828533653506, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:50', 0);
INSERT INTO `t_operation_log` VALUES (1280660832979615745, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:31:51', 0);
INSERT INTO `t_operation_log` VALUES (1280660903850770433, '查看权限菜单分页列表', '/api/permission/page', 'current=2&size=10', '192.168.0.12', 'admin', 1, '2020-07-08 08:32:08', 0);
INSERT INTO `t_operation_log` VALUES (1281054588195803138, '获取登录用户信息', '/api/getLoginInfo', NULL, '114.238.51.181', 'admin', 1, '2020-07-09 10:36:29', 0);
INSERT INTO `t_operation_log` VALUES (1281054589739307009, '获取登录用户信息', '/api/getLoginInfo', NULL, '114.238.51.181', 'admin', 1, '2020-07-09 10:36:30', 0);
INSERT INTO `t_operation_log` VALUES (1281055695064240130, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 10:40:53', 0);
INSERT INTO `t_operation_log` VALUES (1281055719147933697, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 10:40:59', 0);
INSERT INTO `t_operation_log` VALUES (1281056112921776130, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10&name=&ipAddr=114.238.51.181&operationUserName=', '114.238.51.181', 'admin', 1, '2020-07-09 10:42:33', 0);
INSERT INTO `t_operation_log` VALUES (1281056144089649153, '查看日志分页列表', '/api/operationLog/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 10:42:40', 0);
INSERT INTO `t_operation_log` VALUES (1281067845254090754, '获取登录用户信息', '/api/getLoginInfo', NULL, '114.238.51.181', 'admin', 1, '2020-07-09 11:29:10', 0);
INSERT INTO `t_operation_log` VALUES (1281067845920985090, '获取登录用户信息', '/api/getLoginInfo', NULL, '114.238.51.181', 'admin', 1, '2020-07-09 11:29:10', 0);
INSERT INTO `t_operation_log` VALUES (1281067961541169154, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 11:29:38', 0);
INSERT INTO `t_operation_log` VALUES (1281067966104571906, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 11:29:39', 0);
INSERT INTO `t_operation_log` VALUES (1281067996253229058, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '114.238.51.181', 'admin', 1, '2020-07-09 11:29:46', 0);
INSERT INTO `t_operation_log` VALUES (1281123079212244994, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-09 15:08:39', 0);
INSERT INTO `t_operation_log` VALUES (1281123080009162754, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-09 15:08:39', 0);
INSERT INTO `t_operation_log` VALUES (1281123103258189826, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-09 15:08:45', 0);
INSERT INTO `t_operation_log` VALUES (1281123170920701953, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-09 15:09:01', 0);
INSERT INTO `t_operation_log` VALUES (1281428606291030017, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 11:22:42', 0);
INSERT INTO `t_operation_log` VALUES (1281428607117307905, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 11:22:42', 0);
INSERT INTO `t_operation_log` VALUES (1281428616407691266, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:22:45', 0);
INSERT INTO `t_operation_log` VALUES (1281429157187694593, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:24:54', 0);
INSERT INTO `t_operation_log` VALUES (1281429206621761538, '添加数据字典类型', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 11:25:05', 0);
INSERT INTO `t_operation_log` VALUES (1281429212929994753, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:25:07', 0);
INSERT INTO `t_operation_log` VALUES (1281429219313725442, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:25:08', 0);
INSERT INTO `t_operation_log` VALUES (1281429522385743873, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:26:21', 0);
INSERT INTO `t_operation_log` VALUES (1281430351943577601, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:29:38', 0);
INSERT INTO `t_operation_log` VALUES (1281430379747618818, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 11:29:45', 0);
INSERT INTO `t_operation_log` VALUES (1281466742727520257, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 13:54:15', 0);
INSERT INTO `t_operation_log` VALUES (1281466743373443074, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 13:54:15', 0);
INSERT INTO `t_operation_log` VALUES (1281466894389358593, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 13:54:51', 0);
INSERT INTO `t_operation_log` VALUES (1281468111496687617, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 13:59:41', 0);
INSERT INTO `t_operation_log` VALUES (1281468118308237313, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 13:59:43', 0);
INSERT INTO `t_operation_log` VALUES (1281471313206063105, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:12:24', 0);
INSERT INTO `t_operation_log` VALUES (1281471337780490242, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:12:30', 0);
INSERT INTO `t_operation_log` VALUES (1281471345388957697, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1281471549479596034, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:13:21', 0);
INSERT INTO `t_operation_log` VALUES (1281471553707454465, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:13:22', 0);
INSERT INTO `t_operation_log` VALUES (1281471571059290113, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:13:26', 0);
INSERT INTO `t_operation_log` VALUES (1281471590868987906, '角色授权', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:13:31', 0);
INSERT INTO `t_operation_log` VALUES (1281471595906347010, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:13:32', 0);
INSERT INTO `t_operation_log` VALUES (1281471624264036354, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:13:38', 0);
INSERT INTO `t_operation_log` VALUES (1281471624717021186, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:13:39', 0);
INSERT INTO `t_operation_log` VALUES (1281471692962541569, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:13:55', 0);
INSERT INTO `t_operation_log` VALUES (1281471693306474498, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:13:55', 0);
INSERT INTO `t_operation_log` VALUES (1281471715259461634, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:00', 0);
INSERT INTO `t_operation_log` VALUES (1281471723849396225, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:02', 0);
INSERT INTO `t_operation_log` VALUES (1281471767017172993, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:13', 0);
INSERT INTO `t_operation_log` VALUES (1281471841499623426, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:30', 0);
INSERT INTO `t_operation_log` VALUES (1281471852237041665, '查看权限菜单详情', '/api/permission/detail/1281468111949672449', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:14:33', 0);
INSERT INTO `t_operation_log` VALUES (1281471880724754433, '编辑权限菜单', '/api/permission/edit/1281468111949672449', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:14:40', 0);
INSERT INTO `t_operation_log` VALUES (1281471886240264194, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:41', 0);
INSERT INTO `t_operation_log` VALUES (1281471923108196354, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:14:50', 0);
INSERT INTO `t_operation_log` VALUES (1281471923691204609, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:14:50', 0);
INSERT INTO `t_operation_log` VALUES (1281471934634143745, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:52', 0);
INSERT INTO `t_operation_log` VALUES (1281471956343861250, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:14:58', 0);
INSERT INTO `t_operation_log` VALUES (1281471993278902273, '角色授权', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:15:06', 0);
INSERT INTO `t_operation_log` VALUES (1281472002787389441, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:09', 0);
INSERT INTO `t_operation_log` VALUES (1281472007950577666, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:10', 0);
INSERT INTO `t_operation_log` VALUES (1281472088577683458, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:29', 0);
INSERT INTO `t_operation_log` VALUES (1281472106281840642, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:33', 0);
INSERT INTO `t_operation_log` VALUES (1281472115756773378, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:36', 0);
INSERT INTO `t_operation_log` VALUES (1281472123553984513, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:38', 0);
INSERT INTO `t_operation_log` VALUES (1281472137667817473, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:41', 0);
INSERT INTO `t_operation_log` VALUES (1281472176356077570, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:50', 0);
INSERT INTO `t_operation_log` VALUES (1281472188184014850, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:15:53', 0);
INSERT INTO `t_operation_log` VALUES (1281472358313373697, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:16:33', 0);
INSERT INTO `t_operation_log` VALUES (1281472363656916993, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:16:35', 0);
INSERT INTO `t_operation_log` VALUES (1281472373320593410, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:16:37', 0);
INSERT INTO `t_operation_log` VALUES (1281472392798941185, '角色授权', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:16:42', 0);
INSERT INTO `t_operation_log` VALUES (1281472397534310401, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:16:43', 0);
INSERT INTO `t_operation_log` VALUES (1281472405788700674, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:16:45', 0);
INSERT INTO `t_operation_log` VALUES (1281472405868392450, '数据字典全部列表', '/api/dictionary/list', 'type=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:16:45', 0);
INSERT INTO `t_operation_log` VALUES (1281472630494343169, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:17:38', 0);
INSERT INTO `t_operation_log` VALUES (1281472630544674818, '数据字典全部列表', '/api/dictionary/list', 'type=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:17:38', 0);
INSERT INTO `t_operation_log` VALUES (1281472900687212545, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:18:43', 0);
INSERT INTO `t_operation_log` VALUES (1281472900695601153, '数据字典全部列表', '/api/dictionary/list', 'type=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:18:43', 0);
INSERT INTO `t_operation_log` VALUES (1281472912934580226, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1281472913198821377, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1281472913525977089, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1281473153708601345, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:19:43', 0);
INSERT INTO `t_operation_log` VALUES (1281473153725378562, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:19:43', 0);
INSERT INTO `t_operation_log` VALUES (1281473353240031234, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:20:31', 0);
INSERT INTO `t_operation_log` VALUES (1281473353240031235, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:20:31', 0);
INSERT INTO `t_operation_log` VALUES (1281473426917175297, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:20:48', 0);
INSERT INTO `t_operation_log` VALUES (1281473434538225665, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:20:50', 0);
INSERT INTO `t_operation_log` VALUES (1281473563341107202, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:21', 0);
INSERT INTO `t_operation_log` VALUES (1281473567246004226, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:22', 0);
INSERT INTO `t_operation_log` VALUES (1281473570542727170, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:23', 0);
INSERT INTO `t_operation_log` VALUES (1281473571335450625, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:23', 0);
INSERT INTO `t_operation_log` VALUES (1281473572144951298, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:23', 0);
INSERT INTO `t_operation_log` VALUES (1281473572832817154, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:23', 0);
INSERT INTO `t_operation_log` VALUES (1281473581326282753, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:21:25', 0);
INSERT INTO `t_operation_log` VALUES (1281473581674409985, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:21:25', 0);
INSERT INTO `t_operation_log` VALUES (1281473832367960065, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:22:25', 0);
INSERT INTO `t_operation_log` VALUES (1281473832837722114, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:22:25', 0);
INSERT INTO `t_operation_log` VALUES (1281473902605774850, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:22:42', 0);
INSERT INTO `t_operation_log` VALUES (1281473925305348098, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:22:47', 0);
INSERT INTO `t_operation_log` VALUES (1281473964253655041, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:22:56', 0);
INSERT INTO `t_operation_log` VALUES (1281473990723907586, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:03', 0);
INSERT INTO `t_operation_log` VALUES (1281474036676702209, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:14', 0);
INSERT INTO `t_operation_log` VALUES (1281474044582965249, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:16', 0);
INSERT INTO `t_operation_log` VALUES (1281474052560531457, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:17', 0);
INSERT INTO `t_operation_log` VALUES (1281474096080629762, '查看权限菜单分页列表', '/api/permission/page', 'current=4&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:28', 0);
INSERT INTO `t_operation_log` VALUES (1281474152275914753, '查看权限菜单分页列表', '/api/permission/page', 'current=3&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:23:41', 0);
INSERT INTO `t_operation_log` VALUES (1281474355783544834, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:24:30', 0);
INSERT INTO `t_operation_log` VALUES (1281474360799932417, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:24:31', 0);
INSERT INTO `t_operation_log` VALUES (1281474484343156738, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:25:00', 0);
INSERT INTO `t_operation_log` VALUES (1281474489581842434, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:25:02', 0);
INSERT INTO `t_operation_log` VALUES (1281474496242397185, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:25:03', 0);
INSERT INTO `t_operation_log` VALUES (1281474515095793666, '角色授权', '/api/role/grant/1', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:25:08', 0);
INSERT INTO `t_operation_log` VALUES (1281474520988790786, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:25:09', 0);
INSERT INTO `t_operation_log` VALUES (1281474531881398274, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:25:12', 0);
INSERT INTO `t_operation_log` VALUES (1281474541805121537, '查看数据字典类型详情', '/api/dictionaryType/detail/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:25:14', 0);
INSERT INTO `t_operation_log` VALUES (1281474578052296706, '编辑数据字典类型', '/api/dictionaryType/edit/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:25:23', 0);
INSERT INTO `t_operation_log` VALUES (1281474691625660418, '编辑数据字典类型', '/api/dictionaryType/edit/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:25:50', 0);
INSERT INTO `t_operation_log` VALUES (1281475010677977090, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:27:06', 0);
INSERT INTO `t_operation_log` VALUES (1281475011013521409, '查看数据字典类型详情', '/api/dictionaryType/detail/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:27:06', 0);
INSERT INTO `t_operation_log` VALUES (1281475018517131266, '编辑数据字典类型', '/api/dictionaryType/edit/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:27:08', 0);
INSERT INTO `t_operation_log` VALUES (1281475023642570753, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:27:09', 0);
INSERT INTO `t_operation_log` VALUES (1281475089606389761, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:27:25', 0);
INSERT INTO `t_operation_log` VALUES (1281475089975488514, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:27:25', 0);
INSERT INTO `t_operation_log` VALUES (1281475098200518658, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:27:27', 0);
INSERT INTO `t_operation_log` VALUES (1281475098548645889, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:27:27', 0);
INSERT INTO `t_operation_log` VALUES (1281475275363725313, '查看数据字典类型详情', '/api/dictionaryType/detail/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:28:09', 0);
INSERT INTO `t_operation_log` VALUES (1281475307676643330, '编辑数据字典类型', '/api/dictionaryType/edit/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:28:17', 0);
INSERT INTO `t_operation_log` VALUES (1281475312168742913, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:28:18', 0);
INSERT INTO `t_operation_log` VALUES (1281475718890401793, '添加数据字典类型', '/api/dictionaryType/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:29:55', 0);
INSERT INTO `t_operation_log` VALUES (1281475723793543170, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:29:56', 0);
INSERT INTO `t_operation_log` VALUES (1281475772288086018, '删除数据字典类型', '/api/dictionaryType/delete/1281429207548702721', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:30:07', 0);
INSERT INTO `t_operation_log` VALUES (1281475776268480514, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:30:08', 0);
INSERT INTO `t_operation_log` VALUES (1281476079126589441, '查看数据字典类型详情', '/api/dictionaryType/detail/1281475719016230914', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:31:21', 0);
INSERT INTO `t_operation_log` VALUES (1281476086072356866, '编辑数据字典类型', '/api/dictionaryType/edit/1281475719016230914', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:31:22', 0);
INSERT INTO `t_operation_log` VALUES (1281476516131115009, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:33:05', 0);
INSERT INTO `t_operation_log` VALUES (1281476526310690817, '查看数据字典类型详情', '/api/dictionaryType/detail/1281475719016230914', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:33:07', 0);
INSERT INTO `t_operation_log` VALUES (1281476534351171585, '编辑数据字典类型', '/api/dictionaryType/edit/1281475719016230914', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:33:09', 0);
INSERT INTO `t_operation_log` VALUES (1281476563577081857, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:33:16', 0);
INSERT INTO `t_operation_log` VALUES (1281476625845719042, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:33:31', 0);
INSERT INTO `t_operation_log` VALUES (1281476930641596417, '添加数据字典', '/api/dictionary/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:34:44', 0);
INSERT INTO `t_operation_log` VALUES (1281476934861066242, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:34:45', 0);
INSERT INTO `t_operation_log` VALUES (1281476946479288322, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:34:47', 0);
INSERT INTO `t_operation_log` VALUES (1281476946521231361, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:34:47', 0);
INSERT INTO `t_operation_log` VALUES (1281476989135360001, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:34:58', 0);
INSERT INTO `t_operation_log` VALUES (1281477036417748993, '查看数据字典详情', '/api/dictionary/detail/1281476930893254657', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:09', 0);
INSERT INTO `t_operation_log` VALUES (1281477073604448257, '编辑数据字典', '/api/dictionary/edit/1281476930893254657', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:18', 0);
INSERT INTO `t_operation_log` VALUES (1281477077861666818, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:19', 0);
INSERT INTO `t_operation_log` VALUES (1281477124653322242, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:30', 0);
INSERT INTO `t_operation_log` VALUES (1281477140826558465, '查看数据字典类型详情', '/api/dictionaryType/detail/1280381305585971201', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:34', 0);
INSERT INTO `t_operation_log` VALUES (1281477156962045954, '编辑数据字典类型', '/api/dictionaryType/edit/1280381305585971201', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:38', 0);
INSERT INTO `t_operation_log` VALUES (1281477161273790465, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:39', 0);
INSERT INTO `t_operation_log` VALUES (1281477173739261953, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:42', 0);
INSERT INTO `t_operation_log` VALUES (1281477181880406018, '查看数据字典详情', '/api/dictionary/detail/1280387705410203650', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:44', 0);
INSERT INTO `t_operation_log` VALUES (1281477191497945089, '编辑数据字典', '/api/dictionary/edit/1280387705410203650', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:46', 0);
INSERT INTO `t_operation_log` VALUES (1281477195738386433, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:47', 0);
INSERT INTO `t_operation_log` VALUES (1281477206769405954, '查看数据字典详情', '/api/dictionary/detail/1280387774683328514', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:49', 0);
INSERT INTO `t_operation_log` VALUES (1281477220778381314, '编辑数据字典', '/api/dictionary/edit/1280387774683328514', NULL, '192.168.0.12', 'admin', 1, '2020-07-10 14:35:53', 0);
INSERT INTO `t_operation_log` VALUES (1281477224788135937, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:35:54', 0);
INSERT INTO `t_operation_log` VALUES (1281477274406752258, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:06', 0);
INSERT INTO `t_operation_log` VALUES (1281477281163776001, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:07', 0);
INSERT INTO `t_operation_log` VALUES (1281477281163776002, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:07', 0);
INSERT INTO `t_operation_log` VALUES (1281477285706207233, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:08', 0);
INSERT INTO `t_operation_log` VALUES (1281477288277315585, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:09', 0);
INSERT INTO `t_operation_log` VALUES (1281477310121250817, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:14', 0);
INSERT INTO `t_operation_log` VALUES (1281477310121250818, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:14', 0);
INSERT INTO `t_operation_log` VALUES (1281477347135983618, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-10 14:36:23', 0);
INSERT INTO `t_operation_log` VALUES (1282851480033837057, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:36:42', 0);
INSERT INTO `t_operation_log` VALUES (1282851480822366209, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:36:42', 0);
INSERT INTO `t_operation_log` VALUES (1282851491345874945, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:36:44', 0);
INSERT INTO `t_operation_log` VALUES (1282851503400304642, '查看角色全部列表', '/api/role/list', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:36:47', 0);
INSERT INTO `t_operation_log` VALUES (1282851503706488833, '查看用户详情', '/api/sysUser/detail/1278607837261471746', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:36:47', 0);
INSERT INTO `t_operation_log` VALUES (1282851607679090689, '编辑用户', '/api/sysUser/edit/1278607837261471746', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:37:12', 0);
INSERT INTO `t_operation_log` VALUES (1282851614197039106, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:37:14', 0);
INSERT INTO `t_operation_log` VALUES (1282851760804741122, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:37:49', 0);
INSERT INTO `t_operation_log` VALUES (1282851776814399489, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:37:52', 0);
INSERT INTO `t_operation_log` VALUES (1282851777003143169, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:37:53', 0);
INSERT INTO `t_operation_log` VALUES (1282851806375854082, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:38:00', 0);
INSERT INTO `t_operation_log` VALUES (1282851808120684545, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:38:00', 0);
INSERT INTO `t_operation_log` VALUES (1282851886566752258, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:38:19', 0);
INSERT INTO `t_operation_log` VALUES (1282851886986182658, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:38:19', 0);
INSERT INTO `t_operation_log` VALUES (1282851930128793602, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:38:29', 0);
INSERT INTO `t_operation_log` VALUES (1282852133653200897, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-14 09:39:18', 0);
INSERT INTO `t_operation_log` VALUES (1282852134068436994, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-14 09:39:18', 0);
INSERT INTO `t_operation_log` VALUES (1283567114292146177, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:00:22', 0);
INSERT INTO `t_operation_log` VALUES (1283567115256836098, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:00:22', 0);
INSERT INTO `t_operation_log` VALUES (1283567129198702594, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:00:26', 0);
INSERT INTO `t_operation_log` VALUES (1283567444245458945, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:01:41', 0);
INSERT INTO `t_operation_log` VALUES (1283568146963345410, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:04:28', 0);
INSERT INTO `t_operation_log` VALUES (1283568165032407042, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:04:33', 0);
INSERT INTO `t_operation_log` VALUES (1283568170682134529, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:04:34', 0);
INSERT INTO `t_operation_log` VALUES (1283568219973595138, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:04:46', 0);
INSERT INTO `t_operation_log` VALUES (1283569006820831233, '添加数据字典', '/api/dictionary/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:07:53', 0);
INSERT INTO `t_operation_log` VALUES (1283569012898377730, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:07:55', 0);
INSERT INTO `t_operation_log` VALUES (1283573566037667841, '查看数据字典详情', '/api/dictionary/detail/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:26:00', 0);
INSERT INTO `t_operation_log` VALUES (1283573587361509377, '编辑数据字典', '/api/dictionary/edit/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:26:06', 0);
INSERT INTO `t_operation_log` VALUES (1283573591929106434, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:26:07', 0);
INSERT INTO `t_operation_log` VALUES (1283573622191009793, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:26:14', 0);
INSERT INTO `t_operation_log` VALUES (1283573622748852226, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:26:14', 0);
INSERT INTO `t_operation_log` VALUES (1283573638401994754, '查看数据字典详情', '/api/dictionary/detail/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:26:18', 0);
INSERT INTO `t_operation_log` VALUES (1283573693250908161, '编辑数据字典', '/api/dictionary/edit/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:26:31', 0);
INSERT INTO `t_operation_log` VALUES (1283573701392052226, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:26:33', 0);
INSERT INTO `t_operation_log` VALUES (1283573861379584002, '查看数据字典详情', '/api/dictionary/detail/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:27:11', 0);
INSERT INTO `t_operation_log` VALUES (1283573877783506946, '编辑数据字典', '/api/dictionary/edit/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:27:15', 0);
INSERT INTO `t_operation_log` VALUES (1283573881499660289, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:27:16', 0);
INSERT INTO `t_operation_log` VALUES (1283574104674381826, '查看数据字典详情', '/api/dictionary/detail/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:28:09', 0);
INSERT INTO `t_operation_log` VALUES (1283574124953841665, '编辑数据字典', '/api/dictionary/edit/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:28:14', 0);
INSERT INTO `t_operation_log` VALUES (1283574555301920769, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:29:56', 0);
INSERT INTO `t_operation_log` VALUES (1283574566035144706, '查看数据字典详情', '/api/dictionary/detail/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:29:59', 0);
INSERT INTO `t_operation_log` VALUES (1283574592425705473, '编辑数据字典', '/api/dictionary/edit/1283569008632770562', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 09:30:05', 0);
INSERT INTO `t_operation_log` VALUES (1283574597282709505, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 09:30:06', 0);
INSERT INTO `t_operation_log` VALUES (1283582506641190913, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:01:32', 0);
INSERT INTO `t_operation_log` VALUES (1283582507454885890, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:01:32', 0);
INSERT INTO `t_operation_log` VALUES (1283582523510681602, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:01:36', 0);
INSERT INTO `t_operation_log` VALUES (1283582535468642306, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:01:39', 0);
INSERT INTO `t_operation_log` VALUES (1283582535699329026, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:01:39', 0);
INSERT INTO `t_operation_log` VALUES (1283582543727226881, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:01:41', 0);
INSERT INTO `t_operation_log` VALUES (1283582573083160578, '查看权限菜单详情', '/api/permission/detail/2', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:01:48', 0);
INSERT INTO `t_operation_log` VALUES (1283582594813849602, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:01:53', 0);
INSERT INTO `t_operation_log` VALUES (1283582734324789249, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:02:26', 0);
INSERT INTO `t_operation_log` VALUES (1283582734517727234, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:02:26', 0);
INSERT INTO `t_operation_log` VALUES (1283582734723248130, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:02:26', 0);
INSERT INTO `t_operation_log` VALUES (1283582746685403138, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:02:29', 0);
INSERT INTO `t_operation_log` VALUES (1283582746874146817, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:02:29', 0);
INSERT INTO `t_operation_log` VALUES (1283582747046113282, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:02:29', 0);
INSERT INTO `t_operation_log` VALUES (1283583002869297154, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:30', 0);
INSERT INTO `t_operation_log` VALUES (1283583002877685761, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:30', 0);
INSERT INTO `t_operation_log` VALUES (1283583015317991425, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1283583015691284482, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1283583016194600962, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:34', 0);
INSERT INTO `t_operation_log` VALUES (1283583121035423746, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:03:59', 0);
INSERT INTO `t_operation_log` VALUES (1283583121236750338, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:59', 0);
INSERT INTO `t_operation_log` VALUES (1283583121496797186, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:03:59', 0);
INSERT INTO `t_operation_log` VALUES (1283586044813438978, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:15:36', 0);
INSERT INTO `t_operation_log` VALUES (1283586044813438979, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:15:36', 0);
INSERT INTO `t_operation_log` VALUES (1283586150027554818, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:16:01', 0);
INSERT INTO `t_operation_log` VALUES (1283586150027554819, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:16:01', 0);
INSERT INTO `t_operation_log` VALUES (1283586179249270785, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:16:08', 0);
INSERT INTO `t_operation_log` VALUES (1283586333696126977, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:16:44', 0);
INSERT INTO `t_operation_log` VALUES (1283586333847121922, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:16:45', 0);
INSERT INTO `t_operation_log` VALUES (1283586334245580802, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:16:45', 0);
INSERT INTO `t_operation_log` VALUES (1283586501451485185, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:17:24', 0);
INSERT INTO `t_operation_log` VALUES (1283586525208023042, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:17:30', 0);
INSERT INTO `t_operation_log` VALUES (1283586525526790145, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:17:30', 0);
INSERT INTO `t_operation_log` VALUES (1283586525640036353, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:17:30', 0);
INSERT INTO `t_operation_log` VALUES (1283586751478140929, '添加权限菜单', '/api/permission/add', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:18:24', 0);
INSERT INTO `t_operation_log` VALUES (1283586758109335554, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:18:26', 0);
INSERT INTO `t_operation_log` VALUES (1283586776270671873, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:18:30', 0);
INSERT INTO `t_operation_log` VALUES (1283586794352316418, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:18:34', 0);
INSERT INTO `t_operation_log` VALUES (1283586857652752385, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 10:18:49', 0);
INSERT INTO `t_operation_log` VALUES (1283586857858273282, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 10:18:49', 0);
INSERT INTO `t_operation_log` VALUES (1283586858067988481, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 10:18:50', 0);
INSERT INTO `t_operation_log` VALUES (1283717407038251010, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-16 18:57:35', 0);
INSERT INTO `t_operation_log` VALUES (1283717407038251011, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.12', 'admin', 1, '2020-07-16 18:57:35', 0);
INSERT INTO `t_operation_log` VALUES (1283717408070049793, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 18:57:35', 0);
INSERT INTO `t_operation_log` VALUES (1283717414248259586, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-16 18:57:37', 0);
INSERT INTO `t_operation_log` VALUES (1283999704849326081, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:39:20', 0);
INSERT INTO `t_operation_log` VALUES (1283999706178920449, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:39:20', 0);
INSERT INTO `t_operation_log` VALUES (1283999725401415681, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 13:39:25', 0);
INSERT INTO `t_operation_log` VALUES (1283999878120218626, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 13:40:01', 0);
INSERT INTO `t_operation_log` VALUES (1283999884336177153, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 13:40:03', 0);
INSERT INTO `t_operation_log` VALUES (1283999890761850881, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:40:04', 0);
INSERT INTO `t_operation_log` VALUES (1284002932089434114, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:52:09', 0);
INSERT INTO `t_operation_log` VALUES (1284002932089434115, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:52:09', 0);
INSERT INTO `t_operation_log` VALUES (1284003956908896258, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 13:56:14', 0);
INSERT INTO `t_operation_log` VALUES (1284003964253122561, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 13:56:15', 0);
INSERT INTO `t_operation_log` VALUES (1284003972373295106, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:56:17', 0);
INSERT INTO `t_operation_log` VALUES (1284004204100202497, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 13:57:13', 0);
INSERT INTO `t_operation_log` VALUES (1284010888189194241, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:23:46', 0);
INSERT INTO `t_operation_log` VALUES (1284010888784785409, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:23:46', 0);
INSERT INTO `t_operation_log` VALUES (1284010899333459969, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 14:23:49', 0);
INSERT INTO `t_operation_log` VALUES (1284010905096433665, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-17 14:23:50', 0);
INSERT INTO `t_operation_log` VALUES (1284010911715045377, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:23:52', 0);
INSERT INTO `t_operation_log` VALUES (1284011139868405762, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:24:46', 0);
INSERT INTO `t_operation_log` VALUES (1284011140136841218, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:24:46', 0);
INSERT INTO `t_operation_log` VALUES (1284011194155282434, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:24:59', 0);
INSERT INTO `t_operation_log` VALUES (1284011194385969153, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:24:59', 0);
INSERT INTO `t_operation_log` VALUES (1284011372736163841, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:25:42', 0);
INSERT INTO `t_operation_log` VALUES (1284011372912324609, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:25:42', 0);
INSERT INTO `t_operation_log` VALUES (1284011807026982913, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:27:25', 0);
INSERT INTO `t_operation_log` VALUES (1284011807060537345, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:27:25', 0);
INSERT INTO `t_operation_log` VALUES (1284011846285668354, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:27:35', 0);
INSERT INTO `t_operation_log` VALUES (1284011846663155714, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-17 14:27:35', 0);
INSERT INTO `t_operation_log` VALUES (1285097196449968130, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:20:22', 0);
INSERT INTO `t_operation_log` VALUES (1285097196995227650, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:20:22', 0);
INSERT INTO `t_operation_log` VALUES (1285097207111888898, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:20:25', 0);
INSERT INTO `t_operation_log` VALUES (1285097219157929986, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:20:28', 0);
INSERT INTO `t_operation_log` VALUES (1285097226577653762, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:20:29', 0);
INSERT INTO `t_operation_log` VALUES (1285098161680953345, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:24:12', 0);
INSERT INTO `t_operation_log` VALUES (1285098161869697026, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:24:12', 0);
INSERT INTO `t_operation_log` VALUES (1285098715882729473, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:26:25', 0);
INSERT INTO `t_operation_log` VALUES (1285098716335714306, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:26:25', 0);
INSERT INTO `t_operation_log` VALUES (1285099422711033858, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:29:13', 0);
INSERT INTO `t_operation_log` VALUES (1285099422862028801, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:29:13', 0);
INSERT INTO `t_operation_log` VALUES (1285100980186132481, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:35:24', 0);
INSERT INTO `t_operation_log` VALUES (1285100980186132482, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:35:24', 0);
INSERT INTO `t_operation_log` VALUES (1285101048976912386, '编辑权限菜单', '/api/permission/edit/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:35:41', 0);
INSERT INTO `t_operation_log` VALUES (1285101289025318914, '编辑权限菜单', '/api/permission/edit/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:36:38', 0);
INSERT INTO `t_operation_log` VALUES (1285101680601346050, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:11', 0);
INSERT INTO `t_operation_log` VALUES (1285101680639094785, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:38:11', 0);
INSERT INTO `t_operation_log` VALUES (1285101702986346497, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:38:17', 0);
INSERT INTO `t_operation_log` VALUES (1285101710930358274, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:19', 0);
INSERT INTO `t_operation_log` VALUES (1285101724230496257, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:22', 0);
INSERT INTO `t_operation_log` VALUES (1285101724268244994, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:22', 0);
INSERT INTO `t_operation_log` VALUES (1285101750335844354, '编辑权限菜单', '/api/permission/edit/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:28', 0);
INSERT INTO `t_operation_log` VALUES (1285101808791859201, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:38:42', 0);
INSERT INTO `t_operation_log` VALUES (1285101817658617857, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:38:44', 0);
INSERT INTO `t_operation_log` VALUES (1285101824734408706, '查看权限菜单详情', '/api/permission/detail/1283586752459608065', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:38:46', 0);
INSERT INTO `t_operation_log` VALUES (1285101979651026945, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:39:23', 0);
INSERT INTO `t_operation_log` VALUES (1285102038132207618, '查看权限菜单分页列表', '/api/permission/page', 'current=5&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:39:37', 0);
INSERT INTO `t_operation_log` VALUES (1285102216281075714, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:40:19', 0);
INSERT INTO `t_operation_log` VALUES (1285102216687923201, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:40:19', 0);
INSERT INTO `t_operation_log` VALUES (1285102298204221441, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:40:39', 0);
INSERT INTO `t_operation_log` VALUES (1285102364096737282, '编辑权限菜单', '/api/permission/edit/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:40:54', 0);
INSERT INTO `t_operation_log` VALUES (1285102370908286978, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:40:56', 0);
INSERT INTO `t_operation_log` VALUES (1285102387849080833, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 14:41:00', 0);
INSERT INTO `t_operation_log` VALUES (1285102388385951746, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-20 14:41:00', 0);
INSERT INTO `t_operation_log` VALUES (1285113096376971266, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:23:33', 0);
INSERT INTO `t_operation_log` VALUES (1285113097329078273, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:23:33', 0);
INSERT INTO `t_operation_log` VALUES (1285114732944039938, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:30:03', 0);
INSERT INTO `t_operation_log` VALUES (1285114733229252609, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:30:03', 0);
INSERT INTO `t_operation_log` VALUES (1285114874384355329, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:30:37', 0);
INSERT INTO `t_operation_log` VALUES (1285114875311296514, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-20 15:30:37', 0);
INSERT INTO `t_operation_log` VALUES (1285467281790341121, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:50:57', 0);
INSERT INTO `t_operation_log` VALUES (1285467282541121538, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:50:58', 0);
INSERT INTO `t_operation_log` VALUES (1285467532878155777, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:51:57', 0);
INSERT INTO `t_operation_log` VALUES (1285467533645713409, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:51:58', 0);
INSERT INTO `t_operation_log` VALUES (1285467541585530881, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 14:51:59', 0);
INSERT INTO `t_operation_log` VALUES (1285467553526714370, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 14:52:02', 0);
INSERT INTO `t_operation_log` VALUES (1285468903081775105, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:57:24', 0);
INSERT INTO `t_operation_log` VALUES (1285468903635423234, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 14:57:24', 0);
INSERT INTO `t_operation_log` VALUES (1285468913018081281, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 14:57:26', 0);
INSERT INTO `t_operation_log` VALUES (1285468999131336705, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:57:47', 0);
INSERT INTO `t_operation_log` VALUES (1285468999726927874, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:57:47', 0);
INSERT INTO `t_operation_log` VALUES (1285469009541599234, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:57:49', 0);
INSERT INTO `t_operation_log` VALUES (1285469010128801794, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:57:50', 0);
INSERT INTO `t_operation_log` VALUES (1285469204144721921, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:58:36', 0);
INSERT INTO `t_operation_log` VALUES (1285469204669009921, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:58:36', 0);
INSERT INTO `t_operation_log` VALUES (1285469332549144577, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:59:06', 0);
INSERT INTO `t_operation_log` VALUES (1285469333065043970, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 14:59:07', 0);
INSERT INTO `t_operation_log` VALUES (1285469629673639938, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:00:17', 0);
INSERT INTO `t_operation_log` VALUES (1285469630214705153, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:00:17', 0);
INSERT INTO `t_operation_log` VALUES (1285469823974772737, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:04', 0);
INSERT INTO `t_operation_log` VALUES (1285469824524226562, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:04', 0);
INSERT INTO `t_operation_log` VALUES (1285469845583826945, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:09', 0);
INSERT INTO `t_operation_log` VALUES (1285469846095532033, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:09', 0);
INSERT INTO `t_operation_log` VALUES (1285469899921035265, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:22', 0);
INSERT INTO `t_operation_log` VALUES (1285469900411768833, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:01:22', 0);
INSERT INTO `t_operation_log` VALUES (1285470419725324289, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:26', 0);
INSERT INTO `t_operation_log` VALUES (1285470420291555330, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:26', 0);
INSERT INTO `t_operation_log` VALUES (1285470444673044481, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:32', 0);
INSERT INTO `t_operation_log` VALUES (1285470445281218561, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:32', 0);
INSERT INTO `t_operation_log` VALUES (1285470450536681474, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1285470451593646081, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:33', 0);
INSERT INTO `t_operation_log` VALUES (1285470456245129218, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:34', 0);
INSERT INTO `t_operation_log` VALUES (1285470456794583042, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:34', 0);
INSERT INTO `t_operation_log` VALUES (1285470465103499265, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:36', 0);
INSERT INTO `t_operation_log` VALUES (1285470465803948033, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:37', 0);
INSERT INTO `t_operation_log` VALUES (1285470471218794497, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:38', 0);
INSERT INTO `t_operation_log` VALUES (1285470471881494529, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:38', 0);
INSERT INTO `t_operation_log` VALUES (1285470515527421953, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:48', 0);
INSERT INTO `t_operation_log` VALUES (1285470516106235906, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:03:49', 0);
INSERT INTO `t_operation_log` VALUES (1285470646343569410, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 15:04:20', 0);
INSERT INTO `t_operation_log` VALUES (1285470663070453762, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:04:24', 0);
INSERT INTO `t_operation_log` VALUES (1285471052561911809, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:05:56', 0);
INSERT INTO `t_operation_log` VALUES (1285471063139946497, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:05:59', 0);
INSERT INTO `t_operation_log` VALUES (1285471063257387009, '查看权限菜单详情', '/api/permission/detail/7', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 15:05:59', 0);
INSERT INTO `t_operation_log` VALUES (1285471386889883649, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 15:07:16', 0);
INSERT INTO `t_operation_log` VALUES (1285496173649469441, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 16:45:46', 0);
INSERT INTO `t_operation_log` VALUES (1285496174853234689, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 16:45:46', 0);
INSERT INTO `t_operation_log` VALUES (1285496266599440386, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:46:08', 0);
INSERT INTO `t_operation_log` VALUES (1285496456840486914, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 16:46:53', 0);
INSERT INTO `t_operation_log` VALUES (1285496457457049601, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 16:46:53', 0);
INSERT INTO `t_operation_log` VALUES (1285496491942617090, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:47:02', 0);
INSERT INTO `t_operation_log` VALUES (1285496496204029954, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:47:03', 0);
INSERT INTO `t_operation_log` VALUES (1285496518597419010, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:47:08', 0);
INSERT INTO `t_operation_log` VALUES (1285496527858442242, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:47:10', 0);
INSERT INTO `t_operation_log` VALUES (1285496533533335553, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:47:12', 0);
INSERT INTO `t_operation_log` VALUES (1285497549330849794, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:51:14', 0);
INSERT INTO `t_operation_log` VALUES (1285497567609626626, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 16:51:18', 0);
INSERT INTO `t_operation_log` VALUES (1285500599332249602, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 17:03:21', 0);
INSERT INTO `t_operation_log` VALUES (1285500599751680002, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 17:03:21', 0);
INSERT INTO `t_operation_log` VALUES (1285500723299098625, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 17:03:51', 0);
INSERT INTO `t_operation_log` VALUES (1285500739778519041, '查看权限菜单详情', '/api/permission/detail/2', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 17:03:54', 0);
INSERT INTO `t_operation_log` VALUES (1285500769478385666, '编辑权限菜单', '/api/permission/edit/2', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 17:04:02', 0);
INSERT INTO `t_operation_log` VALUES (1285500776214437889, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 17:04:03', 0);
INSERT INTO `t_operation_log` VALUES (1285500791871774722, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.12', 'admin', 1, '2020-07-21 17:04:07', 0);
INSERT INTO `t_operation_log` VALUES (1285500792429617153, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.12', 'admin', 1, '2020-07-21 17:04:07', 0);
INSERT INTO `t_operation_log` VALUES (1287918298124795905, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:10:25', 0);
INSERT INTO `t_operation_log` VALUES (1287918298670055426, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:10:25', 0);
INSERT INTO `t_operation_log` VALUES (1287918745967411202, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:12:12', 0);
INSERT INTO `t_operation_log` VALUES (1287918746265206786, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:12:12', 0);
INSERT INTO `t_operation_log` VALUES (1287919722187472898, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:16:05', 0);
INSERT INTO `t_operation_log` VALUES (1287919722632069121, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:16:05', 0);
INSERT INTO `t_operation_log` VALUES (1287920048995057666, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:17:23', 0);
INSERT INTO `t_operation_log` VALUES (1287920049284464641, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:17:23', 0);
INSERT INTO `t_operation_log` VALUES (1287920399051771906, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1287920399924187138, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:18:46', 0);
INSERT INTO `t_operation_log` VALUES (1287920407767535618, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:18:48', 0);
INSERT INTO `t_operation_log` VALUES (1287920408132440066, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:18:48', 0);
INSERT INTO `t_operation_log` VALUES (1287920517616357378, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:19:14', 0);
INSERT INTO `t_operation_log` VALUES (1287920517859627009, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:19:15', 0);
INSERT INTO `t_operation_log` VALUES (1287920616580960257, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:19:38', 0);
INSERT INTO `t_operation_log` VALUES (1287920616836812801, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.199', 'admin', 1, '2020-07-28 09:19:38', 0);
INSERT INTO `t_operation_log` VALUES (1308208086773616641, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:54:48', 0);
INSERT INTO `t_operation_log` VALUES (1308208087423733761, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:54:48', 0);
INSERT INTO `t_operation_log` VALUES (1308208100564488194, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:54:51', 0);
INSERT INTO `t_operation_log` VALUES (1308208103274008578, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:54:52', 0);
INSERT INTO `t_operation_log` VALUES (1308208117660471298, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:54:55', 0);
INSERT INTO `t_operation_log` VALUES (1308208120806199297, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:54:56', 0);
INSERT INTO `t_operation_log` VALUES (1308208520758251521, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:56:31', 0);
INSERT INTO `t_operation_log` VALUES (1308208521328676865, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:56:31', 0);
INSERT INTO `t_operation_log` VALUES (1308208526756106242, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:33', 0);
INSERT INTO `t_operation_log` VALUES (1308208528920367106, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:33', 0);
INSERT INTO `t_operation_log` VALUES (1308208530216407042, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:34', 0);
INSERT INTO `t_operation_log` VALUES (1308208535140519937, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:35', 0);
INSERT INTO `t_operation_log` VALUES (1308208536457531393, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:35', 0);
INSERT INTO `t_operation_log` VALUES (1308208536491085826, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:35', 0);
INSERT INTO `t_operation_log` VALUES (1308208537422221313, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:35', 0);
INSERT INTO `t_operation_log` VALUES (1308208538479185922, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:36', 0);
INSERT INTO `t_operation_log` VALUES (1308208542887399425, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:56:37', 0);
INSERT INTO `t_operation_log` VALUES (1308208543034200066, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:56:37', 0);
INSERT INTO `t_operation_log` VALUES (1308209353654751233, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:59:50', 0);
INSERT INTO `t_operation_log` VALUES (1308209354166456321, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 08:59:50', 0);
INSERT INTO `t_operation_log` VALUES (1308209391361544193, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:59:59', 0);
INSERT INTO `t_operation_log` VALUES (1308209393148317697, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 08:59:59', 0);
INSERT INTO `t_operation_log` VALUES (1308209396126273537, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:00', 0);
INSERT INTO `t_operation_log` VALUES (1308209397170655233, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:00', 0);
INSERT INTO `t_operation_log` VALUES (1308209398231814146, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:01', 0);
INSERT INTO `t_operation_log` VALUES (1308209400463183873, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:01', 0);
INSERT INTO `t_operation_log` VALUES (1308209402593890305, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:02', 0);
INSERT INTO `t_operation_log` VALUES (1308209403810238466, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:02', 0);
INSERT INTO `t_operation_log` VALUES (1308209405005615106, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:02', 0);
INSERT INTO `t_operation_log` VALUES (1308209407299899393, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:03', 0);
INSERT INTO `t_operation_log` VALUES (1308209408231034882, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:03', 0);
INSERT INTO `t_operation_log` VALUES (1308209409682264065, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:03', 0);
INSERT INTO `t_operation_log` VALUES (1308209410667925505, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:04', 0);
INSERT INTO `t_operation_log` VALUES (1308209631451893762, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:56', 0);
INSERT INTO `t_operation_log` VALUES (1308209634895417346, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:00:57', 0);
INSERT INTO `t_operation_log` VALUES (1308209638603182082, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:58', 0);
INSERT INTO `t_operation_log` VALUES (1308209639811141633, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:58', 0);
INSERT INTO `t_operation_log` VALUES (1308209640830357505, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:00:58', 0);
INSERT INTO `t_operation_log` VALUES (1308209960163692546, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:02:15', 0);
INSERT INTO `t_operation_log` VALUES (1308209960968998914, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:15', 0);
INSERT INTO `t_operation_log` VALUES (1308209966224461825, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:02:16', 0);
INSERT INTO `t_operation_log` VALUES (1308209966518063105, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:16', 0);
INSERT INTO `t_operation_log` VALUES (1308209972310396930, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:02:17', 0);
INSERT INTO `t_operation_log` VALUES (1308209973652574210, '数据字典分页列表', '/api/dictionary/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:18', 0);
INSERT INTO `t_operation_log` VALUES (1308210013964029954, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:02:27', 0);
INSERT INTO `t_operation_log` VALUES (1308210017302695937, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:28', 0);
INSERT INTO `t_operation_log` VALUES (1308210019559231489, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:29', 0);
INSERT INTO `t_operation_log` VALUES (1308210021060792321, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:29', 0);
INSERT INTO `t_operation_log` VALUES (1308210026228174849, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:02:30', 0);
INSERT INTO `t_operation_log` VALUES (1308210026702131202, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:02:30', 0);
INSERT INTO `t_operation_log` VALUES (1308210646133723137, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:04:58', 0);
INSERT INTO `t_operation_log` VALUES (1308210646565736450, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:04:58', 0);
INSERT INTO `t_operation_log` VALUES (1308210800559607810, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:05:35', 0);
INSERT INTO `t_operation_log` VALUES (1308210802988109826, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:05:35', 0);
INSERT INTO `t_operation_log` VALUES (1308210804305121281, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:05:36', 0);
INSERT INTO `t_operation_log` VALUES (1308210805500497921, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:05:36', 0);
INSERT INTO `t_operation_log` VALUES (1308210806523908097, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:05:36', 0);
INSERT INTO `t_operation_log` VALUES (1308210811376717826, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:05:37', 0);
INSERT INTO `t_operation_log` VALUES (1308210811884228610, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:05:38', 0);
INSERT INTO `t_operation_log` VALUES (1308211038166929409, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:06:32', 0);
INSERT INTO `t_operation_log` VALUES (1308211038573776897, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:06:32', 0);
INSERT INTO `t_operation_log` VALUES (1308211275539369985, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:07:28', 0);
INSERT INTO `t_operation_log` VALUES (1308211275962994690, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:07:28', 0);
INSERT INTO `t_operation_log` VALUES (1308211402622586881, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:07:58', 0);
INSERT INTO `t_operation_log` VALUES (1308211403092348930, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:07:59', 0);
INSERT INTO `t_operation_log` VALUES (1308211713240158210, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:12', 0);
INSERT INTO `t_operation_log` VALUES (1308211713642811393, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:13', 0);
INSERT INTO `t_operation_log` VALUES (1308211720810876929, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:14', 0);
INSERT INTO `t_operation_log` VALUES (1308211721234501633, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:14', 0);
INSERT INTO `t_operation_log` VALUES (1308211724409589761, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:15', 0);
INSERT INTO `t_operation_log` VALUES (1308211724950654978, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:15', 0);
INSERT INTO `t_operation_log` VALUES (1308211761822781441, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:24', 0);
INSERT INTO `t_operation_log` VALUES (1308211762313515010, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:24', 0);
INSERT INTO `t_operation_log` VALUES (1308211765002063873, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:25', 0);
INSERT INTO `t_operation_log` VALUES (1308211765471825922, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:25', 0);
INSERT INTO `t_operation_log` VALUES (1308211880890683394, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:52', 0);
INSERT INTO `t_operation_log` VALUES (1308211881402388481, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:53', 0);
INSERT INTO `t_operation_log` VALUES (1308211885890293762, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:54', 0);
INSERT INTO `t_operation_log` VALUES (1308211886410387458, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:09:54', 0);
INSERT INTO `t_operation_log` VALUES (1308212014512820226, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:10:24', 0);
INSERT INTO `t_operation_log` VALUES (1308212014949027842, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:10:24', 0);
INSERT INTO `t_operation_log` VALUES (1308212026537889794, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:10:27', 0);
INSERT INTO `t_operation_log` VALUES (1308212026999263234, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:10:27', 0);
INSERT INTO `t_operation_log` VALUES (1308212241684713473, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:18', 0);
INSERT INTO `t_operation_log` VALUES (1308212242221584386, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:19', 0);
INSERT INTO `t_operation_log` VALUES (1308212352254955521, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:45', 0);
INSERT INTO `t_operation_log` VALUES (1308212352678580226, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:45', 0);
INSERT INTO `t_operation_log` VALUES (1308212357552361474, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:46', 0);
INSERT INTO `t_operation_log` VALUES (1308212357996957697, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:46', 0);
INSERT INTO `t_operation_log` VALUES (1308212358840012801, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:46', 0);
INSERT INTO `t_operation_log` VALUES (1308212359490129922, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:11:47', 0);
INSERT INTO `t_operation_log` VALUES (1308212377055875074, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:11:51', 0);
INSERT INTO `t_operation_log` VALUES (1308212380402929666, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:11:52', 0);
INSERT INTO `t_operation_log` VALUES (1308212385343819777, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:11:53', 0);
INSERT INTO `t_operation_log` VALUES (1308212387269005314, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:11:53', 0);
INSERT INTO `t_operation_log` VALUES (1308212488662110210, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:12:17', 0);
INSERT INTO `t_operation_log` VALUES (1308212488901185537, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:17', 0);
INSERT INTO `t_operation_log` VALUES (1308212529720152066, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:12:27', 0);
INSERT INTO `t_operation_log` VALUES (1308212532849102850, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:28', 0);
INSERT INTO `t_operation_log` VALUES (1308212534782676993, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:28', 0);
INSERT INTO `t_operation_log` VALUES (1308212535948693505, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:29', 0);
INSERT INTO `t_operation_log` VALUES (1308212537483808769, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:29', 0);
INSERT INTO `t_operation_log` VALUES (1308212538503024641, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:12:29', 0);
INSERT INTO `t_operation_log` VALUES (1308212541510340610, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:30', 0);
INSERT INTO `t_operation_log` VALUES (1308212543469080577, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:30', 0);
INSERT INTO `t_operation_log` VALUES (1308212544668651522, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:31', 0);
INSERT INTO `t_operation_log` VALUES (1308212545704644610, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:31', 0);
INSERT INTO `t_operation_log` VALUES (1308212548439330817, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1308212549806673921, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1308212549840228353, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1308212551220154370, '数据字典全部列表', '/api/dictionary/list', 'typeCode=attachmentType', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1308212551228542977, '附件分页列表', '/api/attachment/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:32', 0);
INSERT INTO `t_operation_log` VALUES (1308212552512000002, '查看权限菜单分页列表', '/api/permission/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:33', 0);
INSERT INTO `t_operation_log` VALUES (1308212554617540609, '数据字典类型分页列表', '/api/dictionaryType/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:33', 0);
INSERT INTO `t_operation_log` VALUES (1308212556484005889, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:34', 0);
INSERT INTO `t_operation_log` VALUES (1308212557910069249, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:34', 0);
INSERT INTO `t_operation_log` VALUES (1308212558983811073, '查看用户分页列表', '/api/sysUser/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:34', 0);
INSERT INTO `t_operation_log` VALUES (1308212561307455490, '查看角色分页列表', '/api/role/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:35', 0);
INSERT INTO `t_operation_log` VALUES (1308212562335059969, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:35', 0);
INSERT INTO `t_operation_log` VALUES (1308212569477959681, '获取登录用户信息', '/api/getLoginInfo', NULL, '192.168.0.15', 'admin', 1, '2020-09-22 09:12:37', 0);
INSERT INTO `t_operation_log` VALUES (1308212569813504001, '查看部门分页列表', '/api/department/page', 'current=1&size=10', '192.168.0.15', 'admin', 1, '2020-09-22 09:12:37', 0);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(20) NOT NULL COMMENT '权限菜单ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限菜单名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限菜单Code',
  `parent_id` bigint(11) NULL DEFAULT NULL COMMENT '父级ID',
  `type` int(1) NOT NULL COMMENT '类型，0：根菜单，1：子菜单，2：接口',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `jump` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应页面地址',
  `level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `delete_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '主页', '/main', 0, 0, '/main', '/', NULL, NULL, 999, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (2, '系统管理', '/systemSetting', 0, 0, '/systemSetting', '', NULL, '', 0, 1, '2020-06-29 10:36:27', 1, '2020-07-21 17:04:02', 0, 0);
INSERT INTO `t_permission` VALUES (3, '用户管理', '/api/sysUser/manager', 0, 0, '/api/sysUser/manager', '/user/page', NULL, '', 4, 1, '2020-06-29 10:36:27', 1, '2020-07-07 14:32:09', 0, 0);
INSERT INTO `t_permission` VALUES (4, '角色管理', '/api/role/manager', 0, 0, '/api/role/manager', '/role/page', NULL, '', 3, 1, '2020-06-29 10:36:27', 1, '2020-07-07 14:32:15', 0, 0);
INSERT INTO `t_permission` VALUES (5, '部门管理', '/api/department/manager', 0, 0, '/api/department/manager', '/department/page', NULL, '', 2, 1, '2020-06-29 10:36:27', 1, '2020-07-07 14:32:23', 0, 0);
INSERT INTO `t_permission` VALUES (6, '菜单管理', '/api/permission/manager', 2, 1, '/api/permission/manager', '/permission/page', NULL, '', 1, 1, '2020-06-29 10:36:27', 1, '2020-07-07 14:32:32', 0, 0);
INSERT INTO `t_permission` VALUES (7, '日志管理', '/api/operationLog/manager', 2, 1, '/api/operationLog/manager', '/operationLog/page', NULL, '', 0, 1, '2020-06-29 10:36:27', 1, '2020-07-20 14:40:54', 0, 0);
INSERT INTO `t_permission` VALUES (8, '获取登录用户信息', '/api/getLoginInfo', 1, 2, '/api/getLoginInfo', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (9, '查看角色全部列表', '/api/role/list', 4, 2, '/api/role/list', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (10, '查看部门全部列表', '/api/department/list', 5, 2, '/api/department/list', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (11, '添加用户', '/api/sysUser/add', 3, 2, '/api/sysUser/add', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (12, '编辑用户', '/api/sysUser/edit', 3, 2, '/api/sysUser/edit', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (13, '删除用户', '/api/sysUser/delete', 3, 2, '/api/sysUser/delete', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (14, '查看用户详情', '/api/sysUser/detail', 3, 2, '/api/sysUser/detail', '', NULL, '', 0, 1, '2020-06-29 10:36:27', 1, '2020-07-03 09:58:59', 0, 0);
INSERT INTO `t_permission` VALUES (15, '添加部门', '/api/department/add', 5, 2, '/api/department/add', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (16, '编辑部门', '/api/department/edit', 5, 2, '/api/department/edit', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (17, '删除部门', '/api/department/delete', 5, 2, '/api/department/delete', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (18, '查询部门树', '/api/department/findDepTree', 5, 2, '/api/department/findDepTree', NULL, NULL, NULL, 0, 1, '2020-06-29 10:36:27', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (19, '查看部门详情', '/api/department/detail', 5, 2, '/api/department/detail', '', NULL, '', 0, 1, '2020-06-29 10:36:27', 1, '2020-07-03 09:59:15', 0, 0);
INSERT INTO `t_permission` VALUES (20, '添加权限菜单', '/api/permission/add', 6, 2, '/api/permission/add', NULL, NULL, NULL, 0, 1, '2020-07-03 09:44:18', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278867684703920130, '编辑权限菜单', '/api/permission/edit', 6, 2, '/api/permission/edit', '', NULL, '', 0, 1, '2020-07-03 09:46:31', 1, '2020-07-03 10:00:32', 0, 0);
INSERT INTO `t_permission` VALUES (1278867827100540929, '删除权限菜单', '/api/permission/delete', 6, 2, '/api/permission/delete', '', NULL, '', 0, 1, '2020-07-03 09:47:05', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278867963608358914, '查看权限菜单详情', '/api/permission/detail', 6, 2, '/api/permission/detail', '', NULL, '', 0, 1, '2020-07-03 09:47:37', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278874525479485442, '新增角色', '/api/role/add', 4, 2, '/api/role/add', '', NULL, '', 0, 1, '2020-07-03 10:13:42', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278874627455598593, '编辑角色', '/api/role/edit', 4, 2, '/api/role/edit', '', NULL, '', 0, 1, '2020-07-03 10:14:06', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278874730614505474, '删除角色', '/api/role/delete', 4, 2, '/api/role/delete', '', NULL, '', 0, 1, '2020-07-03 10:14:31', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278874826726981634, '查看角色详情', '/api/role/detail', 4, 2, '/api/role/detail', '', NULL, '', 0, 1, '2020-07-03 10:14:54', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278935244824649729, '角色授权', '/api/role/grant', 4, 2, '/api/role/grant', '', NULL, '', 0, 1, '2020-07-03 14:14:58', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278940217222373378, '查看用户分页列表', '/api/sysUser/page', 3, 2, '/api/sysUser/page', '', NULL, '', 6, 1, '2020-07-03 14:34:44', 1, '2020-07-06 08:46:56', 0, 0);
INSERT INTO `t_permission` VALUES (1278945074905882626, '查看日志分页列表', '/api/operationLog/page', 7, 2, '/api/operationLog/page', '', NULL, '', 0, 1, '2020-07-03 14:54:02', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278945363008430081, '查看权限菜单分页列表', '/api/permission/page', 6, 2, '/api/permission/page', '', NULL, '', 6, 1, '2020-07-03 14:55:11', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278945795302760449, '查看角色分页列表', '/api/role/page', 4, 2, '/api/role/page', '', NULL, '', 6, 1, '2020-07-03 14:56:54', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1278945921748443138, '查看部门分页列表', '/api/department/page', 5, 2, '/api/department/page', '', NULL, '', 6, 1, '2020-07-03 14:57:24', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279961796995772418, '数据字典管理', '/api/dictionary/manager', 2, 1, '/api/dictionary/manager', '/dictionary/page', NULL, '', 4, 1, '2020-07-06 10:14:08', 1, '2020-07-07 14:33:52', 0, 0);
INSERT INTO `t_permission` VALUES (1279963706653995010, '数据字典分页列表', '/api/dictionary/page', 1279961796995772418, 2, '/api/dictionary/page', '', NULL, '', 6, 1, '2020-07-06 10:21:43', 1, '2020-07-06 10:25:41', 0, 0);
INSERT INTO `t_permission` VALUES (1279964662510710786, '添加数据字典', '/api/dictionary/add', 1279961796995772418, 2, '/api/dictionary/add', '', NULL, '', 4, 1, '2020-07-06 10:25:31', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279965014840635394, '编辑数据字典', '/api/dictionary/edit', 1279961796995772418, 2, '/api/dictionary/edit', '', NULL, '', 3, 1, '2020-07-06 10:26:55', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279965164728283138, '删除数据字典', '/api/dictionary/delete', 1279961796995772418, 2, '/api/dictionary/delete', '', NULL, '', 0, 1, '2020-07-06 10:27:31', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279965476973244417, '数据字典类型管理', '/api/dictionaryType/manager', 2, 1, '/api/dictionaryType/manager', '/dictionaryType/page', NULL, '', 3, 1, '2020-07-06 10:28:45', 1, '2020-07-07 14:34:19', 0, 0);
INSERT INTO `t_permission` VALUES (1279965683060371458, '数据字典类型分页列表', '/api/dictionaryType/page', 1279965476973244417, 2, '/api/dictionaryType/page', '', NULL, '', 6, 1, '2020-07-06 10:29:34', 1, '2020-07-06 10:32:05', 0, 0);
INSERT INTO `t_permission` VALUES (1279965871720165378, '数据字典类型全部列表', '/api/dictionaryType/list', 1279965476973244417, 2, '/api/dictionaryType/list', '', NULL, '', 5, 1, '2020-07-06 10:30:19', 1, '2020-07-06 10:31:53', 0, 0);
INSERT INTO `t_permission` VALUES (1279966073675902978, '添加数据字典类型', '/api/dictionaryType/add', 1279965476973244417, 2, '/api/dictionaryType/add', '', NULL, '', 4, 1, '2020-07-06 10:31:07', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279966216672309250, '编辑数据字典类型', '/api/dictionaryType/edit', 1279965476973244417, 2, '/api/dictionaryType/edit', '', NULL, '', 3, 1, '2020-07-06 10:31:41', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1279966467911118850, '删除数据字典类型', '/api/dictionaryType/delete', 1279965476973244417, 2, '/api/dictionaryType/delete', '', NULL, '', 0, 1, '2020-07-06 10:32:41', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1281468111949672449, '附件管理', '/api/attachment/manager', 2, 1, '/api/attachment/manager', '/attachment/page', NULL, '', 2, 1, '2020-07-10 13:59:41', 1, '2020-07-10 14:14:40', 0, 0);
INSERT INTO `t_permission` VALUES (1281471549622202370, '附件分页列表', '/api/attachment/page', 1281468111949672449, 2, '/api/attachment/page', '', NULL, '', 0, 1, '2020-07-10 14:13:21', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1281472358460174338, '数据字典全部列表', '/api/dictionary/list', 1279961796995772418, 2, '/api/dictionary/list', '', NULL, '', 5, 1, '2020-07-10 14:16:34', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1281474355909373954, '查看数据字典类型详情', '/api/dictionaryType/detail', 1279965476973244417, 2, '/api/dictionaryType/detail', '', NULL, '', 0, 1, '2020-07-10 14:24:30', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1281474484460597249, '查看数据字典详情', '/api/dictionary/detail', 1279961796995772418, 2, '/api/dictionary/detail', '', NULL, '', 0, 1, '2020-07-10 14:25:00', NULL, NULL, 0, 0);
INSERT INTO `t_permission` VALUES (1283586752459608065, '测试', '/api/test', 0, 0, '/api/test', '', NULL, '', 0, 1, '2020-07-16 10:18:24', 1, '2020-07-20 14:38:28', 0, 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) NOT NULL COMMENT '角色Id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简述',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `delete_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '超级管理员', 1, '2020-06-29 10:33:20', NULL, NULL, 0, 0);
INSERT INTO `t_role` VALUES (2, '管理员', '管理员', 1, '2020-06-29 10:33:49', NULL, NULL, 0, 0);
INSERT INTO `t_role` VALUES (3, '普通用户', '普通用户', 1, '2020-06-29 10:34:08', NULL, NULL, 0, 0);
INSERT INTO `t_role` VALUES (1278875194882015234, 'test', 'test', 1, '2020-07-03 10:16:21', 1, '2020-07-03 10:21:27', 0, 0);
INSERT INTO `t_role` VALUES (1278875227509506049, '1', '1', 1, '2020-07-03 10:16:29', NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `per_id` bigint(20) NOT NULL COMMENT '权限菜单id',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1278985863581196290, 3, 1, 0);
INSERT INTO `t_role_permission` VALUES (1278985863589584898, 3, 8, 0);
INSERT INTO `t_role_permission` VALUES (1278986029910515713, 2, 1, 0);
INSERT INTO `t_role_permission` VALUES (1278986029918904322, 2, 8, 0);
INSERT INTO `t_role_permission` VALUES (1278986029927292929, 2, 3, 0);
INSERT INTO `t_role_permission` VALUES (1278986029927292930, 2, 1278940217222373378, 0);
INSERT INTO `t_role_permission` VALUES (1278986029927292931, 2, 11, 0);
INSERT INTO `t_role_permission` VALUES (1278986029935681537, 2, 12, 0);
INSERT INTO `t_role_permission` VALUES (1278986029935681538, 2, 13, 0);
INSERT INTO `t_role_permission` VALUES (1278986029935681539, 2, 14, 0);
INSERT INTO `t_role_permission` VALUES (1278986029935681540, 2, 4, 0);
INSERT INTO `t_role_permission` VALUES (1278986029944070146, 2, 1278945795302760449, 0);
INSERT INTO `t_role_permission` VALUES (1278986029944070147, 2, 1278874627455598593, 0);
INSERT INTO `t_role_permission` VALUES (1278986029944070148, 2, 1278874826726981634, 0);
INSERT INTO `t_role_permission` VALUES (1278986029952458753, 2, 1278874730614505474, 0);
INSERT INTO `t_role_permission` VALUES (1278986029952458754, 2, 1278874525479485442, 0);
INSERT INTO `t_role_permission` VALUES (1278986029952458755, 2, 9, 0);
INSERT INTO `t_role_permission` VALUES (1278986029952458756, 2, 5, 0);
INSERT INTO `t_role_permission` VALUES (1278986029960847362, 2, 1278945921748443138, 0);
INSERT INTO `t_role_permission` VALUES (1278986029960847363, 2, 10, 0);
INSERT INTO `t_role_permission` VALUES (1278986029960847364, 2, 15, 0);
INSERT INTO `t_role_permission` VALUES (1278986029960847365, 2, 16, 0);
INSERT INTO `t_role_permission` VALUES (1278986029969235969, 2, 17, 0);
INSERT INTO `t_role_permission` VALUES (1278986029969235970, 2, 18, 0);
INSERT INTO `t_role_permission` VALUES (1278986029969235971, 2, 19, 0);
INSERT INTO `t_role_permission` VALUES (1278986029977624578, 2, 2, 0);
INSERT INTO `t_role_permission` VALUES (1278986029977624579, 2, 7, 0);
INSERT INTO `t_role_permission` VALUES (1278986029977624580, 2, 1278945074905882626, 0);
INSERT INTO `t_role_permission` VALUES (1279940157146963969, 1278875194882015234, 1, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352577, 1278875194882015234, 8, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352578, 1278875194882015234, 3, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352579, 1278875194882015234, 1278940217222373378, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352580, 1278875194882015234, 4, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352581, 1278875194882015234, 1278945795302760449, 0);
INSERT INTO `t_role_permission` VALUES (1279940157155352582, 1278875194882015234, 5, 0);
INSERT INTO `t_role_permission` VALUES (1279940157163741186, 1278875194882015234, 1278945921748443138, 0);
INSERT INTO `t_role_permission` VALUES (1279940157163741187, 1278875194882015234, 2, 0);
INSERT INTO `t_role_permission` VALUES (1279940157163741188, 1278875194882015234, 6, 0);
INSERT INTO `t_role_permission` VALUES (1279940157163741189, 1278875194882015234, 1278945363008430081, 0);
INSERT INTO `t_role_permission` VALUES (1281474515221622786, 1, 1, 0);
INSERT INTO `t_role_permission` VALUES (1281474515230011393, 1, 8, 0);
INSERT INTO `t_role_permission` VALUES (1281474515230011394, 1, 3, 0);
INSERT INTO `t_role_permission` VALUES (1281474515230011395, 1, 1278940217222373378, 0);
INSERT INTO `t_role_permission` VALUES (1281474515230011396, 1, 11, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400001, 1, 12, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400002, 1, 13, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400003, 1, 14, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400004, 1, 4, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400005, 1, 1278945795302760449, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400006, 1, 1278935244824649729, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400007, 1, 1278874627455598593, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400008, 1, 1278874826726981634, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400009, 1, 1278874730614505474, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400010, 1, 1278874525479485442, 0);
INSERT INTO `t_role_permission` VALUES (1281474515238400011, 1, 9, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788609, 1, 5, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788610, 1, 1278945921748443138, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788611, 1, 10, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788612, 1, 15, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788613, 1, 16, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788614, 1, 17, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788615, 1, 18, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788616, 1, 19, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788617, 1, 2, 0);
INSERT INTO `t_role_permission` VALUES (1281474515246788618, 1, 1279961796995772418, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177217, 1, 1279963706653995010, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177218, 1, 1281472358460174338, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177219, 1, 1279964662510710786, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177220, 1, 1279965014840635394, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177221, 1, 1281474484460597249, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177222, 1, 1279965164728283138, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177223, 1, 1279965476973244417, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177224, 1, 1279965683060371458, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177225, 1, 1279965871720165378, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177226, 1, 1279966073675902978, 0);
INSERT INTO `t_role_permission` VALUES (1281474515255177227, 1, 1279966216672309250, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565825, 1, 1281474355909373954, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565826, 1, 1279966467911118850, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565827, 1, 1281468111949672449, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565828, 1, 1281471549622202370, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565829, 1, 6, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565830, 1, 1278945363008430081, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565831, 1, 1278867827100540929, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565832, 1, 1278867963608358914, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565833, 1, 1278867684703920130, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565834, 1, 20, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565835, 1, 7, 0);
INSERT INTO `t_role_permission` VALUES (1281474515263565836, 1, 1278945074905882626, 0);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `identity_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `sex` int(1) NULL DEFAULT 0 COMMENT '性别，0：男，1：女',
  `level` int(1) NULL DEFAULT NULL COMMENT '级别',
  `level_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别名称',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_user_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改者ID',
  `delete_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除标志，0：未删除，1：已删除',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  `state` int(1) NOT NULL DEFAULT 1 COMMENT '状态，1：启用；0：冻结',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, NULL, NULL, 'admin', 'f2a725c70151c21881f06356418f06b0db7a801b3e02d81d', NULL, '超级管理员', '320811199106254016', '12345@qq.com', '17766112120', 1, NULL, NULL, NULL, '2020-06-22 15:44:05', NULL, NULL, 0, 0, 1, NULL);
INSERT INTO `t_sys_user` VALUES (2, NULL, NULL, 'test', 'f2a725c70151c21881f06356418f06b0db7a801b3e02d81d', NULL, '测试', '320811199106254016', '12345@qq.com', '17766112120', 1, NULL, NULL, NULL, '2020-06-22 15:44:05', '2020-07-06 08:53:07', 1, 0, 0, 1, NULL);
INSERT INTO `t_sys_user` VALUES (1278529399221657601, NULL, NULL, 'wj', 'b8aa95e2858d77923e28800b011b47b9c232b5342e727448', NULL, 'wangjian', '320811199106254016', '123@qq.com', '17766112121', 0, NULL, NULL, 1, '2020-07-02 11:22:17', NULL, NULL, 0, 0, 1, NULL);
INSERT INTO `t_sys_user` VALUES (1278607837261471746, NULL, NULL, 'tt', 'e6ba4ec42a26c13593a81e9cf86219408c8de96e87967020', '', 'tt', '320811199106254016', '123@qq.com', '17766112121', 0, NULL, NULL, 1, '2020-07-02 16:33:58', '2020-07-14 09:37:12', 1, 0, 0, 1, NULL);

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_department
-- ----------------------------
DROP TABLE IF EXISTS `t_user_department`;
CREATE TABLE `t_user_department`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dep_id` bigint(20) NOT NULL COMMENT '部门ID',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_department
-- ----------------------------
INSERT INTO `t_user_department` VALUES (1, 1, 1, 0);
INSERT INTO `t_user_department` VALUES (1278529399938883585, 1278529399221657601, 2, 0);
INSERT INTO `t_user_department` VALUES (1279941411323551746, 2, 1278592332471476225, 0);
INSERT INTO `t_user_department` VALUES (1282851608891244546, 1278607837261471746, 2, 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '版本，分布式事务标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1, 0);
INSERT INTO `t_user_role` VALUES (1278529399582367745, 1278529399221657601, 2, 0);
INSERT INTO `t_user_role` VALUES (1279941411139002370, 2, 1278875194882015234, 0);
INSERT INTO `t_user_role` VALUES (1282851608866078722, 1278607837261471746, 3, 0);

SET FOREIGN_KEY_CHECKS = 1;
