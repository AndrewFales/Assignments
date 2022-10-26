public class Program {
    public static void main(String[] args) {
        MinHeap<Point> mh = new MinHeap<>();
        mh.offer(new Point(10, 11), 3);
        mh.offer(new Point(20, 1), 1);
        System.out.println(mh.peek());
    }
}