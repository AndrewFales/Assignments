package sorttimes;
import java.util.PriorityQueue;
public class HeapSort{
    private static PriorityQueue<Integer> heap = new PriorityQueue<>();
    public static int[] sort(int[] data){
        int[] returnInt = new int[data.length];
        heap = new PriorityQueue<>(data.length);
        for(int i = 0; i < data.length; i++)
            heap.offer(data[i]);
        for(int i = 0; i < data.length; i++)
            returnInt[i] = heap.poll();
        return returnInt;
    }



}