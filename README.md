# jdbc-postgres
Using JDBC along with Postgres database


### Calling Stored Procedure or Function

When woking with a stored procedure in JDBC, you can often use PreparedStatement with Select clause rather than using a Callable Statement.

### Common Pooling Libraies

A connection pool works as a cache, keeping a gounp of connection alive. 

* Apache Commons DBCP
* C3P0
* HikariCP
