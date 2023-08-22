CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- Enable UUID generation function

/*
DROP TABLE IF EXISTS "subprototype";
DROP TABLE IF EXISTS "prototype";
*/

CREATE TABLE IF NOT EXISTS "prototype" (
    "id"                UUID            NOT NULL    DEFAULT uuid_generate_v4()
        PRIMARY KEY,
    "name"              VARCHAR(30)     NOT NULL,
    "description"       VARCHAR(100)    NOT NULL,
    "created_at"        TIMESTAMPTZ     NOT NULL    DEFAULT ( CURRENT_TIMESTAMP AT TIME ZONE 'UTC' ),
    "last_modified_at"  TIMESTAMPTZ     NOT NULL    DEFAULT ( CURRENT_TIMESTAMP AT TIME ZONE 'UTC' )
);

CREATE TABLE IF NOT EXISTS "subprototype" (
    "id"            UUID            NOT NULL    DEFAULT uuid_generate_v4()
        PRIMARY KEY,
    "name"          VARCHAR(30)     NOT NULL,
    "description"   VARCHAR(100)    NOT NULL,
    "prototype_id"  UUID            NOT NULL
        REFERENCES "prototype"("id")
);
