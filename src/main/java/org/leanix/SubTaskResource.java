package org.leanix;

import io.dropwizard.hibernate.UnitOfWork;
import org.leanix.db.SubTaskDAO;
import org.leanix.model.SubTask;

import java.util.List;

public class SubTaskResource {
    private SubTaskDAO subTaskDAO;

    public SubTaskResource(SubTaskDAO deviceDAO) {
        this.subTaskDAO = deviceDAO;
    }

    @UnitOfWork
    public SubTask create(SubTask toDoInput) {
        return subTaskDAO.create(toDoInput);
    }

    @UnitOfWork
    public List<SubTask> findAll() {
        return subTaskDAO.findAll();
    }

    @UnitOfWork
    public SubTask findById(long id) {
        return subTaskDAO.findById(id);
    }
}
