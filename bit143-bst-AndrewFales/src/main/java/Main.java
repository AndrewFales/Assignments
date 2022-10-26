public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(4);
        bst.add(8);
        bst.add(2);
        bst.add(6);
        bst.add(10);
        bst.add(12);
        bst.add(7);

        System.out.println(bst);
        System.out.println(bst.levelOrderString());
        System.out.println(bst.inOrderString());
        System.out.println(bst.preOrderString());
        System.out.println(bst.height());
        bst.add(13);
        bst.add(3);
        System.out.println(bst);
        System.out.println(bst.levelOrderString());
        System.out.println(bst.height());
        bst.delete(10);
        bst.delete(4);
        System.out.println(bst.levelOrderString());
        System.out.println(bst.height());


        System.out.println(bst);
    }
}