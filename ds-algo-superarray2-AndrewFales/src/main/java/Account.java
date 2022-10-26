public class Account {
    private String userName;
    private String password;

    public Account(String userName, String password) {
        this.userName = userName;
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // @Override is a  Java annotation that hints the compiler that we are overriding an inherited method
    @Override
    public boolean equals(Object user) {
        Account otherUser = (Account) user;
        return this.getUserName().equals(otherUser.getUserName()) 
            && this.getPassword().equals(otherUser.getPassword());
    }


    @Override
    public String toString() {
        return String.format("{username: %s, password: %s}", this.getUserName(), this.getPassword());
    }

    

}
