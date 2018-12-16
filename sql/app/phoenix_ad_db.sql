-- ----------------------------
-- Table structure for t_user
-- AUTO_INCREMENT=257 : 添加数据，第一行数据从257开始
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `real_name` varchar(64) NOT NULL COMMENT '用户姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `company` varchar(128) NOT NULL COMMENT '公司名称',
  `is_root` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否超级管理员: 1.是 0.不是',
  `is_deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '被删除的数据: 1.已删除，0.未删除',
  `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `channel_id` varchar(32) NOT NULL COMMENT '渠道ID',
  `channel_name` varchar(32) NOT NULL COMMENT '渠道名称',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '被删除的数据: 1.已删除，0.未删除',
  `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_channel_id` (`channel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道';

-- ----------------------------
-- Table structure for t_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_user_channel`;
CREATE TABLE `t_user_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `channel_id` varchar(32) NOT NULL COMMENT '渠道ID',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '被删除的数据: 1.已删除，0.未删除',
  `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `index_channel_id` (`channel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户渠道关联表';

-- ----------------------------
-- Table structure for t_operation_data
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_data`;
CREATE TABLE `t_operation_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `pv` int(11) unsigned NOT NULL COMMENT '页面访问量',
  `uv` int(11) unsigned NOT NULL COMMENT '独立访客访问数',
  `income` double(16,2) NOT NULL COMMENT '收入',
  `date` date NOT NULL COMMENT '日期',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `channel_id` varchar(32) NOT NULL COMMENT '渠道ID',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '被删除的数据: 1.已删除，0.未删除',
  `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `index_channel_id` (`channel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营数据表';
