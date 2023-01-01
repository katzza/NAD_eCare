INSERT INTO tariff (tariff_id, tariff_name, tariff_price, tariff_grade)
VALUES (10, 'test-XS', 3., 0);
INSERT INTO tariff (tariff_id, tariff_name, tariff_price, tariff_grade)
VALUES (11, 'test-S', 7., 1);
INSERT INTO tariff (tariff_id, tariff_name, tariff_price, tariff_grade)
VALUES (12, 'test-M', 10., 2);
INSERT INTO tariff (tariff_id, tariff_name, tariff_price, tariff_grade)
VALUES (13, 'test-L', 15., 3);
INSERT INTO tariff (tariff_id, tariff_name, tariff_price, tariff_grade)
VALUES (14, 'test-XL', 20., 4);

INSERT INTO option (option_id, option_name, option_price)
VALUES (21, 'Test-MultiSIM', 5.5);
INSERT INTO option (option_id, option_name, option_price)
VALUES (22, 'Test-Hotspot Flat', 9.);
INSERT INTO option (option_id, option_name, option_price)
VALUES (23, 'Test-Spotify', 6.);

INSERT INTO contract (contract_id, business_id, tariff_id)
VALUES (31, '777', 12);

INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 21);
INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 22);
