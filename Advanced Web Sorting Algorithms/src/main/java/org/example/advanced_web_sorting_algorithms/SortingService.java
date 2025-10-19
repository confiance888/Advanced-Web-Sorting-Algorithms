package org.example.advanced_web_sorting_algorithms;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class SortingService {

    public List<Integer> bubbleSort(List<Integer> list) {
        List<Integer> arr = new ArrayList<>(list);
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        return arr;
    }

    public List<Integer> selectionSort(List<Integer> list) {
        List<Integer> arr = new ArrayList<>(list);
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(min)) {
                    min = j;
                }
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(min));
            arr.set(min, temp);
        }
        return arr;
    }

    public List<Integer> quickSort(List<Integer> list) {
        List<Integer> arr = new ArrayList<>(list);
        quickSortHelper(arr, 0, arr.size() - 1);
        return arr;
    }

    private void quickSortHelper(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }

    public List<Integer> sortUsing(String algo, List<Integer> values) {
        switch (algo.toLowerCase()) {
            case "bubble":
                return bubbleSort(values);
            case "selection":
                return selectionSort(values);
            case "quick":
                return quickSort(values);
            default:
                throw new IllegalArgumentException("Invalid algorithm: " + algo);
        }
    }
}

