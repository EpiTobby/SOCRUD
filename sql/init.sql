CREATE DATABASE socrud;

\c socrud;

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

INSERT INTO subjects (title, description) VALUES ('Math', 'Mathematics');
INSERT INTO subjects (title, description) VALUES ('Algo', '...');
INSERT INTO subjects (title, description) VALUES ('C', '...');
INSERT INTO subjects (title, description) VALUES ('Management', '...');
INSERT INTO subjects (title, description) VALUES ('Physics', '...');

INSERT INTO programs (title, campus, duration_months, degree_id, tarif, remote_percentage, start_date, description)
VALUES ('IT Engineering', 'Paris', 27, 3, 9200, 0.6, '2022-07-01', 'IT courses');

INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 1, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 2, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 2, 4);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 3, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 3, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 3, 4);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (1, 3, 5);

INSERT INTO programs (title, campus, duration_months, degree_id, tarif, remote_percentage, start_date, description)
VALUES ('Math Physics License', 'Paris', 18, 2, 5000, 0.2, '2022-07-15', 'A license in math physics');

INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 1, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 1, 3);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 1, 4);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 5, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 5, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 5, 3);
INSERT INTO program_subjects (program_id, subject_id, semester_index)
VALUES (2, 5, 4);