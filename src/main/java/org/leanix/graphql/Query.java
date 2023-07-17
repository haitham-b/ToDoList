package org.leanix.graphql;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import org.leanix.model.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

@GraphQLName("query")
public class Query {
    private final Logger log = LoggerFactory.getLogger("queryLogger");

    @GraphQLField
    public ToDo retrieveToDo(
            @NotNull @GraphQLName("id") long id
    ) {
        // create TODO
        log.warn("retrieveToDo called");
        return new ToDo("Title", "Description");
    }
}