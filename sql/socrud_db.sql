create table subjects
(
    id          serial
        constraint subjects_pk
            primary key,
    title       varchar not null,
    description text
);

create unique index subjects_title_uindex
    on subjects (title);

create table degrees
(
    id    SERIAL PRIMARY KEY,
    title varchar                                                not null
);

create table programs
(
    id                SERIAL PRIMARY KEY,
    title             varchar                                            not null,
    campus            varchar,
    duration_months          integer,
    degree_id         integer
        constraint programs_degrees_id_fk
            references degrees
            on update cascade on delete cascade,
    tarif             integer,
    remote_percentage integer,
    start_date       date,
    description       text
);

create unique index programs_title_uindex
    on programs (title);

create table program_subjects
(
    id             SERIAL PRIMARY KEY,
    program_id       integer                                                    not null
        constraint subject_tracks_tracks_id_fk
            references programs (id)
            on update cascade on delete cascade,
    subject_id     integer                                                    not null
        constraint subject_tracks_subjects_id_fk
            references subjects
            on update cascade on delete cascade,
    semester_index integer                                                    not null
);

INSERT INTO degrees (title) values ('BTS');
INSERT INTO degrees (title) values ('LICENCE');
INSERT INTO degrees (title) values ('MASTER');
INSERT INTO degrees (title) values ('DOCTORAT');