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

create table admins
(
    id          SERIAL PRIMARY KEY,
    login       varchar                                                not null,
    password    varchar                                                not null
);

INSERT INTO degrees (id, title) values (1, 'BTS');
INSERT INTO degrees (id, title) values (2, 'LICENCE');
INSERT INTO degrees (id, title) values (3, 'MASTER');
INSERT INTO degrees (id, title) values (4, 'DOCTORAT');

INSERT INTO subjects (id, title, description) values (1, 'ASE', 'Analyse Statistique et Donn??es');
INSERT INTO subjects (id, title, description) values (2, 'AMS', 'Analyse mod??lisation num??rique');

/* MTI */
INSERT INTO subjects (id, title, description) values (3, 'SEO', 'M??thodes d''optimisation pour les moteurs de recherche');
INSERT INTO subjects (id, title, description) values (4, 'SOCRA', 'Sofware Craftsmanship');
INSERT INTO subjects (id, title, description) values (5, 'MPRO', 'Management et Pilotage de projets');
INSERT INTO subjects (id, title, description) values (6, 'DEVOPS', 'Approche DevOps');

/* IMAGE */
INSERT INTO subjects (id, title, description) values (7, 'MLRF', 'ML pour la reconnaissance des formes dans les images');
INSERT INTO subjects (id, title, description) values (8, 'OCVX1', 'Analyse Convexe et Programmation Math??matique (1)');
INSERT INTO subjects (id, title, description) values (9, 'OCVX2', 'Analyse Convexe et Programmation Math??matique (2)');
INSERT INTO subjects (id, title, description) values (10, 'POGL', 'Programmation OpenGL');
INSERT INTO subjects (id, title, description) values (11, 'PRST', 'Probabilit??s et Statistiques pour l''image');

/* SCIA */
INSERT INTO subjects (id, title, description) values (12, 'Big Data', 'Big Data');
INSERT INTO subjects (id, title, description) values (13, 'SCALA', 'SCALA');
INSERT INTO subjects (id, title, description) values (14, 'PTML', 'Python, Introduction au Machine Learning');
INSERT INTO subjects (id, title, description) values (15, 'RAND', 'Langage R et analyse de donn??es');

/* GISTRE */
INSERT INTO subjects (id, title, description) values (16, 'IOT1', 'Internet des Objets et Bluetooth Low Energy');
INSERT INTO subjects (id, title, description) values (17, 'ARM', 'Technologies ARM');
INSERT INTO subjects (id, title, description) values (18, 'WIN32', 'API syst??me WIN32');
INSERT INTO subjects (id, title, description) values (19, 'ELEC', 'Electronique GISTRE');

/* GITM */
INSERT INTO subjects (id, title, description) values (20, 'HTUDA', 'How to use DevOps Approach');
INSERT INTO subjects (id, title, description) values (21, 'SPFBD', 'Spark & Python for Big Data');
INSERT INTO subjects (id, title, description) values (22, 'ItBC', 'Introduction to Block Chain & Bitcoin');
INSERT INTO subjects (id, title, description) values (23, 'MIT', 'Managing International Teams');

/* ICE */
INSERT INTO subjects (id, title, description) values (24, 'BUSI', 'BUSINESS INFORMATION');
INSERT INTO subjects (id, title, description) values (25, 'PYTH', 'Python');
INSERT INTO subjects (id, title, description) values (26, 'AMBI', 'Ambition et Projet');
INSERT INTO subjects (id, title, description) values (27, 'MATH', 'Maths');

/* SANTE */
INSERT INTO subjects (id, title, description) values (28, 'ISDM', 'Interop??rabilit?? et Standards de Donn??es M??dicales');
INSERT INTO subjects (id, title, description) values (29, 'IREN', 'Initiation aux r??seaux de Neurones et Deep Learning');
INSERT INTO subjects (id, title, description) values (30, 'Doctolib', 'Doctolib');
INSERT INTO subjects (id, title, description) values (31, 'IMMI', 'Imagerie M??dicale & IA / MICCAI');

/* SIGL */
INSERT INTO subjects (id, title, description) values (32, 'SOAR', 'Solution Architecture');
INSERT INTO subjects (id, title, description) values (33, 'OPDI', 'Organisation et pilotage d???une DSI');
INSERT INTO subjects (id, title, description) values (34, 'AMOA1', 'Ma??trise d''Ouvrage des Syst??mes d''Information (1)');
INSERT INTO subjects (id, title, description) values (35, 'URSI1', 'Urbanisation des SI (1)');
INSERT INTO subjects (id, title, description) values (36, 'OPCO1', 'Organisation Personnelle et Communication (1)');

