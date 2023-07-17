package org.leanix.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import org.leanix.model.ToDo;

import java.util.List;

public class ToDoDAO extends AbstractDAO<ToDo> {
    public ToDoDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @UnitOfWork
    public ToDo findById(long id) {
        return get(id);
    }

    @UnitOfWork
    public ToDo create(ToDo todo) {
        return persist(todo);
    }

    @UnitOfWork
    public ToDo update(ToDo todo) {
        return persist(todo);
    }

    @UnitOfWork
    public List<ToDo> findAll() {
        return list(namedTypedQuery("org.leanix.model.ToDo.findAll"));
    }

    @UnitOfWork
    public void delete(ToDo toDo) {
        currentSession().delete(toDo);
    }
}