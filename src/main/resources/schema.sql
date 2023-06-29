
/* DROP TABLE IF EXISTS `prototype`; */

CREATE TABLE IF NOT EXISTS `prototype` (
    `id` UUID DEFAULT uuid() NOT NULL,
    `name` VARCHAR(30) NOT NULL,
    `description` VARCHAR(100) NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
