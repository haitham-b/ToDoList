package org.leanix;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smoketurner.dropwizard.graphql.GraphQLFactory;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ToDoListConfig extends Configuration {

    @Valid
    @NotNull
    public final GraphQLFactory graphql = new GraphQLFactory();
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        database = dataSourceFactory;
    }

    @JsonProperty
    public GraphQLFactory getGraphQLFactory() {
        return graphql;
    }
}
