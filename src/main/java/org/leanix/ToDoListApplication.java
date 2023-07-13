package org.leanix;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.leanix.db.SubTaskDAO;
import org.leanix.db.ToDoDAO;
import org.leanix.graphql.Query;
import org.leanix.model.SubTask;
import org.leanix.model.ToDo;
import org.leanix.resources.SubTaskResource;
import org.leanix.resources.ToDoResource;

import java.util.*;


public class ToDoListApplication extends Application<ToDoListConfig> {

    public static void main(String[] args) throws Exception {
        new ToDoListApplication().run(args);
    }

    @Override
    public void run(ToDoListConfig configuration, Environment environment) {
        System.out.println("inside ToDoListApplication.run()");
//        ToDoDAO dao = new ToDoDAO(hibernateBundle.getSessionFactory());
//        environment.jersey().register(dao);

        ToDoDAO toDoDAO = new ToDoDAO(hibernateBundle.getSessionFactory());
        SubTaskDAO subTaskDAO = new SubTaskDAO(hibernateBundle.getSessionFactory());

        ToDoResource toDoResource = new UnitOfWorkAwareProxyFactory(hibernateBundle).create(ToDoResource.class, ToDoDAO.class, toDoDAO);
        SubTaskResource subTaskResource = new UnitOfWorkAwareProxyFactory(hibernateBundle).create(SubTaskResource.class, SubTaskDAO.class, subTaskDAO);
        environment.jersey().register(toDoResource);
        ToDo todo = persistToDoItem(subTaskResource, toDoResource);
        System.out.println(">>>>>>>>>>>>>>> Find By Id");

        System.out.println(toDoResource.findById(todo.getId()));

        System.out.println(">>>>>>>>>>>>>>> Delete");
//        toDoResource.delete(todo);
        subTaskResource.delete(todo.getSubTasks().stream().findFirst().get());
        System.out.println(">>>>>>>>>>>>>>> Delete finished");
        System.out.println(">>>>>>>>>>>>>>> Attempt to findById after Delete");
        System.out.println(toDoResource.findById(todo.getId()));

//        System.out.println(">>>>>>>>>>>>>>> Find All");
//        System.out.println(toDoResource.findAll());

        // TODO add toDoListComponent (graphQl?)
        environment.jersey().register(new Query());
        //  environment.jersey().register(new Mutation())

    }

    private ToDo persistToDoItem(SubTaskResource subTaskResource, ToDoResource toDoResource) {
        SubTask subTask = new SubTask();
        subTask.setDescription("Description");
        subTask.setTitle("Title");
        Set<SubTask> subTasks = new HashSet<>(Collections.emptySet());
        subTasks.add(subTask);
        ToDo todo = new ToDo("Title", "Description");
//        subTask.setToDo(todo);
        todo.setSubTasks(subTasks);
        subTask.setToDo(todo);
        toDoResource.create(todo);
        subTaskResource.create(subTask);
        return todo;
    }

    @Override
    public String getName() {
        return "todo-list";
    }

    private MigrationsBundle migrationBundle = new MigrationsBundle<ToDoListConfig>() {
        @Override
        public DataSourceFactory getDataSourceFactory(ToDoListConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private HibernateBundle<ToDoListConfig> hibernateBundle = new ScanningHibernateBundle<ToDoListConfig>("org.leanix.model") {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(ToDoListConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

//    private HibernateBundle<ToDoListConfig> hibernateBundle = new HibernateBundle<ToDoListConfig>(ToDo.class) {
//        @Override
//        public DataSourceFactory getDataSourceFactory(ToDoListConfig configuration) {
//            return configuration.getDataSourceFactory();
//        }
//    };

    @Override
    public void initialize(Bootstrap<ToDoListConfig> bootstrap) {

        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(migrationBundle);
    }

}



