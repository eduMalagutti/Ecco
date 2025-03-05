CREATE TABLE category_tb
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category_tb PRIMARY KEY (id)
);

CREATE TABLE message_tb
(
    id             UUID                        NOT NULL,
    moment         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    content        VARCHAR(255)                NOT NULL,
    user_id        UUID                        NOT NULL,
    reservation_id UUID                        NOT NULL,
    CONSTRAINT pk_message_tb PRIMARY KEY (id)
);

CREATE TABLE reservation_tb
(
    id                         UUID                        NOT NULL,
    rejection_reason           VARCHAR(255),
    start_time                 TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time                   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    number                     INTEGER                     NOT NULL,
    street                     VARCHAR(255)                NOT NULL,
    neighborhood               VARCHAR(255)                NOT NULL,
    city                       VARCHAR(255)                NOT NULL,
    state                      VARCHAR(255)                NOT NULL,
    cep                        VARCHAR(255)                NOT NULL,
    comment                    VARCHAR(255)                NOT NULL,
    request_date               date                        NOT NULL,
    comment_review_to_provider VARCHAR(255),
    note_review_to_provider    INTEGER,
    comment_review_to_customer VARCHAR(255),
    note_review_to_customer    INTEGER,
    status                     VARCHAR(255)                NOT NULL,
    user_id                    UUID                        NOT NULL,
    service_id                 UUID                        NOT NULL,
    CONSTRAINT pk_reservation_tb PRIMARY KEY (id)
);

CREATE TABLE service_day_tb
(
    id         UUID         NOT NULL,
    date       date         NOT NULL,
    period     VARCHAR(255) NOT NULL,
    service_id UUID         NOT NULL,
    CONSTRAINT pk_service_day_tb PRIMARY KEY (id)
);

CREATE TABLE service_tb
(
    id              UUID NOT NULL,
    fixed_price     DOUBLE PRECISION,
    min_price       DOUBLE PRECISION,
    max_price       DOUBLE PRECISION,
    sub_category_id UUID NOT NULL,
    user_id         UUID NOT NULL,
    CONSTRAINT pk_service_tb PRIMARY KEY (id)
);

CREATE TABLE sub_category_tb
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    category_id UUID         NOT NULL,
    CONSTRAINT pk_sub_category_tb PRIMARY KEY (id)
);

CREATE TABLE user_tb
(
    id                                    UUID         NOT NULL,
    profile_photo                         BYTEA,
    phone                                 VARCHAR(255),
    password                              VARCHAR(255) NOT NULL,
    email                                 VARCHAR(255) NOT NULL,
    name                                  VARCHAR(255) NOT NULL,
    photo_front_document                  BYTEA,
    photo_back_document                   BYTEA,
    type_document                         VARCHAR(255),
    verified                              BOOLEAN      NOT NULL,
    token_verification                    VARCHAR(255),
    change_password_token                 VARCHAR(255),
    change_password_token_expiration_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user_tb PRIMARY KEY (id)
);

ALTER TABLE user_tb
    ADD CONSTRAINT uc_user_tb_email UNIQUE (email);

ALTER TABLE message_tb
    ADD CONSTRAINT FK_MESSAGE_TB_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservation_tb (id);

ALTER TABLE message_tb
    ADD CONSTRAINT FK_MESSAGE_TB_ON_USER FOREIGN KEY (user_id) REFERENCES user_tb (id);

ALTER TABLE reservation_tb
    ADD CONSTRAINT FK_RESERVATION_TB_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_tb (id);

ALTER TABLE reservation_tb
    ADD CONSTRAINT FK_RESERVATION_TB_ON_USER FOREIGN KEY (user_id) REFERENCES user_tb (id);

ALTER TABLE service_day_tb
    ADD CONSTRAINT FK_SERVICE_DAY_TB_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service_tb (id);

ALTER TABLE service_tb
    ADD CONSTRAINT FK_SERVICE_TB_ON_SUB_CATEGORY FOREIGN KEY (sub_category_id) REFERENCES sub_category_tb (id);

ALTER TABLE service_tb
    ADD CONSTRAINT FK_SERVICE_TB_ON_USER FOREIGN KEY (user_id) REFERENCES user_tb (id);

ALTER TABLE sub_category_tb
    ADD CONSTRAINT FK_SUB_CATEGORY_TB_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category_tb (id);