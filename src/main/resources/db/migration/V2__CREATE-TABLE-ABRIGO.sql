CREATE TABLE abrigo
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255)  NOT NULL,
    phone      VARCHAR(255)  NOT NULL,

    created_on TIMESTAMP(6) NOT NULL,

    address    VARCHAR(50),
    number     INTEGER,
    district   VARCHAR(50),
    city       VARCHAR(50),
    state      VARCHAR(10),
    zip_code   VARCHAR(50)
)