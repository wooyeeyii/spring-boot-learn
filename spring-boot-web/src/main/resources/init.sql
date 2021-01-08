CREATE DATABASE IF NOT EXISTS test default charset utf8 COLLATE utf8_general_ci;

USE test;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) NOT	NULL COMMENT '邮箱',
  `nick_name` varchar(32) DEFAULT NULL,
  `reg_time` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test`.`user`(`id`, `user_name`, `password`, `email`, `nick_name`, `reg_time`, `create_time`) VALUES
(0, 'haiyang', 'peng', 'email@abc.com', 'hai', '2020-06-01', now()),
(0, 'yudong', 'lin', 'linemail@abc.com', 'hai', '2020-07-01', now()),
(0, 'guotao', 'qin', 'qinemail@abc.com', 'hai', '2020-08-01', now());