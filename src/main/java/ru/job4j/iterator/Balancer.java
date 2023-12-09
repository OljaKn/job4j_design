package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
            while (source.hasNext()) {
                Integer num = source.next();
                ArrayList<Integer> list = nodes.get(index);
                list.add(num);
                index = index + 1 % nodes.size();
                if (index == nodes.size()) {
                    index = 0;
                }
            }
    }
}
