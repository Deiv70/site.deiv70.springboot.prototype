

/*
DROP TABLE IF EXISTS `subprototype`;
DROP TABLE IF EXISTS `prototype`;
*/

CREATE TABLE IF NOT EXISTS `prototype` (
    `id`                UUID            NOT NULL    DEFAULT uuid()
        PRIMARY KEY,
    `name`              VARCHAR(30)     NOT NULL,
    `description`       VARCHAR(100)    NOT NULL,
    `created_at`        TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    `last_modified_at`  TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `subprototype` (
    `id`                UUID            NOT NULL    DEFAULT uuid()
        PRIMARY KEY,
    `name`              VARCHAR(30)     NOT NULL,
    `description`       VARCHAR(100)    NOT NULL,
    `prototype_id`      UUID            NOT NULL
        REFERENCES prototype(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
