package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

public class UniqElement {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 2, 5};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            if (!map.containsKey(el)) {
                map.put(el, 1);
            } else {
                map.put(el, map.get(el) + 1);
            }
        }
        int firstEl = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (map.get(num) == 1) {
                firstEl = num;
                break;
            }
        }
        System.out.println(firstEl);
    }
}
