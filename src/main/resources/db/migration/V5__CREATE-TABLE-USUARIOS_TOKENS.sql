

CREATE TABLE usuario
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL,
    profile VARCHAR NOT NULL
);

CREATE TABLE token
(
    id SERIAL PRIMARY KEY,
    token_value VARCHAR(500) NOT NULL,
    token_type VARCHAR(15) NOT NULL,
    user_id SERIAL NOT NULL REFERENCES usuario(id),

    revoked BOOLEAN,
    expired BOOLEAN
);
