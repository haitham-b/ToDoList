package org.leanix.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
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
    @OneToMany(mappedBy = "toDo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubTask> subTasks;

    @JsonCreator
    public ToDo(String title, String description, Set<SubTask> subTasks) {
        this.title = title;
        this.description = description;
        this.subTasks = subTasks;
    }

    @JsonCreator
    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
        this.subTasks = Collections.emptySet();
    }

    @Override
    public String toString() {
        return String.format("{Id: %d, Title: %s, Description: %s, Subtasks: %s}", id, title, description, subTasks.toString());
    }
}