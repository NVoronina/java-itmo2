INSERT INTO users(id, email, name, surname, password)
VALUES ('2b6c0374-f044-4075-a629-a11c0cf6ee32', 'test@vas.te', 'Василий', 'Иванов', 'test'),
       ('3b6c0374-f044-4075-a629-a11c0cf6ee32', 'pete@my.te', 'Василий', 'Петров', 'test'),
       ('4b6c0374-f044-4075-a629-a11c0cf6ee32', 'john@my.te', 'John', 'Doe', 'test'),
       ('5b6c0374-f044-4075-a629-a11c0cf6ee32', 'test@my.judd', 'Nataly', 'Voronina', 'test'),
       ('6b6c0374-f044-4075-a629-a11c0cf6ee32', 'tets@surname.te', 'Test', 'Surname', 'test'),
       ('7b6c0374-f044-4075-a629-a11c0cf6ee32', 'ivan@my.te', 'Иван', 'Сидоров', 'test');

INSERT INTO owners(id, date_start, date_end, registration_document, user_id)
VALUES (1, CURRENT_TIMESTAMP , null, 'DR4444WE', null),
       (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TR8876RT', null),
       (3, CURRENT_TIMESTAMP, null, 'TT8876RT', null),
       (4, CURRENT_TIMESTAMP, null, 'RR8476RT', '6b6c0374-f044-4075-a629-a11c0cf6ee32'),
       (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'EW8876RT', null),
       (6, CURRENT_TIMESTAMP, null, 'OY8876RT', '7b6c0374-f044-4075-a629-a11c0cf6ee32');

INSERT INTO drivers(id, age, license_number, user_id)
VALUES (1, null, 'DHSKDLSDLDLD', null),
       (2, 44, 'PWJVNSSMDVVS', null),
       (3, 56, 'PJVGSKDLSVNS', '2b6c0374-f044-4075-a629-a11c0cf6ee32'),
       (4, 32, 'TYUEKVNDEHVK', '6b6c0374-f044-4075-a629-a11c0cf6ee32'),
       (5, 78, 'UUUUUUEYUISN', null),
       (6, 23, 'HDGSHJFLBUNC', '7b6c0374-f044-4075-a629-a11c0cf6ee32');

INSERT INTO cars(id, brand, seats_count, vin_number, owner_id)
VALUES (1, 'bmw', 3, 'RETYRUUFNNF', 1),
       (2, 'ford', 5, 'EREVSDVSDVSDV',2),
       (3, 'bmw', 4, 'EEGSDSDBBSFBSF', 3),
       (4, 'bmw', 6, 'FSDFSVSDVSDVSDV', 4),
       (5, 'ford', 5, 'YEJKJHSKDJFHSKD', 3),
       (6, 'ford', 4, 'SDVSDVSVSLDV', 2),
       (7, 'bmw', 3, 'YYYYSTTYSG', 1),
       (8, 'mercedes', 5, 'FSGDJHDBJDC',5),
       (9, 'toyota', 4, 'LSJNCKASKC', 3),
       (10, 'lada', 6, 'KDHVBJHBDVKS', 6),
       (11, 'toyota', 5, 'DHBCJHDBHS', 6),
       (12, 'mercedes', 4, 'LDJLSDJLJSD', 2);

INSERT INTO cars_drivers(car_id, driver_id)
VALUES (1,1),
       (1,2),
       (2,3),
       (3,2),
       (4,4),
       (5,6),
       (6,5);