package org.leanix;

import com.smoketurner.dropwizard.graphql.GraphQLBundle;
import com.smoketurner.dropwizard.graphql.GraphQLFactory;
import graphql.schema.idl.RuntimeWiring;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.migrations.MigrationsBundle;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.leanix.db.SubTaskDAO;
import org.leanix.db.ToDoDAO;
import org.leanix.graphql.config.SubTaskDataFetcher;
import org.leanix.model.SubTask;
import org.leanix.model.ToDo;
import org.leanix.resources.SubTaskResource;
import org.leanix.graphql.config.ToDoDataFetcher;
import org.leanix.resources.ToDoResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ToDoListApplication extends Application<ToDoListConfig> {
    private final Logger log = LoggerFactory.getLogger("toDoListApplicationLogger");

    private static ToDoDAO toDoDAO;
    private static SubTaskDAO subTaskDAO;

    public static void main(String[] args) throws Exception {
        new ToDoListApplication().run(args);
    }

    @Override
    public void run(ToDoListConfig configuration, Environment environment) throws Exception {
        log.info("inside ToDoListApplication.run()");

        // Prepare DAOs
        toDoDAO = new ToDoDAO(hibernateBundle.getSessionFactory());
        subTaskDAO = new SubTaskDAO(hibernateBundle.getSessionFactory());

        // Setup graphqlBundle
        final GraphQLBundle<ToDoListConfig> graphqlBundle =
                new GraphQLBundle<ToDoListConfig>() {
                    @Override
                    public GraphQLFactory getGraphQLFactory(ToDoListConfig configuration) {
                        final GraphQLFactory factory = configuration.getGraphQLFactory();
                        factory.setRuntimeWiring(buildWiring(toDoDAO, subTaskDAO));
                        return factory;
                    }
                };
        graphqlBundle.run(configuration, environment);

        // Enable CORS to allow GraphiQL on a separate port to reach the API
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("cors", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Prepare Transactional Resource Methods Outside Jersey Resources
        ToDoResource toDoResource = new UnitOfWorkAwareProxyFactory(hibernateBundle).create(ToDoResource.class, ToDoDAO.class, toDoDAO);
        SubTaskResource subTaskResource = new UnitOfWorkAwareProxyFactory(hibernateBundle).create(SubTaskResource.class, SubTaskDAO.class, subTaskDAO);

        // Prepare 1 entry of ToDoList
        ToDo todo = persistToDoItem(subTaskResource, toDoResource);
        log.warn(">>>>>>>>>>>>>>> Find By Id");
        log.warn(toDoResource.findById(todo.getId()).toString());

//        log.warn(">>>>>>>>>>>>>>> Delete");
//        toDoResource.delete(todo);
//        subTaskResource.delete(todo.getSubTasks().stream().findFirst().get());
//        log.warn(">>>>>>>>>>>>>>> Delete finished");
//        log.warn(">>>>>>>>>>>>>>> Attempt to findById after Delete");
//        log.warn(toDoResource.findById(todo.getId()));
//        log.warn(">>>>>>>>>>>>>>> Find All");
//        log.warn(toDoResource.findAll());
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

    @Override
    public void initialize(Bootstrap<ToDoListConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(migrationBundle);
    }

    private static RuntimeWiring buildWiring(ToDoDAO toDoDAO, SubTaskDAO subTaskDAO) {

        final ToDoDataFetcher toDoFetcher = new ToDoDataFetcher(toDoDAO);
        final SubTaskDataFetcher subTaskFetcher = new SubTaskDataFetcher(subTaskDAO);

        return
                RuntimeWiring.newRuntimeWiring()
                        .type("Query", typeWiring -> typeWiring
                                .dataFetcher("retrieveToDo", toDoFetcher)
                                .dataFetcher("retrieveSubTask", subTaskFetcher))
//                        .type("Mutation", typeWiring -> typeWiring.dataFetcher("createToDo", toDoFetcher))
                        .build();

    }
}



