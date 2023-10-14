package org.saurabh.users;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiParam;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;

import static io.javalin.openapi.HttpMethod.DELETE;
import static io.javalin.openapi.HttpMethod.GET;
import static io.javalin.openapi.HttpMethod.PATCH;
import static io.javalin.openapi.HttpMethod.POST;

// This is a controller, it should contain logic related to client/server IO
public class UserController {

    private UserController() {
    }

    @OpenApi(
        summary = "Create user",
        operationId = "createUser",
        path = "/users",
        methods = POST,
        tags = {"User"},
        requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
        responses = {
            @OpenApiResponse(status = "201", content = {@OpenApiContent(from = User.class)}),
            @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
        }
    )
    public static void create(Context ctx) {
        final var user = ctx.bodyAsClass(NewUserRequest.class);
        if (user.name() == null || user.name().length() < 5) {
            throw new BadRequestResponse("Name is not valid");
        }
        final var created = UserService.save(user.name(), user.email());
        ctx.status(201);
        ctx.json(created);
    }

    @OpenApi(
        summary = "Get all users",
        operationId = "getAllUsers",
        path = "/users",
        methods = GET,
        tags = {"User"},
        responses = {
            @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)})
        }
    )
    public static void getAll(Context ctx) {
        ctx.json(UserService.getAll());
    }

    @OpenApi(
        summary = "Get user by ID",
        operationId = "getUserById",
        path = "/users/:userId",
        methods = GET,
        pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
        tags = {"User"},
        responses = {
            @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
            @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
            @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
        }
    )
    public static void getOne(Context ctx) {
        final var user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }

    @OpenApi(
        summary = "Update user by ID",
        operationId = "updateUserById",
        path = "/users/:userId",
        methods = PATCH,
        pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
        tags = {"User"},
        requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
        responses = {
            @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
            @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
            @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
        }
    )
    public static void update(Context ctx) {
        final var userId = validPathParamUserId(ctx);
        if (UserService.findById(userId) == null) {
            throw new NotFoundResponse("User %s not found".formatted(userId));
        } else {
            final var newUser = ctx.bodyAsClass(NewUserRequest.class);
            final var updatedUser = UserService.update(userId, newUser.name(), newUser.email());
            ctx.status(200);
            ctx.json(updatedUser);
        }
    }

    @OpenApi(
        summary = "Delete user by ID",
        operationId = "deleteUserById",
        path = "/users/:userId",
        methods = DELETE,
        pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
        tags = {"User"},
        responses = {
            @OpenApiResponse(status = "204"),
            @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
            @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
        }
    )
    public static void delete(Context ctx) {
        final var user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            UserService.delete(user.id());
            ctx.status(204);
        }
    }

    // Prevent duplicate validation of userId
    private static int validPathParamUserId(Context ctx) {
        return ctx.pathParamAsClass("userId", Integer.class).check(id -> id > 0, "ID must be greater than 0").get();
    }

}