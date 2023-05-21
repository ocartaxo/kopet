ALTER TABLE tutores ADD COLUMN user_id SERIAL REFERENCES usuarios(id);
ALTER TABLE abrigos ADD COLUMN user_id SERIAL REFERENCES usuarios(id);