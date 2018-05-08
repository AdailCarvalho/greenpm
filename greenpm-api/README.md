# GreenPM API

Provides project data access.

## Building

Generates the war file:

	mvn clean package
	
## Running

Starts the application using embed Tomcat provided by Spring:

	mvn spring-boot:run

## End points



### /projects

**Action - creating a new project:**

*Request template*

	*	Method: HTTPPost

	*	Content-Type: application/json

	* Example:

	POST /projects
	{
		"projectName":"Track Company X",
		"username": "admin",
		"password" : "admin123",
		"pmName":"Anderson Mendes",
		"pmEmail" : "anderson@gmail.com", 
		"pmSkill" : "Scrum,Kanban,Lean Startup,XP",
		"employee":
		[
			{"name":"Jay Bilzerian", "email": "bilzerian@yahoo.com","team":"Quality Assurance", "skill":"E2E Tests,Manual Tests,API Integration Tests"},
			{"name":"Missy","email": "missy@gmail.com" ,"team":"Integration", "skill":"Apache Kafka,Active MQ,Hadoop,Presto"}
		],
		"planDatInit":"05/21/2018",
		"planDatEnd" :"10/16/2018"
	}

*Returns*

A generated ID for the project.

*Throws*

*	*EntityExistsException* if a project with same code already exists.

**Action - list all projecst**

*Request template*

	GET	/projects
	
	{
	}

*Returns*

A list representing all(max = 100) the projects registered on database.

**Action - get a specific project**

*Request template*

	GET /projects/{projectCod}
	{
	}
	
The project code is generated using the project description. You need to supply
it to get a project information. E.g: Project Track Company, code = TRC-20183:

	GET / projects/TRC-20183
	{
	}
	
*Returns*

A project with the informed code.

	[
		{
			"projectCod": "TRC-20183",
			"projectName": "Anderson Mendes",
			"pmName": "anderson@gmail.com",
			"planDatInit": "2018-05-21",
			"planDatEnd": "2018-10-16",
			"user": "admin",
			"employee": [
				{
					"codEmployee": null,
					"name": "Jay Bilzerian",
					"team": "Quality Assurance",
					"email": null,
					"skill": "E2E Tests,Manual Tests,API Integration Tests"
				},
				{
					"codEmployee": null,
					"name": "Missy",
					"team": "Integration",
					"email": null,
					"skill": "Apache Kafka,Active MQ,Hadoop,Presto"
				}
			]
		}
	]

*Throws*

*	*EntityNotFoundException* if project with the informed code was not found on database.

**Action - file a project**

*Request template*

	PUT	/projects/{projectCod}

Informe the project code you want to file. The attribute *flg_is_closed* on table **pm.PROJECT** will
be setted to 'Y' (defaul is 'N'), indicating that the project is filed.

	PUT /projects/TRC-20183

*Throws*

*	*EntityNotFoundException* if project with the informed code was not found on database.


The 
	
### /users

**Action - register a new user**

*Request template*

	POST /users
	{
		"username" : "adailcf",
		"password" : "july#04",
		"dscUsername" : "Adail Carvalho",
		"flgIsAdmin" : "Y"
	}
*Throws*

*	*EntityExistsException* if a user with same username already exists on database.

**Action - list all users**

*Request template*

	GET /users
	{
	}
	
*Returns*

A list containing all registered users (max = 100).

	[
		{
			"id": 2,
			"username": "bilzerian",
			"descUsername": "bilzerian",
			"isAdmin": false
		},
		{
			"id": 3,
			"username": "birch",
			"descUsername": "birch",
			"isAdmin": false
		},
		{
			"id": 4,
			"username": "bojack",
			"descUsername": "bojack",
			"isAdmin": false
		},
		{
			"id": 5,
			"username": "butter",
			"descUsername": "butter",
			"isAdmin": false
		}
	]

**Action - get a specific user**

*Request template*

	GET /users/{username}
	{
	}

*Returns*

A user with the specific username. E.g: username = adailcf

	{
		"id": 10,
		"username": "adailcf",
		"descUsername": "Adail Carvalho",
		"isAdmin": true
	}

*Throws*

*	*EntityNotFoundException* if a user with the given name was not found on database.