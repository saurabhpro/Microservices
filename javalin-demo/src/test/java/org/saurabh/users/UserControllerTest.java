package org.saurabh.users;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.saurabh.users.UserController.create;

class UserControllerTest {

    private final Context ctx = mock(Context.class);

    @Test
    void POST_to_create_users_gives_201_for_valid_username() {
        // given
        given(ctx.bodyAsClass(NewUserRequest.class))
            .willReturn(new NewUserRequest("saurabh", "abc@yum.com"));

        // when
        create(ctx); // the handler we're testing

        // then
        then(ctx).should().status(201);
    }

    @Test
    void POST_to_create_users_throws_for_invalid_username() {
        // given
        given(ctx.bodyAsClass(NewUserRequest.class))
            .willReturn(new NewUserRequest(null, "abc@yum.com"));

        // when/then
        assertThatThrownBy(() -> create(ctx))
            .isInstanceOf(BadRequestResponse.class)
            .hasMessage("Name is not valid"); // the handler we're testing
    }

}