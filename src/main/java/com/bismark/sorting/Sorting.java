package com.bismark.sorting;

public class Sorting {


    public static void main(String[] args) {
        int[] arr = {3, 1, 7, 13, 12};
        int key = 0;
        switch (key) {
            case 1:
                bubbleSort(arr);
                break;
            case 2:
                selectionSort(arr);
                break;
            case 3:
                insertionSort(arr);
                break;
        }
        printArr(arr);


    }

    /**
     * N^2/2
     */
    public static void bubbleSort(int[] arr) {
        int out, in;
        for (out = arr.length - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (arr[in] > arr[in + 1]) {
                    swap(in, in + 1, arr);
                }
            }
        }
    }

    /**
     * N^2
     */
    public static void selectionSort(int[] arr) {
        int out, in, min;
        for (out = 0; out < arr.length - 1; out++) {
            min = out;
            for (in = out + 1; in < arr.length; in++) {
                if (arr[in] < arr[min]) {
                    min = in;
                }
            }
            swap(out, min, arr);
        }
    }

    public static void insertionSort(int[] arr) {
        int out, in;
        for (out = 1; out < arr.length; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void swap(int one, int two, int[] arr) {
        int tmp = arr[one];
        arr[one] = arr[two];
        arr[two] = tmp;
    }
}
