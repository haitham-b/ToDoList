package org.leanix.graphql.config;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;
import org.leanix.db.SubTaskDAO;
import org.leanix.model.SubTask;

public class SubTaskDataFetcher implements DataFetcher<SubTask> {
    private final SubTaskDAO subTaskDAO;

    @Inject
    public SubTaskDataFetcher(SubTaskDAO subTaskDAO) {
        this.subTaskDAO = subTaskDAO;
    }

    @Override
    public SubTask get(DataFetchingEnvironment environment) {
        return subTaskDAO.findById(environment.getArgument("id"));
    }
}
