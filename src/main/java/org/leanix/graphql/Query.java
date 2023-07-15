package org.leanix.graphql;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import org.leanix.model.ToDo;

import javax.validation.constraints.NotNull;

@GraphQLName("query")
public class Query {
    @GraphQLField
    public ToDo retrieveToDo(
            @NotNull @GraphQLName("id") long id
    ) {
        // create TODO
        System.out.println("retrieveToDo called");
        return new ToDo("Title", "Description");
    }
}