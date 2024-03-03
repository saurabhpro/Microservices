package org.saurabh.users;

import com.fasterxml.jackson.databind.node.TextNode;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.openapi.JsonSchemaLoader;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import io.javalin.security.RouteRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static org.saurabh.users.JavalinOpenApiExampleApp.Rules.ANONYMOUS;

public class JavalinOpenApiExampleApp {

    enum Rules implements RouteRole {
        ANONYMOUS,
        USER,
    }

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
        return Javalin.create(javalinConfig -> {

            openapiConfigConsumer();

            for (var generatedJsonSchema : new JsonSchemaLoader().loadGeneratedSchemes()) {
                System.out.println(generatedJsonSchema.getName());
                System.out.println(generatedJsonSchema.getContentAsString());
            }

            javalinConfig.router.apiBuilder(() -> {
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
            });
        });
    }

    private static Consumer<JavalinConfig> openapiConfigConsumer() {
        return config -> {
            // config.routing.contextPath = "/custom";
            final var deprecatedDocsPath = "/api/openapi.json"; // by default it's /openapi

            config.registerPlugin(new OpenApiPlugin(openApiConfig ->
                openApiConfig
                    .withDocumentationPath(deprecatedDocsPath)
                    .withRoles(ANONYMOUS)
                    .withDefinitionConfiguration((version, openApiDefinition) ->
                        openApiDefinition
                            .withInfo(openApiInfo ->
                                openApiInfo
                                    .description("App description goes right here")
                                    .termsOfService("https://example.com/tos")
                                    .contact("API Support", "https://www.example.com/support", "support@example.com")
                                    .license("Apache 2.0", "https://www.apache.org/licenses/", "Apache-2.0")
                            )
                            .withServer(openApiServer ->
                                openApiServer
                                    .description("Server description goes here")
                                    .url("http://localhost:{port}{basePath}/" + version + "/")
                                    .variable("port", "Server's port", "8080", "8080", "7070")
                                    .variable("basePath", "Base path of the server", "", "", "v1")
                            )
                            // Based on official example: https://swagger.io/docs/specification/authentication/oauth2/
                            .withSecurity(openApiSecurity ->
                                openApiSecurity
                                    .withBasicAuth()
                                    .withBearerAuth()
                                    .withApiKeyAuth("ApiKeyAuth", "X-Api-Key")
                                    .withCookieAuth("CookieAuth", "JSESSIONID")
                                    .withOpenID("OpenID", "https://example.com/.well-known/openid-configuration")
                                    .withOAuth2(
                                        "OAuth2",
                                        "This API uses OAuth 2 with the implicit grant flow.",
                                        oauth2 ->
                                            oauth2
                                                .withClientCredentials("https://api.example.com/credentials/authorize")
                                                .withImplicitFlow("https://api.example.com/oauth2/authorize", flow ->
                                                    flow
                                                        .withScope("read_pets", "read your pets")
                                                        .withScope("write_pets", "modify pets in your account")
                                                )
                                    )
                                    .withGlobalSecurity("OAuth2", globalSecurity ->
                                        globalSecurity
                                            .withScope("write_pets")
                                            .withScope("read_pets")
                                    )
                            )
                            .withDefinitionProcessor(content -> { // you can add whatever you want to this document using your favourite json api
                                content.set("test", new TextNode("Value"));
                                return content.toPrettyString();
                            })
                    )));
            config.registerPlugin(new SwaggerPlugin(swaggerConfiguration -> {
                swaggerConfiguration.setDocumentationPath(deprecatedDocsPath);
            }));

            config.registerPlugin(new ReDocPlugin(reDocConfiguration -> {
                reDocConfiguration.setDocumentationPath(deprecatedDocsPath);
            }));
        };
    }

}