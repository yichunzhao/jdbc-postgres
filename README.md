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

Every exception in JDBC is SQLException, is too general to handle. 

So, may repack it into your own exceptions, having an error code and/or into a specific exception. 
and handle them in a higher layer, rather than handling them anywhere.  

If you leak a SQLException, then having to pollute your method signature; and leaving the invokers concerns. 


