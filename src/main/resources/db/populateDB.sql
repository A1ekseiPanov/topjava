DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

ALTER SEQUENCE meals_id_seq RESTART WITH 1;
INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2023-02-19 18:39:00.000000', 'хот дог', 1200, 100000),
       ('2023-02-20 18:40:00.000000', 'чизбургер', 1500, 100000),
       ('2023-02-21 18:41:00.000000', 'пицца', 1600, 100000),
       ('2023-02-19 18:39:00.000000', 'рыба', 200, 100001),
       ('2023-02-20 18:43:00.000000', 'бургер', 1500, 100001),
       ('2023-02-21 18:47:00.000000', 'пицца', 1600, 100001),
       ('2023-02-19 18:39:00.000000', 'гречка', 50, 100002),
       ('2023-02-20 18:52:00.000000', 'сыр', 20, 100002),
       ('2023-02-21 19:22:00.000000', 'яблоко', 30, 100002);