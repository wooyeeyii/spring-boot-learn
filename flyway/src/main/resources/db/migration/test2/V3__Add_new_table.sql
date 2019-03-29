DROP TABLE IF EXISTS flyway_test_table_3;
CREATE TABLE `flyway_test_table_3`  (
    `id`            int(11) AUTO_INCREMENT NOT NULL,
    `column_alpha`  varchar(30) NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;