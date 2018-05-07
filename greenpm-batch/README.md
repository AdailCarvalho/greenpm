# GreenPM Batch

Support to importing project data from a CSV File.

## Build

Generating .jar:

	mvn clean package
	
	
## Preparing before running

### File

*Path*

*	On Windows: C:/opt/greenpm
*	On Linux :	/opt/greenpm

*File structure*

*	name: gm-challenges
*	extension: csv
*	delimited by: (,)

The file must have the following *header*:

		# ProjectName,PlannedStart,PlannedEnd,PM,PMEmail,PMSkills,EmployeeName,EmployeeEmail,EmployeeTeam,EmployeeSkills

## Running 

	mvn spring-boot-run
	
## Jobs

### Import Project Job

A project that imports the job 