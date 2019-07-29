/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18 : Database - test_account
*********************************************************************
*/

/*Table structure for table `account` */

/* DROP TABLE IF EXISTS `account`; */

CREATE TABLE IF NOT EXISTS `account` (
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `user_key` varchar(10) NOT NULL COMMENT '用户密钥',
  `channel` int(11) NOT NULL DEFAULT '0' COMMENT '渠道Id',
  `sub_channel` int(11) NOT NULL DEFAULT '0' COMMENT '子渠道',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `last_time` datetime NOT NULL COMMENT '最后登陆时间',
  `os` int(11) NOT NULL COMMENT '手机系统,0:IOS,1:Android',
  `handset` varchar(30) DEFAULT NULL COMMENT '手机型号',
  `is_super` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否超级账号,0:否,1:是',
  `is_ban` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被封号',
  `ban_txt` varchar(100) DEFAULT NULL COMMENT '封号原因',
  `last_server` int(11) NOT NULL DEFAULT '0',
  `channel_uid` varchar(64) DEFAULT NULL COMMENT 'sdk方生成的唯一账号id',
  `channel_uname` varchar(64) DEFAULT NULL COMMENT 'sdk方生成的账号',
  `input_uname` varchar(64) NOT NULL COMMENT '玩家输入的账号',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号表';

/*Table structure for table `actor_info` */

/* DROP TABLE IF EXISTS `actor_info`; */

CREATE TABLE IF NOT EXISTS `actor_info` (
  `actor_id` int(11) NOT NULL COMMENT '角色Id',
  `user_name` varchar(64) NOT NULL COMMENT '帐号id',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `actor_type` int(11) NOT NULL COMMENT '角色类型',
  `last_update` datetime NOT NULL COMMENT '最后更新时间',
  `level` int(11) NOT NULL COMMENT '等级',
  `server_id` int(11) NOT NULL COMMENT '游戏区id',
  PRIMARY KEY (`actor_id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表 ';

/*Table structure for table `channel_server` */

/*DROP TABLE IF EXISTS `channel_server`; */

CREATE TABLE IF NOT EXISTS `channel_server` (
  `channel` int(11) NOT NULL,
  `serverId` int(11) NOT NULL,
  PRIMARY KEY (`channel`,`serverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道跟服务器对应表';

/*Table structure for table `order_info` */

/*DROP TABLE IF EXISTS `order_info`; */

CREATE TABLE IF NOT EXISTS `order_info` (
  `order_id` bigint(30) NOT NULL COMMENT '订单id',
  `user_name` varchar(64) DEFAULT NULL COMMENT '玩家账号id',
  `item_id` int(11) DEFAULT NULL COMMENT '商品id',
  `actor_id` int(20) DEFAULT NULL COMMENT '角色id',
  `server_id` int(11) DEFAULT NULL COMMENT '游戏区id',
  `flags` tinyint(4) DEFAULT NULL COMMENT '辨识',
  `amount` int(11) DEFAULT NULL COMMENT '订单金额',
  `game_money` int(11) DEFAULT NULL COMMENT '兑换相应的游戏币',
  `state` smallint(5) DEFAULT NULL COMMENT '状态',
  `descrption` varchar(50) DEFAULT NULL COMMENT '订单描述',
  `traces` varchar(255) DEFAULT NULL COMMENT '订单追踪备注',
  `channel_order_id` varchar(50) DEFAULT NULL COMMENT '渠道订单id',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `update_time` datetime NOT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='充值订单表';

/*Table structure for table `server_status` */

/*DROP TABLE IF EXISTS `server_status`; */

CREATE TABLE IF NOT EXISTS `server_status` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '服务器名',
  `show_id` int(11) NOT NULL COMMENT '服务器显示序号',
  `ip` varchar(15) NOT NULL COMMENT '网关ip地址',
  `inner_ip` varchar(15) DEFAULT '-1' COMMENT '内网ip地址',
  `port` int(11) NOT NULL,
  `status` smallint(5) NOT NULL COMMENT '0:流畅,1:繁忙,2:火爆,3:爆满,4:维护,5:关闭',
  `is_test` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否测试服',
  `is_recommand` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为推荐服务器',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ipport` (`ip`,`port`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器状态';

/*
CREATE TABLE IF NOT EXISTS `channel_notice` (
  `channel_id` int(11) NOT NULL,
  `notice` varchar(255) NOT NULL COMMENT '公告',
  `is_valid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否生效,0:否,1:繁忙',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器公告';
 */
 

