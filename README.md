# jdbc-postgres
Using JDBC along with Postgres database


### Calling Stored Procedure or Function

When woking with a stored procedure in JDBC, you can often use PreparedStatement with Select clause rather than using a Callable Statement.

### Common Pooling Libraies

A connection pool works as a cache, keeping a gounp of connection alive, responding requests connecting to the database at the same time. It is a common data access pattern, which allows to reuse existing database connections, and therefore reducing the time and resource cost to create a new connection from request to request. 

* Apache Commons DBCP
* C3P0
* HikariCP

### Exception Handling

There is only SQLException in JDBC. It is too general to handle. 

So, may re-pack it into your own exceptions, having an error code and/or into a specific exception. 

Repacking them into Runtime exceptions, and handle them in a higher layer, rather than handling them anywhere.  

If you leak a SQLException, then you have to pollut your method signature; and leaving the invokers concerns. 

So a better way may be re-pack SQLException in a Runtime exception and with an error code. 
