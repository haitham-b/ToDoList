package org.leanix.model;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.leanix.model.ToDo;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ToDoListDataFetcher implements DataFetcher<ToDo> {
    @Override
    public ToDo get(DataFetchingEnvironment environment) {
        return new ToDo("TITLE", "DESCRIPTION");
    }
}
