/*
 A class to encapsulate the Insertion Sort, implemented as a
 public static method that sorts the array in place, and returns
 a reference to the sorted array
*/
package sorttimes;

/**
 *
 * @author Tom
 */
public class InsertionSort {

    public static int[] sort(int[] numbers) {
        for (int i = 1; i < numbers.length; ++i) {
            // Insert numbers[i] into sorted part, stopping
            // once numbers[i] is in the correct position
            for (int j = i; j > 0 && numbers[j] < numbers[j - 1]; j--) {
                // Swap numbers[j] and numbers[j - 1]
                int tmp = numbers[j];
                numbers[j] = numbers[j - 1];
                numbers[j - 1] = tmp;
            }
        }

        return numbers;
    }
}
