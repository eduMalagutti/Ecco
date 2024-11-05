-- Insert initial data into category_tb
INSERT INTO category_tb (id, name)
VALUES ('11111111-1111-1111-1111-111111111111', 'MANUTENCAO'),
       ('22222222-2222-2222-2222-222222222222', 'LIMPEZA');

-- Insert initial data into sub_category_tb
INSERT INTO sub_category_tb (id, name, category_id)
VALUES ('33333333-3333-3333-3333-333333333333', 'PINTURA', '11111111-1111-1111-1111-111111111111'),
       ('44444444-4444-4444-4444-444444444444', 'ELÉTRICA', '11111111-1111-1111-1111-111111111111'),
       ('55555555-5555-5555-5555-555555555555', 'HIDRÁULICA', '11111111-1111-1111-1111-111111111111'),
       ('66666666-6666-6666-6666-666666666666', 'PEQUENOS REPAROS', '11111111-1111-1111-1111-111111111111'),
       ('77777777-7777-7777-7777-777777777777', 'INSTALAÇÕES', '11111111-1111-1111-1111-111111111111'),
       ('88888888-8888-8888-8888-888888888888', 'FAXINA', '22222222-2222-2222-2222-222222222222'),
       ('99999999-9999-9999-9999-999999999999', 'DE VIDROS', '22222222-2222-2222-2222-222222222222'),
       ('10101010-1010-1010-1010-101010101010', 'DE PISCINA', '22222222-2222-2222-2222-222222222222'),
       ('11111111-1111-1111-1111-111111111111', 'DE JARDIM', '22222222-2222-2222-2222-222222222222');

