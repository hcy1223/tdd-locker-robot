package cn.xpbootcamp.gilded_rose.locker;

import static java.util.Objects.isNull;

public class Cupboard {

    private String password;
    private int number;

    public Cupboard(int number, String password) {
        this.number = number;
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public boolean isMatch(String password) {
        if (isNull(password)) {
            return false;
        }
        return password.equalsIgnoreCase(this.password);
    }
}
