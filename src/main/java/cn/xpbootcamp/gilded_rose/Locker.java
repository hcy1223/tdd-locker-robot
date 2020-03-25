package cn.xpbootcamp.gilded_rose;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Locker {
    private List<Integer> emptyCupboards;
    private Map<Integer, Cupboard> usedCupboards;


    public Locker(int totalCount) {
        this.emptyCupboards = IntStream.range(0, totalCount).boxed().collect(Collectors.toList());
        this.usedCupboards = new HashMap<>();
    }

    public Ticket store() {
        if (emptyCupboards.size() < 1) {
            throw new NoEmptyCupboardException("no empty cupboard");
        }
        int number = emptyCupboards.get(0);
        String password = Long.toHexString(System.currentTimeMillis());
        this.usedCupboards.put(number, new Cupboard(1, password));
        emptyCupboards.remove(0);
        return new Ticket(number, password);
    }

    public int getStatus(int number) {
        return this.usedCupboards.get(number).getStatus();
    }

    public int getEmptyCount() {
        return this.emptyCupboards.size();
    }
}
