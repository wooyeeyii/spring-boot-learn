ALTER TABLE `flyway_test_alpha` ADD COLUMN `column_bravo` varchar(25) NULL;

DROP TABLE IF EXISTS flyway_test_exist;
CREATE TABLE `flyway_test_exist`  (
    `id`            int(11) AUTO_INCREMENT NOT NULL,
    `column_alpha`  varchar(30) NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;