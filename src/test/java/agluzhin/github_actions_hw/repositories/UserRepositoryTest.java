package agluzhin.github_actions_hw.repositories;

import agluzhin.github_actions_hw.entities.User;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тестирование репозитория 'UserRepository'.")
class UserRepositoryTest {
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUpTests() {
        user = new User("Alex", 22);
        userRepository = new UserRepository();
    }

    @Test
    @Order(1)
    @DisplayName("Добавление 'несуществующего' пользователя.")
    public void addNonExistUserTest() {
        assertTrue(userRepository.getAllUsers().isEmpty());
        userRepository.addUser(user.getId(), user);
        assertFalse(userRepository.getAllUsers().isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("Добавление 'существующего' пользователя.")
    public void addExistUserTest() {
        userRepository.addUser(user.getId(), user);
        Exception illegalArgumentException = assertThrows(
                IllegalArgumentException.class, () -> userRepository.addUser(user.getId(), user)
        );
        assertEquals(
                illegalArgumentException.getMessage(),
                String.format(
                        "пользователь с id '%s' уже существует",
                        user.getId()
                )
        );
    }

    @Test
    @Order(3)
    @DisplayName("Получение 'несуществующего' пользователя")
    public void getNonExistUserTest() {
        assertTrue(userRepository.getAllUsers().isEmpty());
        Exception noSuchElementException = assertThrows(
                NoSuchElementException.class, () -> userRepository.getUser(user.getId())
        );
        assertEquals(
                noSuchElementException.getMessage(),
                String.format(
                        "пользователь с id '%s' не найден",
                        user.getId()
                )
        );
    }

    @Test
    @Order(4)
    @DisplayName("Получение 'существующего' пользователя")
    public void getExistUserTest() {
        assertTrue(userRepository.getAllUsers().isEmpty());
        userRepository.addUser(user.getId(), user);
        assertFalse(userRepository.getAllUsers().isEmpty());
        assertNotNull(userRepository.getUser(user.getId()));
    }

    @Test
    @Order(5)
    @DisplayName("Получение всех пользователей")
    public void getAllUsersTest() {
        assertNotNull(userRepository.getAllUsers());
    }


    @Test
    @Order(6)
    @DisplayName("Удаление 'несуществующего' пользователя")
    public void deleteNonExistUserTest() {
        assertTrue(userRepository.getAllUsers().isEmpty());
        Exception noSuchElementException = assertThrows(
                NoSuchElementException.class, () -> userRepository.deleteUser(user.getId())
        );
        assertEquals(
                noSuchElementException.getMessage(),
                String.format(
                        "пользователь с id '%s' не найден",
                        user.getId()
                )
        );
    }

    @Test
    @Order(7)
    @DisplayName("Удаление 'существующего' пользователя")
    public void deleteExistUserTest() {
        assertTrue(userRepository.getAllUsers().isEmpty());
        userRepository.addUser(user.getId(), user);
        assertFalse(userRepository.getAllUsers().isEmpty());
        assertDoesNotThrow(() -> userRepository.deleteUser(user.getId()));
        assertTrue(userRepository.getAllUsers().isEmpty());
    }
}