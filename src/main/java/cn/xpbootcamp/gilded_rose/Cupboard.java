package cn.xpbootcamp.gilded_rose;

public class Cupboard {
    private int status;
    private String password;

    public Cupboard(int status, String password) {
        this.status = status;
        this.password = password;
    }

    public int getStatus() {
        return status;
    }
}
