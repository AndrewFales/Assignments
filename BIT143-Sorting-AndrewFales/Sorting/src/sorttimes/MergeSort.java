/*
 A class to encapsulate the Merge Sort, implemented as a
 public static method that sorts the array in place, and returns
 a reference to the array
*/
package sorttimes;

/**
 *
 * @author Tom
 */
public class MergeSort {

    private static int[] merge(int[] numbers, int i, int j, int k) {
        int mergedSize = k - i + 1;                // Size of merged partition
        int mergePos = 0;                          // Position to insert merged number
        int[] mergedNumbers = new int[mergedSize];   // Dynamically allocates temporary array
        // for merged numbers

        int leftPos = i;                           // Initialize left partition position
        int rightPos = j + 1;                      // Initialize right partition position

        // Add smallest element from left or right partition to merged numbers
        while (leftPos <= j && rightPos <= k) {
            if (numbers[leftPos] <= numbers[rightPos]) {
                mergedNumbers[mergePos] = numbers[leftPos];
                ++leftPos;
            } else {
                mergedNumbers[mergePos] = numbers[rightPos];
                ++rightPos;

            }
            ++mergePos;
        }

        // If left partition is not empty, add remaining elements to merged numbers
        while (leftPos <= j) {
            mergedNumbers[mergePos] = numbers[leftPos];
            ++leftPos;
            ++mergePos;
        }

        // If right partition is not empty, add remaining elements to merged numbers
        while (rightPos <= k) {
            mergedNumbers[mergePos] = numbers[rightPos];
            ++rightPos;
            ++mergePos;
        }

        // Copy merge number back to numbers
        for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
            numbers[i + mergePos] = mergedNumbers[mergePos];
        }
        return numbers;
    }

    private static int[] sort(int[] numbers, int i, int k) {
        int j = 0;

        if (i < k) {
            j = (i + k) / 2;  // Find the midpoint in the partition

            // Recursively sort left and right partitions
            sort(numbers, i, j);
            sort(numbers, j + 1, k);

            // Merge left and right partition in sorted order
            merge(numbers, i, j, k);
        }
        return numbers;
    }

    public static int[] sort(int[] numbers) {
        return sort(numbers, 0, numbers.length - 1);
    }
}
