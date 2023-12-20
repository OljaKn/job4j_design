package ru.job4j.map;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Masha", 2, birthday);
        User user2 = new User("Masha", 2, birthday);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
