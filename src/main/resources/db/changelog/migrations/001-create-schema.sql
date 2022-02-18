CREATE TABLE if not exists singer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE if not exists recording
(
    id           BIGSERIAL PRIMARY KEY,
    song_code    VARCHAR(32),
    title        VARCHAR(4096),
    version      VARCHAR(128),
    release_time TIMESTAMP,
    singer_id    BIGINT REFERENCES singer (id)
);

CREATE TABLE if not exists company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256)
);

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


INSERT INTO recording (song_code, title, version, release_time, singer_id)
VALUES ('SN 123', 'Frozen', '1', '1008-01-15', 1);

INSERT INTO recording (song_code, title, version, release_time, singer_id)
VALUES ('SN 125', 'Earth songs', '1', '1982-01-15', 2);

INSERT INTO recording (song_code, title, version, release_time, singer_id)
VALUES ('SN 126', 'Bad Guy', '1', '2019-01-15', 3);

INSERT INTO recording (song_code, title, version, release_time, singer_id)
VALUES ('SN 127', 'Nothing else matters', '1', '1991-01-15', 4);

CREATE TABLE if not exists copyright
(
    company_id   BIGINT NOT NULL,
    is_active    BOOLEAN,
    recording_id BIGINT,
    "from"       TIMESTAMP WITHOUT TIME ZONE,
    "to"         TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_copyright PRIMARY KEY (company_id)
);

ALTER TABLE copyright
    ADD CONSTRAINT FK_COPYRIGHT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE copyright
    ADD CONSTRAINT FK_COPYRIGHT_ON_RECORDING FOREIGN KEY (recording_id) REFERENCES recording (id);

INSERT INTO copyright (company_id, is_active, recording_id, "from", "to")
VALUES (1, true, 1, now(), now());

INSERT INTO copyright (company_id, is_active, recording_id, "from", "to")
VALUES (2, true, 2, now(), now());

INSERT INTO copyright (company_id, is_active, recording_id, "from", "to")
VALUES (3, true, 3, now(), now());

INSERT INTO copyright (company_id, is_active, recording_id, "from", "to")
VALUES (4, true, 4, now(), now());

