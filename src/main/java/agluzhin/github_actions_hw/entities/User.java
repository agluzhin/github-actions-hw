package agluzhin.github_actions_hw.entities;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private int age;

    public User(String name, int age) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
