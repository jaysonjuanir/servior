CREATE TABLE person (
    person_id bigserial primary key NOT NULL,
    person_last_name varchar(250) NOT NULL,
    person_first_name varchar(250) NOT NULL,
    person_middle_name varchar(250) NOT NULL,
    person_suffix varchar(250),
    person_title varchar(250)
);