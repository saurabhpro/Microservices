package org.saurabh.users;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.saurabh.users.UserService.USERS;

class JavalinOpenApiExampleAppSpec {

    private final Javalin app = new JavalinOpenApiExampleApp().app; // inject any dependencies you might have
    private final String usersJson = new JavalinJackson().toJsonString(USERS.values(), List.class);

    @Test
    void GET_to_fetch_users_returns_list_of_users() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/users").code()).isEqualTo(200);
            assertThat(client.get("/users").body().string()).isEqualTo(usersJson);
        });
    }

}