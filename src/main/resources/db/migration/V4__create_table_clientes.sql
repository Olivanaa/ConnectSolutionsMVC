CREATE TABLE IF NOT EXISTS cliente (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     nome VARCHAR(200) NOT NULL,
     email VARCHAR(255) NOT NULL,
     telefone VARCHAR(11) NOT NULL,
     dtaNascimento DATE NOT NULL,
     dtaCadastro DATE NOT NULL,
     segmentoMercado VARCHAR(50) NOT NULL,
     dtaUltimaInteracao DATE NOT NULL,
     endereco_id BIGINT,
     interesse_id BIGINT,

     FOREIGN KEY (endereco_id) REFERENCES Endereco(id),
     FOREIGN KEY (interesse_id) REFERENCES Interesse(id)


);