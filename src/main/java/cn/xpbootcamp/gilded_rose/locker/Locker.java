package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private Map<Ticket, Bag> usedLocker;
    private int capacity;

    private Locker(int capacity) {
        this.capacity = capacity;
        this.usedLocker = new HashMap<>();
    }

    public static Locker createLocker(int capacity) {
        if (capacity < 1) throw new RuntimeException("capacity invalid");
        return new Locker(capacity);
    }

    public Ticket store(Bag bag) {
        if (getEmptyCount() < 1) {
            throw new NoEmptyLockersException();
        }

        Ticket ticket = new Ticket();
        usedLocker.put(ticket, bag);
        return ticket;
    }

    public int getEmptyCount() {
        return capacity - usedLocker.size();
    }

    public float getEmptyPercent() {
        return (capacity - usedLocker.size()) / (float) capacity;
    }

    public Bag pick(Ticket ticket) {
        if (this.usedLocker.containsKey(ticket)) {
            return this.usedLocker.remove(ticket);
        } else {
            throw new InvalidTicketException();
        }
    }

}
