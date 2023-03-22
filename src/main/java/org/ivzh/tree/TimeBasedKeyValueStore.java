package org.ivzh.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/time-based-key-value-store/description/
public class TimeBasedKeyValueStore {

    Map<String, TreeMap<Integer, String>> container;

    public TimeBasedKeyValueStore() {
        container = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (container.containsKey(key)) {
            container.get(key).put(timestamp, value);
        } else {

            TreeMap<Integer, String> map = new TreeMap<>();
            map.put(timestamp, value);

            container.put(key, map);
        }
    }

    public String get(String key, int timestamp) {
        if (!container.containsKey(key)) {
            return "";
        }

        Map.Entry<Integer, String> e = container.get(key).floorEntry(timestamp);

        return e == null ? "" : e.getValue();
    }
}
