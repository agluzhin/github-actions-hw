package agluzhin.github_actions_hw.entities;

import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тестирование сущности 'User'.")
class UserTest {
    private Pattern UUID_PATTERN;

    @BeforeEach
    void setUpTests() {
        UUID_PATTERN = Pattern.compile(
                "^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$"
        );
    }

    @Test
    @Order(1)
    @DisplayName("Инициализация пользователя.")
    public void userInitializationTest() {
        String name = "Alex";
        int age = 22;
        User user = new User(name, age);

        assertNotNull(user.getId());
        assertFalse(user.getId().isBlank());
        assertTrue(UUID_PATTERN.matcher(user.getId().trim()).matches());
        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
    }

    @Test
    @Order(2)
    @DisplayName("Уникальность 'id' пользователей.")
    public void userUniqueIdTest() {
        User alex = new User("Alex", 22);
        User ben = new User("Ben", 35);

        assertNotEquals(alex.getId(), ben.getId());
    }
}