package com.spring.henallux.firstSpringProject.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, Integer> items = new HashMap<>();

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }
}

