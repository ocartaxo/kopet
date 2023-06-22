CREATE TABLE adocoes
(
    id   SERIAL NOT NULL,
    date TIMESTAMP NOT NULL,

    pet_id SERIAL NOT NULL,
    tutor_id SERIAL NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(pet_id) REFERENCES pets(id),
    FOREIGN KEY(tutor_id) REFERENCES tutores(id)
)