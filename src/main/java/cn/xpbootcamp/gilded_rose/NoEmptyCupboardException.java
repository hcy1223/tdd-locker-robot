package cn.xpbootcamp.gilded_rose;

public class NoEmptyCupboardException extends RuntimeException {
    public NoEmptyCupboardException(String message) {
        super(message);
    }
}
