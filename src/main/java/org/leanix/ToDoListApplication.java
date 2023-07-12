package org.leanix;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.leanix.db.ToDoDAO;
import org.leanix.graphql.Query;
import org.leanix.model.ToDo;


public class ToDoListApplication extends Application<ToDoListConfig> {

    public static void main(String[] args) throws Exception {
        new ToDoListApplication().run(args);
    }

    @Override
    public void run(ToDoListConfig configuration, Environment environment) {
        System.out.println("inside ToDoListApplication.run()");
//        ToDoDAO dao = new ToDoDAO(hibernateBundle.getSessionFactory());
//        environment.jersey().register(dao);

        ToDoDAO dao2 = new ToDoDAO(hibernateBundle.getSessionFactory());

        ToDoResource toDoResource = new UnitOfWorkAwareProxyFactory(hibernateBundle).create(ToDoResource.class, ToDoDAO.class, dao2);
        environment.jersey().register(toDoResource);

        toDoResource.create(new ToDo("Title", "Description"));
        System.out.println(toDoResource.findAll());

        // TODO add toDoListComponent (graphQl?)
        environment.jersey().register(new Query());
        //  environment.jersey().register(new Mutation())

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

    private HibernateBundle<ToDoListConfig> hibernateBundle = new HibernateBundle<ToDoListConfig>(ToDo.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ToDoListConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<ToDoListConfig> bootstrap) {

        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(migrationBundle);
    }

}



