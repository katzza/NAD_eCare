INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (10, 'testXS', 'test-XS', 3., 0);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (11, 'testS', 'test-S', 7., 1);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (12, 'testM', 'test-M', 10., 2);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (13, 'testL', 'test-L', 15., 3);
INSERT INTO tariff (tariff_id, tariff_name, tariff_description, tariff_price, tariff_grade)
VALUES (14, 'testXL', 'test-XL', 20., 4);

INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (21, 'TestMultiSIM', 'Test-MultiSIM', 5.5);
INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (22, 'TestHotspotFlat', 'Test-Hotspot Flat', 9.);
INSERT INTO option (option_id, option_name, option_description, option_price)
VALUES (23, 'TestSpotify', 'Test-Spotify', 6.);

INSERT INTO contract (contract_id, business_id, tariff_id)
VALUES (31, '777', 12);

INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 21);
INSERT INTO tariff_option (tariff_id, option_id)
VALUES (12, 22);