# GreenPM

A service that provides support to project management.

## Modules

*	*GreenPM-API*
*	*GreenBatch*


## Requirements

### General

*	*Java 8*
*	*Maven 3.5*
*	*PostgreSQL 9.6*

### Configuring database

API and Batch modules share the same server to process information. The *pg_schema* (src/main/resources)
provides the necessary database structure for both applications use. **The DDL auto mode is disable, by default**.

*	Defines a new database  **greenpm**;
*	Apply the script *pg_schema* (*src/main/resources*) on **greenpm** database.

The picture bellow ilustrates the Database MR:

![relacao](https://user-images.githubusercontent.com/6726365/39716052-98aff814-5205-11e8-8ae4-0c5a0318e6c2.png)


Detailing entities:

*API / BATCH*

*	**sys_user**: stores system users.
*	**employee**:  stores information about employees and which project they participate.
*	**manager**: stores information about project managers.
*	**project**: stores information about the project.

*BATCH*

*	**stage_project**: stores the data imported from the file.

## Building

To build the projects, run:

	mvn clean package