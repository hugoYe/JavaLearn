-- ----------------------------
-- Table structure for t_user
-- AUTO_INCREMENT=257 : 添加数据，第一行数据从257开始
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `real_name` varchar(64) NOT NULL COMMENT '用户姓名',
  `is_root` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否超级管理员: 1.是 0.不是',
  `is_deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '被删除的数据: 1.已删除，0.未删除',
  `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '最后修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8 COMMENT='用户'