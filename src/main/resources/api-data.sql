INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (10, 'XS', 'Tariff XS: 20 min 2Gb', 3., 0);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (11, 'S', 'Tariff S: 40 min 8Gb', 7., 1);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (12, 'M', 'Tariff M: 128 min 20Gb', 10., 2);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (13, 'L', 'Tariff L: 500 min 50 Gb', 15., 3);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (14, 'L', 'Tariff XL: Unlimited', 20., 4);

INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (21, 'MultiSIM', ' Multi SIM', 5.5);
INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (22, 'HotspotFlat', 'Hotspot Flat', 9.);
INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (23, 'Spotify', 'Spotify', 6.);

INSERT INTO contract (contract_id, business_id, tariff_id)
VALUES (31, '777', 12);

INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 21);
INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 22);

INSERT INTO roles (id, name, status)
VALUES (41, 'ROLE_USER', 'ACTIVE');
INSERT INTO roles (id, name, status)
VALUES (42, 'ROLE_ADMIN', 'ACTIVE');

INSERT INTO users (id, username, email, password, status)
VALUES (51, 'test@aa.aa', 'test@aa.aa', '$2a$04$kd1eRTJuu6AOFwb1.2MMJOBLe3Yg8.5iuJ1da9ww6ulKhRRjXZEBy', 'ACTIVE');
INSERT INTO users (id, username, email, password, status)
VALUES (52, 'admin@aa.aa', 'admin@aa.aa', '$2a$04$ZfhTh/sluVm9DncWmOxcnu.LJ8OxLLsaZLIjtmMNIteNyQT/hiz0m', 'ACTIVE');

INSERT INTO user_roles (user_id, role_id)
VALUES (51, 41);
INSERT INTO user_roles (user_id, role_id)
VALUES (52, 42)