DROP TABLE IF EXISTS `test`;

CREATE TABLE IF NOT EXISTS `test` (
    `id` varchar(36) DEFAULT uuid() NOT NULL,
    `name` varchar(30) NOT NULL,
    `description` VARCHAR(100) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
