package org.leanix.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.leanix.db.ToDoDAO;
import org.leanix.model.ToDo;

import java.util.List;

public class ToDoResource {
    private ToDoDAO toDoDAO;

    public ToDoResource(ToDoDAO deviceDAO) {
        this.toDoDAO = deviceDAO;
    }

    @UnitOfWork
    public ToDo create(ToDo toDoInput) {
        return toDoDAO.create(toDoInput);
    }

    @UnitOfWork
    public List<ToDo> findAll() {
        return toDoDAO.findAll();
    }

    @UnitOfWork
    public ToDo findById(long id) {
        return toDoDAO.findById(id);
    }

    @UnitOfWork
    public void delete(ToDo toDo) {
        toDoDAO.delete(toDo);
    }
}
