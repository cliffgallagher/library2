package com.cliff2;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cliff2.resources.HelloWorldResource;
import com.cliff2.health.TemplateHealthCheck;

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
        final HelloWorldResource resource = new HelloWorldResource(
        configuration.getTemplate(),
        configuration.getDefaultName()
    );
	//environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(resource);
    }

}
