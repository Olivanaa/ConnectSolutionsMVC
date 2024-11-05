CREATE TABLE IF NOT EXISTS usuarios (
    id bigint NOT NULL auto_increment,
    nome varchar(100),
    email varchar(100),
    senha varchar(100),
    role varchar(50),
    created_at datetime,
    primary key (id)
    );

INSERT INTO usuarios (nome, email, senha, role, created_at)
VALUES ('Jane', 'jane@example.com', '$2b$12$KYNF7WxAIK/TQxCTi2s3ROEgRNb0ipARReaZe.Steyr.aEIYjA9xG', 'ROLE_USER', TO_DATE('2024-10-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

commit;