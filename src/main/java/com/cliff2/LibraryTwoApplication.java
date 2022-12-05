package com.cliff2;

import com.cliff2.resources.PersonResource;
import com.cliff2.resources.TaskResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cliff2.resources.HelloWorldResource;
import com.cliff2.health.TemplateHealthCheck;
import org.jdbi.v3.core.Jdbi;

public class LibraryTwoApplication extends Application<LibraryTwoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new LibraryTwoApplication().run(args);
    }

    @Override
    public String getName() {
        return "LibraryTwo";
    }

    @Override
    public void initialize(final Bootstrap<LibraryTwoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final LibraryTwoConfiguration configuration,
                    final Environment environment) {

        //Register PersonResource
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");


        environment.jersey().register(new PersonResource(jdbi));
        environment.jersey().register(new TaskResource(jdbi));
    }

}
