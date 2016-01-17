# LocalDatabaseServices

The Local Database Service is a SOAP web service. It is on top of a database in order to provide data to the [Storage Service](https://github.com/introsde-2015-FinalProject/StorageServices). It is responsible for handling all persistence tasks on the database.

[API Documentation](http://docs.localdatabaseservice.apiary.io/#)  
[URL of the server (heroku)](http://lds-hidden-taiga-5842.herokuapp.com/ws/people)  
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
