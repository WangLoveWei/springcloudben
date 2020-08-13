
create database ben_sys;
use ben_sys;
CREATE TABLE ben_user (
	id INT PRIMARY KEY auto_increment,
	`username` VARCHAR ( 20 )  DEFAULT NULL COMMENT '用户名',
	`password` VARCHAR ( 64 )  DEFAULT NULL COMMENT '密码',
	`salt` VARCHAR ( 64 ) DEFAULT NULL COMMENT '密码盐',
	`phone_number` VARCHAR ( 11 ) DEFAULT NULL COMMENT '电话号码',
	`email` VARCHAR ( 64 )  DEFAULT NULL COMMENT '电子邮件',
	`sex` CHAR ( 1 )  DEFAULT NULL COMMENT '性别: F-女；M-男',
	`status` INT ( 11 ) DEFAULT '0' COMMENT '状态: 1:启用 0:禁用',
	`nickname` VARCHAR ( 20 ) DEFAULT NULL COMMENT '用户昵称',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '修改时间'

)

CREATE TABLE ben_user_token (
	id INT PRIMARY KEY auto_increment,
	`username` VARCHAR ( 20 ) DEFAULT NULL COMMENT '账号',
	`token` varchar(255) DEFAULT NULL COMMENT 'token',
	`user_id` VARCHAR ( 20 )  DEFAULT NULL COMMENT '用户Id',
		`status` INT ( 11 ) DEFAULT '0' COMMENT '状态: 1:启用 0:禁用',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '修改时间'

)

-- 权限

CREATE TABLE `ben_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
)


CREATE TABLE `ben_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20)  DEFAULT NULL COMMENT '角色名称',
  `code` varchar(20)  DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255)  DEFAULT NULL COMMENT '详情',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(11) DEFAULT '0' COMMENT '状态: 1-启用  0-禁用',
  PRIMARY KEY (`id`)
)



CREATE TABLE `ben_role_perm` (
  `role_id` bigint(20) NOT NULL,
  `perm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`perm_id`)
)

CREATE TABLE `ben_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20)  DEFAULT NULL COMMENT '资源名称',
  `description` varchar(255)  DEFAULT NULL COMMENT '详情',
  `url` varchar(50)  DEFAULT NULL COMMENT '接口或链接地址',
	`method` varchar(255) DEFAULT NULL COMMENT '请求方式',
  `menu_flag` char(1)  DEFAULT '0' COMMENT '是否是菜单: Y-是菜单  N-不是菜单',
  `icon` varchar(255)  DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `role_id` bigint(20)   DEFAULT NULL COMMENT '权限id',
  `status` int(11) DEFAULT '0' COMMENT '状态: 1-启用 0-禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)

