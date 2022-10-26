public class Main {
    public static void main(String[] args) {        
        
        SuperArray<Account> accounts = new SuperArray<>();
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user2", "abcd"));
        accounts.push(new Account("user1", "1234"));
        accounts.push(new Account("user3", "eeee"));
        accounts.push(new Account("user4", "aaaa"));

        System.out.println(accounts);

        System.out.println(accounts.get(1));

        Account ac = new Account("user2", "abcd");

        System.out.println(accounts.find(ac));
        accounts.delete(3);
        System.out.println(accounts.get(3));
        System.out.println(accounts.size());
        
        Account tmpAccount = new Account("user1", "1234");
        accounts.delete(tmpAccount);
        System.out.println(accounts);
        
    }
}