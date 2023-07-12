# About
Sample TODO list app using Dropwizard, Graphql as API, and Postgres as storage

# Running
to check status of the schema, run in console:
```
java -jar target/ToDoList-1.0-SNAPSHOT.jar db status .\src\config\todo-list.yml
```

to create the schema, run in console:
```
java -jar target/ToDoList-1.0-SNAPSHOT.jar db migrate .\src\config\todo-list.yml
```

use the following command line arguments
```
server .\src\config\todo-list.yml
```


# Next Steps

- review DAO and resources structure.
  - separate Entity and graphql model?
- fix persisting objects to DB
    ```
    org.hibernate.UnknownEntityTypeException: Unable to locate persister: org.leanix.model.ToDo
        at org.hibernate.metamodel.model.domain.internal.MappingMetamodelImpl.getEntityDescriptor(MappingMetamodelImpl.java:416)
        at org.hibernate.internal.SessionImpl.getEntityPersister(SessionImpl.java:1492)
        at org.hibernate.engine.internal.ForeignKeys.isTransient(ForeignKeys.java:292)
        at org.hibernate.event.internal.EntityState.getEntityState(EntityState.java:59)
        at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.performSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:88)
        at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:78)
        at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:107)
        at org.hibernate.internal.SessionImpl.fireSaveOrUpdate(SessionImpl.java:628)
        at org.hibernate.internal.SessionImpl.saveOrUpdate(SessionImpl.java:620)
        at org.hibernate.internal.SessionImpl.saveOrUpdate(SessionImpl.java:615)
        at io.dropwizard.hibernate.AbstractDAO.persist(AbstractDAO.java:185)
        at org.leanix.db.ToDoDAO.create(ToDoDAO.java:19)
        at org.leanix.ToDoResource.create(ToDoResource.java:18)
        at org.leanix.ToDoResource$ByteBuddy$8jtmsrRw.create$accessor$Useg66Gy(Unknown Source)
        at org.leanix.ToDoResource$ByteBuddy$8jtmsrRw$auxiliary$K0ewbZfX.call(Unknown Source)
        at io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory$MethodInterceptor.invoke(UnitOfWorkAwareProxyFactory.java:167)
        at org.leanix.ToDoResource$ByteBuddy$8jtmsrRw.create(Unknown Source)
        at org.leanix.ToDoListApplication.run(ToDoListApplication.java:31)
        at org.leanix.ToDoListApplication.run(ToDoListApplication.java:14)
        at io.dropwizard.core.cli.EnvironmentCommand.run(EnvironmentCommand.java:66)
        at io.dropwizard.core.cli.ConfiguredCommand.run(ConfiguredCommand.java:98)
        at io.dropwizard.core.cli.Cli.run(Cli.java:78)
        at io.dropwizard.core.Application.run(Application.java:94)
        at org.leanix.ToDoListApplication.main(ToDoListApplication.java:17)
    
    ```
- figure out how to "register" Graphql mutations and queries.