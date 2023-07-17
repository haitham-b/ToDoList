package org.leanix.graphql;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import org.leanix.model.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

@GraphQLName("mutation")
public class Mutation {
    private final Logger log = LoggerFactory.getLogger("mutationLogger");

    @GraphQLField
    public void createToDo(
            @NotNull @GraphQLName("ToDo") ToDo toDo
    ) {
        // create TODO
        log.warn("createToDO called");

    }
}