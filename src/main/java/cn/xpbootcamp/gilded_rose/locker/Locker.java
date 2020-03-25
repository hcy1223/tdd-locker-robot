package cn.xpbootcamp.gilded_rose.locker;


import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Locker {
    private Queue<Integer> emptyCupboards;
    private Map<Integer, Cupboard> usedCupboards;

    public Locker(Queue<Integer> emptyCupboards) {
        this.emptyCupboards = emptyCupboards;
        this.usedCupboards = new HashMap<>();
    }

    public static Locker createLocker(int totalCount) {
        Queue<Integer> emptyCupboards = IntStream.range(0, totalCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        return new Locker(emptyCupboards);
    }

    public Ticket store() {
        if (emptyCupboards.size() < 1) {
            throw new NoEmptyCupboardException("no empty cupboard");
        }

        int number = emptyCupboards.remove();
        String password = generatePassword();

        this.usedCupboards.put(number, new Cupboard(password));
        return new Ticket(number, password);
    }

    private String generatePassword() {
        return Long.toHexString(System.currentTimeMillis());
    }

    public boolean locked(int number) {
        return this.usedCupboards.containsKey(number);
    }

    public int getEmptyCount() {
        return this.emptyCupboards.size();
    }

}
