INSERT INTO categories (category)
VALUES  ('Фантастика'),
        ('История'),
        ('Автобиография'),
        ('Философия'),
        ('Роман'),
        ('Психология');

INSERT INTO books (title, year, pages, description, number_copies, category_id_fk)
VALUES ('Мартин Иден', 2012, 416, 'Роман выдающегося американского писателя Джека Лондона о мечте и успехе. Простой моряк, в котором легко узнать самого автора, проходит длинный, полный лишений путь к литературному бессмертию', 2, 5),
       ('Алые Паруса', 1999, 209, 'Повесть-феерия Александра Грина о непоколебимой вере и всепобеждающей, возвышенной мечте, о том, что каждый может сделать для близкого чудо.', 3, 5),
       ('Евгений Онегин', 2000, 448, 'Роман охватывает события с 1819 по 1825 год: от заграничных походов русской армии после разгрома Наполеона до восстания декабристов. Сюжет романа прост и хорошо известен, в центре него — любовная история.', 1, 5),
       ('Анна Каренина', 1998, 864, 'Роман о трагической любви замужней дамы Анны Карениной и блестящего офицера Вронского на фоне счастливой семейной жизни дворян Константина Лёвина и Кити Щербацкой.', 1, 5),
       ('Франкенштейн', 2008, 193, 'В книге рассказывается о жизни и трудах учёного Виктора Франкенштейна, которому удалось постичь тайну зарождения жизни и научиться оживлять безжизненную материю.', 2, 1),
       ('Дракула', 1996, 418, 'Одна из известнейших историй о вампирах, ирландского писателя Брэма Стокера, не раз послужила для постановок в театре и созданий лучших экранизаций.', 3, 1),
       ('Рождение Государства', 2018, 298, 'Книга о становлении российской государственности. Но вместо традиционного рассказа о военных походах и присоединении земель автор акцентирует наше внимание на внутренних аспектах государственного строительства', 2, 3),
       ('Страдающее Средневековье', 2018, 416, 'Эта книга расскажет о том, как в христианской иконографии священное переплеталось с комичным, монструозным и непристойным. Многое из того, что сегодня кажется возмутительным святотатством, в Средневековье, эпоху почти всеобщей религиозности, было вполне в порядке вещей.', 2, 3),
       ('Моя краткая история', 2017, 128, 'Книга представляет собой автобиографию, где учёный от первого лица делится историей свой жизни, начиная с самого детства и заканчивая последними научными достижениями.', 3, 3),
       ('Философия: краткий курс', 2016, 256, 'Книга предлагает отличное введение в основные проблемы философии и рассказывает о тех мыслителях, которые предлагали их решения.', 1, 4),
       ('Психология Влияние', 2011, 429, 'Лучшее учебное пособие по социальной психологии, конфликтологии, менеджменту.', 2, 6),
       ('Введение в психоанализ', 2000, 437, 'В книге изложено систематизированное в 1916-1917 годах самим З. Фрейдом учение о психоанализе, оказавшее огромное влияние на развитие медицины, психиатрии, философии и литературы в XX веке.', 1, 6);

INSERT INTO authors (author)
VALUES ('Джек Лондон'),
       ('Александр Грин'),
       ('Александр Пушкин'),
       ('Лев Толстой'),
       ('Мэри Шелли'),
       ('Брэм Стокер'),
       ('Михаил Кром'),
       ('Сергей Зотов'),
       ('Михаил Майзульс'),
       ('Дильшат Харман'),
       ('Стивен Хокинг'),
       ('Пол Клейнман'),
       ('Роберт Чалдини'),
       ('Зигмунд Фрейд');

INSERT INTO book_authors (book_id_fk, author_id_fk)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (8, 9),
       (8, 10),
       (9, 11),
       (10, 12),
       (11, 13),
       (12, 14);

INSERT INTO roles (role)
VALUES ('Admin'),
       ('Librarian'),
       ('Reader');

INSERT INTO user_statuses (user_status)
VALUES ('Active'),
       ('Blocked'),
       ('Unconfirmed');

INSERT INTO users (login, password, role_id_fk, name, surname, email, user_status_id_fk, confirm)
VALUES ('adminius', 'ccc0efccbeafae925ae3f2987bb170b644b4083d', 1, 'not required', 'not required', 'not required', '1', '0'),
       ('librarianius', 'adc0bbac0ace2ce1580955d519d5c42eef9c5ca1', 2, 'Мария', 'Шилковская', 'test@gmail.com', '1', '0'),
       ('user1', '7928afb4387d9e4d26a30b222ea07211c17eb72a', 3, 'Дмитрий', 'Уласовец', 'user1@gmail.com', '1', '0');

INSERT INTO order_statuses (status)
VALUES ('New'),
       ('Approved'),
       ('Denied');

INSERT INTO order_types (type)
VALUES ('Reading room'),
       ('Home');

INSERT INTO orders (user_id_fk, book_id_fk, order_status_id_fk, order_types_id_fk, order_date, return_date)
VALUES (3, 1, 3, 2, 1599854214000, 1599911154000);