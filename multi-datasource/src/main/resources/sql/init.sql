CREATE DATABASE datasource1;
CREATE DATABASE datasource2;


USE datasource1;
DROP TABLE
IF
	EXISTS `cat`;
CREATE TABLE `cat` (
`id` INT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键id',
`name` VARCHAR ( 1024 ) DEFAULT NULL COMMENT '名字',
`category` VARCHAR ( 32 ) DEFAULT NULL COMMENT '品种',
PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO cat ( id, name, category)
VALUES
	( 1, 'kitty', 'origin cat' ),
	( 2, 'mitten', 'white cat');


USE datasource2;
DROP TABLE
IF
	EXISTS `dog`;
CREATE TABLE `dog` (
`id` INT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键id',
`name` VARCHAR ( 1024 ) DEFAULT NULL COMMENT '名字',
`category` VARCHAR ( 32 ) DEFAULT NULL COMMENT '品种',
PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO dog ( id, name, category)
VALUES
	( 1, '旺财', '中华田园犬' ),
	( 2, 'miao', '藏獒'),
	( 3, '二哈', '哈士奇');

