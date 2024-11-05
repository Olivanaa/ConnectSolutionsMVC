CREATE TABLE IF NOT EXISTS lead (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    dtInteracao DATE NOT NULL,
    interesse_id BIGINT,

    FOREIGN KEY (interesse_id) REFERENCES interesse(id)

);