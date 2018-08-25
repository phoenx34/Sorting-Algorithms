package com.sort;

public class Sort {

    // Simple swap function to switch numerical values in array.
    // Only will work when array is natural numbers

    private static void swap(int[] a, int i, int j) {
        a[i] = (a[i] + a[j]) - (a[j] = a[i]);
    }


    // Selection Sort, O(n^2) **Not sensitive to initial order of input

    public static int[] selectionSort(int[] a, int n) {
        if(a == null)
            throw new NullPointerException();
        if(n > a.length)
            throw new IndexOutOfBoundsException();

        // While there is more than one value in the unsorted section of the array
        for(int sizeUnsort = n; sizeUnsort > 1; sizeUnsort--) {

            int maxPos = 0, currPos = 1;

            // Finds the largest element in the unsorted section of array, stores in maxPos
            while(currPos < sizeUnsort) {
                if(a[currPos] > a[maxPos])
                    maxPos = currPos;
                currPos++;
            }

            // Swap the largest number with the last element in the unsorted section
            int lastIndex = a[sizeUnsort - 1];
            a[sizeUnsort - 1] = a[maxPos];
            a[maxPos] = lastIndex;
        }

        return a;
    }

    // Insertion Sort O(n^2) **Sensitive to initial order with worst case being numbers in descending order.

    public static int[] insertionSort(int[] a, int n) {
        if(a == null)
            throw new NullPointerException();
        if(n > a.length)
            throw new IndexOutOfBoundsException();

        // While there is more than one value in the unsorted part of the array
        for(int targetPos = 1; targetPos < n; targetPos++) {
            int target = a[targetPos];

            // Find target's insertion point in sorted section
            // While there are elements in the unsorted section to examine
            // AND we haven't found the insertion point for the target
            int currPos = targetPos - 1;
            while(currPos >= 0 && a[currPos] > target) {
                a[currPos + 1] = a[currPos];        // Element is bigger than target, move it behind target in array.
                currPos--;
            }
            a[currPos + 1] = target;
        }

        return a;
    }

    // Takes last element as pivot, and places pivot in correct location in array.
    // I.E. all elements to left of pivot are less and to the right are more.

    private static int partition(int[] a, int low, int high) {

        int pivot = a[high];
        int i = low - 1;

        for(int j = low; j <= (high - 1); j++) {

            if(a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);

        return i+1;
    }

    // Quicksort implementation - O(nlog(n)) avg, O(n^2) worst.

    public static void quickSort(int[] a, int low, int high) {

        if(low < high)
        {
            int part = partition(a, low, high);
            quickSort(a, low, part - 1);
            quickSort(a, part + 1, high);
        }

    }

    private static void merge(int[] a, int[] temp, int first, int mid, int last) {
        int tempSize = last - first + 1, insertIndex = first, firstPartInd = 0, secondPartInd = mid;

        System.arraycopy(a, first, temp, 0, mid - first);

        while((firstPartInd < (mid - first)) && secondPartInd <= last) {

            if(temp[firstPartInd] < a[secondPartInd]) {
                a[insertIndex] = temp[firstPartInd];
                firstPartInd++;
            } else {
                a[insertIndex] = a[secondPartInd];
                secondPartInd++;
            }
            insertIndex++;
        }

        // We need to check if either partition is empty.
        // If the lower partition is empty we can just return since secondPart is already in a[]
        // If upper partition is empty, copy lowerPart from temp[] into a[]

        while(firstPartInd < (mid - first)) {
            a[insertIndex] = temp[firstPartInd];
            firstPartInd++;
            insertIndex++;
        }

    }

    private static void internalMerge(int[] a, int[] temp, int first, int last) {

        // Base case
        if((last - first + 1) <= 1)
            return;

        // Midpoint calculation
        int mid = (first + last + 1) / 2;

        // Loop to split and find midpoints of partitions respectively and run merge on each partition.
        internalMerge(a, temp, first, mid - 1);
        internalMerge(a, temp, mid, last);
        merge(a, temp, first, mid, last);
    }

    // Merge sort O(nlog(n)) Worst/Average.

    public static void mergeSort(int[] a) {

        if(a == null)
            throw new NullPointerException();

        int temp[] = new int[a.length / 2];
        internalMerge(a, temp, 0, a.length - 1);
    }
}
