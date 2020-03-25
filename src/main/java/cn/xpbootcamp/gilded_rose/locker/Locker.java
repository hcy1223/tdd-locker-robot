package cn.xpbootcamp.gilded_rose.locker;


import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        this.usedCupboards.put(number, new Cupboard(password));
        emptyCupboards.remove(0);
        return new Ticket(number, password);
    }

    public boolean locked(int number) {
        return this.usedCupboards.containsKey(number);
    }

    public int getEmptyCount() {
        return this.emptyCupboards.size();
    }
}
