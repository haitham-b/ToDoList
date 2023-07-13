package org.leanix.model;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@GraphQLName("subTask")
@Entity
@Table(name = "subtask")
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLField
    private long id;

    @GraphQLField
    private String title;

    @GraphQLField
    private String description;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private ToDo toDo;
}