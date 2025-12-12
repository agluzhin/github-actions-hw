package agluzhin.github_actions_hw.repositories;

import agluzhin.github_actions_hw.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class UserRepository {
    private final Map<String, User> USER_STORAGE;

    public UserRepository() {
        USER_STORAGE = new HashMap<>();
    }

    public Map<String, User> getAllUsers() {
        return USER_STORAGE;
    }

    public User getUser(String userId) throws NoSuchElementException {
        if (!USER_STORAGE.containsKey(userId)) {
            throw new NoSuchElementException(
                    String.format(
                            "пользователь с id '%s' не найден",
                            userId
                    )
            );
        } else {
            return USER_STORAGE.get(userId);
        }
    }

    public void addUser(String userId, User user) throws IllegalArgumentException {
        if (USER_STORAGE.containsKey(userId)) {
            throw new IllegalArgumentException(
                    String.format(
                            "пользователь с id '%s' уже существует",
                            userId
                    )
            );
        } else {
            USER_STORAGE.put(userId, user);
        }
    }

    public void deleteUser(String userId) throws NoSuchElementException {
        if (!USER_STORAGE.containsKey(userId)) {
            throw new NoSuchElementException(
                    String.format(
                            "пользователь с id '%s' не найден",
                            userId
                    )
            );
        } else {
            USER_STORAGE.remove(userId);
        }
    }
}
