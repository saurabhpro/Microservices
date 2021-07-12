package com.saurabh;

import com.saurabh.core.Template;
import com.saurabh.filters.DateRequiredFeature;
import com.saurabh.health.TemplateHealthCheck;
import com.saurabh.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardHelloWorldApplication extends Application<DropwizardHelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardHelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardHelloWorld";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardHelloWorldConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

    }

    @Override
    public void run(final DropwizardHelloWorldConfiguration configuration,
                    final Environment environment) {
        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(DateRequiredFeature.class);

        environment.jersey().register(new HelloWorldResource(template));

    }

}
