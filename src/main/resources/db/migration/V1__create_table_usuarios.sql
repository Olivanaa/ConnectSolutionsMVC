CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100),
    role VARCHAR(50),
    created_at TIMESTAMP DEFAULT SYSDATE,
    PRIMARY KEY (id)
);

