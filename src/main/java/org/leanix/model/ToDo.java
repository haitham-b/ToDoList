package org.leanix.model;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
//@GraphQLName("toDo")
@Entity
@Table(name = "ToDo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GraphQLField
    private String id;
    @GraphQLField
    private String title;
    @GraphQLField
    private String description;
//    @GraphQLField
//    private List<SubTask> subTasks;

    public ToDo(String id, String title, String description, List<SubTask> subTasks) {
        this.id = id;
        this.title = title;
        this.description = description;
//        this.subTasks = subTasks;
    }

    public ToDo(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
//        this.subTasks = new ArrayList<SubTask>();
    }
}