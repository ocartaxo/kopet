CREATE TABLE tutor
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(266)  NOT NULL,
    created_on TIMESTAMP(6) NOT NULL,

    phone      VARCHAR(50),
    about      varchar(500),
    image      VARCHAR(255),

    /*LOCATION*/
    address    VARCHAR(50),
    number     INTEGER,
    district   VARCHAR(50),
    city       VARCHAR(50),
    state      VARCHAR(10),
    zip_code   VARCHAR(50)
)