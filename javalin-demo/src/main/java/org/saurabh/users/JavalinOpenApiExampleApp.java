package org.saurabh.users;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class JavalinOpenApiExampleApp {

    private static final Logger LOG = LoggerFactory.getLogger(JavalinOpenApiExampleApp.class);
    public final Javalin app;

    public JavalinOpenApiExampleApp() {
        app = javalinApp();
    }

    public static void main(String[] args) {
        new JavalinOpenApiExampleApp().app.start(7002);
        LOG.info("Check out ReDoc docs at http://localhost:7002/redoc");
        LOG.info("Check out Swagger UI docs at http://localhost:7002/swagger-ui");
    }

    private static Javalin javalinApp() {
        return Javalin.create(openapiConfigConsumer())
            .routes(() -> {
                    path(
                        "users",
                        () -> {
                            get(UserController::getAll);
                            post(UserController::create);
                            path("{userId}", () -> {
                                get(UserController::getOne);
                                patch(UserController::update);
                                delete(UserController::delete);
                            });
                        });
                    get("/ui", ctx -> ctx.html("<h1>User UI</h1>"));
                }
            );
    }

    private static Consumer<JavalinConfig> openapiConfigConsumer() {
        return config -> {
            final var openApiConfiguration = new OpenApiPluginConfiguration();
            config.plugins.register(new OpenApiPlugin(openApiConfiguration));
            config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
            config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
        };
    }

}