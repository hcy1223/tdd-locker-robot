package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;

import java.util.List;

public class Robot {
    private List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        for (Locker locker : lockers) {
            try {
                return locker.store(bag);
            } catch (NoEmptyLockersException ignored) {
            }
        }
        throw new NoEmptyLockersException();
    }

    public Bag pick(Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.pick(ticket);
            } catch (InvalidTicketException ignored) {
            }
        }
        throw new InvalidTicketException();
    }
}
