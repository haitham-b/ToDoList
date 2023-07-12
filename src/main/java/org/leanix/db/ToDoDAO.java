package org.leanix.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.leanix.model.ToDo;

import java.util.List;

public class ToDoDAO extends AbstractDAO<ToDo> {
    public ToDoDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public ToDo findById(String id) {
        return get(id);
    }

    public ToDo create(ToDo todo) {
        return persist(todo);
    }

    public ToDo update(ToDo todo) {
        return persist(todo);
    }

    public List<ToDo> findAll() {
        return list(namedTypedQuery("org.leanix.model.ToDo.findAll"));
    }

    public void delete(String id) {
        query("delete from TODO where id = $id");
    }
}