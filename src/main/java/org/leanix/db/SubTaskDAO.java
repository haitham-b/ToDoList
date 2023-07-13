package org.leanix.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.leanix.model.SubTask;
import org.leanix.model.ToDo;

import java.util.List;

public class SubTaskDAO extends AbstractDAO<SubTask> {
    public SubTaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SubTask findById(long id) {
        return get(id);
    }

    public SubTask create(SubTask todo) {
        return persist(todo);
    }

    public SubTask update(SubTask todo) {
        return persist(todo);
    }

    public List<SubTask> findAll() {
        return list(namedTypedQuery("org.leanix.model.SubTask.findAll"));
    }

    public void delete(SubTask subTask) {
        currentSession().delete(subTask);
    }
}