USE test;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) NOT	NULL COMMENT '邮箱',
  `nick_name` varchar(32) DEFAULT NULL,
  `reg_time` varchar(32) NOT NULL,
  `create_time` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- INSERT INTO `user` (id, user_name, password, email, nick_name, reg_time, `create_time`) VALUES
-- (100, "11", "11", "jun@163.com", "j", "2020-08-24 00:00:00", "2020-09-01 00:00:00"),
-- (200, "22", "22", "jie@163.com", "j", "2020-08-24 10:10:10", "2020-08-31 00:00:00");