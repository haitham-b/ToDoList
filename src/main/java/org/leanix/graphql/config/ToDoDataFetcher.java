package org.leanix.graphql.config;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;
import org.leanix.db.ToDoDAO;
import org.leanix.model.ToDo;

public class ToDoDataFetcher implements DataFetcher<ToDo> {
    private final ToDoDAO toDoDAO;

    @Inject
    public ToDoDataFetcher(ToDoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    @Override
    public ToDo get(DataFetchingEnvironment environment) {
        return toDoDAO.findById(Long.valueOf(environment.getArgument("id")));
    }
}
