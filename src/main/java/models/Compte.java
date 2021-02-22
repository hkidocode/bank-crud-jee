package models;

public class Compte extends Client {
    private int id;
    private int idClient;
    private long accountNum;
    private String type;
    private double balance;

    public Compte() {
    }

    public Compte(String firstName, String lastName, long accountNum, String type) {
        super(firstName, lastName);
        this.accountNum = accountNum;
        this.type = type;
    }

    public Compte(String firstName, String lastName, long accountNum, String type, double balance) {
        super(firstName, lastName);
        this.accountNum = accountNum;
        this.type = type;
        this.balance = balance;
    }

    public Compte(String firstName, String lastName, int idClient, long accountNum, String type, double balance) {
        super(firstName, lastName);
        this.idClient = idClient;
        this.accountNum = accountNum;
        this.type = type;
        this.balance = balance;
    }

    public Compte(String firstName, String lastName, int idClient, long accountNum, double balance) {
        super(firstName, lastName);
        this.idClient = idClient;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
