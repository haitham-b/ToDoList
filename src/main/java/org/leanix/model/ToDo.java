package org.leanix.model;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
//@GraphQLName("toDo")
@Entity
@Table(name = "todo")
//@NamedQueries({@NamedQuery( name =
//        "org.leanix.model.ToDo.findAll",
//        query = "SELECT t from todo t")})
@AllArgsConstructor
@NoArgsConstructor
public class ToDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GraphQLField
    private long id;

    @GraphQLField
    private String title;

    @GraphQLField
    private String description;

    @GraphQLField
//    @OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER)
    private Set<SubTask> subTasks;

    public ToDo(String title, String description, Set<SubTask> subTasks) {
        this.title = title;
        this.description = description;
        this.subTasks = subTasks;
    }

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
        this.subTasks = Collections.emptySet();
    }
}