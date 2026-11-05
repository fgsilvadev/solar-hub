-- "users" (plural) because "user" is a reserved word in PostgreSQL.
CREATE TABLE users (
    id            BIGSERIAL    PRIMARY KEY,
    name          VARCHAR(120) NOT NULL,
    email         VARCHAR(160) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role          VARCHAR(20)  NOT NULL,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

CREATE TABLE installations (
    id                     BIGSERIAL     PRIMARY KEY,
    user_id                BIGINT        NOT NULL REFERENCES users (id),
    address                VARCHAR(255)  NOT NULL,
    installed_capacity_kwp NUMERIC(10,2) NOT NULL CHECK (installed_capacity_kwp > 0),
    peak_sun_hours         NUMERIC(4,2)  NOT NULL DEFAULT 5.00 CHECK (peak_sun_hours > 0),
    installed_at           DATE          NOT NULL
);

CREATE TABLE panels (
    id              BIGSERIAL    PRIMARY KEY,
    installation_id BIGINT       NOT NULL REFERENCES installations (id),
    model           VARCHAR(120) NOT NULL,
    power_wp        INTEGER      NOT NULL CHECK (power_wp > 0),
    serial_number   VARCHAR(80)  NOT NULL UNIQUE
);

CREATE TABLE generation_readings (
    id              BIGSERIAL     PRIMARY KEY,
    installation_id BIGINT        NOT NULL REFERENCES installations (id),
    reading_date    DATE          NOT NULL,
    generated_kwh   NUMERIC(10,3) NOT NULL CHECK (generated_kwh >= 0),
    expected_kwh    NUMERIC(10,3) NOT NULL CHECK (expected_kwh >= 0),
    -- one reading per installation per day
    CONSTRAINT uq_reading_installation_date UNIQUE (installation_id, reading_date)
);

CREATE TABLE alerts (
    id              BIGSERIAL    PRIMARY KEY,
    installation_id BIGINT       NOT NULL REFERENCES installations (id),
    type            VARCHAR(30)  NOT NULL,
    message         VARCHAR(255) NOT NULL,
    created_at      TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    resolved        BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_installations_user_id        ON installations (user_id);
CREATE INDEX idx_panels_installation_id       ON panels (installation_id);
CREATE INDEX idx_alerts_installation_resolved ON alerts (installation_id, resolved);
