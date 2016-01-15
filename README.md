# LocalDatabaseServices

The Local Database Service is a SOAP web service. It is on top of a database in order to provide data to the [Storage Service](https://github.com/introsde-2015-FinalProject/StorageServices). It is responsible for handling all persistence tasks on the database.

[URL of the server (heroku)](https://lds-hidden-taiga-5842.herokuapp.com/sdelab)  
[WSDL file](https://lds-hidden-taiga-5842.herokuapp.com/ws/people?wsdl)

### Install
In order to execute this server locally you need the following technologies (in the brackets you see the version used to develop):

* Java (jdk1.8.0)
* ANT (version 1.9.4)

Then, clone the repository. Run in your terminal:

```
git clone https://github.com/introsde-2015-FinalProject/LocalDatabaseServices.git && cd LocalDatabaseServices
```

and run the following command:
```
ant install
```

### Getting Started
To run the server locally then run:
```
ant start
```

### API Documentation

|Return Codes|
|---|
| 1: OK|
|-2: No resource|
|-1: ERROR|


## **Person**
| Provided Methods | Return |
|------------------|--------|
|createPerson(Person person)| idPerson |
|getPerson(int idPerson)| Person |
|updatePerson(Person person)| idPerson |
|deletePerson(int idPerson)| idPerson |
|||
|getPeopleList|List of Person|
|getCurrentHealth(int idPerson)|List of Measure |
|ListgetVitalSigns|List of Measure|


## **Doctor**
|Provide Methods|Return|
|---|---|
|createDoctor(Doctor doctor)| idDoctor |
|getDoctor(int idDoctor)| Doctor |
|updateDoctor(Doctor doctor)| idDoctor |
|deleteDoctor(int idDoctor)| idDoctor |
|||
|getPersonByDoctor(int idDoctor)|List of Person|


## **Family**
| Provided Methods | Return |
|------------------|--------|
|getFamily(int idFamily)|Family|


## **Target**
| Provided Methods | Return |
|------------------|--------|
|createTarget(Target target,int idPeson)| idTarget |
|getTarget(int idPerson, int idMeasureDef)| List of Target |
|updateTarget(Target target)| idTarget |
|deleteTarget(int idTarget)| idTarget |
|||
|getTargetList|List of Target|


## **Reminder**
| Provided Methods | Return |
|------------------|--------|
|createReminder(Reminder reminder, int idPeson)| idReminder |
|getReminder(int idReminder)| List of Reminder |
|updatePerson(Reminder reminder)| idReminder |
|deletePerson(int idReminder)| idReminder |


## **Measure**
| Provided Methods | Return |
|------------------|--------|
|createMeasure(Measure measure, int idPerson)| idPerson |
|getMeasure(int idMeasure)| List of Measure |
|updateMeasure(Measure Measure)| idMeasure |
|deleteMeasure(int idMeasure)| idMeasure |

## **MeasureDefinition**
| Provided Methods | Return |
|------------------|--------|
|getMeasureDefinition()| List of MeasureDefinition |
