/*
	PG Schema
	@author: Adail Carvalho
	@since : 2018-04-28
*/


-- SEQUENCE
DROP SEQUENCE IF EXISTS project_seq CASCADE;
 CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;
 
DROP SEQUENCE IF EXISTS employee_seq CASCADE;
CREATE SEQUENCE employee_seq START WITH 1 INCREMENT BY 1;
 
DROP SEQUENCE IF EXISTS manager_seq CASCADE; 
CREATE SEQUENCE manager_seq START WITH 1 INCREMENT BY 1; 

DROP SEQUENCE IF EXISTS user_seq CASCADE;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
 
-- SCHEMAS / TABLES

CREATE SCHEMA IF NOT EXISTS batch;
DROP TABLE IF EXISTS batch.STAGE_PROJECT;
CREATE TABLE batch.STAGE_PROJECT
(	
	project_cod VARCHAR(32),
	project_name VARCHAR(128),
	plan_init DATE,
	plan_end DATE,
	manager_cod VARCHAR(32),
	manager_name VARCHAR(64),
	manager_email VARCHAR(64),
	manager_skill VARCHAR(512),
	employee_cod VARCHAR(32),
	employee_name VARCHAR(64),
	employee_email VARCHAR(64),
	employee_team VARCHAR(64),
	employee_skill VARCHAR(512)
);

CREATE SCHEMA IF NOT EXISTS staff;
DROP TABLE IF EXISTS staff.EMPLOYEE;

CREATE TABLE staff.EMPLOYEE
(
	id_employee SERIAL NOT NULL,
	cod_employee VARCHAR(64) NOT NULL,
	dsc_team VARCHAR(128),
	dsc_name VARCHAR(64) NOT NULL,
	dsc_email VARCHAR(64),
	dsc_skill VARCHAR(128),
	fk_id_project INTEGER,
	dsc_persisted_by VARCHAR(32) DEFAULT 'API',
	PRIMARY KEY (id_employee, fk_id_project),
	UNIQUE (cod_employee)
);

CREATE SCHEMA IF NOT EXISTS pm;
DROP TABLE IF EXISTS pm.PROJECT;
CREATE TABLE pm.PROJECT
(
	id_project SERIAL NOT NULL,
	fk_id_manager INTEGER NOT NULL,
	fk_id_user INTEGER NOT NULL,
	cod_project VARCHAR(32),
	dsc_project VARCHAR(64),
	dat_init_plan DATE,
	dat_end_plan DATE,
	dsc_persisted_by VARCHAR(32) DEFAULT 'API',
	PRIMARY KEY (id_project),
	UNIQUE (cod_project)
);

CREATE SCHEMA IF NOT EXISTS auth;
DROP TABLE IF EXISTS auth.SYS_USER;
CREATE TABLE auth.SYS_USER
(
	id_user SERIAL,
	cod_username VARCHAR(32) NOT NULL,
	cod_password VARCHAR NOT NULL,
	dsc_user VARCHAR,
	flg_is_admin VARCHAR(1),
	dsc_persisted_by VARCHAR(32) DEFAULT 'API',
	PRIMARY KEY (id_user),
	UNIQUE (cod_username)
);

DROP TABLE IF EXISTS staff.MANAGER;
CREATE TABLE staff.MANAGER
(
	id_manager SERIAL ,
	cod_manager VARCHAR(64) NOT NULL,
	dsc_name VARCHAR(64) NOT NULL,
	dsc_email VARCHAR(64),
	dsc_skill VARCHAR(128),
	dsc_persisted_by VARCHAR(32) DEFAULT 'API',
	PRIMARY KEY (id_manager),
	UNIQUE (cod_manager)
);

-- Foreing Keys
ALTER TABLE pm.PROJECT ADD FOREIGN KEY (fk_id_manager) REFERENCES staff.MANAGER(id_manager);
ALTER TABLE pm.PROJECT ADD FOREIGN KEY (fk_id_user) REFERENCES auth.SYS_USER(id_user);
ALTER TABLE staff.EMPLOYEE ADD FOREIGN KEY (fk_id_project) REFERENCES pm.PROJECT(id_project);

ALTER TABLE pm.PROJECT ADD CONSTRAINT TEST_DATE_INTEG CHECK  (dat_end_plan > dat_init_plan)