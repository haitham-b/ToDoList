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

- fix issue "No session currently bound to execution context"
  ```
  ! org.hibernate.HibernateException: No session currently bound to execution context
  ! at org.hibernate.context.internal.ManagedSessionContext.currentSession(ManagedSessionContext.java:58)
  ! at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:514)
  ! at io.dropwizard.hibernate.AbstractDAO.currentSession(AbstractDAO.java:42)
  ! at io.dropwizard.hibernate.AbstractDAO.get(AbstractDAO.java:170)
  ! at org.leanix.db.ToDoDAO.findById(ToDoDAO.java:15)
  ! at org.leanix.graphql.config.ToDoDataFetcher.get(ToDoDataFetcher.java:21)
  ! at org.leanix.graphql.config.ToDoDataFetcher.get(ToDoDataFetcher.java:10)
  ! at graphql.execution.ExecutionStrategy.invokeDataFetcher(ExecutionStrategy.java:309)
  ! at graphql.execution.ExecutionStrategy.fetchField(ExecutionStrategy.java:286)
  ! at graphql.execution.ExecutionStrategy.resolveFieldWithInfo(ExecutionStrategy.java:212)
  ! at graphql.execution.AsyncExecutionStrategy.execute(AsyncExecutionStrategy.java:55)
  ! at graphql.execution.Execution.executeOperation(Execution.java:161)
  ! at graphql.execution.Execution.execute(Execution.java:104)
  ! at graphql.GraphQL.execute(GraphQL.java:557)
  ! at graphql.GraphQL.lambda$parseValidateAndExecute$11(GraphQL.java:476)
  ! at java.base/java.util.concurrent.CompletableFuture.uniComposeStage(CompletableFuture.java:1187)
  ! at java.base/java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:2309)
  ! at graphql.GraphQL.parseValidateAndExecute(GraphQL.java:471)
  ! at graphql.GraphQL.executeAsync(GraphQL.java:439)
  ! at graphql.kickstart.execution.GraphQLInvoker.executeAsync(GraphQLInvoker.java:37)
  ! at graphql.kickstart.execution.GraphQLInvoker.execute(GraphQLInvoker.java:28)
  ! at graphql.kickstart.servlet.HttpRequestInvokerImpl.invoke(HttpRequestInvokerImpl.java:200)
  ! at graphql.kickstart.servlet.HttpRequestInvokerImpl.lambda$invokeAndHandleAsync$2(HttpRequestInvokerImpl.java:84)
  ! at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
  ! at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
  ! at java.base/java.lang.Thread.run(Thread.java:833)
  ```

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
- introduce ToDoInput for the creation of ToDo items.
- prettify README.md, and fix long "--migrations path" issue.