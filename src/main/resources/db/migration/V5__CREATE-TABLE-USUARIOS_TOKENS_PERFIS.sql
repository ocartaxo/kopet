CREATE TABLE perfis
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE usuarios
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password varchar(255) NOT NULL,
    profile SERIAL NOT NULL REFERENCES perfis(id)
);

CREATE TABLE tokens
(
    id SERIAL PRIMARY KEY,
    tokenValue VARCHAR(500) NOT NULL,
    tokenType VARCHAR(15) NOT NULL,
    user_id SERIAL NOT NULL REFERENCES usuarios(id),

    revoked BOOLEAN,
    expired BOOLEAN
);
