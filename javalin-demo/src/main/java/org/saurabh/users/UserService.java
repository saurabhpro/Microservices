package org.saurabh.users;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// This is a service, it should be independent of Javalin
public class UserService {

    private UserService() {
    }

    public static final Map<Integer, User> USERS = new HashMap<>();
    private static final AtomicInteger lastId;

    static {
        USERS.put(0, new User(0, "Alice", "alice@alice.java"));
        USERS.put(1, new User(1, "Bob", "bob@bob.java"));
        USERS.put(2, new User(2, "Carol", "carol@carol.java"));
        USERS.put(3, new User(3, "Dave", "dave@dave.java"));
        lastId = new AtomicInteger(USERS.size());
    }

    public static User save(String name, String email) {
        final var id = lastId.incrementAndGet();
        USERS.put(id, new User(id, name, email));

        return USERS.get(id);
    }

    public static Collection<User> getAll() {
        return USERS.values();
    }

    public static User update(int userId, String name, String email) {
        USERS.put(userId, new User(userId, name, email));
        return USERS.get(userId);
    }

    public static User findById(int userId) {
        return USERS.get(userId);
    }

    public static void delete(int userId) {
        USERS.remove(userId);
    }

}