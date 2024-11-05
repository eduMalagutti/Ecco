-- Migration script to create category_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS category_tb
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Migration script to create sub_category_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS sub_category_tb
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    category_id UUID         NOT NULL,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id) REFERENCES category_tb (id)
            ON DELETE CASCADE
);

-- Migration script to create user_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS user_tb
(
    id                                    UUID PRIMARY KEY,
    profile_photo                         BYTEA                 DEFAULT NULL,
    phone                                 VARCHAR(255),
    password                              VARCHAR(255) NOT NULL,
    email                                 VARCHAR(255) NOT NULL UNIQUE,
    name                                  VARCHAR(255) NOT NULL,
    photo_front_document                  BYTEA                 DEFAULT NULL,
    photo_back_document                   BYTEA                 DEFAULT NULL,
    type_document                         VARCHAR(255),
    verified                              BOOLEAN      NOT NULL DEFAULT false,
    token_verification                    VARCHAR(255),
    change_password_token                 VARCHAR(255),
    change_password_token_expiration_date TIMESTAMP
);

-- Migration script to create service_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS service_tb
(
    id              UUID PRIMARY KEY,
    fixed_price     DOUBLE PRECISION,
    min_price       DOUBLE PRECISION,
    max_price       DOUBLE PRECISION,
    sub_category_id UUID NOT NULL,
    user_id         UUID NOT NULL,
    CONSTRAINT fk_sub_category
        FOREIGN KEY (sub_category_id) REFERENCES sub_category_tb (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES user_tb (id)
            ON DELETE CASCADE
);

-- Migration script to create service_day_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS service_day_tb
(
    id         UUID PRIMARY KEY,
    date       DATE         NOT NULL,
    period     VARCHAR(255) NOT NULL,
    service_id UUID         NOT NULL,
    CONSTRAINT fk_service
        FOREIGN KEY (service_id) REFERENCES service_tb (id)
            ON DELETE CASCADE
);

-- Migration script to create reservation_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS reservation_tb
(
    id                         UUID PRIMARY KEY,
    rejection_reason           VARCHAR(255),
    start_time                 TIMESTAMP    NOT NULL,
    end_time                   TIMESTAMP    NOT NULL,
    number                     INTEGER      NOT NULL,
    street                     VARCHAR(255) NOT NULL,
    neighborhood               VARCHAR(255) NOT NULL,
    city                       VARCHAR(255) NOT NULL,
    state                      VARCHAR(255) NOT NULL,
    cep                        VARCHAR(255) NOT NULL,
    comment                    TEXT         NOT NULL,
    request_date               DATE,
    comment_review_to_provider TEXT,
    note_review_to_provider    INTEGER,
    comment_review_to_customer TEXT,
    note_review_to_customer    INTEGER,
    status                     VARCHAR(255) NOT NULL,
    user_id                    UUID         NOT NULL,
    service_id                 UUID         NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES user_tb (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_service
        FOREIGN KEY (service_id) REFERENCES service_tb (id)
            ON DELETE CASCADE
);

-- Migration script to create message_tb table in PostgreSQL
CREATE TABLE IF NOT EXISTS message_tb
(
    id             UUID PRIMARY KEY,
    moment         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    content        TEXT      NOT NULL,
    user_id        UUID      NOT NULL,
    reservation_id UUID      NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES user_tb (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_reservation
        FOREIGN KEY (reservation_id) REFERENCES reservation_tb (id)
            ON DELETE CASCADE
);