package org.leanix.graphql;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import org.leanix.model.ToDo;

import javax.validation.constraints.NotNull;

@GraphQLName("mutation")
public class Mutation {
    @GraphQLField
    public void createToDo(
            @NotNull @GraphQLName("ToDo") ToDo toDo
    ) {
        // create TODO
        System.out.println("createToDO called");

    }
}