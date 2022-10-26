package sorttimes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Thomas.Abbott
 */
public class SortTimes {

    private static final long SAMPLESIZE = 100;
    private static final long MAXARRAYSIZE = 1000000;
    private static Random rand = new Random();

    public static void main(String[] args) {
        
        long insertionSortTime = 0;
        long mergeSortTime = 0;
        long quickSortTime = 0;
        long heapSortTime = 0;
        System.out.println("Sorted ascending dataset");
        for (int arraysz = 10; arraysz < MAXARRAYSIZE; arraysz *= 10) {
            System.out.println("\tdataset size == " + arraysz);
            int[] sortedAsc = getAscendingValues(arraysz);

            quickSortTime = quickSort(sortedAsc);
            mergeSortTime = mergeSort(sortedAsc);
            insertionSortTime = insertionSort(sortedAsc);
            heapSortTime = heapSort(sortedAsc);
        }

        System.out.println("");
        System.out.println("Sorted descending dataset");
        for (int arraysz = 10; arraysz < MAXARRAYSIZE; arraysz *= 10) {
            System.out.println("\tdataset size == " + arraysz);
            int[] sortedAsc = getDescendingValues(arraysz);

            quickSortTime = quickSort(sortedAsc);
            mergeSortTime = mergeSort(sortedAsc);
            insertionSortTime = insertionSort(sortedAsc);
            heapSortTime = heapSort(sortedAsc);
        }

        System.out.println("");
        System.out.println("Randomized dataset");
        for (int arraysz = 10; arraysz < MAXARRAYSIZE; arraysz *= 10) {
            System.out.println("\tdataset size == " + arraysz);
            int[] randomNumbers = getRandomizedValues(arraysz);

            quickSortTime = quickSort(randomNumbers);
            mergeSortTime = mergeSort(randomNumbers);
            insertionSortTime = insertionSort(randomNumbers);
            heapSortTime = heapSort(randomNumbers);
        }
    }

    private static int[] getAscendingValues(int n) {
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = i;
        }
        return numbers;
    }

    private static int[] getDescendingValues(int n) {
        int[] numbers = new int[n];
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            numbers[i] = j;
        }
        return numbers;
    }

    private static int[] getRandomizedValues(int n) {
        Random rand = new Random();
        int[] rtnAry = getAscendingValues(n);
        
        for (int i = n - 1; i > 1; i--) {
            int r = rand.nextInt(i);
            int tmp = rtnAry[i];
            rtnAry[i] = rtnAry[r];
            rtnAry[r] = tmp;
        }
        return rtnAry;
    }

    private static long insertionSort(int[] src) {
        int[] cpy = new int[src.length];
        long startTime = 0;
        long endTime = 0;

        System.arraycopy(src, 0, cpy, 0, src.length);

        startTime = System.nanoTime();
        InsertionSort.sort(cpy);
        endTime = System.nanoTime();

        System.out.println("\t\tInsertion Sort took " + (endTime - startTime));
        return (endTime - startTime);
    }

    private static long mergeSort(int[] src) {
        int[] cpy = new int[src.length];
        long startTime = 0;
        long endTime = 0;

        System.arraycopy(src, 0, cpy, 0, src.length);

        startTime = System.nanoTime();
        MergeSort.sort(cpy);
        endTime = System.nanoTime();

        System.out.println("\t\tMerge Sort took " + (endTime - startTime));
        return (endTime - startTime);
    }

    private static long quickSort(int[] src) {
        int[] cpy = new int[src.length];
        long startTime = 0;
        long endTime = 0;

        System.arraycopy(src, 0, cpy, 0, src.length);

        startTime = System.nanoTime();
        QuickSort.sort(cpy);
        endTime = System.nanoTime();

        System.out.println("\t\tQuick Sort took " + (endTime - startTime));
        return (endTime - startTime);
    }

    private static long heapSort(int[] src){
        int[] cpy = new int[src.length];
        long startTime = 0;
        long endTime = 0;

        System.arraycopy(src, 0, cpy, 0, src.length);

        startTime = System.nanoTime();
        HeapSort.sort(cpy);
        endTime = System.nanoTime();

        System.out.println("\t\tHeap Sort took " + (endTime - startTime));
        return (endTime - startTime);
    }

}
