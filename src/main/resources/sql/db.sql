/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost
 Source Database       : db

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : utf-8

 Date: 08/02/2019 16:20:16 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `hibernate_sequence`
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES ('18');
COMMIT;

-- ----------------------------
--  Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `group_id` varchar(255) NOT NULL,
  `persion_id` varchar(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`group_id`,`persion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_permissions` (`role_name`,`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `roles_permissions`
-- ----------------------------
BEGIN;
INSERT INTO `roles_permissions` VALUES ('1', 'system', 'update');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_key` varchar(255) NOT NULL,
  `dict_type` varchar(255) NOT NULL,
  `dict_value` varchar(50) NOT NULL,
  `level` int(11) NOT NULL,
  `parent_key` varchar(30) DEFAULT NULL,
  `parent_type` varchar(30) DEFAULT NULL,
  `root` bit(1) NOT NULL,
  PRIMARY KEY (`dict_key`,`dict_type`),
  KEY `FKqlcjg7r07kssyukevt6o4i9lk` (`parent_key`,`parent_type`),
  CONSTRAINT `FKqlcjg7r07kssyukevt6o4i9lk` FOREIGN KEY (`parent_key`, `parent_type`) REFERENCES `sys_dict` (`dict_key`, `dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1', 'SF', '是', '1', 'SF', 'SF', b'0'), ('1', 'XB', '男', '0', null, null, b'0'), ('2', 'XB', '女', '0', null, null, b'0'), ('SF', 'SF', '是否', '0', null, null, b'1'), ('XB', 'XB', 'value', '0', null, null, b'1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(255) NOT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  `ENABLE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_users`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `USER_ID` varchar(255) NOT NULL,
  `ENABLED` bit(1) DEFAULT NULL,
  `ACCOUNT_NON_EXPIRED` bit(1) DEFAULT NULL,
  `ACCOUNT_NON_LOCKED` bit(1) DEFAULT NULL,
  `CREDENTIALS_NON_EXPIRED` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `address` varchar(60) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`),
  CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `password_salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'yueking', '123', null), ('2', 'yuekinger', '2e04793d3f4170d684111d22f949b923', '2f17b3720a809d9e3a7d977e4efb3c46'), ('3', 'admin', '666', null), ('4', 'yuewu', '$shiro1$SHA-512$1$$PJkJr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEwg==', null), ('5', 'liu', 'a9a114054aa6758184314fbb959fbda4', '24520ee264eab73ec09451d0e9ea6aac');
COMMIT;

-- ----------------------------
--  Table structure for `y_module`
-- ----------------------------
DROP TABLE IF EXISTS `y_module`;
CREATE TABLE `y_module` (
  `id` varchar(33) NOT NULL,
  `level` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` varchar(33) DEFAULT NULL,
  `root` bit(1) NOT NULL,
  `sort` int(11) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `resource_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9n7d9ri46s3lhma3uglfto41o` (`permission_id`),
  KEY `FKn9tlldqvqc9trudl9e03ko9iq` (`resource_id`),
  KEY `FKosjuxgkeqnk4n74la15spllj5` (`parent_id`),
  CONSTRAINT `FK9n7d9ri46s3lhma3uglfto41o` FOREIGN KEY (`permission_id`) REFERENCES `y_permissions` (`id`),
  CONSTRAINT `FKn9tlldqvqc9trudl9e03ko9iq` FOREIGN KEY (`resource_id`) REFERENCES `y_resource` (`id`),
  CONSTRAINT `FKosjuxgkeqnk4n74la15spllj5` FOREIGN KEY (`parent_id`) REFERENCES `y_module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_module`
-- ----------------------------
BEGIN;
INSERT INTO `y_module` VALUES ('402881026c506c2f016c506c3e720000', '0', 'module1', null, b'1', '0', null, '16', '402881026c506c2f016c506c3e7e0001'), ('402881026c507340016c50734ff00000', '0', 'module1', null, b'1', '0', null, '17', '402881026c507340016c50734ffa0001');
COMMIT;

-- ----------------------------
--  Table structure for `y_permission_init`
-- ----------------------------
DROP TABLE IF EXISTS `y_permission_init`;
CREATE TABLE `y_permission_init` (
  `id` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '程序对应url地址',
  `permission_init` varchar(255) DEFAULT NULL COMMENT '对应shiro权限',
  `sort` int(100) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_permission_init`
-- ----------------------------
BEGIN;
INSERT INTO `y_permission_init` VALUES ('1', '/userInfo', 'anon', '2'), ('10', '/img/**', 'anon', '1'), ('11', '/css/**', 'anon', '1'), ('12', '/LESS/**', 'anon', '1'), ('13', '/pdf/**', 'anon', '1'), ('14', '/js/**', 'anon', '1'), ('15', '/fonts/**', 'anon', '1'), ('16', '/font-awesome/**', 'anon', '1'), ('17', '/email_templates/**', 'anon', '1'), ('18', '/locales/**', 'anon', '1'), ('2', '/userInfoTest', 'perms[permission1]', '2'), ('3', '/logout', 'logout', '3'), ('4', '/**', 'authc', '0'), ('5', '/findUserByUserName', 'authc,roles[role1],perms[permission1]', '6'), ('6', '/intoLogin', 'anon', '5'), ('7', '/subLogin', 'anon', '7'), ('8', '/unauthorized', 'anon', '8'), ('9', '/images/**', 'anon', '1');
COMMIT;

-- ----------------------------
--  Table structure for `y_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `y_permissions`;
CREATE TABLE `y_permissions` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_permissions`
-- ----------------------------
BEGIN;
INSERT INTO `y_permissions` VALUES ('1', b'1', 'desc', 'permission1'), ('2', b'1', 'desc', 'permission2'), ('3', b'1', 'desc', 'permission3'), ('9', b'1', 'desc', 'p2'), ('16', b'0', 'desc', 'create'), ('17', b'0', 'desc', 'module1:create');
COMMIT;

-- ----------------------------
--  Table structure for `y_resource`
-- ----------------------------
DROP TABLE IF EXISTS `y_resource`;
CREATE TABLE `y_resource` (
  `id` varchar(33) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_resource`
-- ----------------------------
BEGIN;
INSERT INTO `y_resource` VALUES ('402881026c506c2f016c506c3e7e0001', 'resource1', 'type', 'URL.com'), ('402881026c507340016c50734ffa0001', 'resource1', 'type', 'URL.com');
COMMIT;

-- ----------------------------
--  Table structure for `y_roles`
-- ----------------------------
DROP TABLE IF EXISTS `y_roles`;
CREATE TABLE `y_roles` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_roles`
-- ----------------------------
BEGIN;
INSERT INTO `y_roles` VALUES ('4', b'1', 'desc', 'role1', 'role1'), ('5', b'1', 'desc', 'role2', 'role2');
COMMIT;

-- ----------------------------
--  Table structure for `y_users`
-- ----------------------------
DROP TABLE IF EXISTS `y_users`;
CREATE TABLE `y_users` (
  `id` bigint(20) NOT NULL,
  `locked` bit(1) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_users`
-- ----------------------------
BEGIN;
INSERT INTO `y_users` VALUES ('6', b'0', 'user1', 'salt', 'user1'), ('7', b'0', 'user2', 'salt', 'user2'), ('8', b'0', '2e04793d3f4170d684111d22f949b923', '2f17b3720a809d9e3a7d977e4efb3c46', 'yuekinger'), ('9', b'0', null, null, 'yueking');
COMMIT;

-- ----------------------------
--  Table structure for `y_users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `y_users_roles`;
CREATE TABLE `y_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKdxmbtf8aa31cs17p7nv3ba98p` (`role_id`),
  CONSTRAINT `FKdxmbtf8aa31cs17p7nv3ba98p` FOREIGN KEY (`role_id`) REFERENCES `y_roles` (`id`),
  CONSTRAINT `FKtej9r6evqjcup4mm7hviked3h` FOREIGN KEY (`user_id`) REFERENCES `y_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_users_roles`
-- ----------------------------
BEGIN;
INSERT INTO `y_users_roles` VALUES ('6', '4'), ('7', '4'), ('8', '4'), ('6', '5'), ('7', '5');
COMMIT;

-- ----------------------------
--  Table structure for `y_users_roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `y_users_roles_permissions`;
CREATE TABLE `y_users_roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK62dalj6krynd4nhrq10wme2pd` (`permission_id`),
  CONSTRAINT `FK62dalj6krynd4nhrq10wme2pd` FOREIGN KEY (`permission_id`) REFERENCES `y_permissions` (`id`),
  CONSTRAINT `FKmb6wwk5qut0dng30in0jie6wd` FOREIGN KEY (`role_id`) REFERENCES `y_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `y_users_roles_permissions`
-- ----------------------------
BEGIN;
INSERT INTO `y_users_roles_permissions` VALUES ('4', '1'), ('5', '1'), ('4', '2'), ('5', '2'), ('4', '3'), ('5', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
