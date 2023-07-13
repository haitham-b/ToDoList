# About
Sample TODO list app using Dropwizard, Graphql as API, and Postgres as storage

# Running
to check status of the schema, run in console:
```
java -jar target/ToDoList-1.0-SNAPSHOT.jar db status --migrations C:\Users\Haitham\IdeaProjects\ToDoList2\ToDoList\src\main\resources\migrations.xml .\src\config\todo-list.yml
```

to create the schema, run in console:
```
java -jar target/ToDoList-1.0-SNAPSHOT.jar db migrate --migrations C:\Users\Haitham\IdeaProjects\ToDoList2\ToDoList\src\main\resources\migrations.xml .\src\config\todo-list.yml
```

use the following command line arguments
```
server .\src\config\todo-list.yml
```

# To connect to the postgres image and list tables:
```
docker ps
docker exec -it <image_id> bash
psql -U pg-user db
> \l  # to list schemas
> \d  # to list tables
```


# Next Steps

- fix issue with findAll() query
  ```
  java.lang.IllegalArgumentException: No query is registered under the name `org.leanix.model.ToDo.findAll`
	at org.hibernate.internal.AbstractSharedSessionContract.buildNamedQuery(AbstractSharedSessionContract.java:1005)
	at org.hibernate.internal.AbstractSharedSessionContract.createNamedQuery(AbstractSharedSessionContract.java:871)
	at org.hibernate.internal.AbstractSharedSessionContract.createNamedQuery(AbstractSharedSessionContract.java:126)
	at io.dropwizard.hibernate.AbstractDAO.namedTypedQuery(AbstractDAO.java:74)
	at org.leanix.db.ToDoDAO.findAll(ToDoDAO.java:27)
	at org.leanix.resources.ToDoResource.findAll(ToDoResource.java:23)
	at org.leanix.resources.ToDoResource$ByteBuddy$PcD0QpnW.findAll$accessor$70QhBDNL(Unknown Source)
	at org.leanix.resources.ToDoResource$ByteBuddy$PcD0QpnW$auxiliary$tyOW8oGy.call(Unknown Source)
	at io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory$MethodInterceptor.invoke(UnitOfWorkAwareProxyFactory.java:167)
	at org.leanix.resources.ToDoResource$ByteBuddy$PcD0QpnW.findAll(Unknown Source)
	at org.leanix.ToDoListApplication.run(ToDoListApplication.java:43)
	at org.leanix.ToDoListApplication.run(ToDoListApplication.java:21)
	at io.dropwizard.core.cli.EnvironmentCommand.run(EnvironmentCommand.java:66)
	at io.dropwizard.core.cli.ConfiguredCommand.run(ConfiguredCommand.java:98)
	at io.dropwizard.core.cli.Cli.run(Cli.java:78)
	at io.dropwizard.core.Application.run(Application.java:94)
	at org.leanix.ToDoListApplication.main(ToDoListApplication.java:24)
  ```
- review DAO and resources structure.
  - separate Entity and graphql model?
- fix retrieving todo objects with subtasks from db. (cascadeType?)
- figure out how to "register" Graphql mutations and queries.
- prettify README.md, and fix long "--migrations path" issue.