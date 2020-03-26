package cn.xpbootcamp.gilded_rose.locker;

import static java.util.Objects.isNull;

public class Cupboard {

    private String password;
    private int number;

    public Cupboard(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMatch(String password) {
        return !isNull(password) && password.equalsIgnoreCase(this.password);
    }
}
