package com.sort;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    @author Marc Isaac
    Fall 2018
    Program demonstrating the various sorting algorithms
    on a set of numbers inputted by the user.

    Sorts implemented:
        -Selection
 */

public class Main {

    private static int menu() {
        int selection;
        Scanner read = new Scanner(System.in);

        System.out.println("Choose which sorting algorithm you would like to use");
        System.out.println("----------------------------------------------------");
        System.out.println("1 - Selection Sort");
        System.out.println("2 - Insertion Sort");
        System.out.println("3 - Quick Sort");
        System.out.println("4 - Merge Sort");

        selection = read.nextInt();
        System.out.println(selection);
        return selection;

    }

    public static void main(String[] args) throws IOException {
        /*TODO
            Implement all major sorts and have user input which method he would like to use.
            Accept a list of unsorted numbers
            Sort using chosen sorting algorithm and return big O, runtime analysis, etc.
         */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s;

        System.out.println("Enter a series of numbers separated by a space: ");
        s = reader.readLine().split("\\s");
        int[] toSort = new int[s.length];

        for(int i = 0; i < s.length; i++)
        {
            toSort[i] = Integer.parseInt(s[i]);
        }

        int sortMethod = menu();

        switch(sortMethod) {
            case 1:
                Sort.selectionSort(toSort, toSort.length);
                System.out.println("Here is the array sorted via selection sort: " + Arrays.toString(toSort) + "\n Time Complexity: O(n^2) not order dependent.");
                break;
            case 2:
                Sort.insertionSort(toSort, toSort.length);
                System.out.println("Here is the array sorted via insertion sort: " + Arrays.toString(toSort) + "\n Time Complexity: O(n^2) order dependent");
                break;
            case 3:
                Sort.quickSort(toSort, 0, toSort.length - 1);
                System.out.println("Here is the array sorted via quick sort: " + Arrays.toString(toSort) + "\n Time Complexity: O(nlogn) average, O(n^2) worst");
                break;
            case 4:
                Sort.mergeSort(toSort);
                System.out.println("Here is the array sorted via merge sort: " + Arrays.toString(toSort) + "\n Time Complexity: O(nlogn) worst/avg");
                break;
            default:
                Sort.mergeSort(toSort);
                System.out.println("Here is the array sorted via merge sort: " + Arrays.toString(toSort) + "\n Time Complexity: O(nlogn) worst/avg");
        }

        // Testing
        //System.out.println("Here is the array to be sorted: " + Arrays.toString(toSort));
        //Sort.selectionSort(toSort, toSort.length);
        //Sort.insertionSort(toSort, toSort.length);
        //Sort.quickSort(toSort, 0, toSort.length - 1);
        //Sort.mergeSort(toSort);
    }
}
