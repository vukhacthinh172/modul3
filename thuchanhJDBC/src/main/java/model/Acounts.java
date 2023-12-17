package model;

public class Acounts {
    private  int id ;
    private int accounts_holder_id ;
    private  double balance ;

    public Acounts() {
    }

    public Acounts(int id, int accounts_holder_id, double balance) {
        this.id = id;
        this.accounts_holder_id = accounts_holder_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccounts_holder_id() {
        return accounts_holder_id;
    }

    public void setAccounts_holder_id(int accounts_holder_id) {
        this.accounts_holder_id = accounts_holder_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Acounts{" +
                "id=" + id +
                ", accounts_holder_id=" + accounts_holder_id +
                ", balance=" + balance +
                '}';
    }
}
