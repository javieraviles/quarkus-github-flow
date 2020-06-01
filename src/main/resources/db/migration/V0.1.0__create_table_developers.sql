
CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE developer (
	id int8 NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT developer_pkey PRIMARY KEY (id)
);