INSERT INTO company (total_balance)
values (123.0),
       (122.0);
commit;

INSERT INTO singer (name)
VALUES ('Madonna'),
       ('Michael Jackson'),
       ('Billie Eilish'),
       ('Metallica');

commit;
INSERT INTO company (name)
VALUES ('Black Company'),
       ('The best company'),
       ('Europe label'),
       ('Imagine label');

commit;
INSERT INTO recording (id, song_code, title, version, release_time, singer_id)
VALUES (1, 'SN 123', 'Frozen', '1', '1008-01-15', 1);

commit;
INSERT INTO recording (id, song_code, title, version, release_time, singer_id)
VALUES (2, 'SN 125', 'Earth songs', '1', '1982-01-15', 2);

commit;
INSERT INTO recording (id, song_code, title, version, release_time, singer_id)
VALUES (3, 'SN 126', 'Bad Guy', '1', '2019-01-15', 3);

commit;
INSERT INTO recording (id, song_code, title, version, release_time, singer_id)
VALUES (4, 'SN 127', 'Nothing else matters', '1', '1991-01-15', 4);

commit;

INSERT INTO copyright (company_id, price, creation_date, recording_id)
values (1, 234.0, now(), 1),
       (2, 123.0, now(), 1);
commit;

INSERT INTO copyright (company_id, price, is_active, recording_id, "FROM_DATE", "TO_DATE")
VALUES (3, 132.0, true, 3, now(), now());
commit;

INSERT INTO copyright (company_id, price, is_active, recording_id, "FROM_DATE", "TO_DATE")
VALUES (4, 231.0, true, 4, now(), now());
commit;
