CREATE DATABASE test;
CREATE DATABASE test2;
USE test;
DROP TABLE
IF
	EXISTS `users`;
CREATE TABLE `users` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键id',
`userName` VARCHAR ( 32 ) DEFAULT NULL COMMENT '用户名',
`password` VARCHAR ( 32 ) DEFAULT NULL COMMENT '密码',
`user_sex` VARCHAR ( 32 ) DEFAULT NULL,
`nick_name` VARCHAR ( 32 ) DEFAULT NULL,
PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO users ( userName, PASSWORD, user_sex, nick_name )
VALUES
	( 'aaa', '111', 'MAN', 'a' ),
	( 'bbb', '222', 'WOMAN', 'b' );
ues test2;
CREATE TABLE `users` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键id',
`userName` VARCHAR ( 32 ) DEFAULT NULL COMMENT '用户名',
`password` VARCHAR ( 32 ) DEFAULT NULL COMMENT '密码',
`user_sex` VARCHAR ( 32 ) DEFAULT NULL,
`nick_name` VARCHAR ( 32 ) DEFAULT NULL,
PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO users ( userName, PASSWORD, user_sex, nick_name )
VALUES
	( 'zzz', '000', 'MAN', 'z' );