public class Main {
    public static void main(String[] args) throws ListIndexOutOfBoundException, EmptyListException {
        LinkedList<Account> accounts = new LinkedList<>();
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user2", "abcd"));
        accounts.push(new Account("user3", "eeee"));
        accounts.push(new Account("user4", "aaaa"));
        System.out.println(accounts);
        System.out.println(accounts.get(1));
        Account user1 = new Account("user1.5", "abcdefg");
        accounts.insert(3, user1);
        System.out.println(accounts);
        System.out.println(accounts.find(user1));
        accounts.delete(1);
        System.out.println(accounts);
        System.out.println(accounts.size());
    }
}