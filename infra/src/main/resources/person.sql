DROP TABLE if EXISTS address CASCADE;
CREATE TABLE address (
    address_id bigserial NOT NULL,
    address_street_number varchar(250) NOT NULL,
    address_barangay varchar(250) NOT NULL,
    address_city varchar(250) NOT NULL,
    address_zipcode varchar(250) NOT NULL,
	CONSTRAINT address_pkey PRIMARY KEY (address_id)
);

DROP TABLE if EXISTS person CASCADE;
CREATE TABLE person (
    person_id bigserial NOT NULL,
	person_first_name varchar(250) NOT NULL,
    person_middle_name varchar(250) NOT NULL,
	person_last_name varchar(250) NOT NULL,
    person_suffix varchar(250),
    person_title varchar(250),
	gwa real,
	address_id bigint NOT NULL,
	person_birthday date,
	person_date_hired date,
	person_employed boolean,
	CONSTRAINT person_pkey PRIMARY KEY (person_id),
	CONSTRAINT address_fkey FOREIGN KEY (address_id)
    REFERENCES address (address_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  	CONSTRAINT address_akey UNIQUE (address_id)
);

DROP TABLE if EXISTS contact CASCADE;
CREATE TABLE contact (
    contact_id bigserial NOT NULL,
    contact_type varchar(250) NOT NULL,
    contact_value varchar(250) NOT NULL,
    person_id bigint NOT NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (contact_id),
	CONSTRAINT person_fkey FOREIGN KEY (person_id)
	REFERENCES person(person_id) MATCH SIMPLE ON DELETE CASCADE
);

DROP TABLE if EXISTS personrole CASCADE;
CREATE TABLE personrole (
    personrole_id bigserial NOT NULL,
    person_id bigint NOT NULL,
    role_id bigint NOT NULL,
	CONSTRAINT personrole_pkey PRIMARY KEY (personrole_id),
	CONSTRAINT person_fkey FOREIGN KEY (person_id)
	REFERENCES person(person_id) MATCH SIMPLE ON DELETE CASCADE
);

DROP TABLE if EXISTS roles CASCADE;
CREATE TABLE roles (
    role_id bigserial NOT NULL,
    role_type varchar(250) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (role_id)
);



 CONSTRAINT person_pkey PRIMARY KEY (id),
  CONSTRAINT address_fkey FOREIGN KEY (address_id)
      REFERENCES address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT address_akey UNIQUE (address_id)


CREATE TABLE test1(
	id bigserial NOT NULL, 
	name character varying(255), 
	CONSTRAINT test_pkey PRIMARY KEY (id)
);

CREATE TABLE test2(
	id bigserial NOT NULL, 
	name character varying(255), 
	test_id bigint, 
	CONSTRAINT test2_pkey PRIMARY KEY (id), 
	CONSTRAINT test1_fkey FOREIGN KEY (test_id) 
	REFERENCES test1(id) MATCH SIMPLE ON DELETE CASCADE
);