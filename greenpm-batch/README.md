# GreenPM Batch

Support to importing project data from a CSV File.

## Build

Generating .jar:

	mvn clean package
	
	
## Preparing before running

### File

The file to be imported must be provided on the following directories:

*Path*

*	On Windows: C:/opt/greenpm
*	On Linux :	/opt/greenpm

*File structure*

*	name: gm-challenges
*	extension: csv
*	delimited by: (,)

The file must have the following *header*:

		# ProjectName,PlannedStart,PlannedEnd,PM,PMEmail,PMSkills,EmployeeName,EmployeeEmail,EmployeeTeam,EmployeeSkills

## Default Actions

*	All the projects created through the batch processing are marked as *BATCH* on column **DSC_PERSISTED_BY**, from **pm.PROJECT** table;
*	Batch has a pre-defined user(see *data.sql or pg_schema on src/main/resources*). All the projects persisted through batch are associated to Batch's user.

## Running 

	mvn spring-boot-run
	
## Job

Bellow is described how **BatchConfiguration.java** (*src/main/java/~/BatchConfiguration.java*) is structured.

### Import Project Job

A job that imports data from the input file, process the information and register them on the **greenpm** database.
After job execution, the data may be accessed through the **GreenPM API**. The code snippet bellow ilustrates the Job flow: 

	@Bean
    public Job importProjectJob(JobCompletionNotificationListener listener, 
            Step processRawDataStp1, Step processManagerDataStp2, Step processUserDataStp3,
            Step processProjectDataStp4, Step processEmployeeDataStp5) {
        return builderFactory.get("importProjectJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(processRawDataStp1)
            .next(processManagerDataStp2)
            .next(processUserDataStp3)
            .next(processProjectDataStp4)
            .next(processEmployeeDataStp5)
            .build();
    }

**Step processRawDataStp1**

*	Reader: Reads data from csv;
*	Processor: Generates codes ;
*	Writer: Writes data to table **batch.STAGE_PROJECT**.

**Step processManagerDataStp2**

*	Reader: Reads manager data from batch.STAGE_PROJECT;
*	Processor: Process manager information;
*	Writer: Writes data to table **staff.MANAGER**.
 
**Step processUserDataStp3** 

*	Reader: Reads employee data from batch.STAGE_PROJECT;
*	Processor: Process employees information;
*	Writer: Writes data to table **auth.SYS_USER**. Employees are registered as users, with defaul *password* equals to username.
    
**Step	processProjectDataStp4**

*	Reader: Reads project data from batch.STAGE_PROJECT;
*	Processor: Process project information;
*	Writer: Writes data to table **pm.PROJECT**, associating their managers and *BATCH* as user.
    
**Step processEmployeeDataStp5**

*	Reader: Reads employee data from batch.STAGE_PROJECT;
*	Processor: Process project information;
*	Writer: Writes data to table **staff.EMPLOYEE**, associating the projects each employee participates.
