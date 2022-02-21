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


CREATE TABLE if not exists copyright
(
    company_id   BIGINT NOT NULL UNIQUE ,
    is_active    BOOLEAN,
    recording_id BIGINT,
    "FROM_DATE"  TIMESTAMP WITHOUT TIME ZONE,
    "TO_DATE"    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_copyright PRIMARY KEY (company_id)
);

ALTER TABLE copyright
    ADD CONSTRAINT FK_COPYRIGHT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE copyright
    ADD CONSTRAINT FK_COPYRIGHT_ON_RECORDING FOREIGN KEY (recording_id) REFERENCES recording (id);


