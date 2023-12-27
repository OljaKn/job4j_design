package ru.job4j.question;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (User user: previous) {
            if (currentMap.containsKey(user.getId()) && !currentMap.containsValue(user.getName())) {
                changed++;
            } else if (!currentMap.containsKey(user.getId())) {
                deleted++;
            }
        }
        added = current.size() - (previous.size() - deleted);
        return new Info(added, changed, deleted);
    }

}