/* SRS */
INSERT INTO subjects (id, title, description) values (37, 'MOSE', 'Mobilit?? & S??curit?? Applications Mobiles');
INSERT INTO subjects (id, title, description) values (38, 'SIDU', 'S??curit?? des syst??mes industriels');
INSERT INTO subjects (id, title, description) values (39, 'DACE', 'Datacenter : Infrastructure et services');
INSERT INTO subjects (id, title, description) values (40, 'SCCO', 'S??curit?? et conduite du changement dans les organisations');

/* STARTUP */
INSERT INTO subjects (id, title, description) values (41, 'DMIOS', 'D??veloppement mobile iOS');
INSERT INTO subjects (id, title, description) values (42, 'DVBE', 'D??veloppement Back-End');
INSERT INTO subjects (id, title, description) values (43, 'IPMA', 'Introduction au Product Management');
INSERT INTO subjects (id, title, description) values (44, 'BUMO', 'Business Models');

/* TCOM */
INSERT INTO subjects (id, title, description) values (45, 'UMTS1', 'R??seaux de Mobiles : EDGE / UMTS (niveau 1)');
INSERT INTO subjects (id, title, description) values (46, 'PCEQ', 'Points cl??s en qualit?? pour les m??tiers TCOM');
INSERT INTO subjects (id, title, description) values (47, 'CALCROI', 'Calcul de ROI en T??l??com');
INSERT INTO subjects (id, title, description) values (48, 'SNA1', 'R??seaux SNA [niveau-1]');

/* SPE */

INSERT INTO subjects(id, title, description) values (49, 'maths', 'Math??matiques');
INSERT INTO subjects(id, title, description) values (50, 'prog', 'Programmation');
INSERT INTO subjects(id, title, description) values (51, 'archi', 'Architecture');
INSERT INTO subjects(id, title, description) values (52, 'angl', 'Anglais');
INSERT INTO subjects(id, title, description) values (53, 'elec', 'Electronique');
INSERT INTO subjects(id, title, description) values (54, 'te', 'Technique d''expression');

/* SUP */
INSERT INTO subjects(id, title, description) values (55, 'phys', 'Physiques');


INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (1, 'MTI', 'Multimedia et technologies de l''information', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (2, 'IMAGE', 'TRAITEMENT, SYNTHESE ET ANALYSE D???IMAGES', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (3, 'SCIA', 'DATA SCIENCE ET INTELLIGENCE ARTIFICIELLE', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (4, 'GISTRE', 'G??nie Informatique des Syst??mes Temps R??el et Embarqu??s', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (5, 'GITM', 'Global IT Management', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (6, 'ICE', 'IT, CONSULTING & EXPLORATION', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (7, 'SANTE', 'Num??rique et Sant??', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (8, 'SIGL', 'Syst??me d???Information et G??nie Logiciel', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (9, 'SRS', 'Syst??mes, R??seaux et S??curit??', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (10, 'STARTUP', 'Startup', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (11, 'TCOM', 'T??l??communications', 'EPITA KB', 12, 3, '50000', 10, '2019-01-01');

INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (12, 'SPE', 'Seconde ann??e du cycle pr??paratoire', 'EPITA VJ', 12, 2, '45000', 0, '2019-01-01');
INSERT INTO programs (id, title, description, campus, duration_months, degree_id, tarif, remote_percentage, start_date) VALUES (13, 'SUP', 'Premi??re ann??e du cycle pr??paratoire', 'EPITA VJ', 12, 2, '45000', 0, '2019-01-01');

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 3, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 4, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 5, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (1, 6, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 7, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 8, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 9, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 10, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (2, 11, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 12, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 13, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 14, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (3, 15, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 16, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 17, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 18, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (4, 19, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 20, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 21, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 22, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (5, 23, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 24, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 25, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 26, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (6, 27, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 28, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 29, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 30, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (7, 31, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 32, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 33, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 34, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 35, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (8, 36, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 37, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 38, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 39, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (9, 40, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 41, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 42, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 43, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (10, 44, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 1, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 2, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 45, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 46, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 47, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (11, 48, 1);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 49, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 50, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 51, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 52, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 53, 2);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (12, 54, 2);

INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 49, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 50, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 51, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 52, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 53, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 54, 1);
INSERT INTO program_subjects (program_id, subject_id, semester_index) VALUES (13, 55, 1);
