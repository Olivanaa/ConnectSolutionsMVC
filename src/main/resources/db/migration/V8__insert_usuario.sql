INSERT INTO usuarios (nome, email, senha, role)
VALUES (
    'Jane',
    'jane@example.com',
    '$2a$12$4VAv6a7APPD9I4jEFTntF.Mc1f5MIwd23b0WLvIJFnhTHoTfR.8Qy',
    'USER'
);

INSERT INTO usuarios (
    nome,
    email,
    senha,
    role
) VALUES (
    'João Silva',
    'joao.silva@example.com',
    '$2a$12$jcnjE0RvcmbMrWm4AS4GS.fdQe9JbfVag50TnK5bUjvYsfNN4WYzm',
    'ADMIN'
);

COMMIT;