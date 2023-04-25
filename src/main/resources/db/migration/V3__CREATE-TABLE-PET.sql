CREATE TABLE pet
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    age         VARCHAR(50)  NOT NULL,
    description VARCHAR(500) NOT NULL,
    image       VARCHAR(500) NOT NULL,
    adopted     BOOLEAN      NOT NULL,
    specie      VARCHAR(255) NOT NULL,
    size        VARCHAR(50)

)