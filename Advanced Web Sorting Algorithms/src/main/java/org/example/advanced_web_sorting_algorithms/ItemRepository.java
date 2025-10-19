package org.example.advanced_web_sorting_algorithms;

import org.example.advancedsorting.model.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ItemRepository {

    private final Map<Integer, Item> store = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

   // public Optional<Item> findById(int id) {
       // return Optional.ofNullable(store.get(id));
    }

    /* Item save(Item item) {
       if (item.getId() == 0) {
     item.setId(idGen.getAndIncrement());
        }
       store.put(item.getId(), item);
        return item;
    }*/

    public boolean delete(int id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
        idGen.set(1);
    }
}
