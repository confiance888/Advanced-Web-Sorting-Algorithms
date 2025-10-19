package org.example.advanced_web_sorting_algorithms;

import org.example.advancedsorting.model.Item;
import org.example.advancedsorting.repository.ItemRepository;
import org.example.advancedsorting.service.SortingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController

public class ItemController {

    private final ItemRepository repository;
    private final SortingService sortingService;

    public ItemController(ItemRepository repository, SortingService sortingService) {
        this.repository = repository;
        this.sortingService = sortingService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return repository.findAll();
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setValue(item.getValue());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (repository.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<Map<String, Object>> sort(@RequestParam(defaultValue = "quick") String algo) {
        List<Item> items = repository.findAll();
        List<Integer> values = items.stream().map(Item::getValue).collect(Collectors.toList());
        List<Integer> sorted = sortingService.sortUsing(algo, values);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("algorithm", algo);
        response.put("original", values);
        response.put("sorted", sorted);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearAll() {
        repository.clear();
        return ResponseEntity.noContent().build();
    }
}
