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

- review DAO and resources structure.
  - separate Entity and graphql model?
- fix retrieving todo objects with subtasks from db. (cascadeType?)
- figure out how to "register" Graphql mutations and queries.
- prettify README.md, and fix long "--migrations path" issue.