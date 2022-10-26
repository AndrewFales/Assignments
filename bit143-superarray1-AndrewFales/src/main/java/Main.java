public class Main {
    public static void main(String[] args) {        
        
        SuperArray accounts = new SuperArray(5);
        accounts.add(new Account("user1", "1234"));
        accounts.add(new Account("user2", "abcd"));
        accounts.add(new Account("user1", "1234"));
        accounts.add(new Account("user3", "eeee"));
        accounts.add(new Account("user4", "aaaa"));
        // Adding one more account will throw an exception
        // try and catch it!
        // accounts.add(new Account("user5", "pwdd"));

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