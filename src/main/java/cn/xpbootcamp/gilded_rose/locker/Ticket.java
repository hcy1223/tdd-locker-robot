package cn.xpbootcamp.gilded_rose.locker;

public class Ticket {

    private int number;
    private String password;

    public Ticket(int number, String password) {
        this.number = number;
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}
